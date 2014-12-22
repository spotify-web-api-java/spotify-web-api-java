package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.FeaturedPlaylists;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeaturedPlaylistsRequest extends AbstractRequest {

  public FeaturedPlaylistsRequest(Builder builder) {
    super(builder);
  }

  /**
   * Get Featured Playlists synchronously.
   * @return Featured playlists.
   * @throws IOException
   * @throws WebApiException
   */
  public FeaturedPlaylists get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);
    return JsonUtil.createFeaturedPlaylist(jsonObject);
  }

  /**
   * Get Featured Playlists asynchronously.
   * @return A future that resolves to featured playlists.
   */
  public SettableFuture<FeaturedPlaylists> getAsync() {
    final SettableFuture<FeaturedPlaylists> future = SettableFuture.create();

    try {
      final String jsonString = getJson();
      future.set(JsonUtil.createFeaturedPlaylist(JSONObject.fromObject(jsonString)));
    } catch (Exception e) {
      future.setException(e);
    }

    return future;
  }

  public static Builder builder() {
    return new Builder();
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

    public Builder locale(String locale) {
      assert (locale != null);
      return parameter("locale", locale);
    }

    public Builder timestamp(Date timestamp) {
      assert (timestamp != null);
      final DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      return parameter("timestamp", format.format(timestamp));
    }

    public Builder accessToken(String accessToken) {
      return header("Authorization", "Bearer " + accessToken);
    }

    public FeaturedPlaylistsRequest build() {
      path("/v1/browse/featured-playlists");
      return new FeaturedPlaylistsRequest(this);
    }

  }
}
