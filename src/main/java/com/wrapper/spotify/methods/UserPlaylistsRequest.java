package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimplePlaylist;

import java.io.IOException;

public class UserPlaylistsRequest extends AbstractRequest {

  public UserPlaylistsRequest(Builder builder) {
   super(builder);
  }

  public SettableFuture<Page<SimplePlaylist>> getAsync() {
    SettableFuture<Page<SimplePlaylist>> simplePlaylistsPageFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      throwIfErrorsInResponse(jsonObject);

      simplePlaylistsPageFuture.set(JsonUtil.createSimplePlaylistsPage(jsonObject));
    } catch (Exception e) {
      simplePlaylistsPageFuture.setException(e);
    }

    return simplePlaylistsPageFuture;
  }

  public Page<SimplePlaylist> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createSimplePlaylistsPage(jsonObject);
  }


  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder username(String username) {
      assert (username != null);
      return path(String.format("/v1/users/%s/playlists", username));
    }

    public Builder accessToken(String accessToken) {
      return header("Authorization", "Bearer " + accessToken);
    }

    public UserPlaylistsRequest build() {
      return new UserPlaylistsRequest(this);
    }

  }
}
