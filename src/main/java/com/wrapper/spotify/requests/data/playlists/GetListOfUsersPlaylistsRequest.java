package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a list of the playlists owned or followed by a Spotify user.
 */
public class GetListOfUsersPlaylistsRequest extends AbstractDataRequest {

  /**
   * The private {@link GetListOfUsersPlaylistsRequest} constructor.
   *
   * @param builder A {@link GetListOfUsersPlaylistsRequest.Builder}.
   */
  private GetListOfUsersPlaylistsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get an user's playlists.
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
   * Builder class for building a {@link GetListOfUsersPlaylistsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetListOfUsersPlaylistsRequest.Builder}.
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
     * The user ID setter.
     *
     * @param user_id The user's Spotify user ID.
     * @return A {@link GetListOfUsersPlaylistsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of playlists to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetListOfUsersPlaylistsRequest.Builder}.
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
     * @return A {@link GetListOfUsersPlaylistsRequest.Builder}.
     */
    public Builder offset(final Integer offset) {
      assert (0 <= offset && offset <= 100000);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetListOfUsersPlaylistsRequest}.
     */
    @Override
    public GetListOfUsersPlaylistsRequest build() {
      setPath("/v1/users/{user_id}/playlists");
      return new GetListOfUsersPlaylistsRequest(this);
    }
  }
}
