package com.wrapper.spotify.exceptions;

import org.apache.http.HttpException;

public class NoContentException extends HttpException {

  public NoContentException() {
    super();
  }

  public NoContentException(String message) {
    super(message);
  }

  public NoContentException(String message, Throwable cause) {
    super(message, cause);
  }

}
