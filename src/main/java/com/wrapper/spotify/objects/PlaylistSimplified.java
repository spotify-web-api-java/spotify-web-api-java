package com.wrapper.spotify.objects;

import net.sf.json.JSONObject;

import java.util.List;

public class PlaylistSimplified extends AbstractModelObject {
  private final boolean collaborative;
  private final ExternalUrls externalUrls;
  private final String href;
  private final String id;
  private final List<Image> images;
  private final String name;
  private final User owner;
  private final boolean publicAccess;
  private final String snapshotId;
  private final PlaylistTracksInformation tracks;
  private final ObjectType type;
  private final String uri;

  private PlaylistSimplified(final PlaylistSimplified.Builder builder) {
    super(builder);

    this.collaborative = builder.collaborative;
    this.externalUrls = builder.externalUrls;
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

  public ExternalUrls getExternalUrls() {
    return externalUrls;
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

  public PlaylistTracksInformation getTracks() {
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

  public static final class Builder extends AbstractModelObject.Builder {
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
    private ObjectType type;
    private String uri;

    public Builder setCollaborative(boolean collaborative) {
      this.collaborative = collaborative;
      return this;
    }

    public Builder setExternalUrls(ExternalUrls externalUrls) {
      this.externalUrls = externalUrls;
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

    public Builder setTracks(PlaylistTracksInformation tracks) {
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
    public PlaylistSimplified build() {
      return new PlaylistSimplified(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<PlaylistSimplified> {
    public PlaylistSimplified createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new PlaylistSimplified.Builder()
              .setCollaborative(jsonObject.getBoolean("collaborative"))
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getJSONObject("external_urls")))
              .setHref(jsonObject.getString("href"))
              .setId(jsonObject.getString("id"))
              .setImages(new Image.JsonUtil().createModelObjectList(jsonObject.getJSONArray("images")))
              .setName(jsonObject.getString("name"))
              .setOwner(new User.JsonUtil().createModelObject(jsonObject.getJSONObject("owner")))
              .setPublicAccess(jsonObject.getBoolean("public"))
              .setSnapshotId(jsonObject.getString("snapshot_id"))
              .setTracks(new PlaylistTracksInformation.JsonUtil().createModelObject(jsonObject.getJSONObject("tracks")))
              .setType(ObjectType.valueOf(jsonObject.getString("type")))
              .setUri(jsonObject.getString("uri"))
              .build();
    }
  }
}
