package com.wrapper.spotify.requests.data.player;

import com.google.gson.JsonArray;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import org.apache.http.entity.ContentType;

import java.io.IOException;

/**
 * Transfer playback to a new device and determine if it should start playing.
 */
public class TransferUsersPlaybackRequest extends AbstractDataRequest {

  /**
   * The private {@link TransferUsersPlaybackRequest} constructor.
   *
   * @param builder A {@link TransferUsersPlaybackRequest.Builder}.
   */
  private TransferUsersPlaybackRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Transfer playback to a new device.
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
   * Builder class for building a {@link TransferUsersPlaybackRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link TransferUsersPlaybackRequest.Builder}.
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
     * @param device_ids Required. A JSON array containing the ID of the device on which playback should be
     *                   started/transferred. <b>Note:</b> Although an array is accepted, only a single
     *                   {@code device_id} is currently supported.
     * @return A {@link TransferUsersPlaybackRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder device_ids(final JsonArray device_ids) {
      assert (device_ids != null);
      assert (!device_ids.isJsonNull());
      assert (device_ids.size() == 1);
      return setBodyParameter("device_ids", device_ids);
    }

    /**
     * The playing state setter.
     *
     * @param play Optional. {@code true}: ensure playback happens on new device.
     *             {@code false} or not provided: keep the current playback state.
     * @return A {@link TransferUsersPlaybackRequest.Builder}.
     */
    public Builder play(final Boolean play) {
      return setBodyParameter("play", play);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link TransferUsersPlaybackRequest}.
     */
    @Override
    public TransferUsersPlaybackRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/player");
      return new TransferUsersPlaybackRequest(this);
    }
  }
}
