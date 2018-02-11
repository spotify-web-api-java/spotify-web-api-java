package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.CopyrightType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#copyright-object">Copyright objects</a>
 * by building instances from this class.
 */
public class Copyright extends AbstractModelObject {
  private final String text;
  private final CopyrightType type;

  private Copyright(final Builder builder) {
    super(builder);

    this.text = builder.text;
    this.type = builder.type;
  }

  /**
   * Get the copyright text of the {@link Album}.
   *
   * @return The copyright text for this {@link Album}.
   */
  public String getText() {
    return text;
  }

  /**
   * Get the {@link CopyrightType} of this {@link Copyright} object.
   *
   * @return The type of copyright: C = the copyright, P = the sound recording (performance) copyright.
   */
  public CopyrightType getType() {
    return type;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Copyright} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String text;
    private CopyrightType type;

    /**
     * The copyright text setter.
     *
     * @param text The copyright text for this album.
     * @return A {@link Copyright.Builder}
     */
    public Builder setText(String text) {
      this.text = text;
      return this;
    }

    /**
     * The copyright type setter.
     *
     * @param type The type of copyright: C = the copyright, P = the sound recording (performance) copyright.
     * @return A {@link Copyright.Builder}
     */
    public Builder setType(CopyrightType type) {
      this.type = type;
      return this;
    }

    @Override
    public Copyright build() {
      return new Copyright(this);
    }
  }

  /**
   * JsonUtil class for building {@link Copyright} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Copyright> {
    public Copyright createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Copyright.Builder()
              .setText(
                      hasAndNotNull(jsonObject, "text")
                              ? jsonObject.get("text").getAsString()
                              : null)
              .setType(
                      hasAndNotNull(jsonObject, "type")
                              ? CopyrightType.keyOf(
                              jsonObject.get("type").getAsString().toLowerCase())
                              : null)
              .build();
    }
  }
}
