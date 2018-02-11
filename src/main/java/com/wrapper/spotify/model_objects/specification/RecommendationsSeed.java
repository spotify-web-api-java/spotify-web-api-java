package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#recommendations-seed-object">
 * Recommendation Seed objects</a> by building instances from this class.
 */
public class RecommendationsSeed extends AbstractModelObject {
  private final Integer afterFilteringSize;
  private final Integer afterRelinkingSize;
  private final String href;
  private final String id;
  private final Integer initialPoolSize;
  private final ModelObjectType type;

  private RecommendationsSeed(final Builder builder) {
    super(builder);

    this.afterFilteringSize = builder.afterFilteringSize;
    this.afterRelinkingSize = builder.afterRelinkingSize;
    this.href = builder.href;
    this.id = builder.id;
    this.initialPoolSize = builder.initialPoolSize;
    this.type = builder.type;
  }

  /**
   * Get the number of tracks available after {@code min_*} and {@code max_*} filters have been applied.
   *
   * @return The number of tracks available after {@code min_*} and {@code max_*} filters have been applied.
   */
  public Integer getAfterFilteringSize() {
    return afterFilteringSize;
  }

  /**
   * Get the number of tracks available after relinking for regional availability.
   *
   * @return The number of tracks available after relinking for regional availability.
   */
  public Integer getAfterRelinkingSize() {
    return afterRelinkingSize;
  }

  /**
   * Get the link to the full track or artist data for this seed.
   *
   * @return A link to the full track or artist data for this seed. For tracks this will be a link to a {@link Track}
   * object. For artists a link to an {@link Artist} Object. For genre seeds, this value will be {@code null}.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the ID used to select this seed.
   *
   * @return The ID used to select this seed. This will be the same as the string used in the {@code seed_artists},
   * {@code seed_tracks} or {@code seed_genres} request parameter.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the number of recommended tracks available for this seed.
   *
   * @return The number of recommended tracks available for this seed.
   */
  public Integer getInitialPoolSize() {
    return initialPoolSize;
  }

  /**
   * Get the entity type of this seed.
   *
   * @return The model object type of this seed. One of {@code artist}, {@code track} or {@code genre}.
   */
  public ModelObjectType getType() {
    return type;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link RecommendationsSeed} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Integer afterFilteringSize;
    private Integer afterRelinkingSize;
    private String href;
    private String id;
    private Integer initialPoolSize;
    private ModelObjectType type;

    /**
     * The after filtering size setter.
     *
     * @param afterFilteringSize The number of tracks available after {@code min_*} and {@code max_*} filters have been
     *                           applied.
     * @return A {@link RecommendationsSeed.Builder}.
     */
    public Builder setAfterFilteringSize(Integer afterFilteringSize) {
      this.afterFilteringSize = afterFilteringSize;
      return this;
    }

    /**
     * The after relinking size setter.
     *
     * @param afterRelinkingSize The number of tracks available after relinking for regional availability.
     * @return A {@link RecommendationsSeed.Builder}.
     */
    public Builder setAfterRelinkingSize(Integer afterRelinkingSize) {
      this.afterRelinkingSize = afterRelinkingSize;
      return this;
    }

    /**
     * The href setter.
     *
     * @param href A link to the full track or artist data for this seed. For tracks this will be a link to a
     *             {@link Track} object. For artists a link to an {@link Artist} Object. For genre seeds, this value
     *             will be {@code null}.
     * @return A {@link RecommendationsSeed.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * The ID setter.
     *
     * @param id The ID used to select this seed. This will be the same as the string used in the {@code seed_artists},
     *           {@code seed_tracks} or {@code seed_genres} request parameter.
     * @return A {@link RecommendationsSeed.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * The initial pool size setter.
     *
     * @param initialPoolSize The number of recommended tracks available for this seed.
     * @return A {@link RecommendationsSeed.Builder}.
     */
    public Builder setInitialPoolSize(Integer initialPoolSize) {
      this.initialPoolSize = initialPoolSize;
      return this;
    }

    /**
     * The model object type setter.
     *
     * @param type The model object type of this seed. One of {@code artist}, {@code track} or {@code genre}.
     * @return A {@link RecommendationsSeed.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    @Override
    public RecommendationsSeed build() {
      return new RecommendationsSeed(this);
    }
  }

  /**
   * JsonUtil class for building {@link RecommendationsSeed} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<RecommendationsSeed> {
    public RecommendationsSeed createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new RecommendationsSeed.Builder()
              .setAfterFilteringSize(
                      hasAndNotNull(jsonObject, "afterFilteringSize")
                              ? jsonObject.get("afterFilteringSize").getAsInt()
                              : null)
              .setAfterRelinkingSize(
                      hasAndNotNull(jsonObject, "afterRelinkingSize")
                              ? jsonObject.get("afterRelinkingSize").getAsInt()
                              : null)
              .setHref(
                      hasAndNotNull(jsonObject, "href")
                              ? jsonObject.get("href").getAsString()
                              : null)
              .setId(
                      hasAndNotNull(jsonObject, "id")
                              ? jsonObject.get("id").getAsString()
                              : null)
              .setInitialPoolSize(
                      hasAndNotNull(jsonObject, "initialPoolSize")
                              ? jsonObject.get("initialPoolSize").getAsInt()
                              : null)
              .setType(
                      hasAndNotNull(jsonObject, "type")
                              ? ModelObjectType.keyOf(
                              jsonObject.get("type").getAsString().toLowerCase())
                              : null)
              .build();
    }
  }
}
