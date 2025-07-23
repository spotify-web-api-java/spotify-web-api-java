package se.michaelthelin.spotify.exceptions.detailed;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

/**
 * The requested resource could not be found. This error can be due to a temporary or permanent condition.
 */
public class NotFoundException extends SpotifyWebApiException {

  /**
   * Constructs a new NotFoundException with no detail message.
   */
  public NotFoundException() {
    super();
  }

  /**
   * Constructs a new NotFoundException with the specified detail message.
   *
   * @param message the detail message
   */
  public NotFoundException(String message) {
    super(message);
  }

  /**
   * Constructs a new NotFoundException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause the cause of this exception
   */
  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
