package se.michaelthelin.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Map;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#external-url-object">External URL objects</a>
 * by building instances from this class.
 */
public class ExternalUrl extends AbstractModelObject {
  /** Known external URLs for this object. */
  private final Map<String, String> externalUrls;

  private ExternalUrl(final Builder builder) {
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
   * <a href="https://developer.spotify.com/web-api/object-model/#external-url-object">External URL object</a>.
   * <br><br>
   * <p>
   * External URL example: <br>
   * "spotify" - The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URL</a>
   * for the object.
   *
   * @return A {@link Map} of external public URLs to its objects.
   */
  public Map<String, String> getExternalUrls() {
    return externalUrls;
  }

  @Override
  public String toString() {
    return "ExternalUrl(externalUrls=" + externalUrls + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link ExternalUrl} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Map<String, String> externalUrls;

    public Builder() {
      super();
    }

    public Builder setExternalUrls(Map<String, String> externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    @Override
    public ExternalUrl build() {
      return new ExternalUrl(this);
    }
  }

  /**
   * JsonUtil class for building {@link ExternalUrl} instances.
   *
   * @deprecated Responses are deserialized by the library itself, so there is no need to call this.
   *             It will be removed in the next major version.
   */
  @Deprecated
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ExternalUrl> {

    public JsonUtil() {
      super();
    }

    // Kept only so callers compiled against this override, whose erased return type differs from the
    // inherited one, still link. The model's JSON shape is handled by an adapter in ModelObjectGson.
    public ExternalUrl createModelObject(JsonObject jsonObject) {
      return super.createModelObject(jsonObject);
    }
  }

}
