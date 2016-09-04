/**
 * Copyright (C) 2014 Spotify AB
 */
package com.wrapper.spotify.models;

import java.util.Date;

public class LibraryAlbum {

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
