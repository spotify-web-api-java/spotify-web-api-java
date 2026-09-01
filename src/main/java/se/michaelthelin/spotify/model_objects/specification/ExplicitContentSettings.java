package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about the current user's explicit content settings by building instances from this class.
 */
@JsonDeserialize(builder = ExplicitContentSettings.Builder.class)
public class ExplicitContentSettings extends AbstractModelObject {
  /** When true, indicates that explicit content should not be played. */
  private final Boolean filterEnabled;
  /** When true, indicates that the explicit content setting is locked and can't be changed by the user. */
  private final Boolean filterLocked;

  private ExplicitContentSettings(final Builder builder) {
    super(builder);

    this.filterEnabled = builder.filterEnabled;
    this.filterLocked = builder.filterLocked;
  }

  /**
   * Check whether explicit content should not be played.
   *
   * @return {@code true} if explicit content should not be played.
   */
  public Boolean getFilterEnabled() {
    return filterEnabled;
  }

  /**
   * Check whether the explicit content setting is locked and can't be changed by the user.
   *
   * @return {@code true} if the explicit content setting is locked.
   */
  public Boolean getFilterLocked() {
    return filterLocked;
  }

  @Override
  public String toString() {
    return "ExplicitContentSettings(filterEnabled=" + filterEnabled + ", filterLocked=" + filterLocked + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link ExplicitContentSettings} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Boolean filterEnabled;
    private Boolean filterLocked;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set whether explicit content should not be played.
     *
     * @param filterEnabled {@code true} if explicit content should not be played.
     * @return A {@link ExplicitContentSettings.Builder}.
     */
    public Builder setFilterEnabled(Boolean filterEnabled) {
      this.filterEnabled = filterEnabled;
      return this;
    }

    /**
     * Set whether the explicit content setting is locked.
     *
     * @param filterLocked {@code true} if the explicit content setting is locked and can't be changed by the user.
     * @return A {@link ExplicitContentSettings.Builder}.
     */
    public Builder setFilterLocked(Boolean filterLocked) {
      this.filterLocked = filterLocked;
      return this;
    }

    @Override
    public ExplicitContentSettings build() {
      return new ExplicitContentSettings(this);
    }
  }

  /**
   * JsonUtil class for building {@link ExplicitContentSettings} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ExplicitContentSettings> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

  }
}
