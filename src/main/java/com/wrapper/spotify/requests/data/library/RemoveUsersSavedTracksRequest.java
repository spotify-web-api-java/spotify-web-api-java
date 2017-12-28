package com.wrapper.spotify.requests.data.library;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class RemoveUsersSavedTracksRequest extends AbstractDataRequest {

  private RemoveUsersSavedTracksRequest(final Builder builder) {
    super(builder);
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

  public static class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }


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
