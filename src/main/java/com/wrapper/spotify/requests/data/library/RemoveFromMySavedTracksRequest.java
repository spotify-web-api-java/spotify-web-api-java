package com.wrapper.spotify.requests.data.library;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class RemoveFromMySavedTracksRequest extends AbstractRequest {

  private RemoveFromMySavedTracksRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public String get() throws
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

  public SettableFuture<String> getAsync() throws
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
    return getAsync(deleteJson());
  }

  public static class Builder extends AbstractRequest.Builder<Builder> {

    public Builder tracks(final String[] trackIds) {
      String idsParameter = Joiner.on(",").join(trackIds);
      return setParameter("ids", idsParameter);
    }

    @Override
    public RemoveFromMySavedTracksRequest build() {
      return new RemoveFromMySavedTracksRequest(this);
    }
  }

}
