package com.wrapper.spotify.requests.data.artists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about an artist’s albums. Optional parameters can be specified in the query string to
 * filter and sort the response.
 */
public class GetArtistsAlbumsRequest extends AbstractDataRequest {

  /**
   * The private {@link GetArtistsAlbumsRequest} constructor.
   *
   * @param builder A {@link GetArtistsAlbumsRequest.Builder}.
   */
  private GetArtistsAlbumsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the {@link AlbumSimplified} objects.
   *
   * @return An {@link AlbumSimplified} paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Paging<AlbumSimplified> execute() throws
          IOException,
          SpotifyWebApiException {
    return new AlbumSimplified.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetArtistsAlbumsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetArtistsAlbumsRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The artist ID setter.
     *
     * @param id The Spotify ID for the artist.
     * @return A {@link GetArtistsAlbumsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    /**
     * The album type filter setter.
     *
     * @param album_type Optional. A comma-separated list of keywords that will be used to filter the response. If not
     *                   supplied, all album types will be returned. Valid values are: {@code album}, {@code single},
     *                   {@code appears_on} and {@code compilation}.
     * @return A {@link GetArtistsAlbumsRequest.Builder}.
     */
    public Builder album_type(final String album_type) {
      assert (album_type != null);
      assert (album_type.matches("((^|,)(single|album|appears_on|compilation))+$"));
      return setQueryParameter("album_type", album_type);
    }

    /**
     * The market filter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Supply this parameter to limit the response to one
     *               particular geographical market.
     * @return A {@link GetArtistsAlbumsRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The number of album objects to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetArtistsAlbumsRequest.Builder}.
     */
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first album to return. Default: 0 (i.e., the first album). Use with
     *               {@link #limit(Integer)} to get the next set of albums.
     * @return A {@link GetArtistsAlbumsRequest.Builder}.
     */
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetArtistsAlbumsRequest}.
     */
    @Override
    public GetArtistsAlbumsRequest build() {
      setPath("/v1/artists/{id}/albums");
      return new GetArtistsAlbumsRequest(this);
    }
  }
}
