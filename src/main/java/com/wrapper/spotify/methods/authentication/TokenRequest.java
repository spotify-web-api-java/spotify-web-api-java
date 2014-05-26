package com.wrapper.spotify.methods.authentication;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.TokenResponse;

import java.io.IOException;

public class TokenRequest extends AbstractRequest {

  protected TokenRequest(Builder builder) {
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

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String code;

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

    public Builder withCode(String code) {
      assert (code != null);
      this.code = code;
      return body("code", code);
    }

    public Builder withRedirectUri(String redirectUri) {
      assert (redirectUri != null);
      this.redirectUri = redirectUri;
      return body("redirect_uri", redirectUri);
    }

    public TokenRequest build() {
      assert (code != null);
      assert (clientId != null);
      assert (clientSecret != null);
      assert (redirectUri != null);

      host(Api.DEFAULT_AUTHENTICATION_HOST);
      port(Api.DEFAULT_AUTHENTICATION_PORT);
      scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);

      final String idSecret = clientId + ":" + clientSecret;
      final String idSecretEncoded = new String(Base64.encodeBase64(idSecret.getBytes()));
      header("Authorization", "Basic " + idSecretEncoded);

      body("grant_type", "authorization_code");
      path("/api/token");

      return new TokenRequest(this);
    }
  }
}
