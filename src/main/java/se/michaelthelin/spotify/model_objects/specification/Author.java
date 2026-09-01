package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/get-an-audiobook">
 * Author objects</a> by building instances from this class.
 */
public class Author extends AbstractModelObject {
  /** The name of the author. */
  private final String name;

  private Author(final Builder builder) {
    super(builder);
    this.name = builder.name;
  }

  /**
   * Get the name of the author.
   *
   * @return The name of the author.
   */
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Author(name=" + name + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Author} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String name;

    public Builder() {
      super();
    }

    /**
     * Set the name of the author to be built.
     *
     * @param name The name of the author.
     * @return An {@link Author.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Author build() {
      return new Author(this);
    }
  }

  /**
   * JsonUtil class for building {@link Author} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Author> {

    public JsonUtil() {
      super();
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
    Author author = (Author) o;
    return Objects.equals(name, author.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
