package com.wrapper.spotify.requests.data.follow;

import com.google.common.base.Joiner;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.ModelObjectType;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class UnfollowArtistsOrUsersRequest extends AbstractDataRequest {

  private UnfollowArtistsOrUsersRequest(final Builder builder) {
    super(builder);
  }

  public void delete() throws
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
    deleteJson();
  }

  public void deleteAsync() throws
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
    executeAsync(deleteJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }


    public Builder type(final ModelObjectType type) {
      assert (type != null);
      return setFormParameter("type", type);
    }

    public Builder ids(final String... ids) {
      assert (ids != null);
      return setFormParameter("ids", Joiner.on(",").join(ids));
    }

    @Override
    public UnfollowArtistsOrUsersRequest build() {
      setPath("/v1/me/following");
      return new UnfollowArtistsOrUsersRequest(this);
    }
  }
}
