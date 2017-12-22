package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.SnapshotResult;

import java.io.IOException;

public class RemoveTrackFromPlaylistRequest extends AbstractRequest {

  public RemoveTrackFromPlaylistRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<SnapshotResult> deleteAsync() {
    final SettableFuture<SnapshotResult> removeTrackFuture = SettableFuture.create();

    try {
      final String jsonString = deleteJson();
      removeTrackFuture.set(JsonUtil.createSnapshotResponse(jsonString));
    } catch (Exception e) {
      removeTrackFuture.setException(e);
    }

    return removeTrackFuture;
  }

  public SnapshotResult delete() throws IOException, WebApiException {
    final String jsonString = deleteJson();
    return JsonUtil.createSnapshotResponse(jsonString);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public RemoveTrackFromPlaylistRequest build() {
      header("Content-Type", "application/json");
      return new RemoveTrackFromPlaylistRequest(this);
    }

  }

}