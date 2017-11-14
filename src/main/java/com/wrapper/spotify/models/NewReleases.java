package com.wrapper.spotify.models;

public class NewReleases {
  private Paging<AlbumSimplified> albums;

  public Paging<AlbumSimplified> getAlbums() {
    return albums;
  }

  public void setAlbums(Paging<AlbumSimplified> albums) {
    this.albums = albums;
  }
}
