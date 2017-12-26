package com.wrapper.spotify.requests.data.follow;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Artist;
import com.wrapper.spotify.model_objects.ModelObjectType;
import com.wrapper.spotify.model_objects.PagingCursorbased;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetUsersFollowedArtistsRequest extends AbstractRequest {

  private GetUsersFollowedArtistsRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public PagingCursorbased<Artist> get() throws
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
    return new Artist.JsonUtil().createModelObjectPagingCursorbased(getJson());
  }

  public SettableFuture<PagingCursorbased<Artist>> getAsync() throws
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
    return getAsync(new Artist.JsonUtil().createModelObjectPagingCursorbased(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder type(final ModelObjectType type) {
      assert (type != null);
      return setParameter("type", type.toString());
    }

    public Builder limit(final Integer limit) {
      assert (limit != null);
      return setParameter("type", limit);
    }

    public Builder after(final String after) {
      assert (after != null);
      return setParameter("type", after);
    }

    @Override
    public GetUsersFollowedArtistsRequest build() {
      setPath("/v1/me/following?type=artist");
      return new GetUsersFollowedArtistsRequest(this);
    }
  }
}
