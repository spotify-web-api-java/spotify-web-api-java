package se.michaelthelin.spotify.requests.data.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Check if one or more tracks is already saved in the current Spotify user’s "Your Music" library.
 */
@JsonDeserialize(builder = CheckUsersSavedTracksRequest.Builder.class)
public class CheckUsersSavedTracksRequest extends AbstractDataRequest<Boolean[]> {

  /**
   * The private {@link CheckUsersSavedTracksRequest} constructor.
   *
   * @param builder A {@link CheckUsersSavedTracksRequest.Builder}.
   */
  private CheckUsersSavedTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Check whether an track is present in the current user's "Your Music" library.
   *
   * @return Whether an track is present in the current user's "Your Music" library.
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
   * Builder class for building a {@link CheckUsersSavedTracksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Boolean[], Builder> {

    /**
     * Create a new {@link CheckUsersSavedTracksRequest.Builder} instance.
     * <p>
     * The {@code user-library-read} scope must have been authorized by the user.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The track IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the tracks. Maximum: 50 IDs.
     * @return A {@link CheckUsersSavedTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link CheckUsersSavedTracksRequest}.
     */
    @Override
    public CheckUsersSavedTracksRequest build() {
      setPath("/v1/me/tracks/contains");
      return new CheckUsersSavedTracksRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
