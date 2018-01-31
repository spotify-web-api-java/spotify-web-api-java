package com.wrapper.spotify.requests.data.albums;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for multiple albums identified by their Spotify IDs.
 */
public class GetSeveralAlbumsRequest extends AbstractDataRequest {

  /**
   * The private {@link GetSeveralAlbumsRequest} constructor.
   *
   * @param builder A {@link GetSeveralAlbumsRequest.Builder}.
   */
  private GetSeveralAlbumsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get multiple albums.
   *
   * @return An array containing albums.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Album[] execute() throws
          IOException,
          SpotifyWebApiException {
    return new Album.JsonUtil().createModelObjectArray(getJson(), "albums");
  }

  /**
   * Builder class for building a {@link GetSeveralAlbumsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetSeveralAlbumsRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The IDs query parameter setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the albums. Maximum: 20 IDs.
     * @return A {@link GetSeveralAlbumsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 20);
      return setQueryParameter("ids", ids);
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply Track
     *               Relinking.
     * @return A {@link GetSeveralAlbumsRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     * @see <a href="https://developer.spotify.com/web-api/track-relinking-guide/">Spotify: Track Relinking Guide</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetSeveralAlbumsRequest}.
     */
    @Override
    public GetSeveralAlbumsRequest build() {
      setPath("/v1/albums");
      return new GetSeveralAlbumsRequest(this);
    }
  }
}
