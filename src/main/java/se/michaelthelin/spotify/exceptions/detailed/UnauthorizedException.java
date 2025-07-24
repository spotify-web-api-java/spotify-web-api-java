package se.michaelthelin.spotify.exceptions.detailed;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

/**
 * The request requires user authorization or, if the request included authorization credentials, authorization has been
 * refused for those credentials.
 */
public class UnauthorizedException extends SpotifyWebApiException {

  /**
   * Constructs a new UnauthorizedException with no detail message.
   */
  public UnauthorizedException() {
    super();
  }

  /**
   * Constructs a new UnauthorizedException with the specified detail message.
   *
   * @param message the detail message
   */
  public UnauthorizedException(String message) {
    super(message);
  }

  /**
   * Constructs a new UnauthorizedException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause the cause of this exception
   */
  public UnauthorizedException(String message, Throwable cause) {
    super(message, cause);
  }

}
