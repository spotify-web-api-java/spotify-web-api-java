package se.michaelthelin.spotify.model_objects.specification;

import com.google.gson.annotations.SerializedName;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.interfaces.IPlaylist;
import se.michaelthelin.spotify.requests.data.playlists.RemovePlaylistItemsRequest;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#playlist-object-full">
 * Playlist objects</a> by building instances from this class.
 */
public class Playlist extends AbstractModelObject implements IPlaylist<Paging<PlaylistTrack>> {
  /** Whether the playlist is collaborative. */
  private final Boolean collaborative;
  /** The description of the playlist. */
  private final String description;
  /** External URLs for the playlist. */
  private final ExternalUrl externalUrls;
  /** Information about the followers of the playlist. */
  private final Followers followers;
  /** The Spotify Web API endpoint URL for the playlist. */
  private final String href;
  /** The Spotify ID for the playlist. */
  private final String id;
  /** Images for the playlist. */
  private final Image[] images;
  /** The name of the playlist. */
  private final String name;
  /** The user who owns the playlist. */
  private final User owner;
  /** Whether the playlist is public. */
  @SerializedName("public")
  private final Boolean publicAccess;
  /** The version identifier for the current playlist. */
  private final String snapshotId;
  /** Information about the tracks of the playlist. */
  private final Paging<PlaylistTrack> items;
  /** The object type. */
  private final ModelObjectType type;
  /** The Spotify URI for the playlist. */
  private final String uri;

  private Playlist(final Builder builder) {
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
    this.items = builder.items;
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
   * @return The playlist description. Only returned for modified, verified playlists, otherwise {@code null}.
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
   * Get information about the followers of the playlist. <br>
   * Example: Follower count.
   *
   * @return Information about the followers of the playlist.
   */
  public Followers getFollowers() {
    return followers;
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
  public Boolean getPublic() {
    return publicAccess;
  }

  /**
   * Get the snapshot ID, the version identifier for the current playlist. Can be supplied in other requests to target
   * a specific playlist version.
   *
   * @return The version identifier for the current playlist.
   * @see RemovePlaylistItemsRequest
   */
  public String getSnapshotId() {
    return snapshotId;
  }

  /**
   * Get information about the tracks of the playlist.
   *
   * @return Information about the tracks of the playlist.
   */
  public Paging<PlaylistTrack> getItems() {
    return items;
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
    return "Playlist(name=" + name + ", description=" + description + ", items=" + items + ", collaborative="
        + collaborative + ", externalUrls=" + externalUrls + ", followers=" + followers + ", href=" + href + ", id="
        + id + ", images=" + Arrays.toString(images) + ", owner=" + owner + ", publicAccess=" + publicAccess
        + ", snapshotId=" + snapshotId + ", type=" + type + ", uri=" + uri + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Playlist} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Boolean collaborative;
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
    private Paging<PlaylistTrack> items;
    private ModelObjectType type;
    private String uri;

    public Builder() {
      super();
    }

    public Builder setCollaborative(Boolean collaborative) {
      this.collaborative = collaborative;
      return this;
    }

    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder setExternalUrls(ExternalUrl externalUrls) {
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

    public Builder setItems(Paging<PlaylistTrack> items) {
      this.items = items;
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

  /**
   * JsonUtil class for building {@link Playlist} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Playlist> {

    public JsonUtil() {
      super();
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
    Playlist playlist = (Playlist) o;
    return Objects.equals(id, playlist.id) && Objects.equals(name, playlist.name) && Objects.equals(uri, playlist.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, uri);
  }
}
