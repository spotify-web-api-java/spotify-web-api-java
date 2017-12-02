package com.wrapper.spotify.objects;

import net.sf.json.JSONObject;

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

  public static final class Builder extends AbstractModelObject.Builder {
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

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ExternalIds> {
    public ExternalIds createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new ExternalIds.Builder()
              .setExternalIds(jsonObject)
              .build();
    }
  }
}
