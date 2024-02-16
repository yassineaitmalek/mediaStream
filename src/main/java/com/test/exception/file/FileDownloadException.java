package com.test.exception.file;

import com.test.exception.config.ApiException;

public class FileDownloadException extends ApiException {

  /**
   * @param message
   */
  public FileDownloadException() {
    super("error could not Download File");

  }

}
