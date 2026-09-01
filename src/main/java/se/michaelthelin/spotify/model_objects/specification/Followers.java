package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#followers-object">Follower objects</a>
 * by building instances from this class.
 */
public class Followers extends AbstractModelObject {
  /** This will always be set to null, as the Web API does not support it at the moment. */
  private final String href;
  /** The total number of followers. */
  private final Integer total;

  private Followers(final Builder builder) {
    super(builder);

    this.href = builder.href;
    this.total = builder.total;
  }

  /**
   * Get a link to the Web API endpoint providing full details of the followers object. <br>
   * <b>Please note:</b> This will always be set to {@code null}, as the Web API does not support it at the moment.
   *
   * @return A link to the Web API endpoint providing full details of the followers; {@code null} if not available.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the total number of followers.
   *
   * @return The total number of followers.
   */
  public Integer getTotal() {
    return total;
  }

  @Override
  public String toString() {
    return "Followers(href=" + href + ", total=" + total + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Followers} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String href;
    private Integer total;

    public Builder() {
      super();
    }

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setTotal(Integer total) {
      this.total = total;
      return this;
    }

    @Override
    public Followers build() {
      return new Followers(this);
    }
  }

  /**
   * JsonUtil class for building {@link Followers} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Followers> {

    public JsonUtil() {
      super();
    }

  }
}
