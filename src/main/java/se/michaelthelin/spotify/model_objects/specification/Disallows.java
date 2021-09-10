package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.enums.Action;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.special.Actions;

import java.util.EnumSet;
import java.util.Map;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#disallows-object">Disallows objects</a>
 * by building instances from this class.
 */
@JsonDeserialize(builder = Actions.Builder.class)
public class Disallows extends AbstractModelObject {
  private final EnumSet<Action> disallowedActions;

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

    /**
     * Set the set of disallowed actions.
     *
     * @param disallowedActions The set of disallowed actions.
     * @return A {@link Disallows.Builder}.
     */
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
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Disallows> {
    @Override
    public Disallows createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      EnumSet<Action> disallowedActions = EnumSet.noneOf(Action.class);
      for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
        if (entry.getValue().getAsJsonPrimitive().getAsBoolean()) {
          disallowedActions.add(
            Action.keyOf(entry.getKey().toLowerCase()));
        }
      }

      return new Builder()
        .setDisallowedActions(
          disallowedActions)
        .build();
    }
  }

}
