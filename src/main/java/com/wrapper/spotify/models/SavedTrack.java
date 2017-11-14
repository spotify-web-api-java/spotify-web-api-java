package com.wrapper.spotify.models;

import java.util.Date;

public class SavedTrack {

  private Date addedAt;
  private Track track;

  public Date getAddedAt() {
    return addedAt;
  }

  public void setAddedAt(Date addedAt) {
    this.addedAt = addedAt;
  }

  public Track getTrack() {
    return track;
  }

  public void setTrack(Track track) {
    this.track = track;
  }
}
