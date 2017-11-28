package com.wrapper.spotify.model_objects;

import java.util.List;

public class PlaylistSimplified {

  private boolean collaborative;
  private ExternalUrls externalUrls;
  private String href;
  private String id;
  private List<Image> images;
  private String name;
  private User owner;
  private boolean publicAccess;
  private String snapshotId;
  private PlaylistTracksInformation tracks;
  private ObjectType type = ObjectType.PLAYLIST;
  private String uri;

  public boolean getIsCollaborative() {
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

  public PlaylistTracksInformation getTracks() {
    return tracks;
  }

  public void setTracks(PlaylistTracksInformation tracks) {
    this.tracks = tracks;
  }

  public ObjectType getType() {
    return type;
  }

  public void setType(ObjectType type) {
    this.type = type;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }
}
