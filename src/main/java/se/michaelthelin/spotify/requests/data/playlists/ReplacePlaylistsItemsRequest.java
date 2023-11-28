package se.michaelthelin.spotify.requests.data.playlists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonArray;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Replace all the items in a playlist, overwriting its existing items. This powerful request can be useful for
 * replacing items, re-ordering existing items, or clearing the playlist.
 */
@JsonDeserialize(builder = ReplacePlaylistsItemsRequest.Builder.class)
public class ReplacePlaylistsItemsRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link ReplacePlaylistsItemsRequest} constructor.
   *
   * @param builder A {@link ReplacePlaylistsItemsRequest.Builder}.
   */
  private ReplacePlaylistsItemsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Replace items in a playlist.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public String execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return putJson();
  }

  /**
   * Builder class for building a {@link ReplacePlaylistsItemsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link ReplacePlaylistsItemsRequest.Builder}.
     * <p>
     * Replacing items in the current user's public playlists requires authorization of the
     * {@code playlist-modify-public} scope; replacing items in the current user's private playlist (including
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
     * @return A {@link ReplacePlaylistsItemsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.isEmpty());
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The item URIs setter.
     *
     * @param uris Optional. A comma-separated list of Spotify track or episode URIs to set. Maximum: 100 track or episode URIs.
     * @return A {@link ReplacePlaylistsItemsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder uris(final String uris) {
      assert (uris != null);
      assert (!uris.isEmpty());
      assert (uris.split(",").length <= 100);
      return setQueryParameter("uris", uris);
    }

    /**
     * The item URIs setter.
     * <p>
     * <b>Note:</b> If the URIs have already been set with {@link #uris(String)}, any URIs set here will be ignored.
     *
     * @param uris Optional. A JSON array of Spotify track or episode URIs to set. Maximum: 100 track or episode URIs.
     * @return A {@link ReplacePlaylistsItemsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder uris(final JsonArray uris) {
      assert (uris != null);
      assert (!uris.isJsonNull());
      assert (uris.size() <= 100);
      return setBodyParameter("uris", uris);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link ReplacePlaylistsItemsRequest}.
     */
    @Override
    public ReplacePlaylistsItemsRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/playlists/{playlist_id}/tracks");
      return new ReplacePlaylistsItemsRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
