package com.wrapper.spotify.exceptions;

public class TooManyRequestsException extends SpotifyWebApiException {

  public TooManyRequestsException() {
    super();
  }

  public TooManyRequestsException(String message) {
    super(message);
  }

  public TooManyRequestsException(String message, Throwable cause) {
    super(message, cause);
  }

}
