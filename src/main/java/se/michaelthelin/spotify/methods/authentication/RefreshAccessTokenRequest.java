package se.michaelthelin.spotify.methods.authentication;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.*;
import se.michaelthelin.spotify.methods.AbstractRequest;
import se.michaelthelin.spotify.models.RefreshAccessTokenResponse;

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

      throwIfErrorsInResponse(jsonObject);

      future.set(JsonUtil.createRefreshAccessTokenResponse(jsonObject));
    } catch (Exception e) {
      future.setException(e);
    }

    return future;
  }

  public RefreshAccessTokenResponse get() throws IOException, WebApiException {
    JSONObject jsonObject = JSONObject.fromObject(postJson());

    throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createRefreshAccessTokenResponse(jsonObject);
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
