package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.miscellaneous.PlaylistTracksInformation;
import se.michaelthelin.spotify.requests.data.playlists.RemoveItemsFromPlaylistRequest;
import se.michaelthelin.spotify.requests.data.search.interfaces.ISearchModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#playlist-object-simplified">
 * simplified Playlist objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = PlaylistSimplified.Builder.class)
public class PlaylistSimplified extends AbstractModelObject implements ISearchModelObject {
  private final Boolean collaborative;
  private final String description;
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
    this.description = builder.description;
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

  /**
   * Check whether the playlist is collaborative or not.
   *
   * @return {@code true} if the owner allows other users to modify the playlist, {@code false} if not.
   * @see <a
   * href="https://developer.spotify.com/documentation/web-api/concepts/playlists">
   * Spotify: Working With Playlists</a>
   */
  public Boolean getIsCollaborative() {
    return collaborative;
  }

  /**
   * Get the description of the playlist.
   *
   * @return The playlist description. Only returned for modified, verified playlists, otherwise null.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get the external URLs of the playlist. <br>
   * Example: Spotify-URL.
   *
   * @return Known external URLs for this playlist.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify API endpoint url of the playlist.
   *
   * @return A link to the Web API endpoint providing full details of the playlist.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify ID</a>
   * of a playlist.
   *
   * @return The Spotify ID for the playlist.
   */
  public String getId() {
    return id;
  }

  /**
   * Images for the playlist. The array may be empty or contain up to three images. The images are returned by size in
   * descending order. <br>
   * <b>Note:</b> If returned, the source URL for the image is temporary and will expire in less than a day.
   *
   * @return An array of images in different sizes.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/playlists">
   * Spotify: Working With Playlists</a>
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
   * @return {@code true} the playlist is public, {@code false} the playlist is private, {@code null}
   * the playlist status is not relevant.
   * @see <a
   * href="https://developer.spotify.com/documentation/web-api/concepts/playlists">
   * Spotify: Working With Playlists</a>
   */
  public Boolean getIsPublicAccess() {
    return publicAccess;
  }

  /**
   * Get the snapshot ID, the version identifier for the current playlist. Can be supplied in other requests to target
   * a specific playlist version.
   *
   * @return The version identifier for the current playlist.
   * @see RemoveItemsFromPlaylistRequest
   */
  public String getSnapshotId() {
    return snapshotId;
  }

  /**
   * Get information about the tracks of the playlist.
   *
   * @return Information about the tracks of the playlist.
   */
  public PlaylistTracksInformation getTracks() {
    return tracks;
  }

  /**
   * Get the model object type. In this case "playlist".
   *
   * @return The object type: "playlist"
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URI</a>
   * of a playlist.
   *
   * @return Spotify playlist URI.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return "PlaylistSimplified(name=" + name + ", tracks=" + tracks + ", collaborative=" + collaborative
        + ", description=" + description + ", externalUrls=" + externalUrls + ", href=" + href + ", id=" + id
        + ", images=" + Arrays.toString(images) + ", owner=" + owner + ", publicAccess=" + publicAccess
        + ", snapshotId=" + snapshotId + ", type=" + type + ", uri=" + uri + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link PlaylistSimplified} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Boolean collaborative;
    private String description;
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

    /**
     * Set whether the playlist to be built is collaborative or not.
     *
     * @param collaborative {@code true} if the owner allows other users to modify the playlist, {@code false} if not.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setCollaborative(Boolean collaborative) {
      this.collaborative = collaborative;
      return this;
    }

    /**
     * Set the description of the playlist to be built.
     *
     * @param description The playlist description.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    /**
     * Set the external URLs of the playlist to be built.
     *
     * @param externalUrls Known external URLs for this playlist.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set the link to the Spotify Web API endpoint providing full details of the playlist.
     *
     * @param href A link to the Spotify Web API endpoint providing full details of the playlist.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set the Spotify ID for the playlist to be built.
     *
     * @param id The Spotify ID for the playlist.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the cover image of the playlist to be built.
     *
     * @param images An array of images in different sizes.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    /**
     * Set the name of the playlist to be built.
     *
     * @param name The playlist name.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the owner of the playlist to be built.
     *
     * @param owner A user object.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setOwner(User owner) {
      this.owner = owner;
      return this;
    }

    /**
     * Set whether the playlist to be built is available in public or not.
     *
     * @param publicAccess {@code true} the playlist is public, {@code false} the playlist is private, {@code null}
     *                     the playlist status is not relevant.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setPublicAccess(Boolean publicAccess) {
      this.publicAccess = publicAccess;
      return this;
    }

    /**
     * Set the version identifier for the playlist to be built.
     *
     * @param snapshotId The version identifier for the playlist.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setSnapshotId(String snapshotId) {
      this.snapshotId = snapshotId;
      return this;
    }

    /**
     * Set some track information of the playlist to be built.
     *
     * @param tracks A playlist tracks information object.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setTracks(PlaylistTracksInformation tracks) {
      this.tracks = tracks;
      return this;
    }

    /**
     * Set the type of the model object. In this case "playlist".
     *
     * @param type The model object type.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URI</a>
     * of the playlist to be built.
     *
     * @param uri The Spotify playlist URI.
     * @return A {@link PlaylistSimplified.Builder}.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public PlaylistSimplified build() {
      return new PlaylistSimplified(this);
    }
  }

  /**
   * JsonUtil class for building {@link PlaylistSimplified} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<PlaylistSimplified> {
    public PlaylistSimplified createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new PlaylistSimplified.Builder()
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
            ? new PlaylistTracksInformation.JsonUtil().createModelObject(
            jsonObject.getAsJsonObject("tracks"))
            : null)
        .setType(
          hasAndNotNull(jsonObject, "type")
            ? ModelObjectType.keyOf(
            jsonObject.get("type").getAsString().toLowerCase())
            : null)
        .setUri(
          hasAndNotNull(jsonObject, "uri")
            ? jsonObject.get("uri").getAsString()
            : null)
        .build();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlaylistSimplified playlist = (PlaylistSimplified) o;
    return Objects.equals(id, playlist.id) && Objects.equals(name, playlist.name) && Objects.equals(uri, playlist.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, uri);
  }
}
