package se.michaelthelin.spotify.exceptions.detailed;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

/**
 * Rate limiting has been applied.
 */
public class TooManyRequestsException extends SpotifyWebApiException {

  /** Number of seconds to wait before retrying the request. */
  private int retryAfter;

  /**
   * Constructs a new TooManyRequestsException with no detail message.
   */
  public TooManyRequestsException() {
    super();
  }

  /**
   * Constructs a new TooManyRequestsException with the specified detail message and retry-after period.
   *
   * @param message the detail message
   * @param retryAfter number of seconds to wait before retrying
   */
  public TooManyRequestsException(String message, int retryAfter) {
    super(message);
    this.setRetryAfter(retryAfter);
  }

  /**
   * Constructs a new TooManyRequestsException with the specified detail message.
   *
   * @param message the detail message
   */
  public TooManyRequestsException(String message) {
    super(message);
  }

  /**
   * Constructs a new TooManyRequestsException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause the cause of this exception
   */
  public TooManyRequestsException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Gets the number of seconds to wait before retrying the request.
   *
   * @return the retry-after period in seconds
   */
  public int getRetryAfter() {
    return retryAfter;
  }

  /**
   * Sets the number of seconds to wait before retrying the request.
   *
   * @param retryAfter the retry-after period in seconds
   */
  public void setRetryAfter(int retryAfter) {
    this.retryAfter = retryAfter;
  }

}
