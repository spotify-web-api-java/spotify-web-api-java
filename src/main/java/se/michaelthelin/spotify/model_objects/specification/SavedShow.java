package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Date;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#saved-show-object">Saved Show objects</a>
 * by building instances from this class.
 */
public class SavedShow extends AbstractModelObject {
  /** The date and time the show was saved. */
  private final Date addedAt;
  /** Information about the show. */
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

    public Builder() {
      super();
    }

    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

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

    public JsonUtil() {
      super();
    }

  }
}
