package com.wrapper.spotify.objects;

import java.util.Date;

public class SavedAlbum extends AbstractModelObject {
  private final Date addedAt;
  private final Album album;

  private SavedAlbum(final SavedAlbum.Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.album = builder.album;
  }

  public Date getAddedAt() {
    return addedAt;
  }

  public Album getAlbum() {
    return album;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<SavedAlbum.Builder> {
    private Date addedAt;
    private Album album;

    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    public Builder setAlbum(Album album) {
      this.album = album;
      return this;
    }

    @Override
    public SavedAlbum build() {
      return new SavedAlbum(this);
    }
  }
}
