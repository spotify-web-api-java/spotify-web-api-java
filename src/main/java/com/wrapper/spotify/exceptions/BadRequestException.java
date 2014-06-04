package com.wrapper.spotify.exceptions;

public class BadRequestException extends WebApiException {

  public BadRequestException(String message) {
    super(message);
  }

}
