package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.EmptyResponseException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Playlist;
import com.wrapper.spotify.models.SimplePlaylist;
import net.sf.json.JSONObject;

import java.io.IOException;

public class AddTrackToPlaylistRequest extends AbstractRequest {

    private String defaultSuccessStringResponse = "Added to playlist";
    private String defaultFailureStringResponse = "Failed to add track to playlist";

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
        addTrackFuture.set(defaultSuccessStringResponse);
      } else {
        final JSONObject jsonObject = JSONObject.fromObject(postJson());
      }
    } catch (Exception e) {
      addTrackFuture.setException(e);
    }

    return addTrackFuture;
  }

  public String get() throws IOException, WebApiException {
    final String jsonString = postJson();
    if("".equals(jsonString)){
      return defaultSuccessStringResponse;
    }else{
      return defaultFailureStringResponse;
    }
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