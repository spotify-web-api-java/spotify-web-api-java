package com.wrapper.spotify.exceptions;

import org.apache.http.HttpException;

public class BadRequestException extends HttpException {

  public BadRequestException() {
    super();
  }

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

}
