package com.wrapper.spotify.requests.data.albums;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about an album's tracks. Optional parameters can be used to limit the number of
 * tracks returned.
 */
public class GetAlbumsTracksRequest extends AbstractDataRequest {

  /**
   * The private {@link GetAlbumsTracksRequest} constructor.
   *
   * @param builder A {@link GetAlbumsTracksRequest.Builder}.
   */
  private GetAlbumsTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the tracks from the album.
   *
   * @return A track paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Paging<TrackSimplified> execute() throws
          IOException,
          SpotifyWebApiException {
    return new TrackSimplified.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetAlbumsTracksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetAlbumsTracksRequest.Builder} instance.
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
     * @return A {@link GetAlbumsTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    /**
     * The limit query parameter setter.
     *
     * @param limit Optional. The maximum number of tracks to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetAlbumsTracksRequest.Builder}.
     */
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset query parameter setter.
     *
     * @param offset Optional. The index of the first track to return. Default: 0 (the first object). Use with limit to
     *               get the next set of tracks.
     * @return A {@link GetAlbumsTracksRequest.Builder}.
     */
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply Track
     *               Relinking.
     * @return A {@link GetAlbumsTracksRequest.Builder}.
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
     * @return A custom {@link GetAlbumsTracksRequest}.
     */
    @Override
    public GetAlbumsTracksRequest build() {
      setPath("/v1/albums/{id}/tracks");
      return new GetAlbumsTracksRequest(this);
    }
  }
}
