/**
 * Copyright (C) 2014 Spotify AB
 */
package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.exceptions.WebApiException;

import java.io.IOException;
import java.util.List;

public class RemoveFromMySavedTracksRequest extends AbstractRequest {

  public RemoveFromMySavedTracksRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<String> getAsync() {
    final SettableFuture<String> removeFromMyTracksFuture = SettableFuture.create();

    final String response;
    try {
      response = deleteJson();
      removeFromMyTracksFuture.set(response);
    } catch (IOException e) {
      removeFromMyTracksFuture.setException(e);
    } catch (WebApiException e) {
      removeFromMyTracksFuture.setException(e);
    }

    return removeFromMyTracksFuture;
  }

  public String get() throws IOException, WebApiException {
    return deleteJson();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder extends AbstractRequest.Builder<Builder> {

    public Builder tracks(List<String> trackIds) {
      String idsParameter = Joiner.on(",").join(trackIds).toString();
      return parameter("ids", idsParameter);
    }

    @Override
    public RemoveFromMySavedTracksRequest build() {
      return new RemoveFromMySavedTracksRequest(this);
    }
  }

}
