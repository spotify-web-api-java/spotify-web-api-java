package se.michaelthelin.spotify.requests.data.playlists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Create a playlist for a Spotify user. (The playlist will be empty until you add tracks.)
 *
 * @deprecated This endpoint is deprecated. Use {@link CreatePlaylistRequest} with the current user's ID instead.
 */
@Deprecated
@JsonDeserialize(builder = CreatePlaylistForUserRequest.Builder.class)
public class CreatePlaylistForUserRequest extends AbstractDataRequest<Playlist> {

  /**
   * The private {@link CreatePlaylistForUserRequest} constructor.
   *
   * @param builder A {@link CreatePlaylistForUserRequest.Builder}.
   */
  private CreatePlaylistForUserRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Create a new playlist for a user.
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
   * Builder class for building a {@link CreatePlaylistForUserRequest}.
   *
   * @deprecated This endpoint is deprecated. Use {@link CreatePlaylistRequest} instead.
   */
  @Deprecated
  public static final class Builder extends AbstractDataRequest.Builder<Playlist, Builder> {

    /**
     * Create a new {@link CreatePlaylistForUserRequest.Builder}.
     * <p>
     * Creating a public playlist for a user requires authorization of the {@code playlist-modify-public} scope;
     * creating a private playlist requires the {@code playlist-modify-private} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The user ID path parameter setter.
     *
     * @param user_id Required. The user's Spotify user ID.
     * @return A {@link CreatePlaylistForUserRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.isEmpty());
      return setPathParameter("user_id", user_id);
    }

    /**
     * The playlist name setter.
     *
     * @param name Required. The name for the new playlist.
     * @return A {@link CreatePlaylistForUserRequest.Builder}.
     */
    public Builder name(final String name) {
      assert (name != null);
      assert (!name.isEmpty());
      return setBodyParameter("name", name);
    }

    /**
     * The public status setter.
     *
     * @param public_ Optional. Defaults to {@code true}. If {@code true} the playlist will be public.
     * @return A {@link CreatePlaylistForUserRequest.Builder}.
     */
    public Builder public_(final Boolean public_) {
      return setBodyParameter("public", public_);
    }

    /**
     * The collaborative state setter.
     *
     * @param collaborative Optional. Defaults to {@code false}. If {@code true} the playlist will be collaborative.
     * @return A {@link CreatePlaylistForUserRequest.Builder}.
     */
    public Builder collaborative(final Boolean collaborative) {
      return setBodyParameter("collaborative", collaborative);
    }

    /**
     * The playlist description setter.
     *
     * @param description Optional. Value for playlist description as displayed in Spotify Clients and in the Web API.
     * @return A {@link CreatePlaylistForUserRequest.Builder}.
     */
    public Builder description(final String description) {
      assert (description != null);
      assert (!description.isEmpty());
      return setBodyParameter("description", description);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link CreatePlaylistForUserRequest}.
     */
    @Override
    public CreatePlaylistForUserRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/users/{user_id}/playlists");
      return new CreatePlaylistForUserRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
