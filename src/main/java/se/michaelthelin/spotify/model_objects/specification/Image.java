package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#image-object">Image objects</a>
 * by building instances from this class.
 */
public class Image extends AbstractModelObject {
  /** The image height in pixels. */
  private final Integer height;
  /** The source URL of the image. */
  private final String url;
  /** The image width in pixels. */
  private final Integer width;

  private Image(final Builder builder) {
    super(builder);

    this.height = builder.height;
    this.url = builder.url;
    this.width = builder.width;
  }

  /**
   * Get the height of the image in pixels.
   *
   * @return The image height in pixels. If unknown: {@code null}.
   */
  public Integer getHeight() {
    return height;
  }

  /**
   * Get the source URL of the image.
   *
   * @return The source URL of the image.
   */
  public String getUrl() {
    return url;
  }

  /**
   * Get the width of the image in pixels.
   *
   * @return The image width in pixels. If unknown: {@code null}.
   */
  public Integer getWidth() {
    return width;
  }

  @Override
  public String toString() {
    return "Image(height=" + height + ", url=" + url + ", width=" + width + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Image} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Integer height;
    private String url;
    private Integer width;

    public Builder() {
      super();
    }

    public Builder setHeight(Integer height) {
      this.height = height;
      return this;
    }

    public Builder setUrl(String url) {
      this.url = url;
      return this;
    }

    public Builder setWidth(Integer width) {
      this.width = width;
      return this;
    }

    @Override
    public Image build() {
      return new Image(this);
    }
  }

  /**
   * JsonUtil class for building {@link Image} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Image> {

    public JsonUtil() {
      super();
    }

  }
}
