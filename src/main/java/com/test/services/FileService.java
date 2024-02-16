package com.test.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.test.config.UploadFolder;
import com.test.constants.Constants;
import com.test.constants.FileExtension;
import com.test.controllers.config.ApiDownloadInput;
import com.test.controllers.config.ApiDownloadInputLarge;
import com.test.controllers.config.ApiPartialInput;
import com.test.dto.FileDTO;
import com.test.exception.config.ApiException;
import com.test.exception.file.FileNotFoundException;
import com.test.exception.file.FileUnStreamableException;
import com.test.models.local.AFile;
import com.test.models.local.Attachement;
import com.test.repositories.local.AttachementRepository;
import com.test.utility.FileUtility;
import com.test.utility.RangeUtil;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class FileService {

  private final UploadFolder uploadFolder;

  private final AttachementRepository attachementRepository;

  public Attachement uploadFile(FileDTO fileDTO) {
    return attachementRepository.save(uploadAttachement(fileDTO.getFile(), new AFile()));
  }

  public Path uploadFile(@Valid @NotNull MultipartFile file, @Valid @NotNull @NotEmpty String attachmentId,
      String ext) {

    String realEXT = (ext == null || ext.trim().isEmpty()) ? "" : "." + ext;
    Path filePath = Paths.get(uploadFolder.getUploadFolderPath(), attachmentId + realEXT).toAbsolutePath().normalize();
    Try.run(() -> file.transferTo(filePath.toFile())).onFailure(ApiException::reThrow);
    return filePath;

  }

  public Attachement uploadAttachement(@Valid @NotNull MultipartFile fileToUpload,
      @Valid @NotNull Attachement attachment) {

    attachment.setExt(FileUtility.getFileExtension(fileToUpload.getOriginalFilename()));
    attachment.setExtension(FileExtension.getByValue(attachment.getExt()));
    attachment.setFileType(attachment.getExtension().getType());
    attachment.setOriginalFileName(FileUtility.getFileNameWithoutExtension(fileToUpload.getOriginalFilename()));
    attachment.setPath(uploadFile(fileToUpload, attachment.getId(), attachment.getExt()).toString());
    attachment.setFileSize(fileToUpload.getSize());
    return attachment;

  }

  public String getOriginalFileNameFromFile(@Valid @NotNull Attachement attachement) {
    return Optional.ofNullable(attachement)
        .map(f -> f.getOriginalFileName() + "." + f.getExt())
        .orElse("");

  }

  public Optional<Attachement> getById(@Valid @NotNull @NotEmpty String attachmentId) {
    return attachementRepository.findById(attachmentId);
  }

  public ApiDownloadInput downloadFile(@Valid @NotNull @NotEmpty String attachmentId) {
    log.info("download attachment with id : {}", attachmentId);
    return Optional.of(attachmentId)
        .map(attachementRepository::findById)
        .filter(e -> e.isPresent())
        .map(e -> e.get())
        .map(this::downloadAttachement)
        .orElseThrow(FileNotFoundException::new);

  }

  public ApiDownloadInputLarge downloadFileLarge(@Valid @NotNull @NotEmpty String attachmentId) {
    log.info("download attachment with id : {}", attachmentId);
    return Optional.of(attachmentId)
        .map(attachementRepository::findById)
        .filter(e -> e.isPresent())
        .map(e -> e.get())
        .map(this::downloadAttachementLarge)
        .orElseThrow(FileNotFoundException::new);

  }

  public ApiDownloadInput downloadAttachement(@Valid @NotNull Attachement attachment) {

    log.info("download file with id : {}", attachment.getId());
    return ApiDownloadInput.builder()
        .bytes(Try.of(() -> getFileBytes(attachment)).onFailure(ApiException::reThrow).get())
        .fileName(attachment.getOriginalFileName())
        .ext(attachment.getExt())
        .build();

  }

  public ApiDownloadInputLarge downloadAttachementLarge(@Valid @NotNull Attachement attachment) {

    log.info("download Large file with id : {}", attachment.getId());
    return ApiDownloadInputLarge.builder().streamingResponseBody(getStreamingResponseBody(attachment))
        .fileName(attachment.getOriginalFileName())
        .ext(attachment.getExt()).size(attachment.getFileSize()).build();

  }

  public StreamingResponseBody getStreamingResponseBody(@Valid @NotNull Attachement attachment) {
    return Try.of(() -> attachment)
        .map(Attachement::getPath)
        .map(File::new)
        .mapTry(FileInputStream::new)
        .map(StreamingResponseBodyImpl::new)
        .get();
  }

  public void deleteAttachement(@Valid @NotNull @NotEmpty String attachmentId) {
    log.info("delete file with id : {}", attachmentId);
    Optional.of(attachmentId)
        .map(attachementRepository::findById)
        .filter(e -> e.isPresent())
        .map(e -> e.get())
        .ifPresent(this::deleteAttachement);

  }

  public void deleteAttachement(@Valid @NotNull Attachement attachment) {

    Optional.of(attachment)
        .ifPresent(e -> {
          FileUtility.delete(attachment.getPath());
          attachementRepository.delete(e);
        });

  }

  public byte[] getFileBytes(@Valid @NotNull Attachement file) throws IOException {

    log.info("Getting stream of file with id {}", file.getId());
    InputStream inputStream = new FileInputStream(new File(file.getPath()));
    byte[] bytes = IOUtils.toByteArray(inputStream);
    IOUtils.closeQuietly(inputStream);
    return bytes;

  }

  public ApiPartialInput streamFile(@Valid @NotNull @NotEmpty String fileId, String httpRangeList) {

    return attachementRepository.findById(fileId)
        .map(e -> streamFile(e, httpRangeList))
        .orElseThrow(FileNotFoundException::new);

  }

  public ApiPartialInput streamFile(@Valid @NotNull Attachement attachement, String httpRangeList) {
    if (attachement.getFileType().isStreamable()) {
      RangeUtil ru = RangeUtil.getRangeUtil(httpRangeList, attachement.getFileSize(),
          attachement.getFileType().isImage());

      return ApiPartialInput
          .builder()
          .bytes(readByteRange(readPartialFileInputStream(attachement.getPath(), ru.getStart(), ru.len()), ru.len()))
          .start(ru.getStart())
          .end(ru.getEnd())
          .lenght(ru.len())
          .size(attachement.getFileSize())
          .content(attachement.getFileType().getValue())
          .ext(attachement.getExt())
          .build();
    }
    throw new FileUnStreamableException();

  }

  public InputStream readPartialFileInputStream(String filePath, long startRange, long rangeLength) {

    try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r");
        FileChannel fileChannel = randomAccessFile.getChannel()) {

      fileChannel.position(startRange);
      ByteBuffer buffer = ByteBuffer.allocate((int) rangeLength);
      int bytesRead = fileChannel.read(buffer);
      if (bytesRead == -1) {
        throw new IOException("End of file reached before reading the specified range.");
      }
      byte[] data = new byte[bytesRead];
      buffer.flip();
      buffer.get(data);
      return new ByteArrayInputStream(data);
    } catch (Exception e) {
      throw new ApiException(e.getMessage(), e);

    }

  }

  public byte[] readByteRange(InputStream inputStream, long len) {
    return Try.of(() -> readByteRangeImpl(inputStream, len)).onFailure(ApiException::reThrow).get();

  }

  public byte[] readByteRangeImpl(InputStream inputStream, long len) throws IOException {

    ByteArrayOutputStream bufferedOutputStream = new ByteArrayOutputStream();
    byte[] data = new byte[Constants.BYTE_RANGE];
    int nRead;
    while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
      bufferedOutputStream.write(data, 0, nRead);
    }
    bufferedOutputStream.flush();

    byte[] result = new byte[(int) len];
    System.arraycopy(bufferedOutputStream.toByteArray(), 0, result, 0, result.length);
    return result;

  }
}
