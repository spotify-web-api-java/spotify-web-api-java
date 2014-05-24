package com.wrapper.spotify.exceptions;

public class WebApiException extends Exception {

  public WebApiException(String message) {
    super(message);
  }

  public WebApiException() {
    super();
  }

}
