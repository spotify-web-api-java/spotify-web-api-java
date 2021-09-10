package se.michaelthelin.spotify.exceptions.detailed;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

/**
 * The server understood the request, but is refusing to fulfill it.
 */
public class ForbiddenException extends SpotifyWebApiException {

  public ForbiddenException() {
    super();
  }

  public ForbiddenException(String message) {
    super(message);
  }

  public ForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }

}
