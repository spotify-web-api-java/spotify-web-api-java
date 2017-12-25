package com.wrapper.spotify.model_objects;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 * Retrieve information about playlists by building instances from this class.
 */
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

  /**
   * Check whether a playlist is collaborative or not.
   *
   * @return "true" if the playlist is collaborytive, "false" if not.
   */
  public boolean getIsCollaborative() {
    return collaborative;
  }

  /**
   * Get the description of a playlist.
   *
   * @return Playlist description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get the external urls of a playlist.<br>
   * Example: Spotify-URL.
   *
   * @return The external urls of a playlist..
   */
  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get information about the followers of a playlist.<br>
   * Example: Follower count.
   *
   * @return Followers object.
   */
  public Followers getFollowers() {
    return followers;
  }

  /**
   * Get the full Spotify API endpoint url of a playlist.
   *
   * @return A Spotify API endpoint url.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify id of a playlist.
   *
   * @return A Spotify playlist id.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the cover image of a playlist in different sizes.
   *
   * @return An array of images in different sizes.
   */
  public Image[] getImages() {
    return images;
  }

  /**
   * Get the name of a playlist.
   *
   * @return Playlist name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the owners user object of a playlist.
   *
   * @return A user object.
   */
  public User getOwner() {
    return owner;
  }

  /**
   * Check whether a playlist is available in public or private.
   *
   * @return "true" if the playlist is public, "false" if not.
   */
  public Boolean getIsPublicAccess() {
    return publicAccess;
  }

  /**
   * Get the latest snapshot id of a playlist.
   *
   * @return A snapshot id.
   */
  public String getSnapshotId() {
    return snapshotId;
  }

  /**
   * Get a page of playlist tracks of a playlist.
   *
   * @return A page of tracks.
   */
  public Paging<PlaylistTrack> getTracks() {
    return tracks;
  }

  /**
   * Get the model object type. In this case "playlist".
   *
   * @return A model object type.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify uri of a playlist.
   *
   * @return Spotify playlist uri.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building playlist instances.
   */
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

    /**
     * Set whether the playlist to be built is collaborative or not.
     *
     * @param collaborative "true" for collaborative", false if not.
     * @return A builder object.
     */
    public Builder setCollaborative(boolean collaborative) {
      this.collaborative = collaborative;
      return this;
    }

    /**
     * Set the description of the playlist to be built.
     *
     * @param description Playlist description.
     * @return A builder object.
     */
    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    /**
     * Set external urls of the playlist to be built.
     *
     * @param externalUrls External urls object.
     * @return A builder object.
     */
    public Builder setExternalUrls(ExternalUrls externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set the followers object of the playlist to be built.
     *
     * @param followers A followers object.
     * @return A builder object.
     */
    public Builder setFollowers(Followers followers) {
      this.followers = followers;
      return this;
    }

    /**
     * Set href of Spotify api endpoint of the playlist to be built.
     *
     * @param href Spotify api endpoint url.
     * @return A builder object.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set album id of the playlist to be built.
     *
     * @param id Playlist id.
     * @return A builder object.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the cover image of the playlist to be built.
     *
     * @param images An array of image objects.
     * @return A builder object.
     */
    public Builder setImages(Image[] images) {
      this.images = images;
      return this;
    }

    /**
     * Set the name of the playlist to be built.
     *
     * @param name The playlist name.
     * @return A builder object.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the owner of the playlist to be built.
     *
     * @param owner A user object.
     * @return A builder object.
     */
    public Builder setOwner(User owner) {
      this.owner = owner;
      return this;
    }

    /**
     * Set whether the playlist to be built is available in public or not.
     *
     * @param publicAccess "true" if public, "false" if not.
     * @return A builder object.
     */
    public Builder setPublicAccess(Boolean publicAccess) {
      this.publicAccess = publicAccess;
      return this;
    }

    /**
     * Set the snapshot id of the playlist to be built.
     *
     * @param snapshotId Snapshot id.
     * @return A builder object.
     */
    public Builder setSnapshotId(String snapshotId) {
      this.snapshotId = snapshotId;
      return this;
    }

    /**
     * Set a page of tracks of the playlist to be built.
     *
     * @param tracks A page of playlist tracks.
     * @return A builder object.
     */
    public Builder setTracks(Paging<PlaylistTrack> tracks) {
      this.tracks = tracks;
      return this;
    }

    /**
     * Set the type of the model object. In this case "playlist".
     *
     * @param type The model object type.
     * @return A builder object.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify uri of the playlist to be built.
     *
     * @param uri The Spotify playlist uri.
     * @return A builder object.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public Playlist build() {
      return new Playlist(this);
    }
  }

  /**
   * JsonUtil class for building playlist instances.
   */
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
