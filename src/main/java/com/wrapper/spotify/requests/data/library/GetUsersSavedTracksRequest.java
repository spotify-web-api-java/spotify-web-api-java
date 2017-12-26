package com.wrapper.spotify.requests.data.library;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.LibraryTrack;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetUsersSavedTracksRequest extends AbstractRequest {

  private GetUsersSavedTracksRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Paging<LibraryTrack> get() throws
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
    return new LibraryTrack.JsonUtil().createModelObjectPaging(getJson());
  }

  public SettableFuture<Paging<LibraryTrack>> getAsync() throws
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
    return getAsync(new LibraryTrack.JsonUtil().createModelObjectPaging(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder accessToken(final String accessToken) {
      return setHeaderParameter("Authorization", "Bearer " + accessToken);
    }

    public Builder limit(final int limit) {
      assert (limit > 0);
      return setParameter("limit", String.valueOf(limit));
    }

    public Builder offset(final int offset) {
      assert (offset >= 0);
      return setParameter("offset", String.valueOf(offset));
    }

    @Override
    public GetUsersSavedTracksRequest build() {
      return new GetUsersSavedTracksRequest(this);
    }
  }

}
