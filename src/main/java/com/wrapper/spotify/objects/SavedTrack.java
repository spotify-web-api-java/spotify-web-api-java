package com.wrapper.spotify.objects;

import java.util.Date;

public class SavedTrack extends AbstractModelObject {
  private final Date addedAt;
  private final Track track;

  private SavedTrack(final SavedTrack.Builder builder) {
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

  public static final class Builder extends AbstractModelObject.Builder<SavedTrack.Builder> {
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
    public SavedTrack build() {
      return new SavedTrack(this);
    }
  }
}
