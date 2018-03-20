package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.AbstractModelObject;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#saved-album-object">
 * Saved Album objects</a> by building instances from this class.
 */
public class SavedAlbum extends AbstractModelObject {
  private final Date addedAt;
  private final Album album;

  private SavedAlbum(final Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.album = builder.album;
  }

  /**
   * Get the date, when the album has been saved.
   *
   * @return The date and time the album was saved.
   */
  public Date getAddedAt() {
    return addedAt;
  }

  /**
   * Get information about the album from a saved album object.
   *
   * @return Information about the album.
   */
  public Album getAlbum() {
    return album;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link SavedAlbum} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Date addedAt;
    private Album album;

    /**
     * Set the "added at" date of the saved album to be built.
     *
     * @param addedAt The date and time the album was saved.
     * @return A {@link SavedAlbum.Builder}.
     */
    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    /**
     * Set the full album object of the saved album to be built.
     *
     * @param album Information about the album.
     * @return A {@link SavedAlbum.Builder}.
     */
    public Builder setAlbum(Album album) {
      this.album = album;
      return this;
    }

    @Override
    public SavedAlbum build() {
      return new SavedAlbum(this);
    }
  }

  /**
   * JsonUtil class for building {@link SavedAlbum} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<SavedAlbum> {
    public SavedAlbum createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      try {
        return new Builder()
                .setAddedAt(
                        hasAndNotNull(jsonObject, "added_at")
                                ? SpotifyApi.parseDefaultDate(jsonObject.get("added_at").getAsString())
                                : null)
                .setAlbum(
                        hasAndNotNull(jsonObject, "album")
                                ? new Album.JsonUtil().createModelObject(
                                jsonObject.getAsJsonObject("album"))
                                : null)
                .build();
      } catch (ParseException e) {
        SpotifyApi.LOGGER.log(Level.SEVERE, e.getMessage());
        return null;
      }
    }
  }
}
