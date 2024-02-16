package com.test.controllers.config;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface AbstractController {

  public default <T> ResponseEntity<ApiDataResponse<T>> ok(T data) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ApiDataResponse
            .<T>builder()
            .data(data).httpStatus(HttpStatus.OK.value()).status(HttpStatus.OK.toString())
            .build());
  }

  public default <T> ResponseEntity<ApiDataResponse<T>> create(T data) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ApiDataResponse
            .<T>builder()
            .data(data).httpStatus(HttpStatus.CREATED.value()).status(HttpStatus.CREATED.toString())
            .build());
  }

  public default ResponseEntity<Void> delete() {
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
  }

  public default ResponseEntity<ApiDataResponse<Object>> async() {
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(ApiDataResponse
            .<Object>builder()
            .data(new ArrayList<Object>()).httpStatus(HttpStatus.ACCEPTED.value())
            .status(HttpStatus.ACCEPTED.toString())
            .build());
  }

  public default ResponseEntity<byte[]> download(ApiDownloadInput apiDownloadInput) {
    return ResponseEntity.status(HttpStatus.OK)
        .header("Content-Type", "application/octet-stream")
        .header("Content-Disposition", "attachment; filename=" + apiDownloadInput.getValidName())
        .header("Content-Length", String.valueOf(apiDownloadInput.getSize()))
        .body(apiDownloadInput.getBytes());

  }

  public default ResponseEntity<StreamingResponseBody> downloadLarge(ApiDownloadInputLarge apiDownloadInput) {
    return ResponseEntity.status(HttpStatus.OK)
        .header("Content-Type", "application/octet-stream")
        .header("Content-Disposition", "attachment; filename=" + apiDownloadInput.getValidName())
        .header("Content-Length", String.valueOf(apiDownloadInput.getSize()))
        .body(apiDownloadInput.getStreamingResponseBody());

  }

  public default ResponseEntity<byte[]> partial(ApiPartialInput apiPartialInput) {
    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
        .header("Content-Type", apiPartialInput.getContent() + "/" + apiPartialInput.getExt())
        .header("Accept-Ranges", "bytes")
        .header("Content-Length", String.valueOf(apiPartialInput.getLenght()))
        .header("Content-Range", apiPartialInput.getContentRange())
        .body(apiPartialInput.getBytes());

  }

  public default ResponseEntity<ApiExceptionResponse> internalException(Exception exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ApiExceptionResponse.builder()
            .message(exception.getMessage()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
            .build());

  }

  public default ResponseEntity<ApiExceptionResponse> badRequest(Exception exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ApiExceptionResponse
            .builder().message(exception.getMessage()).httpStatus(HttpStatus.BAD_REQUEST.value())
            .status(HttpStatus.BAD_REQUEST.toString())
            .build());

  }

  public default ResponseEntity<ApiExceptionResponse> notFound(Exception exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ApiExceptionResponse
            .builder().message(exception.getMessage()).httpStatus(HttpStatus.NOT_FOUND.value())
            .status(HttpStatus.NOT_FOUND.toString())
            .build());

  }

}
