package com.wrapper.spotify.requests.data.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

import org.apache.http.entity.ContentType;

/**
 * Start a new context or resume current playback on the user’s active device.
 */
public class StartResumeUsersPlaybackRequest extends AbstractDataRequest {

  /**
   * The private {@link StartResumeUsersPlaybackRequest} constructor.
   *
   * @param builder A {@link StartResumeUsersPlaybackRequest.Builder}.
   */
  private StartResumeUsersPlaybackRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Start or resume a playback.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return putJson();
  }

  /**
   * Builder class for building a {@link StartResumeUsersPlaybackRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link StartResumeUsersPlaybackRequest.Builder}.
     * <p>
     * Your access token must have the {@code user-modify-playback-state} scope authorized in order to control playback.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The device ID setter.
     *
     * @param device_id Optional. The ID of the device this command is targeting. If not supplied, the
     *                  user's currently active device is the target.
     * @return A {@link StartResumeUsersPlaybackRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.equals(""));
      return setQueryParameter("device_id", device_id);
    }

    /**
     * The context URI setter.
     *
     * @param context_uri Optional. Spotify URI of the context to play.
     *                    Valid contexts are albums, artists and playlists.
     * @return A {@link StartResumeUsersPlaybackRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder context_uri(final String context_uri) {
      assert (context_uri != null);
      assert (!context_uri.equals(""));
      return setBodyParameter("context_uri", context_uri);
    }

    /**
     * The URI setter.
     *
     * @param uris Optional. A JSON array of the Spotify track URIs to play.
     * @return A {@link StartResumeUsersPlaybackRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder uris(final JsonArray uris) {
      assert (uris != null);
      assert (!uris.isJsonNull());
      return setBodyParameter("uris", uris);
    }

    /**
     * The offset setter.
     * <p>
     * <b>Note:</b> If {@link #context_uri(String)} has been set and corresponds to an album or playlist object, an
     * offset can be specified either by track {@code uri} OR {@code position}. If both are present the request will
     * return an error. If incorrect values are provided for {@code position} or {@code uri}, the request may be
     * accepted but with an unpredictable resulting action on playback.
     *
     * @param offset Optional. Indicates from where in the context playback should start. Only available when
     *               {@link #context_uri(String)} corresponds to an album or playlist object, or when the
     *               {@link #uris(JsonArray)} parameter is used. <br> The {@code position} parameter in the
     *               {@code offset} object is zero based and can’t be negative. <br> The {@code uri} parameter in the
     *               {@code offset} object is a string representing the URI of the item to start at.
     * @return A {@link StartResumeUsersPlaybackRequest.Builder}.
     */
    public Builder offset(final JsonObject offset) {
      assert (offset != null);
      assert (!offset.isJsonNull());
      return setBodyParameter("offset", offset);
    }

    /**
     * The position setter.
     *
     * @param position_ms Optional. Indicates from what position to start playback. Must be a positive number. Passing
     *                    in a position that is greater than the length of the track will cause the player to start
     *                    playing the next song.
     * @return A {@link StartResumeUsersPlaybackRequest.Builder}.
     */
    public Builder position_ms(final Integer position_ms) {
      assert (position_ms != null);
      assert (position_ms >= 0);
      return setQueryParameter("position_ms", position_ms);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link StartResumeUsersPlaybackRequest}.
     */
    @Override
    public StartResumeUsersPlaybackRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/player/play");
      return new StartResumeUsersPlaybackRequest(this);
    }
  }
}
