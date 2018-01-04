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

  private GetSeveralAlbumsRequest(final Builder builder) {
    super(builder);
  }

  public Album[] execute() throws
          IOException,
          SpotifyWebApiException {
    return new Album.JsonUtil().createModelObjectArray(getJson(), "albums");
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The ids query parameter setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the albums. Maximum: 20 IDs.
     * @return A GetSeveralAlbumsRequest builder.
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
     * @return A GetSeveralAlbumsRequest builder.
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    @Override
    public GetSeveralAlbumsRequest build() {
      setPath("/v1/albums");
      return new GetSeveralAlbumsRequest(this);
    }
  }
}
