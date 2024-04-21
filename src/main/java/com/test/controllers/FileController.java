package com.test.controllers;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.test.controllers.config.AbstractController;
import com.test.controllers.config.ApiDataResponse;
import com.test.dto.FileDTO;
import com.test.models.local.Attachement;
import com.test.services.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController implements AbstractController {

  private final FileService fileService;

  @PutMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
  public ResponseEntity<ApiDataResponse<Attachement>> upload(@ModelAttribute @Valid FileDTO fileDTO) {

    return ok(() -> fileService.uploadFile(fileDTO));
  }

  @GetMapping(value = "/download/{id}")
  public ResponseEntity<StreamingResponseBody> download(@PathVariable String id) {
    return downloadLarge(fileService.downloadFileLarge(id));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {

    return delete(() -> fileService.deleteAttachement(id));
  }

  @GetMapping(value = "/stream/{id}")
  public ResponseEntity<byte[]> stream(
      @RequestHeader(value = "Range", required = false) String range,
      @PathVariable String id) {

    return partial(fileService.streamFile(id, range));
  }

}
