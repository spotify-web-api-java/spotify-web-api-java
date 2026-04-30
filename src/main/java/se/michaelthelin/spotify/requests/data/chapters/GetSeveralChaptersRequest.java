package se.michaelthelin.spotify.requests.data.chapters;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Chapter;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for several chapters identified by their Spotify IDs. Chapters are only available
 * within the US, UK, Ireland, New Zealand and Australia markets.
 *
 * @deprecated This endpoint has been deprecated by Spotify.
 */
@Deprecated
@JsonDeserialize(builder = GetSeveralChaptersRequest.Builder.class)
public class GetSeveralChaptersRequest extends AbstractDataRequest<Chapter[]> {

  /**
   * The private {@link GetSeveralChaptersRequest} constructor.
   *
   * @param builder A {@link GetSeveralChaptersRequest.Builder}.
   */
  private GetSeveralChaptersRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get several {@link Chapter} objects.
   *
   * @return Multiple {@link Chapter} objects.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Chapter[] execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Chapter.JsonUtil().createModelObjectArray(getJson(), "chapters");
  }

  /**
   * Builder class for building a {@link GetSeveralChaptersRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Chapter[], Builder> {

    /**
     * Create a new {@link GetSeveralChaptersRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The chapter IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the chapters. Maximum: 50 IDs.
     * @return A {@link GetSeveralChaptersRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code.
     * @return A {@link GetSeveralChaptersRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetSeveralChaptersRequest}.
     */
    @Override
    public GetSeveralChaptersRequest build() {
      setPath("/v1/chapters");
      return new GetSeveralChaptersRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
