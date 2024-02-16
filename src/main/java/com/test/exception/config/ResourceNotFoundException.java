package com.test.exception.config;

public class ResourceNotFoundException extends RuntimeException {

  /**
   * @param message
   */
  public ResourceNotFoundException(String message) {
    super(message);

  }

  /**
   * @param message
   * @param cause
   */
  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);

  }

  public static void reThrow(Throwable throwable) {

    throw new ResourceNotFoundException(throwable.getMessage(), throwable);

  }

}
