package se.michaelthelin.spotify.model_objects.special;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.specification.Disallows;

/**
 * Retrieve information about Actions objects by building instances from this class. These objects
 * contain information to allow to update the user interface based on which playback actions are
 * available within the current context. These objects contain a {@link Disallows} object.
 */
public class Actions extends AbstractModelObject {
  /** Actions that are disallowed in the current context. */
  private final Disallows disallows;

  /**
   * Create an Actions object.
   *
   * @param builder The builder instance.
   */
  public Actions(Builder builder) {
    super(builder);
    this.disallows = builder.disallows;
  }

  /**
   * Get the Disallows object.
   *
   * @return Disallows object.
   */
  public Disallows getDisallows() {
    return disallows;
  }

  @Override
  public String toString() {
    return "Actions(disallows=" + disallows + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Actions} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Disallows disallows;

    public Builder() {
      super();
    }

    public Builder setDisallows(Disallows disallows) {
      this.disallows = disallows;
      return this;
    }

    @Override
    public Actions build() {
      return new Actions(this);
    }
  }

  /**
   * JsonUtil class for building {@link Actions} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Actions> {

    public JsonUtil() {
      super();
    }

  }

}
