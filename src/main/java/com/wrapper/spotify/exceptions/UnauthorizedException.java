package com.wrapper.spotify.exceptions;

import org.apache.http.HttpException;

public class UnauthorizedException extends HttpException {

  public UnauthorizedException() {
    super();
  }

  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(String message, Throwable cause) {
    super(message, cause);
  }

}
