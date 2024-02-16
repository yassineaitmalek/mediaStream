package com.test.exception.file;

import com.test.exception.config.ApiException;

public class FileUploadException extends ApiException {

  /**
   * @param message
   */
  public FileUploadException() {
    super("error could not upload File");

  }

}
