package se.michaelthelin.spotify.methods;

import org.apache.commons.codec.binary.Base64;

public class TokenRequest extends AbstractRequest {

  protected TokenRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder authorizationHeader(String clientId, String clientSecret) {
      assert (clientId != null);
      assert (clientSecret != null);
      String idSecret = clientId + ":" + clientSecret;
      String idSecretEncoded = new String(Base64.encodeBase64(idSecret.getBytes()));

      return header("Authorization", "Authorization: Basic " + idSecretEncoded);
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
      path("/api/token");
      return new TokenRequest(this);
    }
  }
}
