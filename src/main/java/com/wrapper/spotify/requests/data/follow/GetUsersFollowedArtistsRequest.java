package com.wrapper.spotify.requests.data.follow;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Artist;
import com.wrapper.spotify.model_objects.ModelObjectType;
import com.wrapper.spotify.model_objects.PagingCursorbased;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetUsersFollowedArtistsRequest extends AbstractDataRequest {

  private GetUsersFollowedArtistsRequest(final Builder builder) {
    super(builder);
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
    return executeAsync(new Artist.JsonUtil().createModelObjectPagingCursorbased(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }


    public Builder type(final ModelObjectType type) {
      assert (type != null);
      return setFormParameter("type", type);
    }

    public Builder limit(final Integer limit) {
      assert (limit != null);
      return setFormParameter("type", limit);
    }

    public Builder after(final String after) {
      assert (after != null);
      return setFormParameter("type", after);
    }

    @Override
    public GetUsersFollowedArtistsRequest build() {
      setPath("/v1/me/following?type=artist");
      return new GetUsersFollowedArtistsRequest(this);
    }
  }
}
