package se.michaelthelin.spotify.requests.data.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Check if one or more episodes is already saved in the current Spotify user's 'Your Episodes' library.
 */
@JsonDeserialize(builder = CheckUsersSavedEpisodesRequest.Builder.class)
public class CheckUsersSavedEpisodesRequest extends AbstractDataRequest<Boolean[]> {

  /**
   * The private {@link CheckUsersSavedEpisodesRequest} constructor.
   *
   * @param builder A {@link CheckUsersSavedEpisodesRequest.Builder}.
   */
  private CheckUsersSavedEpisodesRequest(final CheckUsersSavedEpisodesRequest.Builder builder) {
    super(builder);
  }

  /**
   * Check if one or more episodes is already saved in the current Spotify user's 'Your Episodes' library.
   *
   * @return Whether an episode is present in the current user's "Your Music" library.
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
   * Builder class for building a {@link CheckUsersSavedEpisodesRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Boolean[], CheckUsersSavedEpisodesRequest.Builder> {

    /**
     * Create a new {@link CheckUsersSavedEpisodesRequest.Builder} instance.
     * <p>
     * The {@code user-library-read} scope must have been authorized by the user.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The episode IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the episodes. Maximum: 50 IDs.
     * @return A {@link CheckUsersSavedEpisodesRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public CheckUsersSavedEpisodesRequest.Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link CheckUsersSavedEpisodesRequest}.
     */
    @Override
    public CheckUsersSavedEpisodesRequest build() {
      setPath("/v1/me/episodes/contains");
      return new CheckUsersSavedEpisodesRequest(this);
    }

    @Override
    protected CheckUsersSavedEpisodesRequest.Builder self() {
      return this;
    }
  }
}
