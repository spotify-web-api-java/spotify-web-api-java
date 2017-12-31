package com.wrapper.spotify.model_objects;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.ExternalUrls;
import com.wrapper.spotify.model_objects.specification.Followers;
import com.wrapper.spotify.model_objects.specification.Image;
import com.wrapper.spotify.model_objects.specification.Paging;

public class Playlist extends AbstractModelObject {
  private final boolean collaborative;
  private final String description;
  private final ExternalUrls externalUrls;
  private final Followers followers;
  private final String href;
  private final String id;
  private final Image[] images;
  private final String name;
  private final User owner;
  private final Boolean publicAccess;
  private final String snapshotId;
  private final Paging<PlaylistTrack> tracks;
  private final ModelObjectType type;
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

  public Image[] getImages() {
    return images;
  }

  public String getName() {
    return name;
  }

  public User getOwner() {
    return owner;
  }

  public Boolean getIsPublicAccess() {
    return publicAccess;
  }

  public String getSnapshotId() {
    return snapshotId;
  }

  public Paging<PlaylistTrack> getTracks() {
    return tracks;
  }

  public ModelObjectType getType() {
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
    private String description;
    private ExternalUrls externalUrls;
    private Followers followers;
    private String href;
    private String id;
    private Image[] images;
    private String name;
    private User owner;
    private Boolean publicAccess;
    private String snapshotId;
    private Paging<PlaylistTrack> tracks;
    private ModelObjectType type;
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

    public Builder setImages(Image... images) {
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

    public Builder setPublicAccess(Boolean publicAccess) {
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

    public Builder setType(ModelObjectType type) {
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

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Playlist> {
    public Playlist createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Playlist.Builder()
              .setCollaborative(jsonObject.get("collaborative").getAsBoolean())
              .setDescription((jsonObject.get("description") instanceof JsonNull) ? null : jsonObject.get("description").getAsString())
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
              .setFollowers(new Followers.JsonUtil().createModelObject(jsonObject.getAsJsonObject("followers")))
              .setHref(jsonObject.get("href").getAsString())
              .setId(jsonObject.get("id").getAsString())
              .setImages(new Image.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("images")))
              .setName(jsonObject.get("name").getAsString())
              .setOwner(new User.JsonUtil().createModelObject(jsonObject.getAsJsonObject("owner")))
              .setPublicAccess((jsonObject.get("public") instanceof JsonNull) ? null : jsonObject.get("public").getAsBoolean())
              .setSnapshotId(jsonObject.get("snapshot_id").getAsString())
              .setTracks(new PlaylistTrack.JsonUtil().createModelObjectPaging(jsonObject.getAsJsonObject("tracks")))
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setUri(jsonObject.get("uri").getAsString())
              .build();
    }
  }
}
