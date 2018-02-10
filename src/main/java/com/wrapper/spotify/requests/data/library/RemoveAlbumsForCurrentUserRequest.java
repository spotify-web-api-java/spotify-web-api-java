package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Remove one or more albums from the current user’s "Your Music" library.
 */
public class RemoveAlbumsForCurrentUserRequest extends AbstractDataRequest {

  /**
   * The private {@link RemoveAlbumsForCurrentUserRequest} constructor.
   *
   * @param builder A {@link RemoveAlbumsForCurrentUserRequest.Builder}.
   */
  private RemoveAlbumsForCurrentUserRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Remove an album.
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
   * Builder class for building a {@link RemoveAlbumsForCurrentUserRequest}.
   */
  public static class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link RemoveAlbumsForCurrentUserRequest.Builder} instance.
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
     * The album IDs setter.
     *
     * @param ids Optional. A comma-separated list of the Spotify IDs. Maximum: 50 IDs.
     * @return A {@link RemoveAlbumsForCurrentUserRequest.Builder}.
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
     * @return A custom {@link RemoveAlbumsForCurrentUserRequest.Builder}.
     */
    @Override
    public RemoveAlbumsForCurrentUserRequest build() {
      setPath("/v1/me/albums");
      return new RemoveAlbumsForCurrentUserRequest(this);
    }
  }
}
