package com.wrapper.spotify.exceptions;

import org.apache.http.HttpException;

/**
 * An exception happened, eg. a HTTP status code 4** or 5** has been returned in a request.
 */
public class SpotifyWebApiException extends HttpException {

  public SpotifyWebApiException() {
    super();
  }

  public SpotifyWebApiException(String message) {
    super(message);
  }

  public SpotifyWebApiException(String message, Throwable cause) {
    super(message, cause);
  }

}
