package com.wrapper.spotify.requests.data.player;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.PlayHistory;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetUsersRecentlyPlayedTracksRequest extends AbstractRequest {

  private GetUsersRecentlyPlayedTracksRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
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
    return new PlayHistory.JsonUtil().createModelObjectPaging(new JsonParser().parse(getJson()).getAsJsonObject().get("items").getAsJsonObject());
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
    return getAsync(new PlayHistory.JsonUtil().createModelObjectPaging(new JsonParser().parse(getJson()).getAsJsonObject().get("items").getAsJsonObject()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    @Override
    public GetUsersRecentlyPlayedTracksRequest build() {
      this.setPath("v1/me/player/recently-played");

      return new GetUsersRecentlyPlayedTracksRequest(this);
    }
  }
}
