package com.wrapper.spotify.model_objects;

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
  private String snapshotId;
  private Paging<PlaylistTrack> tracks;
  private ModelObjectType type = ModelObjectType.PLAYLIST;
  private String uri;

  public boolean getIsCollaborative() {
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public boolean getIsPublicAccess() {
    return publicAccess;
  }

  public void setPublicAccess(boolean publicAccess) {
    this.publicAccess = publicAccess;
  }

  public String getSnapshotId() {
    return snapshotId;
  }

  public void setSnapshotId(String snapshotId) {
    this.snapshotId = snapshotId;
  }

  public Paging<PlaylistTrack> getTracks() {
    return tracks;
  }

  public void setTracks(Paging<PlaylistTrack> tracks) {
    this.tracks = tracks;
  }

  public ModelObjectType getType() {
    return type;
  }

  public void setType(ModelObjectType type) {
    this.type = type;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }
}
