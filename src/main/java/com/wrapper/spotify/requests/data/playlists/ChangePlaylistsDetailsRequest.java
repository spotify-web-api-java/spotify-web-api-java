package com.wrapper.spotify.requests.data.playlists;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;
import java.util.Map;

public class ChangePlaylistsDetailsRequest extends AbstractRequest {

  private ChangePlaylistsDetailsRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
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

  public static final class Builder extends AbstractRequest.Builder<Builder> {

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
