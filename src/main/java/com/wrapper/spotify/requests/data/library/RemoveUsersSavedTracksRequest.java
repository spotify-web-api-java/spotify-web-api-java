package com.wrapper.spotify.requests.data.library;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class RemoveUsersSavedTracksRequest extends AbstractRequest {

  private RemoveUsersSavedTracksRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public String delete() throws
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
    return deleteJson();
  }

  public SettableFuture<String> deleteAsync() throws
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
    return executeAsync(deleteJson());
  }

  public static class Builder extends AbstractRequest.Builder<Builder> {

    public Builder ids(final String... trackIds) {
      String idsParameter = Joiner.on(",").join(trackIds);
      return setQueryParameter("ids", idsParameter);
    }

    @Override
    public RemoveUsersSavedTracksRequest build() {
      return new RemoveUsersSavedTracksRequest(this);
    }
  }

}
