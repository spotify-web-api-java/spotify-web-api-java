package se.michaelthelin.spotify.exceptions;

public class WebApiException extends Exception {

  public WebApiException(String message) {
    super(message);
  }

  public WebApiException() {
    super();
  }

}
