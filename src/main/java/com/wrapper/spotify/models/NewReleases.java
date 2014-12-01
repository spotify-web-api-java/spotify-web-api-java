package com.wrapper.spotify.models;

public class NewReleases {
  private Page<SimpleAlbum> albums;

  public Page<SimpleAlbum> getAlbums() {
    return albums;
  }

  public void setAlbums(Page<SimpleAlbum> albums) {
    this.albums = albums;
  }
}
