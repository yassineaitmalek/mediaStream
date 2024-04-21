package com.test.exception.config;

public class ClientSideException extends ApiException {

  /**
   * @param message
   */
  public ClientSideException(String message) {
    super(message);

  }

  /**
   * @param message
   * @param cause
   */
  public ClientSideException(String message, Throwable cause) {
    super(message, cause);

  }

  public static void reThrow(Throwable throwable) {
    throw new ClientSideException(throwable.getMessage(), throwable);
  }

  public static ClientSideException of(Throwable throwable) {
    return new ClientSideException(throwable.getMessage(), throwable);
  }

  public static ClientSideException of(String message, Throwable cause) {
    return new ClientSideException(message, cause);
  }

  public static ClientSideException of(String message) {
    return new ClientSideException(message);
  }

}
