package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about Restriction objects by building instances from this class. <br><br>
 * <p>
 * Part of the response when <a href="https://developer.spotify.com/web-api/track-relinking-guide/">Track Relinking</a>
 * is applied, the original track is not available in the given market, and Spotify did not have any tracks to relink it
 * with. The track response will still contain metadata for the original track, and a restrictions object containing the
 * reason why the track is not available
 */
public class Restrictions extends AbstractModelObject {
  private final String reason;

  private Restrictions(final Builder builder) {
    super(builder);

    this.reason = builder.reason;
  }

  /**
   * Get the reason why the track is not available.
   *
   * @return The track restriction reason.
   */
  public String getReason() {
    return reason;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Restrictions} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String reason;

    /**
     * The restriction reason setter.
     *
     * @param reason The track restriction reason.
     * @return A {@link Restrictions.Builder}.
     */
    public Builder setReason(String reason) {
      this.reason = reason;
      return this;
    }

    @Override
    public Restrictions build() {
      return new Restrictions(this);
    }
  }

  /**
   * JSonUtil class for building {@link Restrictions} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Restrictions> {
    public Restrictions createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Restrictions.Builder()
              .setReason(
                      hasAndNotNull(jsonObject, "reason")
                              ? jsonObject.get("reason").getAsString()
                              : null)
              .build();
    }
  }
}
