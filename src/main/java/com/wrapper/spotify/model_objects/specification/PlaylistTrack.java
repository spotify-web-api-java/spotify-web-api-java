package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

import java.text.ParseException;
import java.util.Date;

/**
 * Retrieve information about playlist tracks by building instances from this class.
 */
public class PlaylistTrack extends AbstractModelObject {
  private final Date addedAt;
  private final User addedBy;
  private final Boolean isLocal;
  private final Track track;

  private PlaylistTrack(final PlaylistTrack.Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.addedBy = builder.addedBy;
    this.isLocal = builder.isLocal;
    this.track = builder.track;
  }

  /**
   * Get the date, when a track has been added to its playlist.
   *
   * @return A date object.
   */
  public Date getAddedAt() {
    return addedAt;
  }

  /**
   * Get the user, who added a track to its playlist.
   *
   * @return A user object.
   */
  public User getAddedBy() {
    return addedBy;
  }

  /**
   * Check whether a playlist track is a local track or not.<br>
   * Local tracks can only be played on devices, where the track files are present.
   *
   * @return "true" if the track is local, "false" if not.
   */
  public Boolean getIsLocal() {
    return isLocal;
  }

  /**
   * Get a full track object from this playlist track object.
   *
   * @return A track object.
   */
  public Track getTrack() {
    return track;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building playlist track instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Date addedAt;
    private User addedBy;
    private Boolean isLocal;
    private Track track;

    /**
     * Set the "added at" date of the playlist track to be built.
     *
     * @param addedAt A date object.
     * @return A builder object.
     */
    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    /**
     * Set the user who added the track to the playlist.
     *
     * @param addedBy A user object.
     * @return A builder object.
     */
    public Builder setAddedBy(User addedBy) {
      this.addedBy = addedBy;
      return this;
    }

    /**
     * Set whether the track to be built is local or not.
     *
     * @param isLocal "true" if the track is local, "false" if not.
     * @return A builder object.
     */
    public Builder setIsLocal(Boolean isLocal) {
      this.isLocal = isLocal;
      return this;
    }

    /**
     * Set the full track object of the playlist track to be built.
     *
     * @param track A track object.
     * @return A builder object.
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
   * JsonUtil class for building playlist track instances.
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
                                ? simpleDateFormat.parse(jsonObject.get("added_at").getAsString())
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
        e.printStackTrace();
        return null;
      }
    }
  }
}
