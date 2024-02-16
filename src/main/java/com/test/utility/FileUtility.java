package com.test.utility;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.WatchService;
import java.util.Optional;
import java.io.Writer;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import io.vavr.control.Try;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class FileUtility {

  public boolean moveFile(String from, String to) {

    return moveFile(Paths.get(from), Paths.get(to));

  }

  public boolean moveFile(Path from, Path to) {

    return Try
        .of(() -> Files.move(from.toAbsolutePath().normalize(), to.toAbsolutePath().normalize(),
            StandardCopyOption.ATOMIC_MOVE))
        .onSuccess(e -> log.info("File {} moved to {}  successfully", from.toAbsolutePath().normalize().toString(),
            to.toAbsolutePath().normalize().toString()))
        .onFailure(e -> log.error("File {} failed to be moved to {} ", from.toAbsolutePath().normalize().toString(),
            to.toAbsolutePath().normalize().toString()))
        .isSuccess();

  }

  public boolean createFolder(String folderPath) {

    return Optional.ofNullable(folderPath).map(e -> createFolder(Paths.get(e))).orElse(false);

  }

  public boolean createFolder(Path path) {

    return Try.of(() -> Files.createDirectories(path))
        .onSuccess(e -> log.info("Folder {} created successfully", e.toAbsolutePath().normalize().toString()))
        .onFailure(e -> log.error("folder {} failed to be created", path.toAbsolutePath().normalize().toString()))
        .isSuccess();

  }

  public boolean delete(Path path) {
    return Try.run(() -> Files.delete(path))
        .onSuccess(e -> log.info("{} deleted successfully", path.toAbsolutePath().normalize().toString()))
        .onFailure(e -> log.error("{} failed to be deleted", path.toAbsolutePath().normalize().toString()))
        .isSuccess();

  }

  public boolean delete(String path) {
    return delete(Paths.get(path).toAbsolutePath().normalize());
  }

  public void closeInputStram(InputStream stream) {
    Optional.ofNullable(stream).ifPresent(IOUtils::closeQuietly);
  }

  public void closeOutPutStream(OutputStream stream) {
    Optional.ofNullable(stream).ifPresent(IOUtils::closeQuietly);
  }

  public void closeWriter(Writer writer) {
    Optional.ofNullable(writer).ifPresent(IOUtils::closeQuietly);
  }

  public void close(Closeable closeable) {
    Optional.ofNullable(closeable).ifPresent(IOUtils::closeQuietly);
  }

  public void closeWatchService(WatchService watchService) {
    Optional.ofNullable(watchService).ifPresent(IOUtils::closeQuietly);
  }

  public static String getFileNameWithoutExtension(File file) {
    return Optional.ofNullable(file)
        .map(File::getName)
        .map(FileUtility::getFileNameWithoutExtension)
        .orElse(null);
  }

  public static String getFileNameWithoutExtension(String fileName) {

    return Optional.ofNullable(fileName)
        .map(e -> e.lastIndexOf("."))
        .filter(lastDotIndex -> lastDotIndex != -1 && lastDotIndex > 0)
        .map(lastDotIndex -> fileName.substring(0, lastDotIndex))
        .orElse(fileName);

  }

  public static String getFileExtension(File file) {
    return FileUtility.getFileExtension(file.getName());
  }

  public static String getFileExtension(MultipartFile file) {
    return FileUtility.getFileExtension(file.getName());
  }

  public static String getFileExtension(Path path) {
    return FileUtility.getFileExtension(path.toFile());
  }

  public static String getFileExtension(String fileName) {

    return Optional.ofNullable(fileName)
        .map(e -> e.lastIndexOf("."))
        .filter(lastDotIndex -> lastDotIndex != -1 && lastDotIndex < fileName.length() - 1)
        .map(lastDotIndex -> fileName.substring(lastDotIndex + 1))
        .orElse("");

  }
}
