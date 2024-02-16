package com.test.exception.file;

import com.test.exception.config.ApiException;

public class FileDeleteException extends ApiException {

  /**
   * @param message
   */
  public FileDeleteException() {
    super("error while deleting a file");

  }

}
