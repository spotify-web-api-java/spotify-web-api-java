package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.AbstractModelObject;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#playlist-track-object">
 * Playlist Track objects</a> by building instances from this class.
 */
public class PlaylistTrack extends AbstractModelObject {
  private final Date addedAt;
  private final User addedBy;
  private final Boolean isLocal;
  private final Track track;

  private PlaylistTrack(final Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.addedBy = builder.addedBy;
    this.isLocal = builder.isLocal;
    this.track = builder.track;
  }

  /**
   * Get the date, when the track has been added to its playlist.
   * <b>Note:</b> Some very old playlists may return {@code null} in this field.
   *
   * @return The date and time the track was added.
   */
  public Date getAddedAt() {
    return addedAt;
  }

  /**
   * Get the user, who added the track to its playlist.
   * <b>Note:</b> Some very old playlists may return null in this field.
   *
   * @return The Spotify user who added the track.
   */
  public User getAddedBy() {
    return addedBy;
  }

  /**
   * Check whether a playlist track is a local track or not.<br>
   * Local tracks can only be played on devices, where the track files are present.
   *
   * @return Whether this track is a local file or not.
   */
  public Boolean getIsLocal() {
    return isLocal;
  }

  /**
   * Get a full track object from this playlist track object.
   *
   * @return Information about the track.
   */
  public Track getTrack() {
    return track;
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
    private Track track;

    /**
     * Set the "added at" date of the playlist track to be built.
     *
     * @param addedAt The date and time the track was added.
     * @return A {@link PlaylistTrack.Builder}.
     */
    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    /**
     * Set the user who added the track to the playlist.
     *
     * @param addedBy The Spotify user who added the track.
     * @return A {@link PlaylistTrack.Builder}.
     */
    public Builder setAddedBy(User addedBy) {
      this.addedBy = addedBy;
      return this;
    }

    /**
     * Set whether the track to be built is local or not.
     *
     * @param isLocal Whether this track is a local file or not.
     * @return A {@link PlaylistTrack.Builder}.
     */
    public Builder setIsLocal(Boolean isLocal) {
      this.isLocal = isLocal;
      return this;
    }

    /**
     * Set the full track object of the playlist track to be built.
     *
     * @param track Information about the track.
     * @return A {@link PlaylistTrack.Builder}.
     */
    public Builder setTrack(Track track) {
      this.track = track;
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
    public PlaylistTrack createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      try {
        return new Builder()
                .setAddedAt(
                        hasAndNotNull(jsonObject, "added_at")
                                ? SpotifyApi.parseDefaultDate(jsonObject.get("added_at").getAsString())
                                : null)
                .setAddedBy(
                        hasAndNotNull(jsonObject, "added_by")
                                ? new User.JsonUtil().createModelObject(
                                jsonObject.get("added_by").getAsJsonObject())
                                : null)
                .setIsLocal(
                        hasAndNotNull(jsonObject, "is_local")
                                ? jsonObject.get("is_local").getAsBoolean()
                                : null)
                .setTrack(
                        hasAndNotNull(jsonObject, "track")
                                ? new Track.JsonUtil().createModelObject(
                                jsonObject.getAsJsonObject("track"))
                                : null)
                .build();
      } catch (ParseException e) {
        SpotifyApi.LOGGER.log(Level.SEVERE, e.getMessage());
        return null;
      }
    }
  }
}
