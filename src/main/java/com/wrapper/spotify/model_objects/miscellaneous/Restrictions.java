package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class Restrictions extends AbstractModelObject {
  private final String reason;

  private Restrictions(final Builder builder) {
    super(builder);

    this.reason = builder.reason;
  }

  public String getReason() {
    return reason;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private String reason;

    public Builder setReason(String reason) {
      this.reason = reason;
      return this;
    }

    @Override
    public Restrictions build() {
      return new Restrictions(this);
    }
  }

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
