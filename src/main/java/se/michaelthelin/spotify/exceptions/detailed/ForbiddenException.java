package se.michaelthelin.spotify.exceptions.detailed;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

/**
 * The server understood the request, but is refusing to fulfill it.
 */
public class ForbiddenException extends SpotifyWebApiException {

  /**
   * Constructs a new ForbiddenException with no detail message.
   */
  public ForbiddenException() {
    super();
  }

  /**
   * Constructs a new ForbiddenException with the specified detail message.
   *
   * @param message the detail message
   */
  public ForbiddenException(String message) {
    super(message);
  }

  /**
   * Constructs a new ForbiddenException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause the cause of this exception
   */
  public ForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }

}
