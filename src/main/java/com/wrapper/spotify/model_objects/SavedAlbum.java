package com.wrapper.spotify.model_objects;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.util.Date;

/**
 * Retrieve information about your saved albums by building instances from this class.
 */
public class SavedAlbum extends AbstractModelObject {
  private final Date addedAt;
  private final Album album;

  private SavedAlbum(final SavedAlbum.Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.album = builder.album;
  }

  /**
   * Get the date, when an album has been saved.
   *
   * @return A date object.
   */
  public Date getAddedAt() {
    return addedAt;
  }

  /**
   * Get a full album object from this saved album object.
   *
   * @return An album object.
   */
  public Album getAlbum() {
    return album;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building saved album instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Date addedAt;
    private Album album;

    /**
     * Set the "added at" date of the saved album to be built.
     *
     * @param addedAt A date object.
     * @return A builder object.
     */
    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    /**
     * Set the main album object of the saved album to be built.
     *
     * @param album An album object.
     * @return A builder object.
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
   * JsonUtil class for building saved album instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<SavedAlbum> {
    public SavedAlbum createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      try {
        return new Builder()
                .setAddedAt(simpleDateFormat.parse(jsonObject.get("added_at").getAsString()))
                .setAlbum(new Album.JsonUtil().createModelObject(jsonObject.getAsJsonObject("album")))
                .build();
      } catch (ParseException e) {
        e.printStackTrace();
        return null;
      }
    }
  }
}
