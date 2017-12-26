package com.wrapper.spotify.requests.data.artists;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.AlbumSimplified;
import com.wrapper.spotify.model_objects.AlbumType;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetArtistsAlbumsRequest extends AbstractRequest {

  private GetArtistsAlbumsRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Paging<AlbumSimplified> get() throws
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
    return new AlbumSimplified.JsonUtil().createModelObjectPaging(getJson());
  }

  public SettableFuture<Paging<AlbumSimplified>> getAsync() throws
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
    return getAsync(new AlbumSimplified.JsonUtil().createModelObjectPaging(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(final String id) {
      assert (id != null);
      return setPathParameter("id", id);
    }

    public Builder album_type(final AlbumType... album_type) {
      assert (album_type != null);
      assert (album_type.length > 0);
      return setParameter("album_type", Joiner.on(",").join(album_type));
    }

    public Builder market(final CountryCode market) {
      assert (market != null);
      return setParameter("market", market.toString());
    }

    public Builder limit(final Integer limit) {
      assert (limit > 0);
      return setParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setParameter("offset", offset);
    }

    @Override
    public GetArtistsAlbumsRequest build() {
      setPath("/v1/artists/{id}/albums");
      return new GetArtistsAlbumsRequest(this);
    }

  }

}
