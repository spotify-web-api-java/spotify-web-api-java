package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import org.apache.http.entity.ContentType;

import java.io.IOException;

/**
 * Create a playlist for a Spotify user. (The playlist will be empty until you add tracks
 * with an {@link AddTracksToPlaylistRequest}.)
 */
public class CreatePlaylistRequest extends AbstractDataRequest {

  /**
   * The private {@link CreatePlaylistRequest} constructor.
   *
   * @param builder A {@link CreatePlaylistRequest.Builder}.
   */
  private CreatePlaylistRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Create a new playlist.
   *
   * @return The newly created {@link Playlist}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Playlist execute() throws
          IOException,
          SpotifyWebApiException {
    return new Playlist.JsonUtil().createModelObject(postJson());
  }

  /**
   * Builder class for building a {@link CreatePlaylistRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link CreatePlaylistRequest.Builder}.
     * <p>
     * Creating a public playlists requires authorization of the {@code playlist-modify-public}
     * scope; Creating a private playlist requires the {@code playlist-modify-private} scope.
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
     * @param user_id The user's Spotify user ID.
     * @return A {@link CreatePlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    /**
     * The playlist name setter.
     *
     * @param name Optional. The name for the playlist. This name does not need
     *             to be unique; a user may have several playlists with the same name.
     * @return A {@link CreatePlaylistRequest.Builder}.
     */
    public Builder name(final String name) {
      assert (name != null);
      assert (!name.equals(""));
      return setBodyParameter("name", name);
    }

    /**
     * The public status setter.
     * <p>
     * <b>Note:</b> To be able to create private playlists, the user must have
     * granted the {@code playlist-modify-private} scope.
     *
     * @param public_ Optional. If {@code true} the playlist will be public, if {@code false} it will be private.
     * @return A {@link CreatePlaylistRequest.Builder}.
     */
    public Builder public_(final Boolean public_) {
      return setBodyParameter("public", public_);
    }

    /**
     * The collaborative state setter.
     *
     * @param collaborative Optional, default {@code false}. If {@code true} the playlist will be collaborative.
     *                      <b>Note:</b> To create a collaborative playlist you must also set {@link #public_(Boolean)}
     *                      to {@code false}. To create collaborative playlists you must have granted
     *                      {@code playlist-modify-private} and {@code playlist-modify-public} scopes.
     * @return A {@link CreatePlaylistRequest.Builder}.
     */
    public Builder collaborative(final Boolean collaborative) {
      return setBodyParameter("collaborative", collaborative);
    }

    /**
     * The playlist description setter.
     *
     * @param description Optional, value for playlist description as displayed in Spotify Clients and in the Web API.
     * @return A {@link CreatePlaylistRequest.Builder}.
     */
    public Builder description(final String description) {
      assert (description != null);
      assert (!description.equals(""));
      return setBodyParameter("description", description);
    }

    /**
     * the request build method.
     *
     * @return A custom {@link CreatePlaylistRequest}.
     */
    @Override
    public CreatePlaylistRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/users/{user_id}/playlists");
      return new CreatePlaylistRequest(this);
    }
  }
}
