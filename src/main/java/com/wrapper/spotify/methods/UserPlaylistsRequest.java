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

      JsonUtil.throwIfErrorsInResponse(jsonObject);

      simplePlaylistsPageFuture.set(JsonUtil.createSimplePlaylistsPage(jsonObject));
    } catch (Exception e) {
      simplePlaylistsPageFuture.setException(e);
    }

    return simplePlaylistsPageFuture;
  }

  public Page<SimplePlaylist> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    JsonUtil.throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createSimplePlaylistsPage(jsonObject);
  }


  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String userId;

    public Builder withId(String userId) {
      assert (userId != null);
      this.userId = userId;
      return path(String.format("/v1/users/%s/playlists", userId));
    }

    public UserPlaylistsRequest build() {
      assert (userId != null);
      return new UserPlaylistsRequest(this);
    }

  }
}
