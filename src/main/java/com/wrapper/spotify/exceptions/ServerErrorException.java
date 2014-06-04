package com.wrapper.spotify.exceptions;

public class ServerErrorException extends WebApiException {

  public ServerErrorException(String message) {
    super(message);
  }
}
