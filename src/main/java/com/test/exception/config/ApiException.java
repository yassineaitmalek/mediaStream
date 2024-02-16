package com.test.exception.config;

public class ApiException extends RuntimeException {

  /**
   * @param message
   */
  public ApiException(String message) {
    super(message);

  }

  /**
   * @param message
   * @param cause
   */
  public ApiException(String message, Throwable cause) {
    super(message, cause);

  }

  public static void reThrow(Throwable throwable) {

    throw new ApiException(throwable.getMessage(), throwable);

  }

}
