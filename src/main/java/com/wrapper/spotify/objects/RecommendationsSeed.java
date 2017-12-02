package com.wrapper.spotify.objects;

import net.sf.json.JSONObject;

public class RecommendationsSeed extends AbstractModelObject {
  private final int afterFilteringSize;
  private final int afterRelinkingSize;
  private final String href;
  private final String id;
  private final int initialPoolSize;
  private final ObjectType type;

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

  public ObjectType getType() {
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
    private ObjectType type;

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

    public Builder setType(ObjectType type) {
      this.type = type;
      return this;
    }

    @Override
    public RecommendationsSeed build() {
      return new RecommendationsSeed(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<RecommendationsSeed> {
    public RecommendationsSeed createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new RecommendationsSeed.Builder()
              .setAfterFilteringSize(jsonObject.getInt("afterFilteringSize"))
              .setAfterRelinkingSize(jsonObject.getInt("afterRelinkingSize"))
              .setHref(jsonObject.getString("href"))
              .setId(jsonObject.getString("id"))
              .setInitialPoolSize(jsonObject.getInt("initialPoolSize"))
              .setType(ObjectType.valueOf(jsonObject.getString("type")))
              .build();
    }
  }
}
