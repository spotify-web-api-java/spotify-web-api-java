package com.wrapper.spotify.objects;

import net.sf.json.JSONObject;

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

  public static final class Builder extends AbstractModelObject.Builder {
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

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ExternalUrls> {
    public ExternalUrls createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new ExternalUrls.Builder()
              .setExternalUrls(jsonObject)
              .build();
    }
  }

}
