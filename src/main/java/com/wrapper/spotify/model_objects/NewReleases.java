package com.wrapper.spotify.model_objects;

import com.google.gson.JsonObject;

/**
 * Retrieve information about the newest releases in a specific country by building instances from this class.
 */
public class NewReleases extends AbstractModelObject {
  private final Paging<AlbumSimplified> albums;

  private NewReleases(final NewReleases.Builder builder) {
    super(builder);

    this.albums = builder.albums;
  }

    /**
     * Get a page of simplified albums.
     *
     * @return A page of simplified albums.
     */
  public Paging<AlbumSimplified> getAlbums() {
    return albums;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

    /**
     * Builder class for building {@link NewReleases} instances.
     */
  public static final class Builder extends AbstractModelObject.Builder {
    private Paging<AlbumSimplified> albums;

        /**
         * The album page setter.
         *
         * @param albums A page of simplified albums.
         * @return A {@link NewReleases.Builder}.
         */
    public Builder setAlbums(Paging<AlbumSimplified> albums) {
      this.albums = albums;
      return this;
    }

    @Override
    public NewReleases build() {
      return new NewReleases(this);
    }
  }

    /**
     * JsonUtil class for building {@link NewReleases} instances.
     */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<NewReleases> {
    public NewReleases createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new NewReleases.Builder()
              .setAlbums(new AlbumSimplified.JsonUtil().createModelObjectPaging(jsonObject.getAsJsonObject("albums")))
              .build();
    }
  }
}
