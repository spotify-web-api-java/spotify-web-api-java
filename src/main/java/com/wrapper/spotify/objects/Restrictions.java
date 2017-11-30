package com.wrapper.spotify.objects;

public class Restrictions extends AbstractModelObject {
  private final String reason;

  private Restrictions(final Restrictions.Builder builder) {
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

  public static final class Builder extends AbstractModelObject.Builder<Restrictions.Builder> {
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
}
