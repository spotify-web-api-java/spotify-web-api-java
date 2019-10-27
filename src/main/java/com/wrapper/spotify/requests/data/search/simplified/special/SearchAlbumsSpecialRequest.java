package com.wrapper.spotify.requests.data.search.simplified.special;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.AlbumSimplifiedSpecial;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.AbstractDataPagingRequest;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about albums that match a keyword string.
 *
 * This class exists because it includes the property {@code totalTracks}, which is not documented in the official
 * specification, although the albums object as returned by the searches API includes it.
 */
@JsonDeserialize(builder = SearchAlbumsSpecialRequest.Builder.class)
public class SearchAlbumsSpecialRequest extends AbstractDataRequest<Paging<AlbumSimplifiedSpecial>> {

  /**
   * The private {@link SearchAlbumsSpecialRequest} constructor.
   *
   * @param builder A {@link Builder}.
   */
  private SearchAlbumsSpecialRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Search for albums.
   *
   * @return An {@link AlbumSimplifiedSpecial} paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Paging<AlbumSimplifiedSpecial> execute() throws
          IOException,
          SpotifyWebApiException {
    return new AlbumSimplifiedSpecial.JsonUtil().createModelObjectPaging(getJson(), "albums");
  }

  /**
   * Builder class for building a {@link SearchAlbumsSpecialRequest}.
   */
  public static final class Builder extends AbstractDataPagingRequest.Builder<AlbumSimplifiedSpecial, Builder> {

    /**
     * Create a new {@link Builder}.
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
     * @return A {@link Builder}.
     * @see <a href="https://developer.spotify.com/web-api/search-item/#tablepress-47">Spotify: Search Query Options</a>
     */
    public Builder q(final String q) {
      assert (q != null);
      assert (!q.equals(""));
      return setQueryParameter("q", q);
    }

    /**
     * The market country code setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. If a country code is given, only artists,
     *               albums, and tracks with content playable in that market will be returned. (Playlist
     *               results are not affected by the market parameter.)
     * @return A {@link Builder}.
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
     * @return A {@link Builder}.
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
     * @return A {@link Builder}.
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
     * @return A {@link Builder}.
     */
    @Override
    public SearchAlbumsSpecialRequest build() {
      setPath("/v1/search");
      setQueryParameter("type", "album");
      return new SearchAlbumsSpecialRequest(this);
    }
  }
}
