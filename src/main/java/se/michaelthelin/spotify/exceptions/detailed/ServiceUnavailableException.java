package se.michaelthelin.spotify.exceptions.detailed;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

/**
 * The server is currently unable to handle the request due to a temporary condition which will be alleviated after some
 * delay. You can choose to resend the request again.
 */
public class ServiceUnavailableException extends SpotifyWebApiException {

  /**
   * Constructs a new ServiceUnavailableException with no detail message.
   */
  public ServiceUnavailableException() {
    super();
  }

  /**
   * Constructs a new ServiceUnavailableException with the specified detail message.
   *
   * @param message the detail message
   */
  public ServiceUnavailableException(String message) {
    super(message);
  }

  /**
   * Constructs a new ServiceUnavailableException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause the cause of this exception
   */
  public ServiceUnavailableException(String message, Throwable cause) {
    super(message, cause);
  }

}
