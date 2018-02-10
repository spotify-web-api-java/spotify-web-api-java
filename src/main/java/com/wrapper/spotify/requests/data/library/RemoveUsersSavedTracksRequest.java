package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Remove one or more tracks from the current user’s "Your Music" library.
 */
public class RemoveUsersSavedTracksRequest extends AbstractDataRequest {

  /**
   * The private {@link RemoveUsersSavedTracksRequest} constructor.
   *
   * @param builder A {@link RemoveUsersSavedTracksRequest.Builder}.
   */
  private RemoveUsersSavedTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Remove a track.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return deleteJson();
  }

  /**
   * Builder class for building a {@link RemoveUsersSavedTracksRequest}.
   */
  public static class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link RemoveUsersSavedTracksRequest.Builder} instance.
     * <p>
     * Modification of the current user's "Your Music" collection requires authorization of the
     * {@code user-library-modify} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The track IDs setter.
     *
     * @param ids Optional. A comma-separated list of the Spotify IDs. Maximum: 50 IDs.
     * @return A {@link RemoveUsersSavedTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link RemoveUsersSavedTracksRequest}.
     */
    @Override
    public RemoveUsersSavedTracksRequest build() {
      setPath("/v1/me/tracks");
      return new RemoveUsersSavedTracksRequest(this);
    }
  }
}
