package com.wrapper.spotify.model_objects;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Map;

/**
 * Retrieve information about
 *     <a href="https://developer.spotify.com/web-api/object-model/#external-id-object">External ID objects</a>
 *     by building instances from this class.
 */
public class ExternalIds extends AbstractModelObject {
  private final Map<String, String> externalIds;

  private ExternalIds(final ExternalIds.Builder builder) {
    super(builder);

    this.externalIds = builder.externalIds;
  }

    /**
     * Get the external IDs from this <a href="https://developer.spotify.com/web-api/object-model/#external-id-object">
     *     External ID object</a>. <br><br>
     *
     * External ID examples:<br>
     * "isrc" - <a href="http://en.wikipedia.org/wiki/International_Standard_Recording_Code">
     *     International Standard Recording Code</a><br>
     * "ean" - <a href="http://en.wikipedia.org/wiki/International_Article_Number_%28EAN%29">International Article Number
     *     </a><br>
     * "upc" - <a href="http://en.wikipedia.org/wiki/Universal_Product_Code">Universal Product Code</a>
     *
     * @return A {@link Map} of external IDs, containing external identifiers for the object.
     */
  public Map<String, String> getExternalIds() {
    return externalIds;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

    /**
     * Builder class for building {@link ExternalIds} instances.
     */
  public static final class Builder extends AbstractModelObject.Builder {
    private Map<String, String> externalIds;

        /**
         * External IDs setter.
         *
         * @param externalIds A {@link Map} of external IDs, containing external identifiers for the object.
         * @return A {@link ExternalIds.Builder}.
         */
    public Builder setExternalIds(Map<String, String> externalIds) {
      this.externalIds = externalIds;
      return this;
    }

    @Override
    public ExternalIds build() {
      return new ExternalIds(this);
    }
  }

    /**
     * JsonUtil class for building {@link ExternalIds} instances.
     */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ExternalIds> {
    public ExternalIds createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      Map<String, String> map = new Gson().fromJson(jsonObject, new TypeToken<Map<String, String>>() {
      }.getType());

      return new ExternalIds.Builder()
              .setExternalIds(map)
              .build();
    }
  }
}
