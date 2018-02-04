package com.wrapper.spotify.exceptions.detailed;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;

/**
 * The server is currently unable to handle the request due to a temporary condition which will be alleviated after some
 * delay. You can choose to resend the request again.
 */
public class ServiceUnavailableException extends SpotifyWebApiException {

  public ServiceUnavailableException() {
    super();
  }

  public ServiceUnavailableException(String message) {
    super(message);
  }

  public ServiceUnavailableException(String message, Throwable cause) {
    super(message, cause);
  }

}
