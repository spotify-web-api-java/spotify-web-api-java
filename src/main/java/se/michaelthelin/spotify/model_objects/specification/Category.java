package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#category-object">
 * Category objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = Category.Builder.class)
public class Category extends AbstractModelObject {
  /** A link to the Web API endpoint returning full details of the category. */
  private final String href;
  /** The category icon, in various sizes. */
  private final Image[] icons;
  /** The Spotify category ID. */
  private final String id;
  /** The name of the category. */
  private final String name;

  private Category(final Builder builder) {
    super(builder);

    this.href = builder.href;
    this.icons = builder.icons;
    this.id = builder.id;
    this.name = builder.name;
  }

  /**
   * Get the Spotify Web API endpoint URL of the category.
   *
   * @return A link to the Spotify Web API endpoint returning full details of the category.
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
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify ID</a>
   * of the category.
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
  public String toString() {
    return "Category(href=" + href + ", icons=" + Arrays.toString(icons) + ", id=" + id + ", name=" + name + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Category} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String href;
    private Image[] icons;
    private String id;
    private String name;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * The category href setter.
     *
     * @param href A link to the Spotify Web API endpoint returning full details of the category.
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
     * The category ID setter.
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
   * JsonUtil class for building {@link Category} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Category> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public Category createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Category.Builder()
        .setHref(
          hasAndNotNull(jsonObject, "href")
            ? jsonObject.get("href").getAsString()
            : null)
        .setIcons(
          hasAndNotNull(jsonObject, "icons")
            ? new Image.JsonUtil().createModelObjectArray(
            jsonObject.getAsJsonArray("icons"))
            : null)
        .setId(
          hasAndNotNull(jsonObject, "id")
            ? jsonObject.get("id").getAsString()
            : null)
        .setName(
          hasAndNotNull(jsonObject, "name")
            ? jsonObject.get("name").getAsString()
            : null)
        .build();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Category category = (Category) o;
    return Objects.equals(href, category.href) && Objects.equals(id, category.id) &&
      Objects.equals(name, category.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, id, name);
  }
}
