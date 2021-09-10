package se.michaelthelin.spotify.exceptions.detailed;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

/**
 * The request requires user authorization or, if the request included authorization credentials, authorization has been
 * refused for those credentials.
 */
public class UnauthorizedException extends SpotifyWebApiException {

  public UnauthorizedException() {
    super();
  }

  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(String message, Throwable cause) {
    super(message, cause);
  }

}
