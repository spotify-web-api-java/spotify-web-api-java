package com.wrapper.spotify.objects;

import java.util.Date;

public class PlaylistTrack {

  private Date addedAt;
  private User addedBy;
  private boolean isLocal;
  private Track track;

  public Date getAddedAt() {
    return addedAt;
  }

  public void setAddedAt(Date addedAt) {
    this.addedAt = addedAt;
  }

  public User getAddedBy() {
    return addedBy;
  }

  public void setAddedBy(User addedBy) {
    this.addedBy = addedBy;
  }

  public boolean getIsLocal() {
    return isLocal;
  }

  public void setIsLocal(boolean isLocal) {
    this.isLocal = isLocal;
  }

  public Track getTrack() {
    return track;
  }

  public void setTrack(Track track) {
    this.track = track;
  }
}
