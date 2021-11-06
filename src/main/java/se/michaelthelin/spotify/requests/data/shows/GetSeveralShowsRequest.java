package se.michaelthelin.spotify.requests.data.shows;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ShowSimplified;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for multiple shows based on their Spotify IDs.
 */
@JsonDeserialize(builder = GetSeveralShowsRequest.Builder.class)
public class GetSeveralShowsRequest extends AbstractDataRequest<ShowSimplified[]> {

  /**
   * The private {@link GetSeveralShowsRequest} constructor.
   *
   * @param builder A {@link GetSeveralShowsRequest.Builder}.
   */
  private GetSeveralShowsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get several shows.
   *
   * @return Multiple {@link ShowSimplified} objects.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @Override
  public ShowSimplified[] execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new ShowSimplified.JsonUtil().createModelObjectArray(getJson(), "shows");
  }

  /**
   * Builder class for building a {@link GetSeveralShowsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<ShowSimplified[], Builder> {

    /**
     * Create a new {@link GetSeveralShowsRequest.Builder}.
     * <p>
     * Reading the user’s resume points on episode objects requires the {@code user-read-playback-position} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The show IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the shows. Maximum: 50 IDs.
     * @return A {@link GetSeveralShowsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public GetSeveralShowsRequest.Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The market country code setter.<p>
     * If a country code is specified, only shows and episodes that are available in that market will be returned.
     * If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.
     * <i>Note: If neither market or user country are provided, the content is considered unavailable for the client.</i><p>
     * Users can view the country that is associated with their account in the account settings.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code.
     * @return A {@link GetSeveralShowsRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public GetSeveralShowsRequest.Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetSeveralShowsRequest}.
     */
    @Override
    public GetSeveralShowsRequest build() {
      setPath("/v1/shows");
      return new GetSeveralShowsRequest(this);
    }

    @Override
    protected GetSeveralShowsRequest.Builder self() {
      return this;
    }
  }
}
