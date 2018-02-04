package com.wrapper.spotify.exceptions.detailed;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;

/**
 * The requested resource could not be found. This error can be due to a temporary or permanent condition.
 */
public class NotFoundException extends SpotifyWebApiException {

  public NotFoundException() {
    super();
  }

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
