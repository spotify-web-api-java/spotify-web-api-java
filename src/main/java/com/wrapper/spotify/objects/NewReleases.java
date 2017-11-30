package com.wrapper.spotify.objects;

public class NewReleases extends AbstractModelObject {
  private final Paging<AlbumSimplified> albums;

  private NewReleases(final NewReleases.Builder builder) {
    super(builder);

    this.albums = builder.albums;
  }

  public Paging<AlbumSimplified> getAlbums() {
    return albums;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<NewReleases.Builder> {
    private Paging<AlbumSimplified> albums;

    public Builder setAlbums(Paging<AlbumSimplified> albums) {
      this.albums = albums;
      return this;
    }

    @Override
    public NewReleases build() {
      return new NewReleases(this);
    }
  }
}
