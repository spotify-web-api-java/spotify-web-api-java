package com.wrapper.spotify.model_objects.credentials.error;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about Authorization Error objects by building instances from this class.
 *
 * @see <a href="https://developer.spotify.com/web-api/authorization-guide/">Spotify: Authorization Guide</a>
 */
public class AuthenticationError extends AbstractModelObject {
  private final String error;
  private final String error_description;

  private AuthenticationError(final Builder builder) {
    super(builder);

    this.error = builder.error;
    this.error_description = builder.error_description;
  }

  /**
   * Get the high level description of the error as specified in
   * <a href="https://tools.ietf.org/html/rfc6749#section-5.2">RFC 6749 Section 5.2</a>.
   *
   * @return A high level description of the error as specified in RFC 6749 Section 5.2.
   */
  public String getError() {
    return error;
  }

  /**
   * Get the more detailed description of the error as specified in
   * <a href="https://tools.ietf.org/html/rfc6749#section-4.1.2.1">RFC 6749 Section 4.1.2.1</a>.
   *
   * @return string    A more detailed description of the error as specified in RFC 6749 Section 4.1.2.1.
   */
  public String getError_description() {
    return error_description;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link AuthenticationError} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String error;
    private String error_description;

    /**
     * The error setter.
     *
     * @param error A high level description of the error as specified in RFC 6749 Section 5.2.
     * @return An {@link AuthenticationError.Builder}.
     */
    public Builder setError(String error) {
      this.error = error;
      return this;
    }

    /**
     * The error description setter.
     *
     * @param error_description A more detailed description of the error as specified in RFC 6749 Section 4.1.2.1.
     * @return An {@link AuthenticationError.Builder}.
     */
    public Builder setError_description(String error_description) {
      this.error_description = error_description;
      return this;
    }

    @Override
    public AuthenticationError build() {
      return new AuthenticationError(this);
    }
  }

  /**
   * JsonUtil class for building {@link AuthenticationError} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AuthenticationError> {
    public AuthenticationError createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AuthenticationError.Builder()
              .setError(
                      hasAndNotNull(jsonObject, "error")
                              ? jsonObject.get("error").getAsString()
                              : null)
              .setError_description(
                      hasAndNotNull(jsonObject, "error_description")
                              ? jsonObject.get("error_description").getAsString()
                              : null)
              .build();
    }
  }
}
