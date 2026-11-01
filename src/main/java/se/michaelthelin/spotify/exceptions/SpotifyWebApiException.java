package se.michaelthelin.spotify.exceptions;

import org.apache.hc.core5.http.HttpException;

/**
 * An exception happened, eg. a HTTP status code 4** or 5** has been returned in a request.
 */
public class SpotifyWebApiException extends HttpException {

  /** The reason from a player error response, e.g. {@code PREMIUM_REQUIRED}. */
  private String reason;

  /**
   * Creates a new SpotifyWebApiException with no detail message.
   */
  public SpotifyWebApiException() {
    super();
  }

  /**
   * Creates a new SpotifyWebApiException with the specified detail message.
   *
   * @param message the detail message
   */
  public SpotifyWebApiException(String message) {
    super(message);
  }

  /**
   * Creates a new SpotifyWebApiException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause   the cause of this exception
   */
  public SpotifyWebApiException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Gets the reason from a player error response, e.g. {@code PREMIUM_REQUIRED} or
   * {@code NO_ACTIVE_DEVICE}.
   *
   * @return the reason, or {@code null} if the error response did not contain one
   */
  public String getReason() {
    return reason;
  }

  /**
   * Sets the reason from a player error response.
   *
   * @param reason the reason
   */
  public void setReason(String reason) {
    this.reason = reason;
  }

}
