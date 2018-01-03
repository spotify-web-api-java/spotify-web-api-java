package com.wrapper.spotify.exceptions;

public class ServiceUnavailableException extends SpotifyWebApiException {

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
