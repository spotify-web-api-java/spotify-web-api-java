package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about playlists by building instances from this class.
 */
public class Playlist extends AbstractModelObject {
  private final boolean collaborative;
  private final String description;
  private final ExternalUrl externalUrls;
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
   * @return The external urls of a playlist.
   */
  public ExternalUrl getExternalUrls() {
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
   * Check whether a playlist is available in public or is private.
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
   * @return A page of playlist tracks.
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
    private ExternalUrl externalUrls;
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
    public Builder setExternalUrls(ExternalUrl externalUrls) {
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
     * Set Spotify id of the playlist to be built.
     *
     * @param id Spotify playlist id.
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
    public Builder setImages(Image... images) {
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
     * Set a page of playlist tracks of the playlist to be built.
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
              .setCollaborative(
                      hasAndNotNull(jsonObject, "collaborative")
                              ? jsonObject.get("collaborative").getAsBoolean()
                              : null)
              .setDescription(
                      hasAndNotNull(jsonObject, "description")
                              ? jsonObject.get("description").getAsString()
                              : null)
              .setExternalUrls(
                      hasAndNotNull(jsonObject, "external_urls")
                              ? new ExternalUrl.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("external_urls"))
                              : null)
              .setFollowers(
                      hasAndNotNull(jsonObject, "followers")
                              ? new Followers.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("followers"))
                              : null)
              .setHref(
                      hasAndNotNull(jsonObject, "href")
                              ? jsonObject.get("href").getAsString()
                              : null)
              .setId(
                      hasAndNotNull(jsonObject, "id")
                              ? jsonObject.get("id").getAsString()
                              : null)
              .setImages(
                      hasAndNotNull(jsonObject, "images")
                              ? new Image.JsonUtil().createModelObjectArray(
                              jsonObject.getAsJsonArray("images"))
                              : null)
              .setName(
                      hasAndNotNull(jsonObject, "name")
                              ? jsonObject.get("name").getAsString()
                              : null)
              .setOwner(
                      hasAndNotNull(jsonObject, "owner")
                              ? new User.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("owner"))
                              : null)
              .setPublicAccess(
                      hasAndNotNull(jsonObject, "public")
                              ? jsonObject.get("public").getAsBoolean()
                              : null)
              .setSnapshotId(
                      hasAndNotNull(jsonObject, "snapshot_id")
                              ? jsonObject.get("snapshot_id").getAsString()
                              : null)
              .setTracks(
                      hasAndNotNull(jsonObject, "tracks")
                              ? new PlaylistTrack.JsonUtil().createModelObjectPaging(
                              jsonObject.getAsJsonObject("tracks"))
                              : null)
              .setType(
                      hasAndNotNull(jsonObject, "type")
                              ? ModelObjectType.valueOf(
                              jsonObject.get("type").getAsString().toUpperCase())
                              : null)
              .setUri(
                      hasAndNotNull(jsonObject, "uri")
                              ? jsonObject.get("uri").getAsString()
                              : null)
              .build();
    }
  }
}
