package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

import java.text.ParseException;
import java.util.Date;

public class SavedAlbum extends AbstractModelObject {
  private final Date addedAt;
  private final Album album;

  private SavedAlbum(final SavedAlbum.Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.album = builder.album;
  }

  public Date getAddedAt() {
    return addedAt;
  }

  public Album getAlbum() {
    return album;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private Date addedAt;
    private Album album;

    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    public Builder setAlbum(Album album) {
      this.album = album;
      return this;
    }

    @Override
    public SavedAlbum build() {
      return new SavedAlbum(this);
    }
  }

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
