package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.SnapshotResult;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class AddTracksToPlaylistRequest extends AbstractDataRequest {

  private AddTracksToPlaylistRequest(final Builder builder) {
    super(builder);
  }

  public SnapshotResult post() throws
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
    return new SnapshotResult.JsonUtil().createModelObject(postJson());
  }

  public SettableFuture<SnapshotResult> postAsync() throws
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
    return executeAsync(new SnapshotResult.JsonUtil().createModelObject(postJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder position(final Integer position) {
      assert (position >= 0);
      return setFormParameter("position", position);
    }

    @Override
    public AddTracksToPlaylistRequest build() {
      setHeader("Content-Type", "application/json");
      return new AddTracksToPlaylistRequest(this);
    }

  }

}