package com.wrapper.spotify.methods.authentication;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.ApplicationAuthenticationToken;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.util.List;

public class ApplicationAuthenticationRequest extends AbstractRequest {

  public ApplicationAuthenticationRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<ApplicationAuthenticationToken> getAsync() {
    final SettableFuture<ApplicationAuthenticationToken> future = SettableFuture.create();

    try {
      JSONObject jsonObject = JSONObject.fromObject(postJson());

      if (JsonUtil.containsAuthenticationError(jsonObject)) {
        JsonUtil.throwAuthenticationError(jsonObject);
      }

      future.set(JsonUtil.createApplicationAuthenticationToken(jsonObject));
    } catch (Exception e) {
      future.setException(e);
    }

    return future;
  }

  public ApplicationAuthenticationToken get() throws IOException, WebApiException {
    JSONObject jsonObject = JSONObject.fromObject(postJson());

    if (JsonUtil.containsAuthenticationError(jsonObject)) {
      JsonUtil.throwAuthenticationError(jsonObject);
    }

    return JsonUtil.createApplicationAuthenticationToken(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private String clientId;
    private String clientSecret;

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

    public Builder withScopes(List<String> scopes) {
      return body("scope", Joiner.on(" ").join(scopes).toString());
    }

    public ApplicationAuthenticationRequest build() {
      assert (clientId != null);
      assert (clientSecret != null);

      host(Api.DEFAULT_AUTHENTICATION_HOST);
      port(Api.DEFAULT_AUTHENTICATION_PORT);
      scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);

      final String idSecret = clientId + ":" + clientSecret;
      final String idSecretEncoded = new String(Base64.encodeBase64(idSecret.getBytes()));
      header("Authorization", "Basic " + idSecretEncoded);

      body("grant_type", "client_credentials");
      path("/api/token");

      return new ApplicationAuthenticationRequest(this);
    }
  }
}
