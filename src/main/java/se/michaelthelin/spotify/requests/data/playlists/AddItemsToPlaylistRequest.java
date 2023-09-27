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
 * Add one or more items to a user’s playlist.
 * <p>
 * Use this endpoint to add Spotify tracks or episodes to a user’s playlist, sending their Spotify URI.
 * Note that local tracks can’t be added.
 */
@JsonDeserialize(builder = AddItemsToPlaylistRequest.Builder.class)
public class AddItemsToPlaylistRequest extends AbstractDataRequest<SnapshotResult> {

  /**
   * The private {@link AddItemsToPlaylistRequest} constructor.
   *
   * @param builder A {@link AddItemsToPlaylistRequest.Builder}.
   */
  private AddItemsToPlaylistRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Add items to playlist.
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
    return new SnapshotResult.JsonUtil().createModelObject(postJson());
  }

  /**
   * Builder class for building an {@link AddItemsToPlaylistRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<SnapshotResult, Builder> {

    /**
     * Create a new {@link AddItemsToPlaylistRequest.Builder}.
     * <p>
     * Adding items to the current user's public playlists requires authorization of the {@code playlist-modify-public}
     * scope; adding items to the current user's private playlist (including collaborative playlists) requires the
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
     * @return An {@link AddItemsToPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The item URIs setter.
     * <p>
     * <b>Note:</b> It is likely that passing a large number of item URIs as a query parameter will exceed the maximum
     * length of the request URI. When adding a large number of items it is recommended to pass them
     * with {@link #uris(JsonArray)}.
     *
     * @param uris Optional. A comma-separated list of Spotify track or episode URIs to add. Maximum: 100 item URIs.
     * @return An {@link AddItemsToPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder uris(final String uris) {
      assert (uris != null);
      assert (!uris.equals(""));
      assert (uris.split(",").length <= 100);
      return setQueryParameter("uris", uris);
    }

    /**
     * The position setter.
     *
     * @param position Optional. The position to insert the items, a zero-based index. If omitted, the
     *                 items will be appended to the playlist. Items are added in the order they are
     *                 listed in the query string or request body.
     * @return An {@link AddItemsToPlaylistRequest.Builder}.
     */
    public Builder position(final Integer position) {
      return position(position, false);
    }

    /**
     * The item URIs setter.
     * <p>
     * <b>Note:</b> If the URIs already have been set with {@link #uris(String)}, any URIs listed here will be ignored.
     *
     * @param uris Optional. A JSON array of the Spotify track or episode URIs to add. maximum: 100 item URIs.
     * @return An {@link AddItemsToPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder uris(final JsonArray uris) {
      assert (uris != null);
      assert (!uris.isJsonNull());
      assert (uris.size() <= 100);
      return setBodyParameter("uris", uris);
    }

    /**
     * The position setter. You can set it in the request query string or request body, depending on the
     * {@code use_body} parameter.
     *
     * @param position Optional. The position to insert the items, a zero-based index. If omitted, the
     *                 items will be appended to the playlist. Items are added in the order they are
     *                 listed in the query string or request body.
     * @param use_body Whether to include the position in the request query string or body.
     * @return An {@link AddItemsToPlaylistRequest.Builder}.
     */
    public Builder position(final Integer position, final Boolean use_body) {
      assert (position >= 0);

      if (use_body) {
        return setBodyParameter("position", position);
      } else {
        return setQueryParameter("position", position);
      }
    }

    /**
     * The request build method.
     *
     * @return A custom {@link AddItemsToPlaylistRequest}.
     */
    @Override
    public AddItemsToPlaylistRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/playlists/{playlist_id}/tracks");
      return new AddItemsToPlaylistRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
