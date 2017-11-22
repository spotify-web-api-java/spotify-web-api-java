package com.wrapper.spotify.exceptions;

import org.apache.http.HttpException;

public class InternalServerErrorException extends HttpException {

  public InternalServerErrorException() {
    super();
  }

  public InternalServerErrorException(String message) {
    super(message);
  }

  public InternalServerErrorException(String message, Throwable cause) {
    super(message, cause);
  }

}
