package com.wrapper.spotify.objects;

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

  public static final class Builder extends AbstractModelObject.Builder<ArtistSimplified.Builder> {
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
}
