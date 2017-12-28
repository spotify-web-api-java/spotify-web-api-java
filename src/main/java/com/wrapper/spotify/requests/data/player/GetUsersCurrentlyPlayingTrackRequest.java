package com.wrapper.spotify.requests.data.player;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.CurrentlyPlayingTrack;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetUsersCurrentlyPlayingTrackRequest extends AbstractDataRequest {

  private GetUsersCurrentlyPlayingTrackRequest(final Builder builder) {
    super(builder);
  }

  public CurrentlyPlayingTrack get() throws
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
    return new CurrentlyPlayingTrack.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<CurrentlyPlayingTrack> getAsync() throws
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
    return executeAsync(new CurrentlyPlayingTrack.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }


    @Override
    public GetUsersCurrentlyPlayingTrackRequest build() {
      return new GetUsersCurrentlyPlayingTrackRequest(this);
    }

  }
}
