package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#cursor-object">Cursor objects</a>
 * by building instances from this class.
 */
public class Cursor extends AbstractModelObject {
  private final String after;

  private Cursor(final Builder builder) {
    super(builder);

    this.after = builder.after;
  }

  /**
   * Get the key of this {@link Cursor} to find the next set of items in a
   * <a href="https://developer.spotify.com/web-api/object-model/#cursor-based-paging-object">
   * cursor-based paging object</a>.
   *
   * @return The cursor to use as key to find the next page of items.
   * @see PagingCursorbased
   */
  public String getAfter() {
    return after;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Cursor} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String after;

    /**
     * The after key setter.
     *
     * @param after The cursor to use as key to find the next page of items.
     * @return A {@link Cursor.Builder}.
     */
    public Builder setAfter(String after) {
      this.after = after;
      return this;
    }

    @Override
    public Cursor build() {
      return new Cursor(this);
    }
  }

  /**
   * JsonUtil class for building {@link Cursor} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Cursor> {
    public Cursor createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Cursor.Builder()
              .setAfter(
                      hasAndNotNull(jsonObject, "after")
                              ? jsonObject.get("after").getAsString()
                              : null)
              .build();
    }
  }
}
