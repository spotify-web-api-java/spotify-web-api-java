package com.wrapper.spotify.objects;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Map;

public class ExternalUrls extends AbstractModelObject {
  private final Map<String, String> externalUrls;

  private ExternalUrls(final ExternalUrls.Builder builder) {
    super(builder);

    this.externalUrls = builder.externalUrls;
  }

  public String get(String key) {
    return externalUrls.get(key);
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
    public ExternalUrls createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      Map<String, String> map = new Gson().fromJson(jsonObject, new TypeToken<Map<String, String>>() {
      }.getType());

      return new ExternalUrls.Builder()
              .setExternalUrls(map)
              .build();
    }
  }

}
