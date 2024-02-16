package com.test.controllers.config;

import java.util.Optional;

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
public class ApiDownloadInput {

  private byte[] bytes;

  private String fileName;

  private String ext;

  public String getValidName() {

    if (fileName == null || fileName.trim().isEmpty()) {
      throw new ApiException("a name must be given to the file");
    }
    return fileName.concat(Optional.ofNullable(ext).map(e -> ".".concat(e.trim())).orElse(""));

  }

  public int getSize() {
    return bytes.length;
  }

}
