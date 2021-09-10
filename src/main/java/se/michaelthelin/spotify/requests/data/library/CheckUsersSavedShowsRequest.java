package se.michaelthelin.spotify.requests.data.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Check if one or more shows is already saved in the current Spotify user’s library.
 */
@JsonDeserialize(builder = CheckUsersSavedShowsRequest.Builder.class)
public class CheckUsersSavedShowsRequest extends AbstractDataRequest<Boolean[]> {

  /**
   * The private {@link CheckUsersSavedShowsRequest} constructor.
   *
   * @param builder A {@link CheckUsersSavedShowsRequest.Builder}.
   */
  private CheckUsersSavedShowsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Check whether a show is present in the current user's "Your Music" library.
   *
   * @return Whether an show is present in the current user's "Your Music" library.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @Override
  public Boolean[] execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Gson().fromJson(JsonParser.parseString(getJson()).getAsJsonArray(), Boolean[].class);
  }

  /**
   * Builder class for building a {@link CheckUsersSavedShowsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Boolean[], Builder> {
    /**
     * Create a new {@link CheckUsersSavedShowsRequest.Builder} instance.
     * <p>
     * The {@code user-library-read} scope must have been authorized by the user.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The show IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the shows. Maximum: 50 IDs.
     * @return A {@link CheckUsersSavedShowsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public CheckUsersSavedShowsRequest.Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link CheckUsersSavedShowsRequest}.
     */
    @Override
    public CheckUsersSavedShowsRequest build() {
      setPath("/v1/me/shows/contains");
      return new CheckUsersSavedShowsRequest(this);
    }

    @Override
    protected CheckUsersSavedShowsRequest.Builder self() {
      return this;
    }
  }
}
