package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Date;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#saved-track-object">
 * Saved Track objects</a> by building instances from this class.
 */
public class SavedTrack extends AbstractModelObject {
  /** The date and time the track was saved. */
  private final Date addedAt;
  /** Information about the track. */
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
  public String toString() {
    return "SavedTrack(addedAt=" + addedAt + ", track=" + track + ")";
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
     * Default constructor.
     */
    public Builder() {
      super();
    }

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

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

  }
}
