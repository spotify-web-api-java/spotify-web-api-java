package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#artist-object-simplified">
 * simplified Artist objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = ArtistSimplified.Builder.class)
public class ArtistSimplified extends AbstractModelObject {
  /** Known external URLs for this artist. */
  private final ExternalUrl externalUrls;
  /** A link to the Web API endpoint providing full details of the artist. */
  private final String href;
  /** The Spotify ID for the artist. */
  private final String id;
  /** The name of the artist. */
  private final String name;
  /** The object type: "artist". */
  private final ModelObjectType type;
  /** The Spotify URI for the artist. */
  private final String uri;

  private ArtistSimplified(final Builder builder) {
    super(builder);

    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.name = builder.name;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  /**
   * Get the external URLs of the artist. <br>
   * Example: <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify-URL</a>
   *
   * @return An {@link ExternalUrl} object.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify Web API endpoint URL of the artist.
   *
   * @return A Spotify Web API endpoint URL.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify ID of the artist.
   *
   * @return A <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify artist ID</a>.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the name of the artist.
   *
   * @return Artist name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the model object type. In this case "artist".
   *
   * @return A {@link ModelObjectType}.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify URI of the artist.
   *
   * @return <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify artist URI</a>.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return "ArtistSimplified(name=" + name + ", externalUrls=" + externalUrls + ", href=" + href + ", id=" + id
        + ", type=" + type + ", uri=" + uri + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link ArtistSimplified} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private String name;
    private ModelObjectType type;
    private String uri;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set external URLs of the artist to be built.
     *
     * @param externalUrls {@link ExternalUrl} object.
     * @return A {@link ArtistSimplified.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set href of Spotify Web API endpoint of the artist to be built.
     *
     * @param href Spotify Web API endpoint URL.
     * @return A {@link ArtistSimplified.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set artist ID of the artist to be built.
     *
     * @param id <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify artist ID</a>.
     * @return A {@link ArtistSimplified.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the name of the artist to be built.
     *
     * @param name The artist name.
     * @return A {@link ArtistSimplified.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the type of the model object. In this case "artist".
     *
     * @param type The {@link ModelObjectType}.
     * @return A {@link ArtistSimplified.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify artist URI of the artist to be built.
     *
     * @param uri <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">
     *            Spotify artist URI</a>.
     * @return A {@link ArtistSimplified.Builder}.
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
   * JsonUtil class for building {@link ArtistSimplified} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ArtistSimplified> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public ArtistSimplified createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new ArtistSimplified.Builder()
        .setExternalUrls(
          hasAndNotNull(jsonObject, "external_urls")
            ? new ExternalUrl.JsonUtil().createModelObject(
            jsonObject.getAsJsonObject("external_urls"))
            : null)
        .setHref(
          hasAndNotNull(jsonObject, "href")
            ? jsonObject.get("href").getAsString()
            : null)
        .setId(
          hasAndNotNull(jsonObject, "id")
            ? jsonObject.get("id").getAsString()
            : null)
        .setName(
          hasAndNotNull(jsonObject, "name")
            ? jsonObject.get("name").getAsString()
            : null)
        .setType(
          hasAndNotNull(jsonObject, "type")
            ? ModelObjectType.keyOf(
            jsonObject.get("type").getAsString().toLowerCase())
            : null)
        .setUri(
          hasAndNotNull(jsonObject, "uri")
            ? jsonObject.get("uri").getAsString()
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
    ArtistSimplified artist = (ArtistSimplified) o;
    return Objects.equals(id, artist.id) && Objects.equals(name, artist.name) && Objects.equals(uri, artist.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, uri);
  }
}
