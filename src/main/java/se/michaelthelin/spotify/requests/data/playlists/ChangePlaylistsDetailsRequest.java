package se.michaelthelin.spotify.requests.data.playlists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Change a playlist’s name and public/private state. (The user must, of course, own the playlist.)
 */
@JsonDeserialize(builder = ChangePlaylistsDetailsRequest.Builder.class)
public class ChangePlaylistsDetailsRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link ChangePlaylistsDetailsRequest} constructor.
   *
   * @param builder A {@link ChangePlaylistsDetailsRequest.Builder}.
   */
  private ChangePlaylistsDetailsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Change a playlist's details.
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
   * Builder class for building a {@link ChangePlaylistsDetailsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link ChangePlaylistsDetailsRequest.Builder}.
     * <p>
     * Modifying an user's public playlists requires authorization of the {@code playlist-modify-public}
     * scope; Modifying an user's private playlist (including collaborative playlists) requires the
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
     * @return A {@link ChangePlaylistsDetailsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The playlist name setter.
     *
     * @param name Optional. The new name for the playlist.
     * @return A {@link ChangePlaylistsDetailsRequest.Builder}.
     */
    public Builder name(final String name) {
      assert (name != null);
      assert (!name.equals(""));
      return setBodyParameter("name", name);
    }

    /**
     * The public status setter.
     *
     * @param public_ Optional. If {@code true} the playlist will be public, if {@code false} it will be private.
     * @return A {@link ChangePlaylistsDetailsRequest.Builder}.
     */
    public Builder public_(final Boolean public_) {
      return setBodyParameter("public", public_);
    }

    /**
     * The collaborative state setter.
     *
     * @param collaborative Optional. If {@code true}, the playlist will become collaborative and other users will be
     *                      able to modify the playlist in their Spotify client. <b>Note:</b> You can only set
     *                      this to {@code true} on non-public playlists.
     * @return A {@link ChangePlaylistsDetailsRequest.Builder}.
     */
    public Builder collaborative(final Boolean collaborative) {
      return setBodyParameter("collaborative", collaborative);
    }

    /**
     * The playlist description setter.
     *
     * @param description Optional, value for playlist description as displayed in Spotify Clients and in the Web API.
     * @return A {@link ChangePlaylistsDetailsRequest.Builder}.
     */
    public Builder description(final String description) {
      assert (description != null);
      assert (!description.equals(""));
      return setBodyParameter("description", description);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link ChangePlaylistsDetailsRequest}.
     */
    @Override
    public ChangePlaylistsDetailsRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/playlists/{playlist_id}");
      return new ChangePlaylistsDetailsRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
