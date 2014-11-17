/**
 * Copyright (C) 2014 Spotify AB
 */
package com.wrapper.spotify.methods;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.exceptions.WebApiException;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class ChangePlaylistDetailsRequest extends AbstractRequest {

  public ChangePlaylistDetailsRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<String> getAsync() {
    final SettableFuture<String> changeDetailsFuture = SettableFuture.create();

    final String response;
    try {
      response = putJson();
      changeDetailsFuture.set(response);
    } catch (IOException e) {
      changeDetailsFuture.setException(e);
    } catch (WebApiException e) {
      changeDetailsFuture.setException(e);
    }

    return changeDetailsFuture;
  }

  public String get() throws IOException, WebApiException {
    return putJson();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    final private Map<String,Object> properties = Maps.newHashMap();

    public Builder name(String name) {
      assert (name != null);
      properties.put("name", name);
      return this;
    }

    public Builder publicAccess(boolean isPublic) {
      properties.put("public", isPublic);
      return this;
    }

    public ChangePlaylistDetailsRequest build() {
      header("Content-Type", "application/json");
      body(JSONObject.fromObject(properties));
      return new ChangePlaylistDetailsRequest(this);
    }

  }

}
