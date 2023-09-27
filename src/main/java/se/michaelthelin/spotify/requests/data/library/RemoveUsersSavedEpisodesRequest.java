package se.michaelthelin.spotify.requests.data.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonArray;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Remove one or more episodes from the current user's library.
 */
@JsonDeserialize(builder = RemoveUsersSavedEpisodesRequest.Builder.class)
public class RemoveUsersSavedEpisodesRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link RemoveUsersSavedEpisodesRequest} constructor.
   *
   * @param builder A {@link RemoveUsersSavedEpisodesRequest.Builder}.
   */
  private RemoveUsersSavedEpisodesRequest(final RemoveUsersSavedEpisodesRequest.Builder builder) {
    super(builder);
  }

  /**
   * Remove one or more episodes from current Spotify user’s library.
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
    return deleteJson();
  }

  /**
   * Builder class for building a {@link RemoveUsersSavedEpisodesRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, RemoveUsersSavedEpisodesRequest.Builder> {

    /**
     * Create a new {@link RemoveUsersSavedEpisodesRequest.Builder} instance.
     * <p>
     * Modification of the current user's "Your Music" collection requires authorization of the
     * {@code user-library-modify} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The episode IDs setter.
     *
     * @param ids Optional. A comma-separated list of Spotify IDs for the episodes to be deleted from the user’s library. Maximum: 50 IDs.
     * @return A {@link RemoveUsersSavedEpisodesRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public RemoveUsersSavedEpisodesRequest.Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The episode IDs setter.
     * <p>
     * <b>Note:</b> If the ids have already been set with {@link #ids(String)}, any ids added here will be ignored.
     *
     * @param ids Optional. A JSON array of Spotify IDs for the episodes to be deleted from the user’s library. Maximum: 50 IDs.
     * @return A {@link RemoveUsersSavedEpisodesRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public RemoveUsersSavedEpisodesRequest.Builder ids(final JsonArray ids) {
      assert (ids != null);
      assert (!ids.isJsonNull());
      assert (ids.size() <= 50);
      return setBodyParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link RemoveUsersSavedEpisodesRequest}.
     */
    @Override
    public RemoveUsersSavedEpisodesRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/episodes");
      return new RemoveUsersSavedEpisodesRequest(this);
    }

    @Override
    protected RemoveUsersSavedEpisodesRequest.Builder self() {
      return this;
    }
  }
}
