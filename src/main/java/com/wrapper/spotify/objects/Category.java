package com.wrapper.spotify.objects;

import net.sf.json.JSONObject;

import java.util.List;

public class Category extends AbstractModelObject {
  private final String href;
  private final List<Image> icons;
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

  public List<Image> getIcons() {
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
    private List<Image> icons;
    private String id;
    private String name;

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setIcons(List<Image> icons) {
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
    public Category createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new Category.Builder()
              .setHref(jsonObject.getString("href"))
              .setIcons(new Image.JsonUtil().createModelObjectList(jsonObject.getJSONArray("images")))
              .setId(jsonObject.getString("id"))
              .setName(jsonObject.getString("name"))
              .build();
    }
  }
}
