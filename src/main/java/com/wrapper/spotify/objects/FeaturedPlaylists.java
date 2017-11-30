package com.wrapper.spotify.objects;

public class FeaturedPlaylists extends AbstractModelObject {
  private final String message;
  private final Paging<PlaylistSimplified> playlists;

  private FeaturedPlaylists(final FeaturedPlaylists.Builder builder) {
    super(builder);

    this.message = builder.message;
    this.playlists = builder.playlists;
  }

  public String getMessage() {
    return message;
  }

  public Paging<PlaylistSimplified> getPlaylists() {
    return playlists;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<FeaturedPlaylists.Builder> {
    private String message;
    private Paging<PlaylistSimplified> playlists;

    public Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    public Builder setPlaylists(Paging<PlaylistSimplified> playlists) {
      this.playlists = playlists;
      return this;
    }

    @Override
    public FeaturedPlaylists build() {
      return new FeaturedPlaylists(this);
    }
  }
}
