package com.wrapper.spotify.requests.data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a list of new album releases featured in Spotify (shown, for example, on a Spotify player’s "Browse" tab).
 */
public class GetListOfNewReleasesRequest extends AbstractDataRequest {

  /**
   * The private {@link GetListOfNewReleasesRequest} constructor.
   *
   * @param builder A {@link GetListOfNewReleasesRequest.Builder}.
   */
  private GetListOfNewReleasesRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a paging of new {@link AlbumSimplified} releases.
   *
   * @return An {@link AlbumSimplified} paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Paging<AlbumSimplified> execute() throws
          IOException,
          SpotifyWebApiException {
    return new AlbumSimplified.JsonUtil().createModelObjectPaging(getJson(), "albums");
  }

  /**
   * Builder class for building a {@link GetListOfNewReleasesRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetListOfNewReleasesRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The country code setter.
     *
     * @param country Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want the
     *                list of returned items to be relevant to a particular country. If omitted, the returned items will
     *                be relevant to all countries.
     * @return A {@link GetListOfNewReleasesRequest.Builder}.
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
     * @return A {@link GetListOfNewReleasesRequest.Builder}.
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
     * @return A {@link GetListOfNewReleasesRequest.Builder}.
     */
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetListOfNewReleasesRequest}.
     */
    @Override
    public GetListOfNewReleasesRequest build() {
      setPath("/v1/browse/new-releases");
      return new GetListOfNewReleasesRequest(this);
    }
  }
}