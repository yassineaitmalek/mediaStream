package com.test.exception.file;

import com.test.exception.config.ApiException;

public class FileUnStreamableException extends ApiException {

  /**
   * @param message
   */
  public FileUnStreamableException() {
    super("error the file is unstreamable");

  }

}
