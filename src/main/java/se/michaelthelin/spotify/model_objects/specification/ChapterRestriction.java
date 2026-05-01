package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/get-a-chapter">
 * ChapterRestriction objects</a> by building instances from this class.
 *
 * <p>The reason for the restriction. Supported values:
 * <ul>
 *   <li>{@code market} - The content item is not available in the given market.</li>
 *   <li>{@code product} - The content item is not available for the user's subscription type.</li>
 *   <li>{@code explicit} - The content item is explicit and the user's account is set to not play explicit content.</li>
 *   <li>{@code payment_required} - Payment is required to play the content item.</li>
 * </ul>
 * Additional reasons may be added in the future.
 */
@JsonDeserialize(builder = ChapterRestriction.Builder.class)
public class ChapterRestriction extends AbstractModelObject {
  /** The reason for the restriction. */
  private final String reason;

  private ChapterRestriction(final Builder builder) {
    super(builder);
    this.reason = builder.reason;
  }

  /**
   * Get the reason for the restriction.
   *
   * @return The restriction reason.
   */
  public String getReason() {
    return reason;
  }

  @Override
  public String toString() {
    return "ChapterRestriction(reason=" + reason + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link ChapterRestriction} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String reason;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set the restriction reason.
     *
     * @param reason The restriction reason.
     * @return A {@link ChapterRestriction.Builder}.
     */
    public Builder setReason(String reason) {
      this.reason = reason;
      return this;
    }

    @Override
    public ChapterRestriction build() {
      return new ChapterRestriction(this);
    }
  }

  /**
   * JsonUtil class for building {@link ChapterRestriction} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ChapterRestriction> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    @Override
    public ChapterRestriction createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Builder()
        .setReason(
          hasAndNotNull(jsonObject, "reason")
            ? jsonObject.get("reason").getAsString()
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
    ChapterRestriction that = (ChapterRestriction) o;
    return Objects.equals(reason, that.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reason);
  }
}
