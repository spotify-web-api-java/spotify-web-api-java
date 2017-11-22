package com.wrapper.spotify.exceptions;

import org.apache.http.HttpException;

public class ServiceUnavailableException extends HttpException {

  public ServiceUnavailableException() {
    super();
  }

  public ServiceUnavailableException(String message) {
    super(message);
  }

  public ServiceUnavailableException(String message, Throwable cause) {
    super(message, cause);
  }

}
