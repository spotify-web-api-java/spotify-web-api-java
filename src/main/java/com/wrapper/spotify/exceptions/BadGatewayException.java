package com.wrapper.spotify.exceptions;

public class BadGatewayException extends SpotifyWebApiException {

  public BadGatewayException() {
    super();
  }

  public BadGatewayException(String message) {
    super(message);
  }

  public BadGatewayException(String message, Throwable cause) {
    super(message, cause);
  }

}
