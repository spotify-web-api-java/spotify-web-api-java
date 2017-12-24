package com.wrapper.spotify.model_objects;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 * Retrieve information about simplified artists by building instances from this class.
 */
public class ArtistSimplified extends AbstractModelObject {
  private final ExternalUrls externalUrls;
  private final String href;
  private final String id;
  private final String name;
  private final ModelObjectType type;
  private final String uri;

  private ArtistSimplified(final ArtistSimplified.Builder builder) {
    super(builder);

    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.name = builder.name;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  /**
   * Get the external urls of an artist.<br>
   * Example: Spotify-URL.
   *
   * @return The external urls of an artist.
   */
  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify API endpoint url of an artist.
   *
   * @return A Spotify API endpoint url.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify id of an artist.
   *
   * @return A Spotify artist id.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the name of an artist.
   *
   * @return Artist name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the model object type. In this case "artist".
   *
   * @return A model object type.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify uri of an artist.
   *
   * @return Spotify artist uri.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building simplified artist instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private String name;
    private ModelObjectType type;
    private String uri;

    /**
     * Set external urls of the artist to be built.
     *
     * @param externalUrls External urls object.
     * @return A builder object.
     */
    public Builder setExternalUrls(ExternalUrls externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set href of Spotify api endpoint of the artist to be built.
     *
     * @param href Spotify api endpoint url.
     * @return A builder object.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set album id of the artist to be built.
     *
     * @param id Artist id.
     * @return A builder object.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the name of the artist to be built.
     *
     * @param name The artist name.
     * @return A builder object.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the type of the model object. In this case "artist".
     *
     * @param type The model object type.
     * @return A builder object.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify uri of the artist to be built.
     *
     * @param uri The Spotify artist uri.
     * @return A builder object.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public ArtistSimplified build() {
      return new ArtistSimplified(this);
    }
  }

  /**
   * JsonUtil class for building simplified artist instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ArtistSimplified> {
    public ArtistSimplified createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new ArtistSimplified.Builder()
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
              .setHref((jsonObject.get("href") instanceof JsonNull) ? null : jsonObject.get("href").getAsString())
              .setId((jsonObject.get("id") instanceof JsonNull) ? null : jsonObject.get("id").getAsString())
              .setName(jsonObject.get("name").getAsString())
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setUri((jsonObject.get("uri") instanceof JsonNull) ? null : jsonObject.get("uri").getAsString())
              .build();
    }
  }
}
