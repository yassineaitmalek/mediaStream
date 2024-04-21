package com.test.exception.config;

public class ServerSideException extends ApiException {

  /**
   * @param message
   */
  public ServerSideException(String message) {
    super(message);

  }

  /**
   * @param message
   * @param cause
   */
  public ServerSideException(String message, Throwable cause) {
    super(message, cause);

  }

  public static void reThrow(Throwable throwable) {
    throw new ServerSideException(throwable.getMessage(), throwable);
  }

  public static ServerSideException of(Throwable throwable) {
    return new ServerSideException(throwable.getMessage(), throwable);
  }

  public static ServerSideException of(String message, Throwable cause) {
    return new ServerSideException(message, cause);
  }

  public static ServerSideException of(String message) {
    return new ServerSideException(message);
  }

}
