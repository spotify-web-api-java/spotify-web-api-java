package se.michaelthelin.spotify.requests.data.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Check if one or more items are already saved in the current user's library.
 * Accepts Spotify URIs for tracks, albums, episodes, shows, audiobooks, artists, users, and playlists.
 */
@JsonDeserialize(builder = CheckUsersSavedItemsRequest.Builder.class)
public class CheckUsersSavedItemsRequest extends AbstractDataRequest<Boolean[]> {

  /**
   * The private {@link CheckUsersSavedItemsRequest} constructor.
   *
   * @param builder A {@link CheckUsersSavedItemsRequest.Builder}.
   */
  private CheckUsersSavedItemsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Check whether items are in the current user's library.
   *
   * @return A {@link Boolean} array.
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
   * Builder class for building a {@link CheckUsersSavedItemsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Boolean[], Builder> {

    /**
     * Create a new {@link CheckUsersSavedItemsRequest.Builder} instance.
     * <p>
     * The {@code user-library-read} or {@code user-follow-read} or {@code playlist-read-private} scope must have
     * been authorized by the user.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The Spotify URIs setter.
     *
     * @param uris Required. A comma-separated list of Spotify URIs to check. Maximum: 40 URIs.
     *             Supported URI types: track, album, episode, show, audiobook, artist, user, playlist.
     * @return A {@link CheckUsersSavedItemsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder uris(final String uris) {
      assert (uris != null);
      assert (uris.split(",").length <= 40);
      return setQueryParameter("uris", uris);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link CheckUsersSavedItemsRequest}.
     */
    @Override
    public CheckUsersSavedItemsRequest build() {
      setPath("/v1/me/library/contains");
      return new CheckUsersSavedItemsRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
