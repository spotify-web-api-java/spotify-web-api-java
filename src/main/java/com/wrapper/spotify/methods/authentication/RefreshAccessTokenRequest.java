package com.wrapper.spotify.methods.authentication;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.RefreshAccessTokenResponse;

import java.io.IOException;

public class RefreshAccessTokenRequest extends AbstractRequest {

  protected RefreshAccessTokenRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<RefreshAccessTokenResponse> getAsync() {
    final SettableFuture<RefreshAccessTokenResponse> future = SettableFuture.create();

    try {
      JSONObject jsonObject = JSONObject.fromObject(postJson());

      if (JsonUtil.containsAuthenticationError(jsonObject)) {
        JsonUtil.throwAuthenticationError(jsonObject);
      }

      future.set(JsonUtil.createRefreshAccessTokenResponse(jsonObject));
    } catch (Exception e) {
      future.setException(e);
    }

    return future;
  }

  public RefreshAccessTokenResponse get() throws IOException, WebApiException {
    JSONObject jsonObject = JSONObject.fromObject(postJson());

    if (JsonUtil.containsAuthenticationError(jsonObject)) {
      JsonUtil.throwAuthenticationError(jsonObject);
    }

    return JsonUtil.createRefreshAccessTokenResponse(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String clientId;
    private String clientSecret;
    private String refreshToken;

    public Builder withClientId(String clientId) {
      assert (clientId != null);
      this.clientId = clientId;
      return this;
    }

    public Builder withClientSecret(String clientSecret) {
      assert (clientSecret != null);
      this.clientSecret = clientSecret;
      return this;
    }

    public Builder withRefreshToken(String refreshToken) {
      assert (refreshToken != null);
      this.refreshToken = refreshToken;
      return body("refresh_token", refreshToken);
    }

    public RefreshAccessTokenRequest build() {
      host(Api.DEFAULT_AUTHENTICATION_HOST);
      port(Api.DEFAULT_AUTHENTICATION_PORT);
      scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);

      String idSecret = clientId + ":" + clientSecret;
      String idSecretEncoded = new String(Base64.encodeBase64(idSecret.getBytes()));
      header("Authorization", "Basic " + idSecretEncoded);

      body("grant_type", "refresh_token");
      path("/api/token");

      return new RefreshAccessTokenRequest(this);
    }
  }

}
