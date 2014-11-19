/**
 * Copyright (C) 2014 Spotify AB
 */
package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.LibraryTrack;
import com.wrapper.spotify.models.Page;

import net.sf.json.JSONObject;

import java.io.IOException;

public class GetMySavedTracksRequest extends AbstractRequest {

  public GetMySavedTracksRequest(AbstractRequest.Builder builder) {
    super(builder);
  }

  public SettableFuture<Page<LibraryTrack>> getAsync() {
    SettableFuture<Page<LibraryTrack>> libraryTracksFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      libraryTracksFuture.set(JsonUtil.createLibraryTracksPage(jsonObject));
    } catch (Exception e) {
      libraryTracksFuture.setException(e);
    }

    return libraryTracksFuture;
  }

  public Page<LibraryTrack> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    return JsonUtil.createLibraryTracksPage(jsonObject);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder accessToken(String accessToken) {
      return header("Authorization", "Bearer " + accessToken);
    }

    public GetMySavedTracksRequest build() {
      return new GetMySavedTracksRequest(this);
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
