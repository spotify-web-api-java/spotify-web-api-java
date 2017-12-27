package com.wrapper.spotify.requests.data.follow;

import com.google.common.base.Joiner;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.ModelObjectType;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class FollowArtistsOrUsersRequest extends AbstractRequest {

  private FollowArtistsOrUsersRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public void put() throws
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
    putJson();
  }

  public void putAsync() throws
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
    executeAsync(putJson());
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder type(final ModelObjectType type) {
      assert (type != null);
      return setFormParameter("type", type);
    }

    public Builder ids(final String... ids) {
      assert (ids != null);
      return setFormParameter("ids", Joiner.on(",").join(ids));
    }

    @Override
    public FollowArtistsOrUsersRequest build() {
      setPath("/v1/me/following");
      return new FollowArtistsOrUsersRequest(this);
    }
  }
}
