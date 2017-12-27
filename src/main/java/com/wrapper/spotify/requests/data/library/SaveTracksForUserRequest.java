package com.wrapper.spotify.requests.data.library;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class SaveTracksForUserRequest extends AbstractRequest {

  private SaveTracksForUserRequest(final Builder builder) {
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

    public Builder ids(final String... trackIds) {
      setQueryParameter("ids", Joiner.on(",").join(trackIds));
      return this;
    }

    @Override
    public SaveTracksForUserRequest build() {
      return new SaveTracksForUserRequest(this);
    }
  }
}
