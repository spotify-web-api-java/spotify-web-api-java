package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Date;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#saved-album-object">
 * Saved Album objects</a> by building instances from this class.
 */
public class SavedAlbum extends AbstractModelObject {
  /** The date and time the album was saved. */
  private final Date addedAt;
  /** Information about the album. */
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
  public String toString() {
    return "SavedAlbum(addedAt=" + addedAt + ", album=" + album + ")";
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

    public Builder() {
      super();
    }

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

  /**
   * JsonUtil class for building {@link SavedAlbum} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<SavedAlbum> {

    public JsonUtil() {
      super();
    }

  }
}
