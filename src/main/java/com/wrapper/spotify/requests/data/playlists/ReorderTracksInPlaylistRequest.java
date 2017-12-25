package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.SnapshotResult;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class ReorderTracksInPlaylistRequest extends AbstractRequest {

  private ReorderTracksInPlaylistRequest(final Builder builder) {
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
    return getAsync(new SnapshotResult.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {
    private JsonObject jsonBody;

    public Builder rangeStart(int rangeStart) {
      if (jsonBody == null) {
        jsonBody = new JsonObject();
      }

      jsonBody.addProperty("range_start", rangeStart);
      return setBodyParameter(jsonBody);
    }

    public Builder insertBefore(int insertBefore) {
      if (jsonBody == null) {
        jsonBody = new JsonObject();
      }

      jsonBody.addProperty("insert_before", insertBefore);
      return setBodyParameter(jsonBody);
    }

    public Builder rangeLength(int rangeLength) {
      if (jsonBody == null) {
        jsonBody = new JsonObject();
      }

      jsonBody.addProperty("range_length", rangeLength);
      return setBodyParameter(jsonBody);
    }

    public Builder snapshotId(String snapshotId) {
      if (jsonBody == null) {
        jsonBody = new JsonObject();
      }
      jsonBody.addProperty("snapshot_id", String.valueOf(snapshotId));
      return setBodyParameter(jsonBody);
    }

    @Override
    public ReorderTracksInPlaylistRequest build() {
      setHeaderParameter("Content-Type", "application/json");
      return new ReorderTracksInPlaylistRequest(this);
    }

  }
}