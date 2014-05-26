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
        JsonUtil.throwIfErrorsInResponse(jsonObject);
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

    JsonUtil.throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createPlaylist(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String owner;
    private String playlist;
    private List<String> trackUris;

    public Builder withOwner(String ownerUserId) {
      assert (ownerUserId != null);

      this.owner = ownerUserId;
      return this;
    }

    public Builder withId(String playlistId) {
      assert (playlistId != null);

      this.playlist = playlistId;
      return this;
    }

    public Builder withPosition(int position) {
      assert (position >= 0);

      return parameter("position", String.valueOf(position));
    }

    public Builder withTracks(List<String> trackUris) {
      assert (trackUris != null);

      this.trackUris = trackUris;

      final JSONArray jsonArrayUri = new JSONArray();
      jsonArrayUri.addAll(trackUris);
      return body(jsonArrayUri);
    }

    public AddTrackToPlaylistRequest build() {
      assert (owner != null);
      assert (playlist != null);
      assert (trackUris != null);

      path("/v1/users/" + owner + "/playlists/" + playlist + "/tracks");
      header("Content-Type", "application/json");

      return new AddTrackToPlaylistRequest(this);
    }

  }

}