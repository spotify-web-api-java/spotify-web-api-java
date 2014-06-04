package com.wrapper.spotify.methods.authentication;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.models.RefreshAccessTokenCredentials;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.methods.AbstractRequest;

import java.io.IOException;

public class RefreshAccessTokenRequest extends AbstractRequest {

  protected RefreshAccessTokenRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<RefreshAccessTokenCredentials> getAsync() {
    final SettableFuture<RefreshAccessTokenCredentials> future = SettableFuture.create();

    try {
      JSONObject jsonObject = JSONObject.fromObject(postJson());

      future.set(JsonUtil.createRefreshAccessTokenResponse(jsonObject));
    } catch (Exception e) {
      future.setException(e);
    }

    return future;
  }

  public RefreshAccessTokenCredentials get() throws IOException, WebApiException {
    JSONObject jsonObject = JSONObject.fromObject(postJson());

    return JsonUtil.createRefreshAccessTokenResponse(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder basicAuthorizationHeader(String clientId, String clientSecret) {
      assert (clientId != null);
      assert (clientSecret != null);

      String idSecret = clientId + ":" + clientSecret;
      String idSecretEncoded = new String(Base64.encodeBase64(idSecret.getBytes()));

      return header("Authorization", "Basic " + idSecretEncoded);
    }

    public Builder grantType(String grantType) {
      assert (grantType != null);
      return body("grant_type", grantType);
    }

    public Builder refreshToken(String refreshToken) {
      assert (refreshToken != null);
      return body("refresh_token", refreshToken);
    }

    public RefreshAccessTokenRequest build() {
      host(Api.DEFAULT_AUTHENTICATION_HOST);
      port(Api.DEFAULT_AUTHENTICATION_PORT);
      scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);

      path("/api/token");
      return new RefreshAccessTokenRequest(this);
    }
  }

}
