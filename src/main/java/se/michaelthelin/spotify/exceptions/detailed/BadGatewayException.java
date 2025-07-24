package se.michaelthelin.spotify.exceptions.detailed;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

/**
 * The server was acting as a gateway or proxy and received an invalid response from the upstream server.
 */
public class BadGatewayException extends SpotifyWebApiException {

  /**
   * Create a BadGatewayException.
   */
  public BadGatewayException() {
    super();
  }

  /**
   * Create a BadGatewayException with a message.
   *
   * @param message The exception message.
   */
  public BadGatewayException(String message) {
    super(message);
  }

  /**
   * Create a BadGatewayException with a message and cause.
   *
   * @param message The exception message.
   * @param cause   The exception cause.
   */
  public BadGatewayException(String message, Throwable cause) {
    super(message, cause);
  }

}
