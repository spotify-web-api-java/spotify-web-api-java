package com.wrapper.spotify.exceptions;

public class InternalServerErrorException extends SpotifyWebApiException {

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
