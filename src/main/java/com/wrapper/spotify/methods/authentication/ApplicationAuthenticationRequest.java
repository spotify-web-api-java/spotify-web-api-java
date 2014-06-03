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

    public Builder authorizationHeader(String clientId, String clientSecret) {
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

    public Builder scopes(List<String> scopes) {
      return body("scope", Joiner.on(" ").join(scopes).toString());
    }

    public ApplicationAuthenticationRequest build() {
      host(Api.DEFAULT_AUTHENTICATION_HOST);
      port(Api.DEFAULT_AUTHENTICATION_PORT);
      scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);

      path("/api/token");
      return new ApplicationAuthenticationRequest(this);
    }
  }
}
