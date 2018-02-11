package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#context-object">
 * Context objects</a> by building instances from this class. Context objects contain information from where a
 * specific track has been played by a user.
 *
 * @see PlayHistory
 */
public class Context extends AbstractModelObject {
  private final ModelObjectType type;
  private final String href;
  private final ExternalUrl externalUrls;
  private final String uri;

  private Context(final Builder builder) {
    super(builder);

    this.type = builder.type;
    this.href = builder.href;
    this.externalUrls = builder.externalUrls;
    this.uri = builder.uri;
  }

  /**
   * Get the model object type of the context.
   *
   * @return The {@link ModelObjectType}.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get a link to the Spotify Web API endpoint providing full details of the track.
   *
   * @return A link to the Spotify Web API endpoint providing full details of the track.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the external URLs of the context.
   *
   * @return An {@link ExternalUrl} object.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URI</a>
   * for the context.
   *
   * @return The Spotify URI for the context.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Context} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private ModelObjectType type;
    private String href;
    private ExternalUrl externalUrls;
    private String uri;

    /**
     * The model object type setter.
     *
     * @param type The {@link ModelObjectType}.
     * @return A {@link Context.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * The context href setter.
     *
     * @param href A link to the Spotify Web API endpoint providing full details of the track.
     * @return A {@link Context.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * The external URLs setter.
     *
     * @param externalUrls External URLs for this context.
     * @return A {@link Context.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * The Spotify URI setter.
     *
     * @param uri Spotify URI for this context.
     * @return A {@link Context.Builder}.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public Context build() {
      return new Context(this);
    }
  }

  /**
   * JsonUtil class for building {@link Context} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Context> {
    public Context createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Context.Builder()
              .setType(
                      hasAndNotNull(jsonObject, "type")
                              ? ModelObjectType.keyOf(
                              jsonObject.get("type").getAsString().toLowerCase())
                              : null)
              .setHref(
                      hasAndNotNull(jsonObject, "href")
                              ? jsonObject.get("href").getAsString()
                              : null)
              .setExternalUrls(
                      hasAndNotNull(jsonObject, "external_urls")
                              ? new ExternalUrl.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("external_urls"))
                              : null)
              .setUri(
                      hasAndNotNull(jsonObject, "uri")
                              ? jsonObject.get("uri").getAsString()
                              : null)
              .build();
    }
  }
}
