package se.michaelthelin.spotify.requests.data.playlists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
  * @deprecated Use the new endpoints instead.
 * Reorder an item or a group of items in a playlist.
 * <p>
 * When reordering items, the timestamp indicating when they were added and the user who added them will
 * be kept untouched. In addition, the users following the playlists won’t be notified about changes in
 * the playlists when the items are reordered.
 */
@Deprecated
@JsonDeserialize(builder = UpdatePlaylistsItemsReorderDeprecatedRequest.Builder.class)
public class UpdatePlaylistsItemsReorderDeprecatedRequest extends AbstractDataRequest<SnapshotResult> {

  /**
  * @deprecated Use the new endpoints instead.
   * The private {@link UpdatePlaylistsItemsReorderDeprecatedRequest} constructor.
   *
   * @param builder A {@link UpdatePlaylistsItemsReorderDeprecatedRequest.Builder}.
   */
  private UpdatePlaylistsItemsReorderDeprecatedRequest(final Builder builder) {
    super(builder);
  }

  /**
  * @deprecated Use the new endpoints instead.
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
  * @deprecated Use the new endpoints instead.
   * Builder class for building a {@link UpdatePlaylistsItemsReorderDeprecatedRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<SnapshotResult, Builder> {

    /**
  * @deprecated Use the new endpoints instead.
     * Create a new {@link UpdatePlaylistsItemsReorderDeprecatedRequest.Builder}.
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
  * @deprecated Use the new endpoints instead.
     * The playlist ID setter.
     *
     * @param playlist_id The Spotify ID for the playlist.
     * @return A {@link UpdatePlaylistsItemsReorderDeprecatedRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.isEmpty());
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
  * @deprecated Use the new endpoints instead.
     * The range start setter.
     *
     * @param range_start Required. The position of the first item to be reordered.
     * @return A {@link UpdatePlaylistsItemsReorderDeprecatedRequest.Builder}.
     */
    public Builder range_start(final Integer range_start) {
      assert (range_start != null);
      assert (range_start >= 0);
      return setBodyParameter("range_start", range_start);
    }

    /**
  * @deprecated Use the new endpoints instead.
     * The range length setter.
     *
     * @param range_length Optional. The amount of items to be reordered. Defaults to 1 if not set.
     * @return A {@link UpdatePlaylistsItemsReorderDeprecatedRequest.Builder}.
     */
    public Builder range_length(final Integer range_length) {
      assert (range_length != null);
      assert (range_length >= 1);
      return setBodyParameter("range_length", range_length);
    }

    /**
  * @deprecated Use the new endpoints instead.
     * The insert before setter.
     *
     * @param insert_before Required. The position where the items should be inserted. To reorder the items to the
     *                      end of the playlist, simply set insert_before to the position after the last item.
     * @return A {@link UpdatePlaylistsItemsReorderDeprecatedRequest.Builder}.
     */
    public Builder insert_before(final Integer insert_before) {
      assert (insert_before != null);
      assert (insert_before >= 0);
      return setBodyParameter("insert_before", insert_before);
    }

    /**
  * @deprecated Use the new endpoints instead.
     * The playlist snapshot ID setter.
     *
     * @param snapshot_id Optional. The playlist's snapshot ID against which you want to make the changes.
     * @return A {@link UpdatePlaylistsItemsReorderDeprecatedRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/playlists">
     * Spotify: Version Control and Snapshots</a>
     */
    public Builder snapshot_id(final String snapshot_id) {
      assert (snapshot_id != null);
      assert (!snapshot_id.isEmpty());
      return setBodyParameter("snapshot_id", snapshot_id);
    }

    /**
  * @deprecated Use the new endpoints instead.
     * The request build method.
     *
     * @return A custom {@link UpdatePlaylistsItemsReorderDeprecatedRequest}.
     */
    @Override
    public UpdatePlaylistsItemsReorderDeprecatedRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/playlists/{playlist_id}/tracks");
      return new UpdatePlaylistsItemsReorderDeprecatedRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
