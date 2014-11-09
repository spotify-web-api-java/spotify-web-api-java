package com.wrapper.spotify.models;

import java.util.List;

public class SimplePlaylist {

  private boolean collaborative;
  private ExternalUrls externalUrls;
  private String href;
  private String id;
  private List<Image> images;
  private User owner;
  private String name;
  private boolean publicAccess;
  private PlaylistTracksInformation tracks;
  private SpotifyEntityType type = SpotifyEntityType.PLAYLIST;
  private String uri;

  public boolean isCollaborative() {
    return collaborative;
  }

  public void setCollaborative(boolean collaborative) {
    this.collaborative = collaborative;
  }

  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  public void setExternalUrls(ExternalUrls externalUrls) {
    this.externalUrls = externalUrls;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isPublicAccess() {
    return publicAccess;
  }

  public void setPublicAccess(boolean publicAccess) {
    this.publicAccess = publicAccess;
  }

  public PlaylistTracksInformation getTracks() {
    return tracks;
  }

  public void setTracks(PlaylistTracksInformation tracks) {
    this.tracks = tracks;
  }

  public SpotifyEntityType getType() {
    return type;
  }

  public void setType(SpotifyEntityType type) {
    this.type = type;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }
}
