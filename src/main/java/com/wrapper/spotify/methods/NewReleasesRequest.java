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

  public SettableFuture<NewReleases> getAsync()
  {
    SettableFuture<NewReleases> newReleasesFuture = SettableFuture.create();

    try
    {
      final String jsonString = getJson();
      newReleasesFuture.set(JsonUtil.createNewReleases(JSONObject.fromObject(jsonString)));
    }
    catch (Exception e)
    {
      newReleasesFuture.setException(e);
    }

    return newReleasesFuture;
  }

  public NewReleases get() throws IOException, WebApiException
  {
    final String jsonString = getJson();
    return JsonUtil.createNewReleases(JSONObject.fromObject(jsonString));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder>
  {

    public Builder id(int offset, int limit)
    {
      path("/v1/browse/new-releases");
      return parameter("offset", "" + offset).parameter("limit", "" + limit);
    }

    public NewReleasesRequest build() { return new NewReleasesRequest(this); }

  }
}