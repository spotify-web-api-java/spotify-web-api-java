package com.wrapper.spotify.exceptions;

public class TokenRequestException extends WebApiException {

  public TokenRequestException(String message) {
    super(message);
  }
}
