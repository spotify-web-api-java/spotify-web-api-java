package se.michaelthelin.spotify.exceptions.detailed;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

/**
 * The request could not be understood by the server due to malformed syntax.
 */
public class BadRequestException extends SpotifyWebApiException {

  /**
   * Constructs a new BadRequestException with no detail message.
   */
  public BadRequestException() {
    super();
  }

  /**
   * Constructs a new BadRequestException with the specified detail message.
   *
   * @param message the detail message
   */
  public BadRequestException(String message) {
    super(message);
  }

  /**
   * Constructs a new BadRequestException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause the cause of this exception
   */
  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

}
