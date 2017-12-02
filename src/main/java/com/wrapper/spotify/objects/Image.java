package com.wrapper.spotify.objects;

import net.sf.json.JSONObject;

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
    public Image createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new Image.Builder()
              .setHeight(jsonObject.getInt(("height")))
              .setUrl(jsonObject.getString("url"))
              .setWidth(jsonObject.getInt(("width")))
              .build();
    }
  }
}
