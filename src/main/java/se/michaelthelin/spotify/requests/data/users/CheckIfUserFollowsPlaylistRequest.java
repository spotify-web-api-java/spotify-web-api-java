package se.michaelthelin.spotify.requests.data.users;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Check to see if one or more Spotify users are following a specified playlist.
 *
 * @deprecated This endpoint has been deprecated by Spotify.
 */
@Deprecated
@JsonDeserialize(builder = CheckIfUserFollowsPlaylistRequest.Builder.class)
public class CheckIfUserFollowsPlaylistRequest extends AbstractDataRequest<Boolean[]> {

  /**
   * The private {@link CheckIfUserFollowsPlaylistRequest} constructor.
   *
   * @param builder A {@link CheckIfUserFollowsPlaylistRequest.Builder}.
   */
  private CheckIfUserFollowsPlaylistRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Check whether a user is following a playlist or not.
   *
   * @return Whether a user is following a playlist or not.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Boolean[] execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Gson().fromJson(JsonParser.parseString(getJson()).getAsJsonArray(), Boolean[].class);
  }

  /**
   * Builder class for building a {@link CheckIfUserFollowsPlaylistRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Boolean[], Builder> {

    /**
     * Create a new {@link CheckIfUserFollowsPlaylistRequest.Builder} instance.
     * <p>
     * Following a playlist can be done publicly or privately. Checking if a user publicly follows a playlist doesn't
     * require any scopes; if the user is publicly following the playlist, this endpoint returns {@code true}.
     * <p>
     * Checking if the user is privately following a playlist is only possible for the current user when that user has
     * granted access to the {@code playlist-read-private scope}.
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
     * @param playlist_id The Spotify ID of the playlist.
     * @return A {@link CheckIfUserFollowsPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.isEmpty());
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The user IDs setter.
     *
     * @param ids Required. A comma-separated list of Spotify User IDs; the ids of the users that you want to check to
     *            see if they follow the playlist. Maximum: 5 ids.
     * @return A {@link CheckIfUserFollowsPlaylistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 5);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link CheckIfUserFollowsPlaylistRequest}.
     */
    @Override
    public CheckIfUserFollowsPlaylistRequest build() {
      setPath("/v1/playlists/{playlist_id}/followers/contains");
      return new CheckIfUserFollowsPlaylistRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
