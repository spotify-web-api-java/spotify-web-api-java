package com.wrapper.spotify.exceptions.detailed;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;

/**
 * The request has succeeded but returns no message body.
 */
public class NoContentException extends SpotifyWebApiException {

  public NoContentException() {
    super();
  }

  public NoContentException(String message) {
    super(message);
  }

  public NoContentException(String message, Throwable cause) {
    super(message, cause);
  }

}
