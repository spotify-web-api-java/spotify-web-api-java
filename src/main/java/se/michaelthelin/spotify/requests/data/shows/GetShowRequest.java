package se.michaelthelin.spotify.requests.data.shows;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Show;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for a single show identified by its unique Spotify ID.
 */
@JsonDeserialize(builder = GetShowRequest.Builder.class)
public class GetShowRequest extends AbstractDataRequest<Show> {

  /**
   * The private {@link GetShowRequest} constructor.
   *
   * @param builder A {@link GetShowRequest.Builder}.
   */
  private GetShowRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a show.
   *
   * @return An {@link Show}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @Override
  public Show execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Show.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetShowRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Show, Builder> {

    /**
     * Create a new {@link GetShowRequest.Builder}.
     * <p>
     * Reading the user’s resume points on episode objects requires the {@code user-read-playback-position} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The show ID setter.
     *
     * @param id The Spotify ID for the show.
     * @return A {@link GetShowRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.isEmpty());
      return setPathParameter("id", id);
    }

    /**
     * The market country code setter.<p>
     * If a country code is specified, only shows and episodes that are available in that market will be returned.
     * If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.
     * <i>Note: If neither market or user country are provided, the content is considered unavailable for the client.</i><p>
     * Users can view the country that is associated with their account in the account settings.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code.
     * @return A {@link GetShowRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetShowRequest}.
     */
    @Override
    public GetShowRequest build() {
      setPath("/v1/shows/{id}");
      return new GetShowRequest(this);
    }

    @Override
    protected GetShowRequest.Builder self() {
      return this;
    }
  }
}
