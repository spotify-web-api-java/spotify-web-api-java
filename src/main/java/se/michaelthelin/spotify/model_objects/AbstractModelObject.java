package se.michaelthelin.spotify.model_objects;

/**
 * The base class of every model object, with the builder each of them implements.
 * <p>
 * Model objects are read from JSON by the library itself; to read JSON obtained some other way, use
 * {@link ModelObjects}.
 */
public abstract class AbstractModelObject implements IModelObject {

  /**
   * This constructor initializes the time zone.
   *
   * @param builder The builder object of the corresponding model object.
   */
  protected AbstractModelObject(final Builder builder) {
    assert (builder != null);
  }

  /**
   * Returns a String representation of this model object in the style:<p>
   * {@code ModelObject(attr1=value1, attr2=value2, ...)}
   */
  @Override
  public abstract String toString(); // abstract enforces overriding of toString() for subclasses

  /**
   * Each model object needs to implement its own builder class.
   */
  public static abstract class Builder implements IModelObject.Builder {
    public Builder() {
      super();
    }
  }
}
