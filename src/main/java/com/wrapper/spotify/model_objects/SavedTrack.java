package com.wrapper.spotify.model_objects;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.util.Date;

/**
 * Retrieve information about your saved tracks by building instances from this class.
 */
public class SavedTrack extends AbstractModelObject {
  private final Date addedAt;
  private final Track track;

  private SavedTrack(final SavedTrack.Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.track = builder.track;
  }

  /**
   * Get the date, when a track has been saved.
   *
   * @return A date object.
   */
  public Date getAddedAt() {
    return addedAt;
  }

  /**
   * Get a full track object from this saved track object.
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
   * Builder class for building saved track instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Date addedAt;
    private Track track;

    /**
     * Set the "added at" date of the saved track to be built.
     *
     * @param addedAt A date object.
     * @return A builder object.
     */
    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    /**
     * Set the main track object of the saved track to be built.
     *
     * @param track A track object.
     * @return A builder object.
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
   * JsonUtil class for building saved track instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<SavedTrack> {
    public SavedTrack createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      try {
        return new Builder()
                .setAddedAt(simpleDateFormat.parse(jsonObject.get("added_at").getAsString()))
                .setTrack(new Track.JsonUtil().createModelObject(jsonObject.getAsJsonObject("track")))
                .build();
      } catch (ParseException e) {
        e.printStackTrace();
        return null;
      }
    }
  }
}
