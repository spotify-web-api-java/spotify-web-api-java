package com.wrapper.spotify.exceptions;


public class RateLimitException extends WebApiException {

    private final int secondsToSleep;

    public RateLimitException(String message, int secondsToSleep) {
        super(message);
        this.secondsToSleep = secondsToSleep;
    }

    public int getSecondsToSleep() {
        return secondsToSleep;
    }
}
