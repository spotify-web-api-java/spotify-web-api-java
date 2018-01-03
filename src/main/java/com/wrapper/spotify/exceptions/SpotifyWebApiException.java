package com.wrapper.spotify.exceptions;

import org.apache.http.HttpException;

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
