package com.wrapper.spotify.objects;

public class Error extends AbstractModelObject {
  private final int status;
  private final String message;

  private Error(final Error.Builder builder) {
    super(builder);

    this.status = builder.status;
    this.message = builder.message;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<Error.Builder> {
    private int status;
    private String message;

    public Builder setStatus(int status) {
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
}
