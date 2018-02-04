package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#followers-object">Follower objects</a>
 * by building instances from this class.
 */
public class Followers extends AbstractModelObject {
  private final String href;
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
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Followers} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String href;
    private Integer total;

    /**
     * The href setter.
     *
     * @param href A link to the Web API endpoint providing full details of the followers; {@code null} if not
     *             available.
     * @return A {@link Followers.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * The follower count setter.
     *
     * @param total The total number of followers.
     * @return A {@link Followers.Builder}.
     */
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
    public Followers createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Followers.Builder()
              .setHref(
                      hasAndNotNull(jsonObject, "href")
                              ? jsonObject.get("href").getAsString()
                              : null)
              .setTotal(
                      hasAndNotNull(jsonObject, "total")
                              ? jsonObject.get("total").getAsInt()
                              : null)
              .build();
    }
  }
}
