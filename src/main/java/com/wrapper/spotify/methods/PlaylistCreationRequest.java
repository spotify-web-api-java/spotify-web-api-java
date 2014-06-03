package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Playlist;
import net.sf.json.JSONObject;

import java.io.IOException;

public class PlaylistCreationRequest extends AbstractRequest {

  public PlaylistCreationRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<Playlist> getAsync() {
    SettableFuture<Playlist> playlistFuture = SettableFuture.create();

    try {
      final JSONObject jsonObject = JSONObject.fromObject(postJson());

      JsonUtil.throwIfErrorsInResponse(jsonObject);

      playlistFuture.set(JsonUtil.createPlaylist(jsonObject));
    } catch (Exception e) {
      playlistFuture.setException(e);
    }

    return playlistFuture;
  }

  public Playlist get() throws IOException, WebApiException {
    final JSONObject jsonObject = JSONObject.fromObject(postJson());

    JsonUtil.throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createPlaylist(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder publicAccess(boolean publicAccess) {
      return body("public", String.valueOf(publicAccess));
    }

    public PlaylistCreationRequest build() {
      header("Content-Type", "application/json");
      return new PlaylistCreationRequest(this);
    }

  }

}
