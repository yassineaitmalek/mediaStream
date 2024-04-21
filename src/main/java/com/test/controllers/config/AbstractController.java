package com.test.controllers.config;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface AbstractController {

  public default <T> ResponseEntity<ApiDataResponse<T>> ok(Supplier<T> supplier) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ApiDataResponse
            .<T>builder()
            .data(supplier.get())
            .httpStatus(HttpStatus.OK.value())
            .status(HttpStatus.OK.toString())
            .build());
  }

  public default <T> ResponseEntity<ApiDataResponse<T>> create(Supplier<T> supplier) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ApiDataResponse
            .<T>builder()
            .data(supplier.get())
            .httpStatus(HttpStatus.CREATED.value())
            .status(HttpStatus.CREATED.toString())
            .build());
  }

  public default ResponseEntity<Void> delete(Runnable runnable) {
    runnable.run();
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
  }

  public default <T> ResponseEntity<ApiDataResponse<T>> async(Runnable runnable, Supplier<T> supplier) {
    runnable.run();
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(ApiDataResponse.<T>builder()
            .data(supplier.get())
            .httpStatus(HttpStatus.ACCEPTED.value())
            .status(HttpStatus.ACCEPTED.toString())
            .build());
  }

  public default ResponseEntity<byte[]> download(ApiDownloadInput apiDownloadInput) {
    return ResponseEntity.status(HttpStatus.OK)
        .header("Content-Type", "application/octet-stream")
        .header("Content-Disposition", "attachment; filename=" + apiDownloadInput.getValidName())
        .header("Content-Length", String.valueOf(apiDownloadInput.getBytes().length))
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
        .header("Content-Range", "bytes" + " " + apiPartialInput.getStart() + "-" + apiPartialInput.getEnd() + "/"
            + apiPartialInput.getSize())
        .body(apiPartialInput.getBytes());

  }

  public default ResponseEntity<ApiExceptionResponse> internalException(Exception exception, WebRequest request) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ApiExceptionResponse.builder()
            .message(exception.getMessage())
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
            .path(request.getDescription(false))
            .build());

  }

  public default ResponseEntity<ApiExceptionResponse> badRequest(Exception exception, WebRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ApiExceptionResponse
            .builder().message(exception.getMessage())
            .httpStatus(HttpStatus.BAD_REQUEST.value())
            .status(HttpStatus.BAD_REQUEST.toString())
            .path(request.getDescription(false))
            .build());

  }

  public default ResponseEntity<ApiExceptionResponse> notFound(Exception exception, WebRequest request) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ApiExceptionResponse
            .builder().message(exception.getMessage())
            .httpStatus(HttpStatus.NOT_FOUND.value())
            .status(HttpStatus.NOT_FOUND.toString())
            .path(request.getDescription(false))
            .build());

  }

  public default ResponseEntity<ApiExceptionResponse> tooManyRequests(Exception exception, WebRequest request) {
    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
        .body(ApiExceptionResponse
            .builder().message(exception.getMessage())
            .httpStatus(HttpStatus.TOO_MANY_REQUESTS.value())
            .status(HttpStatus.TOO_MANY_REQUESTS.toString())
            .path(request.getDescription(false))
            .build());

  }

}
