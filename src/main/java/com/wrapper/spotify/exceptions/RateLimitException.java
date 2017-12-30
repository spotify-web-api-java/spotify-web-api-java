package com.wrapper.spotify.exceptions;

import org.apache.http.HttpException;

public class RateLimitException extends HttpException {
  private final int secondsToSleep;

  public RateLimitException(int secondsToSleep) {
    super();
    this.secondsToSleep = secondsToSleep;
  }

  public RateLimitException(String message, int secondsToSleep) {
    super(message);
    this.secondsToSleep = secondsToSleep;
  }

  public RateLimitException(String message, Throwable cause, int secondsToSleep) {
    super(message, cause);
    this.secondsToSleep = secondsToSleep;
  }

  public int getSecondsToSleep() {
    return secondsToSleep;
  }
}
