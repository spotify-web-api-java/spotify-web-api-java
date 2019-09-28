package com.wrapper.spotify.requests.data.search;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SearchResult;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about artists, albums, tracks or playlists that match a keyword string.
 */
@JsonDeserialize(builder = SearchItemRequest.Builder.class)
public class SearchItemRequest extends AbstractDataRequest<SearchResult> {

  /**
   * The private {@link SearchItemRequest} constructor.
   *
   * @param builder A {@link SearchItemRequest.Builder}.
   */
  private SearchItemRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Search for an item.
   *
   * @return A {@link SearchResult}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public SearchResult execute() throws
          IOException,
          SpotifyWebApiException {
    return new SearchResult.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link SearchItemRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<SearchResult, Builder> {

    /**
     * Create a new {@link SearchItemRequest.Builder}.
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
     * @return A {@link SearchItemRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/search-item/#tablepress-47">Spotify: Search Query Options</a>
     */
    public Builder q(final String q) {
      assert (q != null);
      assert (!q.equals(""));
      return setQueryParameter("q", q);
    }

    /**
     * The type setter.
     *
     * @param type Required. A comma-separated list of item types to search across. Valid types are: {@code album},
     *             {@code artist}, {@code playlist}, and {@code track}.
     * @return A {@link SearchItemRequest.Builder}.
     */
    public Builder type(final String type) {
      assert (type != null);
      assert (type.matches("((^|,)(album|artist|playlist|track))+$"));
      return setQueryParameter("type", type);
    }

    /**
     * The market country code setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. If a country code is given, only artists,
     *               albums, and tracks with content playable in that market will be returned. (Playlist
     *               results are not affected by the market parameter.)
     * @return A {@link SearchItemRequest.Builder}.
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
     * @return A {@link SearchItemRequest.Builder}.
     */
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
     * @return A {@link SearchItemRequest.Builder}.
     */
    public Builder offset(final Integer offset) {
      assert (offset != null);
      assert (0 <= offset && offset <= 100000);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A {@link SearchItemRequest.Builder}.
     */
    @Override
    public SearchItemRequest build() {
      setPath("/v1/search");
      return new SearchItemRequest(this);
    }
  }
}
