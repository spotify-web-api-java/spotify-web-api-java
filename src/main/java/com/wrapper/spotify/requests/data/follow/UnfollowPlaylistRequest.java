package com.wrapper.spotify.requests.data.follow;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Remove the current user as a follower of a playlist.
 */
public class UnfollowPlaylistRequest extends AbstractDataRequest {

  /**
   * The private {@link UnfollowPlaylistRequest} constructor.
   *
   * @param builder A {@link UnfollowPlaylistRequest.Builder}.
   */
  private UnfollowPlaylistRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Unfollow a playlist.
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
   * Builder class for building an {@link UnfollowPlaylistRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link UnfollowPlaylistRequest.Builder} instance.
     * <p>
     * Unfollowing a publicly followed playlist for a user requires authorization of the {@code playlist-modify-public}
     * scope; unfollowing a privately followed playlist requires the {@code playlist-modify-private} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The playlist owner ID setter.
     *
     * @param owner_id The Spotify user ID of the person who owns the playlist.
     * @return An {@link UnfollowPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder owner_id(final String owner_id) {
      assert (owner_id != null);
      assert (!owner_id.equals(""));
      return setPathParameter("owner_id", owner_id);
    }

    /**
     * The playlist ID setter.
     *
     * @param playlist_id The Spotify ID of the playlist that is to be no longer followed.
     * @return An {@link UnfollowPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link UnfollowPlaylistRequest}.
     */
    @Override
    public UnfollowPlaylistRequest build() {
      setPath("/v1/users/{owner_id}/playlists/{playlist_id}/followers");
      return new UnfollowPlaylistRequest(this);
    }
  }
}
