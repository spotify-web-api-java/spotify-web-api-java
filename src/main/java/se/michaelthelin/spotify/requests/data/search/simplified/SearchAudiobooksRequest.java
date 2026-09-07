package se.michaelthelin.spotify.requests.data.search.simplified;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.enums.CountryCode;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AudiobookSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataPagingRequest;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about audiobooks that match a keyword string.
 */
public class SearchAudiobooksRequest extends AbstractDataRequest<Paging<AudiobookSimplified>> {

  /**
   * The private {@link SearchAudiobooksRequest} constructor.
   *
   * @param builder A {@link SearchAudiobooksRequest.Builder}.
   */
  private SearchAudiobooksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Search for audiobooks.
   *
   * @return An array of {@link AudiobookSimplified} objects wrapped in a {@link Paging} object.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Paging<AudiobookSimplified> execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new AudiobookSimplified.JsonUtil().createModelObjectPaging(getJson(), "audiobooks");
  }

  /**
   * Builder class for building a {@link SearchAudiobooksRequest}.
   */
  public static final class Builder extends AbstractDataPagingRequest.Builder<AudiobookSimplified, Builder> {

    /**
     * Create a new {@link SearchAudiobooksRequest.Builder}.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The search query setter.
     *
     * @param q Required. The search query's keywords (and optional field filters and operators).
     * @return A {@link SearchAudiobooksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/search">Spotify: Search Query Options</a>
     */
    public Builder q(final String q) {
      assert (q != null);
      assert (!q.isEmpty());
      return setQueryParameter("q", q);
    }

    /**
     * The market country code setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. If a country code is given, only content
     *               playable in that market will be returned.
     * @return A {@link SearchAudiobooksRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of results to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link SearchAudiobooksRequest.Builder}.
     */
    @Override
    public Builder limit(final Integer limit) {
      assert (limit != null);
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first result to return. Default: 0 (i.e., the first result). Maximum
     *               offset: 100.000. Use with {@link #limit(Integer)} to get the next page of search results.
     * @return A {@link SearchAudiobooksRequest.Builder}.
     */
    @Override
    public Builder offset(final Integer offset) {
      assert (offset != null);
      assert (0 <= offset && offset <= 100000);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A {@link SearchAudiobooksRequest.Builder}.
     */
    @Override
    public SearchAudiobooksRequest build() {
      setPath("/v1/search");
      setQueryParameter("type", "audiobook");
      return new SearchAudiobooksRequest(this);
    }

    @Override
    protected SearchAudiobooksRequest.Builder self() {
      return this;
    }
  }
}
