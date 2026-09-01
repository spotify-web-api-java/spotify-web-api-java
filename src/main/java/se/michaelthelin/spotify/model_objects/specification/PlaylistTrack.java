package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.IPlaylistItem;

import java.util.Date;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#playlist-track-object">
 * Playlist Track objects</a> by building instances from this class.
 */
public class PlaylistTrack extends AbstractModelObject {
  /** The date and time the track was added. */
  private final Date addedAt;
  /** The user who added the track. */
  private final User addedBy;
  /** Whether the track is a local file. */
  private final Boolean isLocal;
  /** The track or episode information. */
  private final IPlaylistItem item;

  private PlaylistTrack(final Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.addedBy = builder.addedBy;
    this.isLocal = builder.isLocal;
    this.item = builder.item;
  }

  /**
   * Get the date, when the track or episode has been added to its playlist.
   * <b>Note:</b> Some very old playlists may return {@code null} in this field.
   *
   * @return The date and time the track or episode was added.
   */
  public Date getAddedAt() {
    return addedAt;
  }

  /**
   * Get the user, who added the track or episode to its playlist.
   * <b>Note:</b> Some very old playlists may return null in this field.
   *
   * @return The Spotify user who added the track or episode.
   */
  public User getAddedBy() {
    return addedBy;
  }

  /**
   * Check whether a playlist track is a local track or episode or not.<br>
   * Local tracks can only be played on devices, where the track files are present.
   *
   * @return Whether this track is a local file or not.
   */
  public Boolean getIsLocal() {
    return isLocal;
  }

  /**
   * Get a full track or episode object from this playlist track object.
   *
   * @return Information about the track.
   */
  public IPlaylistItem getItem() {
    return item;
  }

  @Override
  public String toString() {
    return "PlaylistTrack(item=" + item + ", addedAt=" + addedAt + ", addedBy=" + addedBy + ", isLocal=" + isLocal
        + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link PlaylistTrack} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Date addedAt;
    private User addedBy;
    private Boolean isLocal;
    private IPlaylistItem item;

    public Builder() {
      super();
    }

    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    public Builder setAddedBy(User addedBy) {
      this.addedBy = addedBy;
      return this;
    }

    public Builder setIsLocal(Boolean isLocal) {
      this.isLocal = isLocal;
      return this;
    }

    public Builder setItem(IPlaylistItem item) {
      this.item = item;
      return this;
    }

    @Override
    public PlaylistTrack build() {
      return new PlaylistTrack(this);
    }
  }

  /**
   * JsonUtil class for building {@link PlaylistTrack} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<PlaylistTrack> {

    public JsonUtil() {
      super();
    }

  }
}
