package com.wrapper.spotify.requests.data.albums;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Album;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for a single album.
 */
public class GetAlbumRequest extends AbstractRequest {

  private GetAlbumRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Album get() throws
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
    return new Album.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<Album> getAsync() throws
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
    return getAsync(new Album.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {
    /**
     * The id path parameter setter.
     *
     * @param id The Spotify ID for the album.
     * @return A GetAlbumRequest builder.
     */
    public Builder id(final String id) {
      assert (id != null);
      return setPathParameter("id", id);
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply Track Relinking.
     * @return A GetAlbumRequest builder.
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setParameter("market", market.toString());
    }

    @Override
    public GetAlbumRequest build() {
      setPath("/v1/albums/{id}");
      return new GetAlbumRequest(this);
    }
  }
}
