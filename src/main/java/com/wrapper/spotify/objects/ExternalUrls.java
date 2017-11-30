package com.wrapper.spotify.objects;

import java.util.Map;

public class ExternalUrls extends AbstractModelObject {
  private final Map<String, String> externalUrls;

  private ExternalUrls(final ExternalUrls.Builder builder) {
    super(builder);

    this.externalUrls = builder.externalUrls;
  }

  public Map<String, String> getExternalUrls() {
    return externalUrls;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<Cursor.Builder> {
    private Map<String, String> externalUrls;

    public Builder setExternalUrls(Map<String, String> externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    @Override
    public ExternalUrls build() {
      return new ExternalUrls(this);
    }
  }

}
