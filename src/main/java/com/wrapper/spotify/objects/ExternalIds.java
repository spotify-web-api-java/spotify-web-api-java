package com.wrapper.spotify.objects;

import java.util.Map;

public class ExternalIds extends AbstractModelObject {
  private final Map<String, String> externalIds;

  private ExternalIds(final ExternalIds.Builder builder) {
    super(builder);

    this.externalIds = builder.externalIds;
  }

  public Map<String, String> getExternalIds() {
    return externalIds;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<ExternalIds.Builder> {
    private Map<String, String> externalIds;

    public Builder setExternalIds(Map<String, String> externalIds) {
      this.externalIds = externalIds;
      return this;
    }

    @Override
    public ExternalIds build() {
      return new ExternalIds(this);
    }
  }

}
