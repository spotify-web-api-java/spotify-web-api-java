package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.miscellaneous.PlaylistTracksInformation;
import com.wrapper.spotify.requests.data.search.interfaces.ISearchModelObject;

public class ArtistSimplified extends AbstractModelObject {
  private final ExternalUrl externalUrls;
  private final String href;
  private final String id;
  private final String name;
  private final ModelObjectType type;
  private final String uri;

  private ArtistSimplified(final ArtistSimplified.Builder builder) {
    super(builder);

    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.name = builder.name;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  public String getHref() {
    return href;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
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
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private String name;
    private ModelObjectType type;
    private String uri;

    public Builder setExternalUrls(ExternalUrl externalUrls) {
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

    public Builder setName(String name) {
      this.name = name;
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
    public ArtistSimplified build() {
      return new ArtistSimplified(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ArtistSimplified> {
    public ArtistSimplified createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new ArtistSimplified.Builder()
              .setExternalUrls(new ExternalUrl.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
              .setHref((jsonObject.get("href") instanceof JsonNull) ? null : jsonObject.get("href").getAsString())
              .setId((jsonObject.get("id") instanceof JsonNull) ? null : jsonObject.get("id").getAsString())
              .setName(jsonObject.get("name").getAsString())
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setUri((jsonObject.get("uri") instanceof JsonNull) ? null : jsonObject.get("uri").getAsString())
              .build();
    }
  }

  public static class PlaylistSimplified extends AbstractModelObject implements ISearchModelObject {
    private final boolean collaborative;
    private final ExternalUrl externalUrls;
    private final String href;
    private final String id;
    private final Image[] images;
    private final String name;
    private final User owner;
    private final Boolean publicAccess;
    private final String snapshotId;
    private final PlaylistTracksInformation tracks;
    private final ModelObjectType type;
    private final String uri;

    private PlaylistSimplified(final Builder builder) {
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

    public ExternalUrl getExternalUrls() {
      return externalUrls;
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

    public PlaylistTracksInformation getTracks() {
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
      private ExternalUrl externalUrls;
      private String href;
      private String id;
      private Image[] images;
      private String name;
      private User owner;
      private Boolean publicAccess;
      private String snapshotId;
      private PlaylistTracksInformation tracks;
      private ModelObjectType type;
      private String uri;

      public Builder setCollaborative(boolean collaborative) {
        this.collaborative = collaborative;
        return this;
      }

      public Builder setExternalUrls(ExternalUrl externalUrls) {
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

      public Builder setTracks(PlaylistTracksInformation tracks) {
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
      public PlaylistSimplified build() {
        return new PlaylistSimplified(this);
      }
    }

    public static final class JsonUtil extends AbstractModelObject.JsonUtil<PlaylistSimplified> {
      public PlaylistSimplified createModelObject(JsonObject jsonObject) {
        if (jsonObject == null || jsonObject.isJsonNull()) {
          return null;
        }

        return new Builder()
                .setCollaborative(jsonObject.get("collaborative").getAsBoolean())
                .setExternalUrls(new ExternalUrl.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
                .setHref(jsonObject.get("href").getAsString())
                .setId(jsonObject.get("id").getAsString())
                .setImages(new Image.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("images")))
                .setName(jsonObject.get("name").getAsString())
                .setOwner(new User.JsonUtil().createModelObject(jsonObject.getAsJsonObject("owner")))
                .setPublicAccess((jsonObject.get("public") instanceof JsonNull) ? null : jsonObject.get("public").getAsBoolean())
                .setSnapshotId(jsonObject.get("snapshot_id").getAsString())
                .setTracks(new PlaylistTracksInformation.JsonUtil().createModelObject(jsonObject.getAsJsonObject("tracks")))
                .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
                .setUri(jsonObject.get("uri").getAsString())
                .build();
      }
    }
  }
}
