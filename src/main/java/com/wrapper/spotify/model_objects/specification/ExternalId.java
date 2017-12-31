package com.wrapper.spotify.model_objects.specification;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

import java.util.Map;

public class ExternalId extends AbstractModelObject {
  private final Map<String, String> externalIds;

  private ExternalId(final ExternalId.Builder builder) {
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
    public ExternalId build() {
      return new ExternalId(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ExternalId> {
    public ExternalId createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      Map<String, String> map = new Gson().fromJson(jsonObject, new TypeToken<Map<String, String>>() {
      }.getType());

      return new ExternalId.Builder()
              .setExternalIds(map)
              .build();
    }
  }
}
