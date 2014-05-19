package se.michaelthelin.spotify.exceptions;

public class ErrorResponseException extends Exception {

  public ErrorResponseException(String reason) {
    super(reason);
  }
}
