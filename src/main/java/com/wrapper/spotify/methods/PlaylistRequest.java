package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Playlist;

import java.io.IOException;

public class PlaylistRequest extends AbstractRequest {

  public PlaylistRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<Playlist> getAsync() {
    SettableFuture<Playlist> playlistFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      playlistFuture.set(JsonUtil.createPlaylist(jsonObject));
    } catch (Exception e) {
      playlistFuture.setException(e);
    }

    return playlistFuture;
  }

  public Playlist get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    return JsonUtil.createPlaylist(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder fields(String fields) {
      assert (fields != null);
      return parameter("fields", fields);
    }

    public PlaylistRequest build() {

      return new PlaylistRequest(this);
    }

  }

}