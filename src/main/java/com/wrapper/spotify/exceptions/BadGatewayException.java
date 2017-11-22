package com.wrapper.spotify.exceptions;

import org.apache.http.HttpException;

public class BadGatewayException extends HttpException {

  public BadGatewayException() {
    super();
  }

  public BadGatewayException(String message) {
    super(message);
  }

  public BadGatewayException(String message, Throwable cause) {
    super(message, cause);
  }

}
