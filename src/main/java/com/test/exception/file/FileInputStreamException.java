package com.test.exception.file;

import com.test.exception.config.ApiException;

public class FileInputStreamException extends ApiException {

  /**
   * @param message
   */
  public FileInputStreamException() {
    super("error while getting the file inputstream");

  }

}
