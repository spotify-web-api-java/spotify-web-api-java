package se.michaelthelin.spotify.requests.data.search.simplified;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.ShowSimplified;
import se.michaelthelin.spotify.requests.data.AbstractDataPagingRequest;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about shows that match a keyword string.
 */
@JsonDeserialize(builder = SearchShowsRequest.Builder.class)
public class SearchShowsRequest extends AbstractDataRequest<Paging<ShowSimplified>> {

  /**
   * The private {@link SearchShowsRequest} constructor.
   *
   * @param builder A {@link SearchShowsRequest.Builder}.
   */
  private SearchShowsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Search for shows.
   *
   * @return An array of {@link ShowSimplified} objects wrapped in a {@link Paging} object.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Paging<ShowSimplified> execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new ShowSimplified.JsonUtil().createModelObjectPaging(getJson(), "shows");
  }

  /**
   * Builder class for building a {@link SearchShowsRequest}.
   */
  public static final class Builder extends AbstractDataPagingRequest.Builder<ShowSimplified, Builder> {

    /**
     * Create a new {@link SearchShowsRequest.Builder}.
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
     * @return A {@link SearchShowsRequest.Builder}.
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
     * @param market Optional. An ISO 3166-1 alpha-2 country code. If a country code is given, only artists,
     *               albums, and tracks with content playable in that market will be returned. (Playlist
     *               results are not affected by the market parameter.)
     * @return A {@link SearchShowsRequest.Builder}.
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
     * @return A {@link SearchShowsRequest.Builder}.
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
     * @return A {@link SearchShowsRequest.Builder}.
     */
    @Override
    public Builder offset(final Integer offset) {
      assert (offset != null);
      assert (0 <= offset && offset <= 100000);
      return setQueryParameter("offset", offset);
    }


    /**
     * The include external setter.
     *
     * @param includeExternal Optional. Possible values: {@code audio}. If {@code audio} is set
     *                        the response will include any relevant audio content that is hosted externally.
     *                        By default external content is filtered out from responses.
     * @return A {@link SearchItemRequest.Builder}.
     */
    public Builder includeExternal(String includeExternal) {
      assert (includeExternal != null);
      assert (includeExternal.matches("audio"));
      return setQueryParameter("include_external", includeExternal);
    }

    /**
     * The request build method.
     *
     * @return A {@link SearchShowsRequest.Builder}.
     */
    @Override
    public SearchShowsRequest build() {
      setPath("/v1/search");
      setQueryParameter("type", "show");
      return new SearchShowsRequest(this);
    }

    @Override
    protected SearchShowsRequest.Builder self() {
      return this;
    }
  }
}
