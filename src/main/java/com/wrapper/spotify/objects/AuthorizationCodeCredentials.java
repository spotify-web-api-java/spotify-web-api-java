package com.wrapper.spotify.objects;

public class AuthorizationCodeCredentials extends AbstractModelObject {
  private final String accessToken;
  private final String tokenType;
  private final int expiresIn;
  private final String refreshToken;

  private AuthorizationCodeCredentials(final AuthorizationCodeCredentials.Builder builder) {
    super(builder);

    this.accessToken = builder.accessToken;
    this.tokenType = builder.tokenType;
    this.expiresIn = builder.expiresIn;
    this.refreshToken = builder.refreshToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<ArtistSimplified.Builder> {
    private String accessToken;
    private String tokenType;
    private int expiresIn;
    private String refreshToken;

    public Builder setAccessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public Builder setTokenType(String tokenType) {
      this.tokenType = tokenType;
      return this;
    }

    public Builder setExpiresIn(int expiresIn) {
      this.expiresIn = expiresIn;
      return this;
    }

    public Builder setRefreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
      return this;
    }

    @Override
    public AuthorizationCodeCredentials build() {
      return new AuthorizationCodeCredentials(this);
    }
  }
}
