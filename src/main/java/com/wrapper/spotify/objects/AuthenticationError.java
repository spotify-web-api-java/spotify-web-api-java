package com.wrapper.spotify.objects;

import com.google.gson.JsonObject;
import com.wrapper.spotify.objects.model_objects.AbstractModelObject;

public class AuthenticationError extends AbstractModelObject {
  private final String error;
  private final String error_description;

  private AuthenticationError(final AuthenticationError.Builder builder) {
    super(builder);

    this.error = builder.error;
    this.error_description = builder.error_description;
  }

  public String getError() {
    return error;
  }

  public String getError_description() {
    return error_description;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private String error;
    private String error_description;

    public Builder setError(String error) {
      this.error = error;
      return this;
    }

    public Builder setError_description(String error_description) {
      this.error_description = error_description;
      return this;
    }

    @Override
    public AuthenticationError build() {
      return new AuthenticationError(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AuthenticationError> {
    public AuthenticationError createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AuthenticationError.Builder()
              .setError(jsonObject.get("error").getAsString())
              .setError_description(jsonObject.get("error_description").getAsString())
              .build();
    }
  }
}
