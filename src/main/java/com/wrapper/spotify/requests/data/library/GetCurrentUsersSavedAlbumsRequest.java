package com.wrapper.spotify.requests.data.library;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.SavedAlbum;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetCurrentUsersSavedAlbumsRequest extends AbstractDataRequest {

  private GetCurrentUsersSavedAlbumsRequest(final Builder builder) {
    super(builder);
  }

  public Paging<SavedAlbum> get() throws
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
    return new SavedAlbum.JsonUtil().createModelObjectPaging(getJson());
  }

  public SettableFuture<Paging<SavedAlbum>> getAsync() throws
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
    return executeAsync(new SavedAlbum.JsonUtil().createModelObjectPaging(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    @Override
    public GetCurrentUsersSavedAlbumsRequest build() {
      setPath("/v1/me/albums");
      return new GetCurrentUsersSavedAlbumsRequest(this);
    }
  }
}
