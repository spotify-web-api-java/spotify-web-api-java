package se.michaelthelin.spotify.requests.data.playlists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Reorder an item or a group of items in a playlist.
 * <p>
 * When reordering items, the timestamp indicating when they were added and the user who added them will
 * be kept untouched. In addition, the users following the playlists won’t be notified about changes in
 * the playlists when the items are reordered.
 */
@JsonDeserialize(builder = ReorderPlaylistsItemsRequest.Builder.class)
public class ReorderPlaylistsItemsRequest extends AbstractDataRequest<SnapshotResult> {

  /**
   * The private {@link ReorderPlaylistsItemsRequest} constructor.
   *
   * @param builder A {@link ReorderPlaylistsItemsRequest.Builder}.
   */
  private ReorderPlaylistsItemsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Reorder the items in a playlist.
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
    return new SnapshotResult.JsonUtil().createModelObject(putJson());
  }

  /**
   * Builder class for building a {@link ReorderPlaylistsItemsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<SnapshotResult, Builder> {

    /**
     * Create a new {@link ReorderPlaylistsItemsRequest.Builder}.
     * <p>
     * Reordering items in the current user's public playlists requires authorization of the
     * {@code playlist-modify-public} scope; reordering items in the current user's private playlist (including
     * collaborative playlists) requires the {@code playlist-modify-private} scope.
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
     * @return A {@link ReorderPlaylistsItemsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.isEmpty());
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The range start setter.
     *
     * @param range_start Required. The position of the first item to be reordered.
     * @return A {@link ReorderPlaylistsItemsRequest.Builder}.
     */
    public Builder range_start(final Integer range_start) {
      assert (range_start != null);
      assert (range_start >= 0);
      return setBodyParameter("range_start", range_start);
    }

    /**
     * The range length setter.
     *
     * @param range_length Optional. The amount of items to be reordered. Defaults to 1 if not set.
     * @return A {@link ReorderPlaylistsItemsRequest.Builder}.
     */
    public Builder range_length(final Integer range_length) {
      assert (range_length != null);
      assert (range_length >= 1);
      return setBodyParameter("range_length", range_length);
    }

    /**
     * The insert before setter.
     *
     * @param insert_before Required. The position where the items should be inserted. To reorder the items to the
     *                      end of the playlist, simply set insert_before to the position after the last item.
     * @return A {@link ReorderPlaylistsItemsRequest.Builder}.
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
     * @return A {@link ReorderPlaylistsItemsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/playlists">
     * Spotify: Version Control and Snapshots</a>
     */
    public Builder snapshot_id(final String snapshot_id) {
      assert (snapshot_id != null);
      assert (!snapshot_id.isEmpty());
      return setBodyParameter("snapshot_id", snapshot_id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link ReorderPlaylistsItemsRequest}.
     */
    @Override
    public ReorderPlaylistsItemsRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/playlists/{playlist_id}/tracks");
      return new ReorderPlaylistsItemsRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
