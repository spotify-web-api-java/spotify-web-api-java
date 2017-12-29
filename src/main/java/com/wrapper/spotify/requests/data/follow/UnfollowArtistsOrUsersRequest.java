package com.wrapper.spotify.requests.data.follow;

import com.google.common.base.Joiner;
import com.google.gson.JsonArray;
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
      assert (type.getType().equals("artist") || type.getType().equals("user"));
      return setQueryParameter("type", type);
    }

    // TODO: Joiner.on(",").join()
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    public Builder ids(final JsonArray ids) {
      assert (ids != null);
      assert (!ids.isJsonNull());
      return setBodyParameter("ids", ids);
    }

    @Override
    public UnfollowArtistsOrUsersRequest build() {
      setPath("/v1/me/following");
      return new UnfollowArtistsOrUsersRequest(this);
    }
  }
}
