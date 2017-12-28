package com.wrapper.spotify.requests.data.player;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.PlayHistory;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetUsersRecentlyPlayedTracksRequest extends AbstractDataRequest {

  private GetUsersRecentlyPlayedTracksRequest(final Builder builder) {
    super(builder);
  }

  public Paging<PlayHistory> get() throws
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
    return new PlayHistory.JsonUtil().createModelObjectPaging(getJson(), "items");
  }

  public SettableFuture<Paging<PlayHistory>> getAsync() throws
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
    return executeAsync(new PlayHistory.JsonUtil().createModelObjectPaging(getJson(), "items"));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }


    @Override
    public GetUsersRecentlyPlayedTracksRequest build() {
      this.setPath("v1/me/player/recently-played");

      return new GetUsersRecentlyPlayedTracksRequest(this);
    }
  }
}
