package com.wrapper.spotify.objects;

public class PlaylistTracksInformation extends AbstractModelObject {
  private final String href;
  private final int total;

  private PlaylistTracksInformation(final PlaylistTracksInformation.Builder builder) {
    super(builder);

    this.href = builder.href;
    this.total = builder.total;
  }

  public String getHref() {
    return href;
  }

  public int getTotal() {
    return total;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<PlaylistTracksInformation.Builder> {
    private String href;
    private int total;

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setTotal(int total) {
      this.total = total;
      return this;
    }

    @Override
    public PlaylistTracksInformation build() {
      return new PlaylistTracksInformation(this);
    }
  }
}
