package com.wrapper.spotify.objects;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class Image extends AbstractModelObject {
  private final int height;
  private final String url;
  private final int width;

  private Image(final Image.Builder builder) {
    super(builder);

    this.height = builder.height;
    this.url = builder.url;
    this.width = builder.width;
  }

  public int getHeight() {
    return height;
  }

  public String getUrl() {
    return url;
  }

  public int getWidth() {
    return width;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private int height;
    private String url;
    private int width;

    public Builder setHeight(int height) {
      this.height = height;
      return this;
    }

    public Builder setUrl(String url) {
      this.url = url;
      return this;
    }

    public Builder setWidth(int width) {
      this.width = width;
      return this;
    }

    @Override
    public Image build() {
      return new Image(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Image> {
    public Image createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Image.Builder()
              .setHeight((jsonObject.get("height") instanceof JsonNull) ? 0 : jsonObject.get("height").getAsInt())
              .setUrl(jsonObject.get("url").getAsString())
              .setWidth((jsonObject.get("width") instanceof JsonNull) ? 0 : jsonObject.get("width").getAsInt())
              .build();
    }
  }
}
