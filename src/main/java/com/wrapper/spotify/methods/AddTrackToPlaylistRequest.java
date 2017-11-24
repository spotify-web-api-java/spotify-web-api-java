package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.SnapshotResult;

import java.io.IOException;

public class AddTrackToPlaylistRequest extends AbstractRequest {

  private AddTrackToPlaylistRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SnapshotResult get() throws
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
    return JsonUtil.createSnapshotResult(postJson());
  }

  public SettableFuture<SnapshotResult> getAsync() throws
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
    return getAsync(JsonUtil.createSnapshotResult(postJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder position(final int position) {
      assert (position >= 0);
      return setParameter("position", position);
    }

    @Override
    public AddTrackToPlaylistRequest build() {
      setHeaderParameter("Content-Type", "application/json");
      return new AddTrackToPlaylistRequest(this);
    }

  }

}