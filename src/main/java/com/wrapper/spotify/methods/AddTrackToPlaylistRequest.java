package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.SnapshotResult;

import java.io.IOException;

public class AddTrackToPlaylistRequest extends AbstractRequest {

  public AddTrackToPlaylistRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<SnapshotResult> getAsync() {
    final SettableFuture<SnapshotResult> addTrackFuture = SettableFuture.create();

    try {
      final String jsonString = postJson();
      addTrackFuture.set(JsonUtil.createSnapshotResponse(jsonString));
    } catch (Exception e) {
      addTrackFuture.setException(e);
    }

    return addTrackFuture;
  }

  public SnapshotResult get() throws IOException, WebApiException {
    final String jsonString = postJson();
    return JsonUtil.createSnapshotResponse(jsonString);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder position(int position) {
      assert (position >= 0);

      return parameter("position", String.valueOf(position));
    }

    public AddTrackToPlaylistRequest build() {
      header("Content-Type", "application/json");
      return new AddTrackToPlaylistRequest(this);
    }

  }

}