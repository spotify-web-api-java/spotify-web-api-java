package com.wrapper.spotify.requests.data.follow;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Check to see if one or more Spotify users are following a specified playlist.
 */
public class CheckUsersFollowPlaylistRequest extends AbstractDataRequest {

  /**
   * The private {@link CheckUsersFollowPlaylistRequest} constructor.
   *
   * @param builder A {@link CheckUsersFollowPlaylistRequest.Builder}.
   */
  private CheckUsersFollowPlaylistRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Check whether a user is following a playlist or not.
   *
   * @return Whether a user is following a playlist or not.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Boolean[] execute() throws
          IOException,
          SpotifyWebApiException {
    return new Gson().fromJson(new JsonParser().parse(getJson()).getAsJsonArray(), Boolean[].class);
  }

  /**
   * Builder class for building a {@link CheckUsersFollowPlaylistRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link CheckUsersFollowPlaylistRequest.Builder} instance.
     * <p>
     * Following a playlist can be done publicly or privately. Checking if a user publicly follows a playlist doesn't
     * require any scopes; if the user is publicly following the playlist, this endpoint returns {@code true}.
     * <p>
     * Checking if the user is privately following a playlist is only possible for the current user when that user has
     * granted access to the {@code playlist-read-private scope}.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The playlists owner ID setter.
     *
     * @param owner_id The Spotify user ID of the person who owns the playlist.
     * @return A {@link CheckUsersFollowPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder owner_id(final String owner_id) {
      assert (owner_id != null);
      assert (!owner_id.equals(""));
      return setPathParameter("owner_id", owner_id);
    }

    /**
     * The paylist ID setter.
     *
     * @param playlist_id The Spotify ID of the playlist.
     * @return A {@link CheckUsersFollowPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The user IDs setter.
     *
     * @param ids Required. A comma-separated list of Spotify User IDs; the ids of the users that you want to check to
     *            see if they follow the playlist. Maximum: 5 ids.
     * @return A {@link CheckUsersFollowPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 5);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link CheckUsersFollowPlaylistRequest}.
     */
    @Override
    public CheckUsersFollowPlaylistRequest build() {
      setPath("/v1/users/{owner_id}/playlists/{playlist_id}/followers/contains");
      return new CheckUsersFollowPlaylistRequest(this);
    }
  }
}
