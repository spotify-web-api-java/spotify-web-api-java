package se.michaelthelin.spotify.model_objects.special;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.specification.Disallows;

/**
 * Retrieve information about Actions objects by building instances from this class. These objects
 * contain information to allow to update the user interface based on which playback actions are
 * available within the current context. These objects contain a {@link Disallows} object.
 */
@JsonDeserialize(builder = Actions.Builder.class)
public class Actions extends AbstractModelObject {
  private final Disallows disallows;

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

    /**
     * Set the Disallows object of the Actions object to be built.
     *
     * @param disallows The {@link Disallows} object.
     * @return A {@link Actions.Builder}.
     */
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
    @Override
    public Actions createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Builder()
        .setDisallows(
          hasAndNotNull(jsonObject, "disallows")
            ? new Disallows.JsonUtil().createModelObject(
            jsonObject.getAsJsonObject("disallows"))
            : null)
        .build();
    }
  }

}
