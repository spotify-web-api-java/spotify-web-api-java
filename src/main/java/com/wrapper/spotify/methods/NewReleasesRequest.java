package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.NewReleases;

import net.sf.json.JSONObject;

import java.io.IOException;

public class NewReleasesRequest extends AbstractRequest {

  protected NewReleasesRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() { return new Builder(); }

  public SettableFuture<NewReleases> getAsync() {
    final SettableFuture<NewReleases> newReleasesFuture = SettableFuture.create();

    try {
      final String jsonString = getJson();
      newReleasesFuture.set(JsonUtil.createNewReleases(JSONObject.fromObject(jsonString)));
    } catch (Exception e) {
      newReleasesFuture.setException(e);
    }

    return newReleasesFuture;
  }

  public NewReleases get() throws IOException, WebApiException {
    final String jsonString = getJson();
    return JsonUtil.createNewReleases(JSONObject.fromObject(jsonString));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder limit(int limit) {
      assert (limit > 0);
      return parameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return parameter("offset", String.valueOf(offset));
    }

    public Builder country(String countryCode) {
      assert (countryCode != null);
      return parameter("country", countryCode);
    }

    public NewReleasesRequest build() {
      path("/v1/browse/new-releases");
      return new NewReleasesRequest(this);
    }

  }
}