package se.michaelthelin.spotify.requests.data.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonArray;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Save one or more shows to current Spotify user’s library.
 */
@JsonDeserialize(builder = SaveShowsForCurrentUserRequest.Builder.class)
public class SaveShowsForCurrentUserRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link SaveShowsForCurrentUserRequest} constructor.
   *
   * @param builder A {@link SaveShowsForCurrentUserRequest.Builder}.
   */
  private SaveShowsForCurrentUserRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Save one or more shows.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @Override
  public String execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return putJson();
  }

  /**
   * Builder class for building a {@link SaveShowsForCurrentUserRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link SaveShowsForCurrentUserRequest.Builder} instance.
     * <p>
     * Modification of the current user's "Your Music" collection requires authorization of the
     * {@code user-library-modify} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(String accessToken) {
      super(accessToken);
    }

    /**
     * The show IDs setter.
     *
     * @param ids Optional. A comma-separated list of Spotify IDs for the shows to be added to the user’s library.
     * @return A {@link SaveShowsForCurrentUserRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The show IDs setter.
     * <p>
     * <b>Note:</b> If the ids have already been set with {@link #ids(String)}, any ids added here will be ignored.
     * @param ids Optional. A JSON array of Spotify IDs for the shows to be added to the user’s library.
     * @return A {@link SaveShowsForCurrentUserRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final JsonArray ids) {
      assert (ids != null);
      assert (!ids.isJsonNull());
      assert (ids.size() <= 50);
      return setBodyParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link SaveShowsForCurrentUserRequest}.
     */
    @Override
    public SaveShowsForCurrentUserRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/shows");
      return new SaveShowsForCurrentUserRequest(this);
    }

    @Override
    protected SaveShowsForCurrentUserRequest.Builder self() {
      return this;
    }
  }
}
