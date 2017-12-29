package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class ChangePlaylistsDetailsRequest extends AbstractDataRequest {

  private ChangePlaylistsDetailsRequest(final Builder builder) {
    super(builder);
  }

  public String put() throws
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
    return putJson();
  }

  public SettableFuture<String> putAsync() throws
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
    return executeAsync(putJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder name(final String name) {
      assert (name != null);
      setBodyParameter("name", name);
      return this;
    }

    public Builder publicAccess(final boolean publicAccess) {
      setBodyParameter("public", publicAccess);
      return this;
    }

    @Override
    public ChangePlaylistsDetailsRequest build() {
      return new ChangePlaylistsDetailsRequest(this);
    }

  }

}
