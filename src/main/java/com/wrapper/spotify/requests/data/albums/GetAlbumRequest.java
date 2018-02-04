package com.wrapper.spotify.requests.data.albums;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for a single album.
 */
public class GetAlbumRequest extends AbstractDataRequest {

  /**
   * The private {@link GetAlbumRequest} constructor.
   *
   * @param builder A {@link GetAlbumRequest.Builder}.
   */
  private GetAlbumRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get an {@link Album} synchronously.
   *
   * @return An {@link Album}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Album execute() throws
          IOException,
          SpotifyWebApiException {
    return new Album.JsonUtil().createModelObject(getJson());
  }


  /**
   * A builder class for a {@link GetAlbumRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetAlbumRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The ID path parameter setter.
     *
     * @param id The Spotify ID for the album.
     * @return A {@link GetAlbumRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply
     *               Track Relinking.
     * @return A {@link GetAlbumRequest.Builder}.
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
     * @return A custom {@link GetAlbumRequest}.
     */
    @Override
    public GetAlbumRequest build() {
      setPath("/v1/albums/{id}");
      return new GetAlbumRequest(this);
    }
  }
}
