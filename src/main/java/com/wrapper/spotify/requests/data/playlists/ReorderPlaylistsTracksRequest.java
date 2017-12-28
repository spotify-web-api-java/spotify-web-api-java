package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.SnapshotResult;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class ReorderPlaylistsTracksRequest extends AbstractDataRequest {

  private ReorderPlaylistsTracksRequest(final Builder builder) {
    super(builder);
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
    return new SnapshotResult.JsonUtil().createModelObject(getJson());
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
    return executeAsync(new SnapshotResult.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }


    public Builder rangeStart(Integer rangeStart) {
      return setBodyParameter("range_start", rangeStart);
    }

    public Builder insertBefore(Integer insertBefore) {
      return setBodyParameter("insert_before", insertBefore);
    }

    public Builder rangeLength(Integer rangeLength) {
      return setBodyParameter("range_length", rangeLength);
    }

    public Builder snapshotId(String snapshotId) {
      assert (snapshotId != null);
      return setBodyParameter("snapshot_id", snapshotId);
    }

    @Override
    public ReorderPlaylistsTracksRequest build() {
      return new ReorderPlaylistsTracksRequest(this);
    }

  }
}