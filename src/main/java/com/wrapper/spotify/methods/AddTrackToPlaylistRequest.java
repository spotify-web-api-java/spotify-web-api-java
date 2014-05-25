package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Playlist;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class AddTrackToPlaylistRequest extends AbstractRequest {

  public AddTrackToPlaylistRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<String> getAsync() {
    final SettableFuture<String> addTrackFuture = SettableFuture.create();

    try {
      final String jsonString = postJson();
      if ("".equals(jsonString)) {
        addTrackFuture.set("Created");
      } else {
        final JSONObject jsonObject = JSONObject.fromObject(postJson());
        throwIfErrorsInResponse(jsonObject);
        throw new IllegalStateException("Should not get here");
      }
    } catch (Exception e) {
      addTrackFuture.setException(e);
    }

    return addTrackFuture;
  }

  public Playlist get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createPlaylist(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String owner = "";
    private String playlist = "";

    public Builder owner(String username) {
      assert (username != null);
      this.owner = username;
      return this;
    }

    public Builder playlist(String playlist) {
      assert (playlist != null);
      this.playlist = playlist;
      return this;
    }

    public Builder position(int index) {
      assert (index >= 0);
      return parameter("position", String.valueOf(index));
    }

    public Builder tracks(List<String> trackUris) {
      final JSONArray jsonArrayUri = new JSONArray();
      jsonArrayUri.addAll(trackUris);
      return body(jsonArrayUri);
    }

    public Builder accessToken(String accessToken) {
      return header("Authorization", "Bearer " + accessToken);
    }

    public AddTrackToPlaylistRequest build() {
      path("/v1/users/" + owner + "/playlists/" + playlist + "/tracks");

      header("Content-Type", "application/json");

      return new AddTrackToPlaylistRequest(this);
    }

  }

}
