/**
 * Copyright (C) 2014 Spotify AB
 */
package com.wrapper.spotify.methods;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.SnapshotResult;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class ReorderPlaylistTracksRequest extends AbstractRequest {

  public ReorderPlaylistTracksRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<SnapshotResult> getAsync() {
    final SettableFuture<SnapshotResult> reorderTracksFuture = SettableFuture.create();

    try {
      final String jsonString = putJson();
      reorderTracksFuture.set(JsonUtil.createSnapshotResponse(jsonString));
    } catch (IOException e) {
    	reorderTracksFuture.setException(e);
    } catch (WebApiException e) {
    	reorderTracksFuture.setException(e);
    }

    return reorderTracksFuture;
  }

  public SnapshotResult get() throws IOException, WebApiException {
	final String jsonString = putJson();
	return JsonUtil.createSnapshotResponse(jsonString);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    final private Map<String,Object> properties = Maps.newHashMap();

    public Builder order(int range_start, int insert_before) {
      assert (range_start >= 0);
      assert (insert_before >= 0);
      properties.put("range_start", range_start);
      properties.put("insert_before", insert_before);
      return this;
    }

    public Builder range(int range_length) {
      properties.put("range_length", range_length);
      return this;
    }

    public ReorderPlaylistTracksRequest build() {
      header("Content-Type", "application/json");
      body(JSONObject.fromObject(properties));
      return new ReorderPlaylistTracksRequest(this);
    }

  }

}
