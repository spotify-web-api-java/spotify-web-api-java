package com.wrapper.spotify.objects;

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

  public static final class Builder extends AbstractModelObject.Builder<Cursor.Builder> {
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
}
