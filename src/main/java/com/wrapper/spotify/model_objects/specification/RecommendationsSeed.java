package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class RecommendationsSeed extends AbstractModelObject {
  private final int afterFilteringSize;
  private final int afterRelinkingSize;
  private final String href;
  private final String id;
  private final int initialPoolSize;
  private final ModelObjectType type;

  private RecommendationsSeed(final RecommendationsSeed.Builder builder) {
    super(builder);

    this.afterFilteringSize = builder.afterFilteringSize;
    this.afterRelinkingSize = builder.afterRelinkingSize;
    this.href = builder.href;
    this.id = builder.id;
    this.initialPoolSize = builder.initialPoolSize;
    this.type = builder.type;
  }

  public int getAfterFilteringSize() {
    return afterFilteringSize;
  }

  public int getAfterRelinkingSize() {
    return afterRelinkingSize;
  }

  public String getHref() {
    return href;
  }

  public String getId() {
    return id;
  }

  public int getInitialPoolSize() {
    return initialPoolSize;
  }

  public ModelObjectType getType() {
    return type;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private int afterFilteringSize;
    private int afterRelinkingSize;
    private String href;
    private String id;
    private int initialPoolSize;
    private ModelObjectType type;

    public Builder setAfterFilteringSize(int afterFilteringSize) {
      this.afterFilteringSize = afterFilteringSize;
      return this;
    }

    public Builder setAfterRelinkingSize(int afterRelinkingSize) {
      this.afterRelinkingSize = afterRelinkingSize;
      return this;
    }

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setInitialPoolSize(int initialPoolSize) {
      this.initialPoolSize = initialPoolSize;
      return this;
    }

    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    @Override
    public RecommendationsSeed build() {
      return new RecommendationsSeed(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<RecommendationsSeed> {
    public RecommendationsSeed createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new RecommendationsSeed.Builder()
              .setAfterFilteringSize(
                      hasAndNotNull(jsonObject, "afterFilteringSize")
                              ? jsonObject.get("afterFilteringSize").getAsInt()
                              : null)
              .setAfterRelinkingSize(
                      hasAndNotNull(jsonObject, "afterRelinkingSize")
                              ? jsonObject.get("afterRelinkingSize").getAsInt()
                              : null)
              .setHref(
                      hasAndNotNull(jsonObject, "href")
                              ? jsonObject.get("href").getAsString()
                              : null)
              .setId(
                      hasAndNotNull(jsonObject, "id")
                              ? jsonObject.get("id").getAsString()
                              : null)
              .setInitialPoolSize(
                      hasAndNotNull(jsonObject, "initialPoolSize")
                              ? jsonObject.get("initialPoolSize").getAsInt()
                              : null)
              .setType(
                      hasAndNotNull(jsonObject, "type")
                              ? ModelObjectType.valueOf(
                              jsonObject.get("type").getAsString().toUpperCase())
                              : null)
              .build();
    }
  }
}
