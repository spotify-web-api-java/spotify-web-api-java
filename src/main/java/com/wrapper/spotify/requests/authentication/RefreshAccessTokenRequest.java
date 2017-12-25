package com.wrapper.spotify.requests.authentication;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.objects.model_objects.credentials.RefreshAccessTokenCredentials;
import com.wrapper.spotify.requests.AbstractRequest;
import org.apache.commons.codec.binary.Base64;

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
      JsonObject jsonObject = new JsonParser().parse(postJson()).getAsJsonObject();
      future.set(new RefreshAccessTokenCredentials.JsonUtil().createModelObject(jsonObject));
    } catch (Exception e) {
      future.setException(e);
    }

    return future;
  }

  public RefreshAccessTokenCredentials get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    JsonObject jsonObject = new JsonParser().parse(postJson()).getAsJsonObject();
    return new RefreshAccessTokenCredentials.JsonUtil().createModelObject(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder basicAuthorizationHeader(String clientId, String clientSecret) {
      assert (clientId != null);
      assert (clientSecret != null);

      String idSecret = clientId + ":" + clientSecret;
      String idSecretEncoded = new String(Base64.encodeBase64(idSecret.getBytes()));

      return setHeaderParameter("Authorization", "Basic " + idSecretEncoded);
    }

    public Builder grantType(String grantType) {
      assert (grantType != null);
      return setBodyParameter("grant_type", grantType);
    }

    public Builder refreshToken(String refreshToken) {
      assert (refreshToken != null);
      return setBodyParameter("refresh_token", refreshToken);
    }

    public RefreshAccessTokenRequest build() {
      setHost(Api.DEFAULT_AUTHENTICATION_HOST);
      setPort(Api.DEFAULT_AUTHENTICATION_PORT);
      setScheme(Api.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/api/token");

      return new RefreshAccessTokenRequest(this);
    }
  }

}
