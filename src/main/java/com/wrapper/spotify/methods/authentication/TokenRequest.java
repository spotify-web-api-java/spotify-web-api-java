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

    public Builder authorizationHeader(String clientId, String clientSecret) {
      assert (clientId != null);
      assert (clientSecret != null);

      final String idSecret = clientId + ":" + clientSecret;
      final String idSecretEncoded = new String(Base64.encodeBase64(idSecret.getBytes()));

      return header("Authorization", "Basic " + idSecretEncoded);
    }

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

    public TokenRequest build() {
      host(Api.DEFAULT_AUTHENTICATION_HOST);
      port(Api.DEFAULT_AUTHENTICATION_PORT);
      scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);

      path("/api/token");

      return new TokenRequest(this);
    }
  }
}
