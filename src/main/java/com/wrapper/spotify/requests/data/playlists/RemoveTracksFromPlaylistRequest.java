package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.PlaylistTrackPosition;
import com.wrapper.spotify.model_objects.SnapshotResult;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class RemoveTrackFromPlaylistRequest extends AbstractRequest {

  private RemoveTrackFromPlaylistRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  private static JsonObject getJsonFromPlaylistTrackPosition(PlaylistTrackPosition playlistTrackPosition) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("uri", playlistTrackPosition.getUri());
    if (playlistTrackPosition.getPositions() != null && playlistTrackPosition.getPositions().length != 0) {
      JsonArray positionArray = new JsonArray();
      for (int pos : playlistTrackPosition.getPositions()) {
        positionArray.add(pos);
      }
      jsonObject.add("positions", positionArray);
    }
    return jsonObject;
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

    public Builder tracks(PlaylistTrackPosition[] playlistTrackPositions) {
      if (jsonBody == null) {
        jsonBody = new JsonObject();
      }

      final JsonArray jsonArrayTrackUri = new JsonArray();
      for (PlaylistTrackPosition playlistTrackPosition : playlistTrackPositions) {
        jsonArrayTrackUri.add(getJsonFromPlaylistTrackPosition(playlistTrackPosition));
      }
      jsonBody.add("tracks", jsonArrayTrackUri);
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
    public RemoveTrackFromPlaylistRequest build() {
      return new RemoveTrackFromPlaylistRequest(this);
    }

  }
}
