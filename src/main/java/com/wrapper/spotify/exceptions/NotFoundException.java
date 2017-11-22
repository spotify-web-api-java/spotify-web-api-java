package com.wrapper.spotify.exceptions;

import org.apache.http.HttpException;

public class NotFoundException extends HttpException {

  public NotFoundException() {
    super();
  }

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
