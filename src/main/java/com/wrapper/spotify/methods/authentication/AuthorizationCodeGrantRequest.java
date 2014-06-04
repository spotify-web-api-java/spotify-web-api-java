package com.wrapper.spotify.methods.authentication;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.TokenResponse;
import net.sf.json.JSONObject;

import java.io.IOException;

public class AuthorizationCodeGrantRequest extends AbstractRequest {

  protected AuthorizationCodeGrantRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<TokenResponse> getAsync() {
    final SettableFuture<TokenResponse> future = SettableFuture.create();

    try {
      final String jsonString = postJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);


      if (JsonUtil.containsAuthenticationError(jsonObject)) {
        JsonUtil.throwAuthenticationError(jsonObject);
      }

      future.set(JsonUtil.createTokenResponse(jsonObject));
    } catch (Exception e) {
      future.setException(e);
    }

    return future;
  }

  public TokenResponse get() throws IOException, WebApiException {
    final String json = postJson();
    final JSONObject jsonObject = JSONObject.fromObject(json);

    if (JsonUtil.containsAuthenticationError(jsonObject)) {
      JsonUtil.throwAuthenticationError(jsonObject);
    }

    return JsonUtil.createTokenResponse(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder grantType(String grantType) {
      assert (grantType != null);
      return body("grant_type", grantType);
    }

    public Builder code(String code) {
      assert (code != null);
      return body("code", code);
    }

    public Builder redirectUri(String redirectUri) {
      assert (redirectUri != null);
      return body("redirect_uri", redirectUri);
    }

    public Builder clientId(String clientId) {
      assert (clientId != null);
      return body("client_id", clientId);
    }

    public Builder clientSecret(String clientSecret) {
      assert (clientSecret != null);
      return body("client_secret", clientSecret);
    }

    public AuthorizationCodeGrantRequest build() {
      host(Api.DEFAULT_AUTHENTICATION_HOST);
      port(Api.DEFAULT_AUTHENTICATION_PORT);
      scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);

      path("/api/token");

      return new AuthorizationCodeGrantRequest(this);
    }

  }
}
