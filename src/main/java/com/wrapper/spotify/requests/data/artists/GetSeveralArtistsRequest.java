package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for several artists based on their Spotify IDs.
 */
public class GetSeveralArtistsRequest extends AbstractDataRequest {

  /**
   * The private {@link GetSeveralArtistsRequest} constructor.
   *
   * @param builder A {@link GetSeveralArtistsRequest.Builder}.
   */
  private GetSeveralArtistsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get several {@link Artist} objects.
   *
   * @return An array with {@link Artist} objects.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Artist[] execute() throws
          IOException,
          SpotifyWebApiException {
    return new Artist.JsonUtil().createModelObjectArray(getJson(), "artists");
  }

  /**
   * Builder class for building a {@link GetSeveralArtistsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetSeveralArtistsRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The artist IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the artists. Maximum: 50 IDs.
     * @return A {@link GetSeveralArtistsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetSeveralArtistsRequest}.
     */
    @Override
    public GetSeveralArtistsRequest build() {
      setPath("/v1/artists");
      return new GetSeveralArtistsRequest(this);
    }
  }
}
