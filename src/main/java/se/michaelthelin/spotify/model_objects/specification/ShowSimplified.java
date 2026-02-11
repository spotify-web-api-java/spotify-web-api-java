package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.requests.data.search.interfaces.ISearchModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#show-object-simplified">
 * simplified Show objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = ShowSimplified.Builder.class)
public class ShowSimplified extends AbstractModelObject implements ISearchModelObject {
  /** The copyright statements of the show. */
  private final Copyright[] copyrights;
  /** A description of the show. */
  private final String description;
  /** Whether the show is explicit. */
  private final Boolean explicit;
  /** External URLs for the show. */
  private final ExternalUrl externalUrls;
  /** The Spotify Web API endpoint URL for the show. */
  private final String href;
  /** The Spotify ID for the show. */
  private final String id;
  /** Images for the show. */
  private final Image[] images;
  /** Whether the show is externally hosted. */
  private final Boolean isExternallyHosted;
  /** The languages spoken in the show. */
  private final String[] languages;
  /** The media type of the show. */
  private final String mediaType;
  /** The name of the show. */
  private final String name;
  /** The object type. */
  private final ModelObjectType type;
  /** The Spotify URI for the show. */
  private final String uri;

  /**
   * Creates a new ShowSimplified object with the specified builder.
   *
   * @param builder the builder containing simplified show configuration
   */
  public ShowSimplified(Builder builder) {
    super(builder);
    this.copyrights = builder.copyrights;
    this.description = builder.description;
    this.explicit = builder.explicit;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.isExternallyHosted = builder.isExternallyHosted;
    this.languages = builder.languages;
    this.mediaType = builder.mediaType;
    this.name = builder.name;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  /**
   * Get the copyright statements of the show.
   *
   * @return An array of {@link Copyright} objects.
   */
  public Copyright[] getCopyrights() {
    return copyrights;
  }

  /**
   * Get a description of the show.
   *
   * @return The description of the show.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Check whether the show is explicit or not.
   *
   * @return Whether or not the show has explicit content ({@code true} = yes it does; {@code false} = no it does not
   * <b>OR</b> unknown).
   */
  public Boolean getExplicit() {
    return explicit;
  }

  /**
   * Get the external URLs of the show. <br>
   * Example: <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify-URL</a>
   *
   * @return An {@link ExternalUrl} object.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify Web API endpoint URL of the show.
   *
   * @return A link to the Web API endpoint providing full details of the show.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify ID of the show.
   *
   * @return A <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify show ID</a>.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the cover art for the show in various sizes, widest first.
   *
   * @return An array of {@link Image} objects.
   */
  public Image[] getImages() {
    return images;
  }

  /**
   * Check whether the show is hosted outside of Spotify's CDN.
   *
   * @return True if the show is hosted outside of Spotify’s CDN. Might be {@code null} in some cases.
   */
  public Boolean getExternallyHosted() {
    return isExternallyHosted;
  }

  /**
   * Get a list of the languages used in the show, identified by their ISO 639 code.
   *
   * @return An array of <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country codes</a>.
   */
  public String[] getLanguages() {
    return languages;
  }

  /**
   * Get the media type of the show.
   *
   * @return The media type of the show.
   */
  public String getMediaType() {
    return mediaType;
  }

  /**
   * Get the name of the show.
   *
   * @return The name of the show.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the model object type. In this case "show".
   *
   * @return A {@link ModelObjectType}.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify URI of the show.
   *
   * @return <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify show URI</a>.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return "ShowSimplified(copyrights="
        + Arrays.toString(copyrights) + ", description=" + description + ", explicit=" + explicit + ", externalUrls="
        + externalUrls + ", href=" + href + ", id=" + id + ", images=" + Arrays.toString(images)
        + ", isExternallyHosted=" + isExternallyHosted + ", languages=" + Arrays.toString(languages) + ", mediaType="
        + mediaType + ", name=" + name + ", type=" + type + ", uri=" + uri + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link ShowSimplified} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Copyright[] copyrights;
    private String description;
    private Boolean explicit;
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private Image[] images;
    private Boolean isExternallyHosted;
    private String[] languages;
    private String mediaType;
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
     * Set the copyrights of the show to be built.
     *
     * @param copyrights {@link Copyright} objects.
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setCopyrights(Copyright... copyrights) {
      this.copyrights = copyrights;
      return this;
    }

    /**
     * Set the description for the show to be built.
     *
     * @param description The description of the show.
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    /**
     * Set whether the show to be built is explicit or not.
     *
     * @param explicit Whether or not the show has explicit content (true = yes it does; false = no it does not OR unknown).
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setExplicit(Boolean explicit) {
      this.explicit = explicit;
      return this;
    }

    /**
     * Set the external URLs for the show to be built.
     *
     * @param externalUrls The {@link ExternalUrl} for the show object.
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set the link to the Web API endpoint providing full details of the show to be built.
     *
     * @param href The link to the Web API endpoint providing full details of the show.
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set the Spotify ID for the show to be built.
     *
     * @param id <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify show ID</a>.
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the cover art for the show to be built.
     *
     * @param images {@link Image} objects.
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    /**
     * Set whether the show to be built is hosted outside of Spotify's CDN.
     *
     * @param externallyHosted True if the show is hosted outside of Spotify’s CDN.
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setExternallyHosted(Boolean externallyHosted) {
      isExternallyHosted = externallyHosted;
      return this;
    }

    /**
     * Set a list of the languages used in the show to be built.
     *
     * @param languages An array of <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country codes</a>.
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setLanguages(String[] languages) {
      this.languages = languages;
      return this;
    }

    /**
     * Set the media type of the show.
     *
     * @param mediaType The media type of the show.
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setMediaType(String mediaType) {
      this.mediaType = mediaType;
      return this;
    }

    /**
     * Set the name for the show to be built.
     *
     * @param name The name of the show.
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the type of the model object. In this case "show".
     *
     * @param type The {@link ModelObjectType}.
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify URI for the show to be built.
     *
     * @param uri The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URI</a> for the show.
     * @return A {@link ShowSimplified.Builder}.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public ShowSimplified build() {
      return new ShowSimplified(this);
    }
  }

  /**
   * JsonUtil class for building {@link ShowSimplified} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ShowSimplified> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    @Override
    public ShowSimplified createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Builder()
        .setCopyrights(
          hasAndNotNull(jsonObject, "copyrights")
            ? new Gson().fromJson(
            jsonObject.getAsJsonArray("copyrights"), Copyright[].class)
            : null)
        .setDescription(
          hasAndNotNull(jsonObject, "description")
            ? jsonObject.get("description").getAsString()
            : null)
        .setExplicit(
          hasAndNotNull(jsonObject, "explicit")
            ? jsonObject.get("explicit").getAsBoolean()
            : null)
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
        .setImages(
          hasAndNotNull(jsonObject, "images")
            ? new Image.JsonUtil().createModelObjectArray(
            jsonObject.getAsJsonArray("images"))
            : null)
        .setExternallyHosted(
          hasAndNotNull(jsonObject, "is_externally_hosted")
            ? jsonObject.get("is_externally_hosted").getAsBoolean()
            : null)
        .setLanguages(
          hasAndNotNull(jsonObject, "languages")
            ? new Gson().fromJson(
            jsonObject.getAsJsonArray("languages"), String[].class)
            : null)
        .setMediaType(
          hasAndNotNull(jsonObject, "media_type")
            ? jsonObject.get("media_type").getAsString()
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
    ShowSimplified show = (ShowSimplified) o;
    return Objects.equals(id, show.id) && Objects.equals(name, show.name) && Objects.equals(explicit, show.explicit) &&
      Objects.equals(uri, show.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, explicit, uri);
  }
}
