package com.wrapper.spotify.model_objects;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Map;

/**
 * Retrieve information about
 *     <a href="https://developer.spotify.com/web-api/object-model/#external-url-object">External URL objects</a>
 *     by building instances from this class.
 */
public class ExternalUrls extends AbstractModelObject {
  private final Map<String, String> externalUrls;

  private ExternalUrls(final ExternalUrls.Builder builder) {
    super(builder);

    this.externalUrls = builder.externalUrls;
  }

    /**
     * Get an specific external URL from this external URLs object with the key string given below.
     *
     * @param key The type of the URL.
     * @return An external, public URL to the object.
     */
  public String get(String key) {
    return externalUrls.get(key);
  }

    /**
     * Get the external URLs from this
     *     <a href="https://developer.spotify.com/web-api/object-model/#external-url-object">External URL object</a>.
     *     <br><br>
     *
     * External URL example: <br>
     * "spotify" - The <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URL</a>
     *     for the object.
     *
     * @return A {@link Map} of external public URLs to its objects.
     */
  public Map<String, String> getExternalUrls() {
    return externalUrls;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

    /**
     * Builder class for building {@link ExternalUrls} instances.
     */
  public static final class Builder extends AbstractModelObject.Builder {
    private Map<String, String> externalUrls;

        /**
         * The external URLs setter.
         *
         * @param externalUrls A {@link Map} of external public URLs to its objects.
         * @return A {@link ExternalUrls.Builder}.
         */
    public Builder setExternalUrls(Map<String, String> externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    @Override
    public ExternalUrls build() {
      return new ExternalUrls(this);
    }
  }

    /**
     * JsonUtil class for building {@link ExternalUrls} instances.
     */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ExternalUrls> {
    public ExternalUrls createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      Map<String, String> map = new Gson().fromJson(jsonObject, new TypeToken<Map<String, String>>() {
      }.getType());

      return new ExternalUrls.Builder()
              .setExternalUrls(map)
              .build();
    }
  }

}
