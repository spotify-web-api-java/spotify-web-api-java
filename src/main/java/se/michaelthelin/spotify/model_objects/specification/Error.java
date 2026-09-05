package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#error-object">
 * Error objects</a> by building instances from this class.
 */
public class Error extends AbstractModelObject {
  /** The HTTP status code. */
  private final Integer status;
  /** A short description of the cause of the error. */
  private final String message;

  private Error(final Builder builder) {
    super(builder);

    this.status = builder.status;
    this.message = builder.message;
  }

  /**
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/api-calls">HTTP status code</a>
   * of the {@link Error} object.
   *
   * @return The <a href="https://developer.spotify.com/documentation/web-api/concepts/api-calls">HTTP status code</a>.
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * Get the error message (description of the cause) of the {@link Error} object.
   *
   * @return A short description of the cause of the error.
   */
  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "Error(status=" + status + ", message=" + message + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Error} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Integer status;
    private String message;

    public Builder() {
      super();
    }

    public Builder setStatus(Integer status) {
      this.status = status;
      return this;
    }

    public Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    @Override
    public Error build() {
      return new Error(this);
    }
  }

  /**
   * JsonUtil class for building {@link Error} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Error> {

    public JsonUtil() {
      super();
    }

  }
}
