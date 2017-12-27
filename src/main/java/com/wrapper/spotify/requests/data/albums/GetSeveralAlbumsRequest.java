package com.wrapper.spotify.requests.data.albums;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Album;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for multiple albums identified by their Spotify IDs.
 */
public class GetSeveralAlbumsRequest extends AbstractRequest {

  private GetSeveralAlbumsRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Album[] get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return new Album.JsonUtil().createModelObjectArray(getJson(), "albums");
  }

  public SettableFuture<Album[]> getAsync() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return executeAsync(new Album.JsonUtil().createModelObjectArray(getJson(), "albums"));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {
    /**
     * The ids query parameter setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the albums. Maximum: 20 IDs.
     * @return A GetSeveralAlbumsRequest builder.
     */
    public Builder ids(final String... ids) {
      assert (ids != null);
      return setFormParameter("ids", Joiner.on(",").join(ids));
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply Track Relinking.
     * @return A GetSeveralAlbumsRequest builder.
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setFormParameter("market", market.toString());
    }

    @Override
    public GetSeveralAlbumsRequest build() {
      setPath("/v1/albums");
      return new GetSeveralAlbumsRequest(this);
    }
  }
}
