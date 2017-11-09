/**
 * Copyright (C) 2017 Spotify AB
 */
package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.WebApiException;
import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.List;

public class AddToMySavedTracksRequest extends AbstractRequest {

  public AddToMySavedTracksRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<String> getAsync() {
    final SettableFuture<String> addToSavedTracksFuture = SettableFuture.create();

    final String response;
    try {
      response = putJson();
      addToSavedTracksFuture.set(response);
    } catch (IOException e) {
      addToSavedTracksFuture.setException(e);
    } catch (WebApiException e) {
      addToSavedTracksFuture.setException(e);
    }

    return addToSavedTracksFuture;
  }

  public String get() throws IOException, WebApiException {
    return putJson();
  }

  public static AddToMySavedTracksRequest.Builder builder() {
    return new Builder();
  }

  public static class Builder extends AbstractRequest.Builder<Builder> {

    public Builder tracks(List<String> trackIds) {
      body(JSONArray.fromObject(trackIds));
      return this;
    }

    @Override
    public AddToMySavedTracksRequest build() {
      return new AddToMySavedTracksRequest(this);
    }
  }
}
