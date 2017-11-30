package com.wrapper.spotify.objects;

import java.util.Date;

public class LibraryTrack extends AbstractModelObject {
  private final Date addedAt;
  private final Track track;

  private LibraryTrack(final LibraryTrack.Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.track = builder.track;
  }

  public Date getAddedAt() {
    return addedAt;
  }

  public Track getTrack() {
    return track;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<LibraryTrack.Builder> {
    private Date addedAt;
    private Track track;

    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    public Builder setTrack(Track track) {
      this.track = track;
      return this;
    }

    @Override
    public LibraryTrack build() {
      return new LibraryTrack(this);
    }
  }
}
