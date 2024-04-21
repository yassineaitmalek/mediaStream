package com.test.exception.config;

public class TooManyRequestsException extends ClientSideException {

  /**
   * @param message
   */
  public TooManyRequestsException(String message) {
    super(message);

  }

  /**
   * @param message
   * @param cause
   */
  public TooManyRequestsException(String message, Throwable cause) {
    super(message, cause);

  }

  public static void reThrow(Throwable throwable) {
    throw new TooManyRequestsException(throwable.getMessage(), throwable);
  }

  public static TooManyRequestsException of(Throwable throwable) {
    return new TooManyRequestsException(throwable.getMessage(), throwable);
  }

  public static TooManyRequestsException of(String message, Throwable cause) {
    return new TooManyRequestsException(message, cause);
  }

  public static TooManyRequestsException of(String message) {
    return new TooManyRequestsException(message);
  }

}
