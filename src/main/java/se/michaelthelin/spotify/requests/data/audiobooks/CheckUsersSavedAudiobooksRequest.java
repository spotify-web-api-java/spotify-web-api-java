package se.michaelthelin.spotify.requests.data.audiobooks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Check if one or more audiobooks are already saved in the current Spotify user's library.
 *
 * @deprecated Use {@link CheckLibraryContainsRequest} instead.
 */
@Deprecated
@JsonDeserialize(builder = CheckUsersSavedAudiobooksRequest.Builder.class)
public class CheckUsersSavedAudiobooksRequest extends AbstractDataRequest<Boolean[]> {

  /**
   * The private {@link CheckUsersSavedAudiobooksRequest} constructor.
   *
   * @param builder A {@link CheckUsersSavedAudiobooksRequest.Builder}.
   */
  private CheckUsersSavedAudiobooksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Check whether audiobooks are present in the current user's library.
   *
   * @return Whether audiobooks are present in the current user's library.
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
   * Builder class for building a {@link CheckUsersSavedAudiobooksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Boolean[], Builder> {

    /**
     * Create a new {@link CheckUsersSavedAudiobooksRequest.Builder} instance.
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
     * The audiobook IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the audiobooks. Maximum: 50 IDs.
     * @return A {@link CheckUsersSavedAudiobooksRequest.Builder}.
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
     * @return A custom {@link CheckUsersSavedAudiobooksRequest}.
     */
    @Override
    public CheckUsersSavedAudiobooksRequest build() {
      setPath("/v1/me/audiobooks/contains");
      return new CheckUsersSavedAudiobooksRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
