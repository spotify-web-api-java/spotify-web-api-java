package com.wrapper.spotify.requests.data.player;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.CurrentlyPlaying;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetUsersCurrentlyPlayingTrackRequest extends AbstractDataRequest {

  private GetUsersCurrentlyPlayingTrackRequest(final Builder builder) {
    super(builder);
  }

  public CurrentlyPlaying get() throws
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
    return new CurrentlyPlaying.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<CurrentlyPlaying> getAsync() throws
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
    return executeAsync(new CurrentlyPlaying.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    @Override
    public GetUsersCurrentlyPlayingTrackRequest build() {
      setPath("/v1/me/player/currently-playing");
      return new GetUsersCurrentlyPlayingTrackRequest(this);
    }
  }
}
