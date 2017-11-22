package com.wrapper.spotify.exceptions;

import org.apache.http.HttpException;

public class ForbiddenException extends HttpException {

  public ForbiddenException() {
    super();
  }

  public ForbiddenException(String message) {
    super(message);
  }

  public ForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }

}
