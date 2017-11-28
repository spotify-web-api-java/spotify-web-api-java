package com.wrapper.spotify.model_objects;

import java.util.Date;

public class SavedAlbum {

  private Date addedAt;
  private Album album;

  public Date getAddedAt() {
    return addedAt;
  }

  public void setAddedAt(Date addedAt) {
    this.addedAt = addedAt;
  }

  public Album getAlbum() {
    return album;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }
}
