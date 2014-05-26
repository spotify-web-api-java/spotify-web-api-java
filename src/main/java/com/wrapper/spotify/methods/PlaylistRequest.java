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

      JsonUtil.throwIfErrorsInResponse(jsonObject);

      playlistFuture.set(JsonUtil.createPlaylist(jsonObject));
    } catch (Exception e) {
      playlistFuture.setException(e);
    }

    return playlistFuture;
  }

  public Playlist get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    JsonUtil.throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createPlaylist(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String username;
    private String playlistId;

    public Builder withOwner(String username) {
      assert (username != null);
      this.username = username;
      return this;
    }

    public Builder withId(String playlistId) {
      assert (playlistId != null);
      this.playlistId = playlistId;
      return this;
    }

    public Builder withFields(String fields) {
      assert (fields != null);
      return parameter("fields", fields);
    }

    public PlaylistRequest build() {
      assert (username != null);
      assert (playlistId != null);

      path("/v1/users/" + username + "/playlists/" + playlistId);
      return new PlaylistRequest(this);
    }

  }

}