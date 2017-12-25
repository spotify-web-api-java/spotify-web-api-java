package com.wrapper.spotify.model_objects;

import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.CopyrightType;

public class Copyright extends AbstractModelObject {
  private final String text;
  private final CopyrightType type;

  private Copyright(final Copyright.Builder builder) {
    super(builder);

    this.text = builder.text;
    this.type = builder.type;
  }

  public String getText() {
    return text;
  }

  public CopyrightType getType() {
    return type;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private String text;
    private CopyrightType type;

    public Builder setText(String text) {
      this.text = text;
      return this;
    }

    public Builder setType(CopyrightType type) {
      this.type = type;
      return this;
    }

    @Override
    public Copyright build() {
      return new Copyright(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Copyright> {
    public Copyright createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Copyright.Builder()
              .setText(jsonObject.get("text").getAsString())
              .setType(CopyrightType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .build();
    }
  }
}
