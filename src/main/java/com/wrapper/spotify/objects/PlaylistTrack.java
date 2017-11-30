package com.wrapper.spotify.objects;

import java.util.Date;

public class PlaylistTrack extends AbstractModelObject {
  private final Date addedAt;
  private final User addedBy;
  private final boolean isLocal;
  private final Track track;

  private PlaylistTrack(final PlaylistTrack.Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.addedBy = builder.addedBy;
    this.isLocal = builder.isLocal;
    this.track = builder.track;
  }

  public Date getAddedAt() {
    return addedAt;
  }

  public User getAddedBy() {
    return addedBy;
  }

  public boolean getIsLocal() {
    return isLocal;
  }

  public Track getTrack() {
    return track;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<PlaylistTrack.Builder> {
    private Date addedAt;
    private User addedBy;
    private boolean isLocal;
    private Track track;

    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    public Builder setAddedBy(User addedBy) {
      this.addedBy = addedBy;
      return this;
    }

    public Builder setIsLocal(boolean isLocal) {
      this.isLocal = isLocal;
      return this;
    }

    public Builder setTrack(Track track) {
      this.track = track;
      return this;
    }

    @Override
    public PlaylistTrack build() {
      return new PlaylistTrack(this);
    }
  }
}
