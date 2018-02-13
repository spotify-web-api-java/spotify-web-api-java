package com.wrapper.spotify.exceptions.detailed;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;

/**
 * The request could not be understood by the server due to malformed syntax.
 * 
 */
public class BadRequestException extends SpotifyWebApiException {
  private String spotifyMessage;

  public BadRequestException() {
    super();
  }

  /**
   * Tries to extract the detailed Errormessage as described here:
   * https://beta.developer.spotify.com/documentation/web-api/#regular-error-object
   */
  public BadRequestException(String message) {
    super(message);
    try {
      JsonObject detailedMessage = new JsonParser().parse(message).getAsJsonObject();
      this.spotifyMessage = detailedMessage.get("message").getAsString();
    } catch (Exception e) {
      //
    }

  }

  /**
   * Tries to extract the detailed Errormessage as described here:
   * https://beta.developer.spotify.com/documentation/web-api/#regular-error-object
   */
  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
    try {
      JsonObject detailedMessage = new JsonParser().parse(message).getAsJsonObject();
      this.spotifyMessage = detailedMessage.get("message").getAsString();
    } catch (Exception e) {
      //
    }
  }

  public String getSpotifyMessage() {
    return spotifyMessage;
  }

  public void setSpotifyMessage(String spotifyMessage) {
    this.spotifyMessage = spotifyMessage;
  }

}
