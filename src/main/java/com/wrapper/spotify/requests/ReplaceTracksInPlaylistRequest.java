package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;

import java.io.IOException;

public class ReplaceTracksInPlaylistRequest extends AbstractRequest {

  private ReplaceTracksInPlaylistRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Boolean get() throws
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
    return Boolean.valueOf(getJson());
  }

  public SettableFuture<Boolean> getAsync() throws
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
    return getAsync(Boolean.valueOf(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    @Override
    public ReplaceTracksInPlaylistRequest build() {
      setHeaderParameter("Content-Type", "application/json");
      return new ReplaceTracksInPlaylistRequest(this);
    }

  }
}
