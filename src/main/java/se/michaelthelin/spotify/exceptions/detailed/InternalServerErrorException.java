package se.michaelthelin.spotify.exceptions.detailed;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

/**
 * You should never receive this error because our clever coders catch them all ... but if you are unlucky enough to get
 * one, please report it to us.
 */
public class InternalServerErrorException extends SpotifyWebApiException {

  /**
   * Constructs a new InternalServerErrorException with no detail message.
   */
  public InternalServerErrorException() {
    super();
  }

  /**
   * Constructs a new InternalServerErrorException with the specified detail message.
   *
   * @param message the detail message
   */
  public InternalServerErrorException(String message) {
    super(message);
  }

  /**
   * Constructs a new InternalServerErrorException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause the cause of this exception
   */
  public InternalServerErrorException(String message, Throwable cause) {
    super(message, cause);
  }

}
