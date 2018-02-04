package com.wrapper.spotify.requests.data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a list of Spotify playlists tagged with a particular category.
 */
public class GetCategorysPlaylistsRequest extends AbstractDataRequest {

  /**
   * The private {@link GetCategorysPlaylistsRequest} constructor.
   *
   * @param builder A {@link GetCategorysPlaylistsRequest.Builder}.
   */
  private GetCategorysPlaylistsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get multiple {@link PlaylistSimplified} objects.
   *
   * @return A {@link PlaylistSimplified} paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Paging<PlaylistSimplified> execute() throws
          IOException,
          SpotifyWebApiException {
    return new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson(), "playlists");
  }

  /**
   * Builder class for building a {@link GetCategorysPlaylistsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetCategorysPlaylistsRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The categroy ID setter.
     *
     * @param category_id The Spotify category ID for the category.
     * @return A {@link GetCategorysPlaylistsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder category_id(final String category_id) {
      assert (category_id != null);
      assert (category_id.matches("^[a-z]+$"));
      return setPathParameter("category_id", category_id);
    }

    /**
     * The country code setter.
     *
     * @param country Optional. A country: an ISO 3166-1 alpha-2 country code.
     * @return A {@link GetCategorysPlaylistsRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder country(final CountryCode country) {
      assert (country != null);
      return setQueryParameter("country", country);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetCategorysPlaylistsRequest.Builder}.
     */
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first item to return. Default: 0 (the first object). Use with
     *               {@link #limit(Integer)} to get the next set of items.
     * @return A {@link GetCategorysPlaylistsRequest.Builder}.
     */
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetCategorysPlaylistsRequest}.
     */
    @Override
    public GetCategorysPlaylistsRequest build() {
      setPath("/v1/browse/categories/{category_id}/playlists");
      return new GetCategorysPlaylistsRequest(this);
    }
  }
}