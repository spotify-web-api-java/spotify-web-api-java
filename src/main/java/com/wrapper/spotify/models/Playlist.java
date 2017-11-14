package com.wrapper.spotify.models;

import java.util.List;

public class Playlist {

  private boolean collaborative;
  private String description;
  private ExternalUrls externalUrls;
  private Followers followers;
  private String href;
  private String id;
  private List<Image> images;
  private String name;
  private User owner;
  private boolean publicAccess;
  private Paging<PlaylistTrack> tracks;
  private ObjectType type = ObjectType.PLAYLIST;
  private String uri;

  public boolean isCollaborative() {
    return collaborative;
  }

  public void setCollaborative(boolean collaborative) {
    this.collaborative = collaborative;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  public void setExternalUrls(ExternalUrls externalUrls) {
    this.externalUrls = externalUrls;
  }

  public Followers getFollowers() {
    return followers;
  }

  public void setFollowers(Followers followers) {
    this.followers = followers;
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

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
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

  }

  }

  public String getUri() {
    return uri;
  public Paging<PlaylistTrack> getTracks() {
    return tracks;
  }

  public void setUri(String uri) {
    this.uri = uri;
  public void setTracks(Paging<PlaylistTrack> tracks) {
    this.tracks = tracks;
  }

  public boolean isPublicAccess() {
    return publicAccess;
  public ObjectType getType() {
    return type;
  }

  public void setPublicAccess(boolean publicAccess) {
    this.publicAccess = publicAccess;
  public void setType(ObjectType type) {
    this.type = type;
  }

  }

  }
}
