package se.michaelthelin.spotify.requests.data.playlists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonArray;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Remove one or more items from a user’s playlist.
 */
@JsonDeserialize(builder = RemoveItemsFromPlaylistRequest.Builder.class)
public class RemoveItemsFromPlaylistRequest extends AbstractDataRequest<SnapshotResult> {

  /**
   * The private {@link RemoveItemsFromPlaylistRequest} constructor.
   *
   * @param builder A {@link RemoveItemsFromPlaylistRequest.Builder}.
   */
  private RemoveItemsFromPlaylistRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Remove items from a playlist.
   *
   * @return A playlist snapshot ID. The snapshot ID can be used to identify your playlist version in future requests.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/playlists">
   * Spotify: Version Control and Snapshots</a>
   */
  public SnapshotResult execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new SnapshotResult.JsonUtil().createModelObject(deleteJson());
  }

  /**
   * Builder class for building a {@link RemoveItemsFromPlaylistRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<SnapshotResult, Builder> {

    /**
     * Create a new {@link RemoveItemsFromPlaylistRequest.Builder}.
     * <p>
     * Removing items from an user's public playlists requires authorization of the {@code playlist-modify-public}
     * scope; removing items from an user's private playlist (including collaborative playlists) requires the
     * {@code playlist-modify-private} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The playlist ID setter.
     *
     * @param playlist_id The Spotify ID for the playlist.
     * @return An {@link RemoveItemsFromPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.isEmpty());
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The item URIs setter.
     * <p>
     * There are several ways to specify which tracks or episodes to remove, determined by the request parameters.
     * Removing all occurrences of specific items: <br>
     * {@code [{ "uri": "spotify:track:4iV5W9uYEdYUVa79Axb7Rh" },
     * {"uri": "spotify:episode:512ojhOuo1ktJprKbVcKyQ" }] } <br>
     * Removing a specific occurrence of an item: <br>
     * {@code [{ "uri": "spotify:track:4iV5W9uYEdYUVa79Axb7Rh", "positions": [0,3] },
     * { "uri": "spotify:track:1301WleyT98MSxVHPZCA6M", "positions": [7] }] }
     *
     * @param tracks Required. An array of objects containing Spotify URIs of the items to remove. A maximum of
     *               100 objects can be sent at once
     * @return A {@link RemoveItemsFromPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
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
     * user will be automatically resolved. If a given item in a given position is not found in the specified snapshot,
     * the entire request will fail an no edits will take place.
     *
     * @param snapshotId Optional. The playlist's snapshot ID against which you want to make the changes. The API will
     *                   validate that the specified items exist and in the specified positions and make the changes,
     *                   even if more recent changes have been made to the playlist.
     * @return A {@link RemoveItemsFromPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/playlists">
     * Spotify: Version Control and Snapshots</a>
     */
    public Builder snapshotId(final String snapshotId) {
      assert (snapshotId != null);
      assert (!snapshotId.isEmpty());
      return setBodyParameter("snapshot_id", snapshotId);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link RemoveItemsFromPlaylistRequest}.
     */
    @Override
    public RemoveItemsFromPlaylistRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/playlists/{playlist_id}/tracks");
      return new RemoveItemsFromPlaylistRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
