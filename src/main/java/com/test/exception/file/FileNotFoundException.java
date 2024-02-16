package com.test.exception.file;

import com.test.exception.config.ResourceNotFoundException;

public class FileNotFoundException extends ResourceNotFoundException {

  /**
   * @param message
   */
  public FileNotFoundException() {
    super("error File does not exist");

  }

}
