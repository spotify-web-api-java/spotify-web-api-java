package com.wrapper.spotify.exceptions;

/**
 * Rate limiting has been applied.
 */
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
