package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/get-an-audiobook">
 * Narrator objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = Narrator.Builder.class)
public class Narrator extends AbstractModelObject {
  /** The name of the Narrator. */
  private final String name;

  private Narrator(final Builder builder) {
    super(builder);
    this.name = builder.name;
  }

  /**
   * Get the name of the Narrator.
   *
   * @return The name of the Narrator.
   */
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Narrator(name=" + name + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Narrator} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String name;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set the name of the Narrator to be built.
     *
     * @param name The name of the Narrator.
     * @return A {@link Narrator.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Narrator build() {
      return new Narrator(this);
    }
  }

  /**
   * JsonUtil class for building {@link Narrator} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Narrator> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    @Override
    public Narrator createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Builder()
        .setName(
          hasAndNotNull(jsonObject, "name")
            ? jsonObject.get("name").getAsString()
            : null)
        .build();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Narrator narrator = (Narrator) o;
    return Objects.equals(name, narrator.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
