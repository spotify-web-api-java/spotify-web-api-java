package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a list of the playlists owned or followed by the current Spotify user.
 */
public class GetListOfCurrentUsersPlaylistsRequest extends AbstractDataRequest {

  /**
   * The private {@link GetListOfCurrentUsersPlaylistsRequest} constructor.
   *
   * @param builder A {@link GetListOfCurrentUsersPlaylistsRequest.Builder}.
   */
  private GetListOfCurrentUsersPlaylistsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a list of the current user's playlists.
   *
   * @return A {@link PlaylistSimplified} paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Paging<PlaylistSimplified> execute() throws
          IOException,
          SpotifyWebApiException {
    return new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetListOfCurrentUsersPlaylistsRequest}
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetListOfCurrentUsersPlaylistsRequest.Builder}.
     * <p>
     * Private playlists are only retrievable for the current user and requires the {@code playlist-read-private}
     * scope to have been authorized by the user. <b>Note:</b> This scope alone will not return collaborative playlists,
     * even though they are always private.
     * <p>
     * Collaborative playlists are only retrievable for the current user and requires the
     * {@code playlist-read-collaborative} scope to have been authorized by the user.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of playlists to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetListOfCurrentUsersPlaylistsRequest.Builder}.
     */
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first playlist to return. Default: 0 (the first object). Maximum offset:
     *               100.000. Use with {@link #limit(Integer)} to get the next set of playlists.
     * @return A {@link GetListOfCurrentUsersPlaylistsRequest.Builder}.
     */
    public Builder offset(final Integer offset) {
      assert (0 <= offset && offset <= 100000);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetListOfCurrentUsersPlaylistsRequest}.
     */
    @Override
    public GetListOfCurrentUsersPlaylistsRequest build() {
      setPath("/v1/me/playlists");
      return new GetListOfCurrentUsersPlaylistsRequest(this);
    }
  }
}
