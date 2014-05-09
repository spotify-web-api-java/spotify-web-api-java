package se.michaelthelin.spotify.models;

import java.util.List;

public class Playlist {

  private boolean collaborative;
  private String description;
  private ExternalUrls externalUrls;
  private int followersCount;
  private String href;
  private String id;
  private List<Image> images;
  private User owner;
  private String name;
  private boolean publicAccess;
  private int tracksCount;
  private SpotifyEntityType type = SpotifyEntityType.PLAYLIST;
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

  public int getFollowersCount() {
    return followersCount;
  }

  public void setFollowersCount(int followersCount) {
    this.followersCount = followersCount;
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

  public int getTracksCount() {
    return tracksCount;
  }

  public void setTracksCount(int tracksCount) {
    this.tracksCount = tracksCount;
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

  public boolean isPublicAccess() {
    return publicAccess;
  }

  public void setPublicAccess(boolean publicAccess) {
    this.publicAccess = publicAccess;
  }
}
