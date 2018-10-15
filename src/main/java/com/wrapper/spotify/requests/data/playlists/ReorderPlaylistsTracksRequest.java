package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import org.apache.http.entity.ContentType;

import java.io.IOException;

/**
 * Reorder a track or a group of tracks in a playlist.
 * <p>
 * When reordering tracks, the timestamp indicating when they were added and the user who added them will
 * be kept untouched. In addition, the users following the playlists won’t be notified about changes in
 * the playlists when the tracks are reordered.
 */
public class ReorderPlaylistsTracksRequest extends AbstractDataRequest {

  /**
   * The private {@link ReorderPlaylistsTracksRequest} constructor.
   *
   * @param builder A {@link ReorderPlaylistsTracksRequest.Builder}.
   */
  private ReorderPlaylistsTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Reorder the tracks in a playlist.
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
    return new SnapshotResult.JsonUtil().createModelObject(putJson());
  }

  /**
   * Builder class for building a {@link ReorderPlaylistsTracksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link ReorderPlaylistsTracksRequest.Builder}.
     * <p>
     * Reordering tracks in the current user's public playlists requires authorization of the
     * {@code playlist-modify-public} scope; reordering tracks in the current user's private playlist (including
     * collaborative playlists) requires the {@code playlist-modify-private} scope.
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
     * @return A {@link ReorderPlaylistsTracksRequest.Builder}.
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
     * @return A {@link ReorderPlaylistsTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The range start setter.
     *
     * @param range_start Required. The position of the first track to be reordered.
     * @return A {@link ReorderPlaylistsTracksRequest.Builder}.
     */
    public Builder range_start(final Integer range_start) {
      assert (range_start != null);
      assert (range_start >= 0);
      return setBodyParameter("range_start", range_start);
    }

    /**
     * The range length setter.
     *
     * @param range_length Optional. The amount of tracks to be reordered. Defaults to 1 if not set.
     * @return A {@link ReorderPlaylistsTracksRequest.Builder}.
     */
    public Builder range_length(final Integer range_length) {
      assert (range_length != null);
      assert (range_length >= 1);
      return setBodyParameter("range_length", range_length);
    }

    /**
     * The insert before setter.
     *
     * @param insert_before Required. The position where the tracks should be inserted. To reorder the tracks to the
     *                      end of the playlist, simply set insert_before to the position after the last track.
     * @return A {@link ReorderPlaylistsTracksRequest.Builder}.
     */
    public Builder insert_before(final Integer insert_before) {
      assert (insert_before != null);
      assert (insert_before >= 0);
      return setBodyParameter("insert_before", insert_before);
    }

    /**
     * The playlist snapshot ID setter.
     *
     * @param snapshot_id Optional. The playlist's snapshot ID against which you want to make the changes.
     * @return A {@link RemoveTracksFromPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/working-with-playlists/#version-control-and-snapshots">
     * Spotify: Version Control and Snapshots</a>
     */
    public Builder snapshot_id(final String snapshot_id) {
      assert (snapshot_id != null);
      assert (!snapshot_id.equals(""));
      return setBodyParameter("snapshot_id", snapshot_id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link ReorderPlaylistsTracksRequest}.
     */
    @Override
    public ReorderPlaylistsTracksRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/playlists/{playlist_id}/tracks");
      return new ReorderPlaylistsTracksRequest(this);
    }
  }
}
