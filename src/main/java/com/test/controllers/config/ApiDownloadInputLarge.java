package com.test.controllers.config;

import java.util.Optional;

import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.test.exception.config.ApiException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDownloadInputLarge {

  private String fileName;

  private String ext;

  private Long size;

  private StreamingResponseBody streamingResponseBody;

  public String getValidName() {

    if (fileName == null || fileName.trim().isEmpty()) {
      throw new ApiException("a name must be given to the file");
    }
    return fileName.concat(Optional.ofNullable(ext).map(e -> ".".concat(e.trim())).orElse(""));

  }

}
