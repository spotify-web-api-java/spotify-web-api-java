package com.wrapper.spotify.requests.data.playlists;

import com.google.gson.JsonArray;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import org.apache.http.entity.ContentType;

import java.io.IOException;

/**
 * Remove one or more tracks from a user’s playlist.
 */
public class RemoveTracksFromPlaylistRequest extends AbstractDataRequest {

  /**
   * The private {@link RemoveTracksFromPlaylistRequest} constructor.
   *
   * @param builder A {@link RemoveTracksFromPlaylistRequest.Builder}.
   */
  private RemoveTracksFromPlaylistRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Remove tracks from a playlist.
   *
   * @return A playlist snapshot ID. The snapshot ID can be used to identify your playlist version in future requests.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   * @see <a href="https://developer.spotify.com/web-api/working-with-playlists/#version-control-and-snapshots">
   * Spotify: Version Control and Snapshots</a>
   */
  @SuppressWarnings("unchecked")
  public SnapshotResult execute() throws
          IOException,
          SpotifyWebApiException {
    return new SnapshotResult.JsonUtil().createModelObject(deleteJson());
  }

  /**
   * Builder class for building a {@link RemoveTracksFromPlaylistRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link RemoveTracksFromPlaylistRequest.Builder}.
     * <p>
     * Removing tracks from an user's public playlists requires authorization of the {@code playlist-modify-public}
     * scope; removing tracks from an user's private playlist (including collaborative playlists) requires the
     * {@code playlist-modify-private} scope.
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
     * @deprecated Playlist IDs are unique for themselves. This parameter is thus no longer used.
     * (https://developer.spotify.com/community/news/2018/06/12/changes-to-playlist-uris/)
     * @param user_id The user's Spotify user ID.
     * @return An {@link RemoveTracksFromPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    @Deprecated
    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    /**
     * The playlist ID setter.
     *
     * @param playlist_id The Spotify ID for the playlist.
     * @return An {@link RemoveTracksFromPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The track URIs setter.
     * <p>
     * There are several ways to specify which tracks to remove, determined by the request parameters.
     * Removing all occurrences of specific tracks: <br>
     * {@code [{ "uri": "spotify:track:4iV5W9uYEdYUVa79Axb7Rh" },
     * {"uri": "spotify:track:1301WleyT98MSxVHPZCA6M" }] } <br>
     * Removing a specific occurrence of a track: <br>
     * {@code [{ "uri": "spotify:track:4iV5W9uYEdYUVa79Axb7Rh", "positions": [0,3] },
     * { "uri": "spotify:track:1301WleyT98MSxVHPZCA6M", "positions": [7] }] }
     *
     * @param tracks Required. An array of objects containing Spotify URIs of the tracks to remove. A maximum of
     *               100 objects can be sent at once
     * @return A {@link RemoveTracksFromPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder tracks(final JsonArray tracks) {
      assert (tracks != null);
      assert (!tracks.isJsonNull());
      assert (tracks.size() <= 100);
      return setBodyParameter("tracks", tracks);
    }

    /**
     * The playlist snapshot ID setter.
     * <p>
     * To guard against errors when concurrent edits happen to the same playlist, we recommend specifying a snapshot ID.
     * The snapshot ID lets us know which version of the playlist you are trying to edit. Concurrent edits by another
     * user will be automatically resolved. If a given track in a given position is not found in the specified snapshot,
     * the entire request will fail an no edits will take place.
     *
     * @param snapshotId Optional. The playlist's snapshot ID against which you want to make the changes. The API will
     *                   validate that the specified tracks exist and in the specified positions and make the changes,
     *                   even if more recent changes have been made to the playlist.
     * @return A {@link RemoveTracksFromPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/working-with-playlists/#version-control-and-snapshots">
     * Spotify: Version Control and Snapshots</a>
     */
    public Builder snapshotId(final String snapshotId) {
      assert (snapshotId != null);
      assert (!snapshotId.equals(""));
      return setBodyParameter("snapshot_id", snapshotId);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link RemoveTracksFromPlaylistRequest}.
     */
    @Override
    public RemoveTracksFromPlaylistRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/playlists/{playlist_id}/tracks");
      return new RemoveTracksFromPlaylistRequest(this);
    }
  }
}
