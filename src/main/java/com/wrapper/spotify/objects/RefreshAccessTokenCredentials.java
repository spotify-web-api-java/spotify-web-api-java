package com.wrapper.spotify.objects;

public class RefreshAccessTokenCredentials extends AbstractModelObject {
  private final String accessToken;
  private final String tokenType;
  private final int expiresIn;

  private RefreshAccessTokenCredentials(final RefreshAccessTokenCredentials.Builder builder) {
    super(builder);

    this.accessToken = builder.accessToken;
    this.tokenType = builder.tokenType;
    this.expiresIn = builder.expiresIn;
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

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<RefreshAccessTokenCredentials.Builder> {
    private String accessToken;
    private String tokenType;
    private int expiresIn;

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

    @Override
    public RefreshAccessTokenCredentials build() {
      return new RefreshAccessTokenCredentials(this);
    }
  }
}
