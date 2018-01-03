package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about categories by building instances from this class.
 */
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

  /**
   * Get the full Spotify API endpoint url of a category.
   *
   * @return A link to the Web API endpoint returning full details of the category.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the category icon in various sizes.
   *
   * @return The category icon, in various sizes.
   */
  public Image[] getIcons() {
    return icons;
  }

  /**
   * Get the Spotify ID of the category.
   *
   * @return The Spotify category ID of the category.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the name of the category.
   *
   * @return The name of the category.
   */
  public String getName() {
    return name;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building category instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String href;
    private Image[] icons;
    private String id;
    private String name;

    /**
     * The category href setter.
     *
     * @param href A link to the Web API endpoint returning full details of the category.
     * @return A Category builder.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * The category icon setter.
     *
     * @param icons The category icon, in various sizes.
     * @return A Category builder.
     */
    public Builder setIcons(Image... icons) {
      this.icons = icons;
      return this;
    }

    /**
     * The category id setter.
     *
     * @param id The Spotify category ID of the category.
     * @return A Category builder.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * The category name setter.
     *
     * @param name The name of the category.
     * @return A Category builder.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Category build() {
      return new Category(this);
    }
  }

  /**
   * JsonUtil class for building category instances.
   */
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
