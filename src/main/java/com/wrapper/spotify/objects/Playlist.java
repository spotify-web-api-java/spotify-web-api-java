package com.wrapper.spotify.objects;

import java.util.List;

public class Playlist extends AbstractModelObject {
  private final boolean collaborative;
  private final String description;
  private final ExternalUrls externalUrls;
  private final Followers followers;
  private final String href;
  private final String id;
  private final List<Image> images;
  private final String name;
  private final User owner;
  private final boolean publicAccess;
  private final String snapshotId;
  private final Paging<PlaylistTrack> tracks;
  private final ObjectType type;
  private final String uri;

  private Playlist(final Playlist.Builder builder) {
    super(builder);

    this.collaborative = builder.collaborative;
    this.description = builder.description;
    this.externalUrls = builder.externalUrls;
    this.followers = builder.followers;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.name = builder.name;
    this.owner = builder.owner;
    this.publicAccess = builder.publicAccess;
    this.snapshotId = builder.snapshotId;
    this.tracks = builder.tracks;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  public boolean getIsCollaborative() {
    return collaborative;
  }

  public String getDescription() {
    return description;
  }

  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  public Followers getFollowers() {
    return followers;
  }

  public String getHref() {
    return href;
  }

  public String getId() {
    return id;
  }

  public List<Image> getImages() {
    return images;
  }

  public String getName() {
    return name;
  }

  public User getOwner() {
    return owner;
  }

  public boolean getIsPublicAccess() {
    return publicAccess;
  }

  public String getSnapshotId() {
    return snapshotId;
  }

  public Paging<PlaylistTrack> getTracks() {
    return tracks;
  }

  public ObjectType getType() {
    return type;
  }

  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<Playlist.Builder> {
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
    private ObjectType type;
    private String uri;

    public Builder setCollaborative(boolean collaborative) {
      this.collaborative = collaborative;
      return this;
    }

    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder setExternalUrls(ExternalUrls externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    public Builder setFollowers(Followers followers) {
      this.followers = followers;
      return this;
    }

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setImages(List<Image> images) {
      this.images = images;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setOwner(User owner) {
      this.owner = owner;
      return this;
    }

    public Builder setPublicAccess(boolean publicAccess) {
      this.publicAccess = publicAccess;
      return this;
    }

    public Builder setSnapshotId(String snapshotId) {
      this.snapshotId = snapshotId;
      return this;
    }

    public Builder setTracks(Paging<PlaylistTrack> tracks) {
      this.tracks = tracks;
      return this;
    }

    public Builder setType(ObjectType type) {
      this.type = type;
      return this;
    }

    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public Playlist build() {
      return new Playlist(this);
    }
  }
}
