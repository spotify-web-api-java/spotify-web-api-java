package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/get-users-saved-audiobooks">
 * Saved Audiobook objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = SavedAudiobook.Builder.class)
public class SavedAudiobook extends AbstractModelObject {
  /** The date and time the audiobook was saved. */
  private final Date addedAt;
  /** Information about the audiobook. */
  private final AudiobookSimplified audiobook;

  private SavedAudiobook(final Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.audiobook = builder.audiobook;
  }

  /**
   * Get the date and time the audiobook was saved.
   *
   * @return The date and time the audiobook was saved.
   */
  public Date getAddedAt() {
    return addedAt;
  }

  /**
   * Get information about the audiobook from a saved audiobook object.
   *
   * @return Information about the audiobook.
   */
  public AudiobookSimplified getAudiobook() {
    return audiobook;
  }

  @Override
  public String toString() {
    return "SavedAudiobook(addedAt=" + addedAt + ", audiobook=" + audiobook + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link SavedAudiobook} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Date addedAt;
    private AudiobookSimplified audiobook;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set the "added at" date of the saved audiobook to be built.
     *
     * @param addedAt The date and time the audiobook was saved.
     * @return A {@link SavedAudiobook.Builder}.
     */
    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    /**
     * Set the simplified audiobook object of the saved audiobook to be built.
     *
     * @param audiobook Information about the audiobook.
     * @return A {@link SavedAudiobook.Builder}.
     */
    public Builder setAudiobook(AudiobookSimplified audiobook) {
      this.audiobook = audiobook;
      return this;
    }

    @Override
    public SavedAudiobook build() {
      return new SavedAudiobook(this);
    }
  }

  /**
   * JsonUtil class for building {@link SavedAudiobook} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<SavedAudiobook> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public SavedAudiobook createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      try {
        return new Builder()
          .setAddedAt(
            hasAndNotNull(jsonObject, "added_at")
              ? SpotifyApi.parseDefaultDate(jsonObject.get("added_at").getAsString())
              : null)
          .setAudiobook(
            hasAndNotNull(jsonObject, "audiobook")
              ? new AudiobookSimplified.JsonUtil().createModelObject(
              jsonObject.getAsJsonObject("audiobook"))
              : null)
          .build();
      } catch (ParseException e) {
        SpotifyApi.LOGGER.log(Level.SEVERE, e.getMessage());
        return null;
      }
    }
  }
}
