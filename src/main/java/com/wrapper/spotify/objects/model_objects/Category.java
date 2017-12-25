package com.wrapper.spotify.objects.model_objects;

import com.google.gson.JsonObject;

public class Category extends AbstractModelObject {
  private final String href;
  private final Image[] icons;
  private final String id;
  private final String name;

  private Category(final Category.Builder builder) {
    super(builder);

    this.href = builder.href;
    this.icons = builder.icons;
    this.id = builder.id;
    this.name = builder.name;
  }

  public String getHref() {
    return href;
  }

  public Image[] getIcons() {
    return icons;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private String href;
    private Image[] icons;
    private String id;
    private String name;

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setIcons(Image[] icons) {
      this.icons = icons;
      return this;
    }

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Category build() {
      return new Category(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Category> {
    public Category createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Category.Builder()
              .setHref(jsonObject.get("href").getAsString())
              .setIcons(new Image.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("images")))
              .setId(jsonObject.get("id").getAsString())
              .setName(jsonObject.get("name").getAsString())
              .build();
    }
  }
}
