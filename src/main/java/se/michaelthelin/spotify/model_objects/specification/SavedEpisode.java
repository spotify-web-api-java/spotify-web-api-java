package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/get-users-saved-episodes">
 * Saved Episode objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = SavedEpisode.Builder.class)
public class SavedEpisode extends AbstractModelObject {
  /** The date and time the episode was saved. */
  private final Date addedAt;
  /** Information about the episode. */
  private final Episode episode;

  private SavedEpisode(final Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.episode = builder.episode;
  }

  /**
   * Get the date and time the episode was saved.
   *
   * @return The date and time the episode was saved.
   */
  public Date getAddedAt() {
    return addedAt;
  }

  /**
   * Get information about the episode from a saved episode object.
   *
   * @return Information about the episode.
   */
  public Episode getEpisode() {
    return episode;
  }

  @Override
  public String toString() {
    return "SavedEpisode(addedAt=" + addedAt + ", episode=" + episode + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link SavedEpisode} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Date addedAt;
    private Episode episode;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set the "added at" date of the saved episode to be built.
     *
     * @param addedAt The date and time the episode was saved.
     * @return A {@link SavedEpisode.Builder}.
     */
    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    /**
     * Set the full episode object of the saved episode to be built.
     *
     * @param episode Information about the episode.
     * @return A {@link SavedEpisode.Builder}.
     */
    public Builder setEpisode(Episode episode) {
      this.episode = episode;
      return this;
    }

    @Override
    public SavedEpisode build() {
      return new SavedEpisode(this);
    }
  }

  /**
   * JsonUtil class for building {@link SavedEpisode} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<SavedEpisode> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public SavedEpisode createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      try {
        return new Builder()
          .setAddedAt(
            hasAndNotNull(jsonObject, "added_at")
              ? SpotifyApi.parseDefaultDate(jsonObject.get("added_at").getAsString())
              : null)
          .setEpisode(
            hasAndNotNull(jsonObject, "episode")
              ? new Episode.JsonUtil().createModelObject(
              jsonObject.getAsJsonObject("episode"))
              : null)
          .build();
      } catch (ParseException e) {
        SpotifyApi.LOGGER.log(Level.SEVERE, e.getMessage());
        return null;
      }
    }
  }
}
