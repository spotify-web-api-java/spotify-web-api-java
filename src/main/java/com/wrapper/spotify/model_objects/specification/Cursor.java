package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class Cursor extends AbstractModelObject {
  private final String after;

  private Cursor(final Cursor.Builder builder) {
    super(builder);

    this.after = builder.after;
  }

  public String getAfter() {
    return after;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private String after;

    public Builder setAfter(String after) {
      this.after = after;
      return this;
    }

    @Override
    public Cursor build() {
      return new Cursor(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Cursor> {
    public Cursor createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Cursor.Builder()
              .setAfter(jsonObject.get("after").getAsString())
              .build();
    }
  }
}
