/**
 * Copyright (C) 2014 Spotify AB
 */
package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.LibraryAlbum;
import com.wrapper.spotify.models.Page;
import net.sf.json.JSONObject;

import java.io.IOException;

public class GetMySavedAlbumsRequest extends AbstractRequest {

  public GetMySavedAlbumsRequest(AbstractRequest.Builder builder) {
    super(builder);
  }

  public SettableFuture<Page<LibraryAlbum>> getAsync() {
    SettableFuture<Page<LibraryAlbum>> libraryTracksFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      libraryTracksFuture.set(JsonUtil.createLibraryAlbumsPage(jsonObject));
    } catch (Exception e) {
      libraryTracksFuture.setException(e);
    }

    return libraryTracksFuture;
  }

  public Page<LibraryAlbum> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    return JsonUtil.createLibraryAlbumsPage(jsonObject);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder accessToken(String accessToken) {
      return header("Authorization", "Bearer " + accessToken);
    }

    public GetMySavedAlbumsRequest build() {
      return new GetMySavedAlbumsRequest(this);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return parameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return parameter("offset", String.valueOf(offset));
    }
  }

}
