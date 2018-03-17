package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.AbstractModelObject;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#saved-track-object">
 * Saved Track objects</a> by building instances from this class.
 */
public class SavedTrack extends AbstractModelObject {
  private final Date addedAt;
  private final Track track;

  private SavedTrack(final Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.track = builder.track;
  }

  /**
   * Get the date, when the track has been saved.
   *
   * @return The date and time the track was saved.
   */
  public Date getAddedAt() {
    return addedAt;
  }

  /**
   * Get information about the track from a saved track object.
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
   * Builder class for building {@link SavedTrack} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Date addedAt;
    private Track track;

    /**
     * Set the "added at" date of the saved track to be built.
     *
     * @param addedAt The date and time the track was saved.
     * @return A {@link SavedTrack.Builder}.
     */
    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    /**
     * Set the full track object of the saved track to be built.
     *
     * @param track Information about the track.
     * @return A {@link SavedTrack.Builder}.
     */
    public Builder setTrack(Track track) {
      this.track = track;
      return this;
    }

    @Override
    public SavedTrack build() {
      return new SavedTrack(this);
    }
  }

  /**
   * JsonUtil class for building {@link SavedTrack} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<SavedTrack> {
    public SavedTrack createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      try {
        return new Builder()
                .setAddedAt(
                        hasAndNotNull(jsonObject, "added_at")
                                ? SpotifyApi.parseDefaultDate(jsonObject.get("added_at").getAsString())
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
