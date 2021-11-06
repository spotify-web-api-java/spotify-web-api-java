package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#saved-show-object">Saved Show objects</a>
 * by building instances from this class.
 */
@JsonDeserialize(builder = SavedShow.Builder.class)
public class SavedShow extends AbstractModelObject {
  private final Date addedAt;
  private final ShowSimplified show;

  private SavedShow(final Builder builder) {
    super(builder);
    this.addedAt = builder.addedAt;
    this.show = builder.show;
  }

  /**
   * Get the date, when the show has been saved.
   *
   * @return The date and time the show was saved.
   */
  public Date getAddedAt() {
    return addedAt;
  }

  /**
   * Get information about the show from a saved show object.
   *
   * @return Information about the show.
   */
  public ShowSimplified getShow() {
    return show;
  }

  @Override
  public String toString() {
    return "SavedShow(addedAt=" + addedAt + ", show=" + show + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link SavedShow} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Date addedAt;
    private ShowSimplified show;

    /**
     * Set the "added at" date of the saved show to be built.
     *
     * @param addedAt The date and time the show was saved.
     * @return A {@link SavedShow.Builder}.
     */
    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    /**
     * Set the full show object of the saved show to be built.
     *
     * @param show Information about the show.
     * @return A {@link SavedShow.Builder}.
     */
    public Builder setShow(ShowSimplified show) {
      this.show = show;
      return this;
    }

    @Override
    public SavedShow build() {
      return new SavedShow(this);
    }
  }

  /**
   * JsonUtil class for building {@link SavedShow} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<SavedShow> {
    @Override
    public SavedShow createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      try {
        return new Builder()
          .setAddedAt(
            hasAndNotNull(jsonObject, "added_at")
              ? SpotifyApi.parseDefaultDate(jsonObject.get("added_at").getAsString())
              : null)
          .setShow(
            hasAndNotNull(jsonObject, "show")
              ? new ShowSimplified.JsonUtil().createModelObject(
              jsonObject.getAsJsonObject("show"))
              : null)
          .build();
      } catch (ParseException e) {
        SpotifyApi.LOGGER.log(Level.SEVERE, e.getMessage());
        return null;
      }
    }
  }
}
