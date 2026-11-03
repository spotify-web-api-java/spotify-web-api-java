package se.michaelthelin.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import se.michaelthelin.spotify.enums.Action;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.special.Actions;

import java.util.EnumSet;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#disallows-object">Disallows objects</a>
 * by building instances from this class.
 */
public class Disallows extends AbstractModelObject {
  /** The set of disallowed actions for the current context. */
  private final EnumSet<Action> disallowedActions;

  /**
   * Creates a new Disallows object with the specified builder.
   *
   * @param builder the builder containing disallowed actions configuration
   */
  public Disallows(Builder builder) {
    super(builder);
    this.disallowedActions = builder.disallowedActions;
  }

  /**
   * Get a set of disallowed actions.
   *
   * @return The set of disallowed actions.
   */
  public EnumSet<Action> getDisallowedActions() {
    return disallowedActions;
  }

  @Override
  public String toString() {
    return "Disallows(disallowedActions=" + disallowedActions + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Disallows} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private EnumSet<Action> disallowedActions;

    public Builder() {
      super();
    }

    public Builder setDisallowedActions(EnumSet<Action> disallowedActions) {
      this.disallowedActions = disallowedActions;
      return this;
    }

    @Override
    public Disallows build() {
      return new Disallows(this);
    }
  }

  /**
   * JsonUtil class for building {@link Disallows} instances.
   *
   * @deprecated Responses are deserialized by the library itself, so there is no need to call this.
   *             It will be removed in the next major version.
   */
  @Deprecated
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Disallows> {

    public JsonUtil() {
      super();
    }

    // Kept only so callers compiled against this override, whose erased return type differs from the
    // inherited one, still link. The model's JSON shape is handled by an adapter in ModelObjectGson.
    @Override
    public Disallows createModelObject(JsonObject jsonObject) {
      return super.createModelObject(jsonObject);
    }
  }

}
