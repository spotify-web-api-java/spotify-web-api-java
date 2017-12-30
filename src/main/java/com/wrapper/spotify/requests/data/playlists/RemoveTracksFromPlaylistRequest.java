package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonArray;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class RemoveTracksFromPlaylistRequest extends AbstractDataRequest {

  private RemoveTracksFromPlaylistRequest(final Builder builder) {
    super(builder);
  }

  public SnapshotResult delete() throws
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

  public SettableFuture<SnapshotResult> deleteAsync() throws
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

    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    public Builder tracks(final JsonArray tracks) {
      assert (tracks != null);
      assert (!tracks.isJsonNull());
      return setBodyParameter("tracks", tracks);
    }

    public Builder snapshotId(final String snapshotId) {
      assert (snapshotId != null);
      assert (!snapshotId.equals(""));
      return setBodyParameter("snapshot_id", snapshotId);
    }

    @Override
    public RemoveTracksFromPlaylistRequest build() {
      setPath("/v1/users/{user_id}/playlists/{playlist_id}/tracks");
      return new RemoveTracksFromPlaylistRequest(this);
    }
  }
}
