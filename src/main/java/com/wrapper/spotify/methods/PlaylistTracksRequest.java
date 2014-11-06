package com.wrapper.spotify.methods;

import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.PlaylistTrack;
import net.sf.json.JSONObject;

import java.io.IOException;

public class PlaylistTracksRequest extends AbstractRequest {

  public PlaylistTracksRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<Page<PlaylistTrack>> getAsync() {
    SettableFuture<Page<PlaylistTrack>> playlistFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      playlistFuture.set(JsonUtil.createPlaylistTrackPage(jsonObject));
    } catch (Exception e) {
      playlistFuture.setException(e);
    }

    return playlistFuture;
  }

  public Page<PlaylistTrack> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    return JsonUtil.createPlaylistTrackPage(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder fields(String fields) {
      assert (fields != null);
      return parameter("fields", fields);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return parameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return parameter("offset", String.valueOf(offset));
    }

    public PlaylistTracksRequest build() {
      return new PlaylistTracksRequest(this);
    }

  }
}
