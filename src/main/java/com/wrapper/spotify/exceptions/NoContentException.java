package com.wrapper.spotify.exceptions;

public class NoContentException extends SpotifyWebApiException {

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
