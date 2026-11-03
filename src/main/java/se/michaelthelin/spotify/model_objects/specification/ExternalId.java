package se.michaelthelin.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Map;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#external-id-object">External ID objects</a>
 * by building instances from this class.
 */
public class ExternalId extends AbstractModelObject {
  /** Known external IDs for this object. */
  private final Map<String, String> externalIds;

  private ExternalId(final Builder builder) {
    super(builder);

    this.externalIds = builder.externalIds;
  }

  /**
   * Get the external IDs from this <a href="https://developer.spotify.com/web-api/object-model/#external-id-object">
   * External ID object</a>. <br><br>
   * <p>
   * External ID examples:<br>
   * "isrc" - <a href="http://en.wikipedia.org/wiki/International_Standard_Recording_Code">
   * International Standard Recording Code</a><br>
   * "ean" - <a href="http://en.wikipedia.org/wiki/International_Article_Number_%28EAN%29">International Article Number
   * </a><br>
   * "upc" - <a href="http://en.wikipedia.org/wiki/Universal_Product_Code">Universal Product Code</a>
   *
   * @return A {@link Map} of external IDs, containing external identifiers for the object.
   */
  public Map<String, String> getExternalIds() {
    return externalIds;
  }

  @Override
  public String toString() {
    return "ExternalId(externalIds=" + externalIds + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link ExternalId} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Map<String, String> externalIds;

    public Builder() {
      super();
    }

    public Builder setExternalIds(Map<String, String> externalIds) {
      this.externalIds = externalIds;
      return this;
    }

    @Override
    public ExternalId build() {
      return new ExternalId(this);
    }
  }

  /**
   * JsonUtil class for building {@link ExternalId} instances.
   *
   * @deprecated Requests already return model objects. To read JSON obtained some other way, use
   *             {@link se.michaelthelin.spotify.model_objects.ModelObjects} instead. This will be removed
   *             in the next major version.
   */
  @Deprecated
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ExternalId> {

    public JsonUtil() {
      super();
    }

    // Kept only so callers compiled against this override, whose erased return type differs from the
    // inherited one, still link. The model's JSON shape is handled by an adapter in ModelObjectGson.
    public ExternalId createModelObject(JsonObject jsonObject) {
      return super.createModelObject(jsonObject);
    }
  }
}
