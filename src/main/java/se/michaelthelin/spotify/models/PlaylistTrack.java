package se.michaelthelin.spotify.models;

import java.util.Date;

public class PlaylistTrack {

  private Date addedAt;
  private User addedBy;
  private SimpleTrack track;

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

  public SimpleTrack getTrack() {
    return track;
  }

  public void setTrack(SimpleTrack track) {
    this.track = track;
  }
}
