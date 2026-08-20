package se.michaelthelin.spotify.requests.data.playlists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Create a playlist for the current Spotify user. (The playlist will be empty until you add tracks.)
 * Each user is generally limited to a maximum of 11000 playlists.
 */
@JsonDeserialize(builder = CreatePlaylistRequest.Builder.class)
public class CreatePlaylistRequest extends AbstractDataRequest<Playlist> {

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
  public Playlist execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Playlist.JsonUtil().createModelObject(postJson());
  }

  /**
   * Builder class for building a {@link CreatePlaylistRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Playlist, Builder> {

    /**
     * Create a new {@link CreatePlaylistRequest.Builder}.
     * <p>
     * Creating a public playlist for a user requires authorization of the {@code playlist-modify-public}
     * scope; creating a private playlist requires the {@code playlist-modify-private} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The playlist name setter.
     *
     * @param name Required. The name for the new playlist, for example {@code "Your Coolest Playlist"}.
     *             This name does not need to be unique; a user may have several playlists with the same name.
     * @return A {@link CreatePlaylistRequest.Builder}.
     */
    public Builder name(final String name) {
      assert (name != null);
      assert (!name.isEmpty());
      return setBodyParameter("name", name);
    }

    /**
     * The public status setter.
     *
     * @param public_ Optional. Defaults to {@code true}. If {@code true} the playlist will be public, if
     *                {@code false} it will be private. To be able to create private playlists, the user must
     *                have granted the {@code playlist-modify-private} scope.
     * @return A {@link CreatePlaylistRequest.Builder}.
     */
    public Builder public_(final Boolean public_) {
      return setBodyParameter("public", public_);
    }

    /**
     * The collaborative state setter.
     *
     * @param collaborative Optional. Defaults to {@code false}. If {@code true} the playlist will be collaborative.
     *                      <b>Note:</b> To create a collaborative playlist you must also set {@code public} to
     *                      {@code false}. To create collaborative playlists you must have granted
     *                      {@code playlist-modify-private} and {@code playlist-modify-public} scopes.
     * @return A {@link CreatePlaylistRequest.Builder}.
     */
    public Builder collaborative(final Boolean collaborative) {
      return setBodyParameter("collaborative", collaborative);
    }

    /**
     * The playlist description setter.
     *
     * @param description Optional. Value for playlist description as displayed in Spotify Clients and in the Web API.
     * @return A {@link CreatePlaylistRequest.Builder}.
     */
    public Builder description(final String description) {
      assert (description != null);
      assert (!description.isEmpty());
      return setBodyParameter("description", description);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link CreatePlaylistRequest}.
     */
    @Override
    public CreatePlaylistRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/playlists");
      return new CreatePlaylistRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
