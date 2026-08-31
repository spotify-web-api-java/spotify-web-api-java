package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/get-an-audiobook">
 * Audiobook objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = Audiobook.Builder.class)
public class Audiobook extends AbstractModelObject {
  /** The author(s) for the audiobook. */
  private final Author[] authors;
  /** A list of the countries in which the audiobook can be played. */
  private final String[] availableMarkets;
  /** The copyright statements of the audiobook. */
  private final Copyright[] copyrights;
  /** A description of the audiobook. HTML tags are stripped away from this field. */
  private final String description;
  /** A description of the audiobook. This field may contain HTML tags. */
  private final String htmlDescription;
  /** The edition of the audiobook. */
  private final String edition;
  /** Whether or not the audiobook has explicit content. */
  private final Boolean explicit;
  /** External URLs for this audiobook. */
  private final ExternalUrl externalUrls;
  /** A link to the Web API endpoint providing full details of the audiobook. */
  private final String href;
  /** The Spotify ID for the audiobook. */
  private final String id;
  /** The cover art for the audiobook in various sizes, widest first. */
  private final Image[] images;
  /** A list of the languages used in the audiobook. */
  private final String[] languages;
  /** The media type of the audiobook. */
  private final String mediaType;
  /** The name of the audiobook. */
  private final String name;
  /** The narrator(s) for the audiobook. */
  private final Narrator[] narrators;
  /** The publisher of the audiobook. */
  private final String publisher;
  /** The object type. Always {@code "audiobook"}. */
  private final ModelObjectType type;
  /** The Spotify URI for the audiobook. */
  private final String uri;
  /** The number of chapters in this audiobook. */
  private final Integer totalChapters;
  /** The chapters of the audiobook. */
  private final Paging<ChapterSimplified> chapters;

  private Audiobook(final Builder builder) {
    super(builder);
    this.authors = builder.authors;
    this.availableMarkets = builder.availableMarkets;
    this.copyrights = builder.copyrights;
    this.description = builder.description;
    this.htmlDescription = builder.htmlDescription;
    this.edition = builder.edition;
    this.explicit = builder.explicit;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.languages = builder.languages;
    this.mediaType = builder.mediaType;
    this.name = builder.name;
    this.narrators = builder.narrators;
    this.publisher = builder.publisher;
    this.type = builder.type;
    this.uri = builder.uri;
    this.totalChapters = builder.totalChapters;
    this.chapters = builder.chapters;
  }

  /**
   * Get the author(s) for the audiobook.
   *
   * @return An array of {@link Author} objects.
   */
  public Author[] getAuthors() {
    return authors;
  }

  /**
   * Get a list of the countries in which the audiobook can be played.
   *
   * @return An array of <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country codes</a>.
   */
  public String[] getAvailableMarkets() {
    return availableMarkets;
  }

  /**
   * Get the copyright statements of the audiobook.
   *
   * @return An array of {@link Copyright} objects.
   */
  public Copyright[] getCopyrights() {
    return copyrights;
  }

  /**
   * Get a description of the audiobook. HTML tags are stripped away from this field.
   *
   * @return The description of the audiobook.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get a description of the audiobook. This field may contain HTML tags.
   *
   * @return The HTML description of the audiobook.
   */
  public String getHtmlDescription() {
    return htmlDescription;
  }

  /**
   * Get the edition of the audiobook.
   *
   * @return The edition of the audiobook.
   */
  public String getEdition() {
    return edition;
  }

  /**
   * Check whether the audiobook is explicit or not.
   *
   * @return Whether or not the audiobook has explicit content.
   */
  public Boolean getExplicit() {
    return explicit;
  }

  /**
   * Get the external URLs of the audiobook.
   *
   * @return An {@link ExternalUrl} object.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify Web API endpoint URL of the audiobook.
   *
   * @return A link to the Web API endpoint providing full details of the audiobook.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify ID of the audiobook.
   *
   * @return A <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify audiobook ID</a>.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the cover art for the audiobook in various sizes, widest first.
   *
   * @return An array of {@link Image} objects.
   */
  public Image[] getImages() {
    return images;
  }

  /**
   * Get a list of the languages used in the audiobook.
   *
   * @return An array of ISO 639 language codes.
   */
  public String[] getLanguages() {
    return languages;
  }

  /**
   * Get the media type of the audiobook.
   *
   * @return The media type of the audiobook.
   */
  public String getMediaType() {
    return mediaType;
  }

  /**
   * Get the name of the audiobook.
   *
   * @return The name of the audiobook.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the narrator(s) for the audiobook.
   *
   * @return An array of {@link Narrator} objects.
   */
  public Narrator[] getNarrators() {
    return narrators;
  }

  /**
   * Get the publisher of the audiobook.
   *
   * @return The publisher of the audiobook.
   */
  public String getPublisher() {
    return publisher;
  }

  /**
   * Get the model object type. In this case {@code "audiobook"}.
   *
   * @return A {@link ModelObjectType}.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify URI of the audiobook.
   *
   * @return <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify audiobook URI</a>.
   */
  public String getUri() {
    return uri;
  }

  /**
   * Get the number of chapters in this audiobook.
   *
   * @return The number of chapters in this audiobook.
   */
  public Integer getTotalChapters() {
    return totalChapters;
  }

  /**
   * Get the chapters of the audiobook.
   *
   * @return A {@link Paging} object containing {@link ChapterSimplified} objects.
   */
  public Paging<ChapterSimplified> getChapters() {
    return chapters;
  }

  @Override
  public String toString() {
    return "Audiobook(authors=" + Arrays.toString(authors) + ", copyrights=" + Arrays.toString(copyrights)
        + ", description=" + description + ", edition=" + edition + ", explicit=" + explicit
        + ", externalUrls=" + externalUrls + ", href=" + href + ", id=" + id + ", images=" + Arrays.toString(images)
        + ", languages=" + Arrays.toString(languages) + ", mediaType=" + mediaType + ", name=" + name
        + ", narrators=" + Arrays.toString(narrators) + ", publisher=" + publisher + ", type=" + type
        + ", uri=" + uri + ", totalChapters=" + totalChapters + ", chapters=" + chapters + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Audiobook} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Author[] authors;
    private String[] availableMarkets;
    private Copyright[] copyrights;
    private String description;
    private String htmlDescription;
    private String edition;
    private Boolean explicit;
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private Image[] images;
    private String[] languages;
    private String mediaType;
    private String name;
    private Narrator[] narrators;
    private String publisher;
    private ModelObjectType type;
    private String uri;
    private Integer totalChapters;
    private Paging<ChapterSimplified> chapters;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set the authors.
     *
     * @param authors The author(s) for the audiobook.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setAuthors(Author... authors) {
      this.authors = authors;
      return this;
    }

    /**
     * Set the available markets.
     *
     * @param availableMarkets ISO 3166-1 alpha-2 country codes.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setAvailableMarkets(String... availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    /**
     * Set the copyrights.
     *
     * @param copyrights The copyright statements of the audiobook.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setCopyrights(Copyright... copyrights) {
      this.copyrights = copyrights;
      return this;
    }

    /**
     * Set the description.
     *
     * @param description A description of the audiobook.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    /**
     * Set the HTML description.
     *
     * @param htmlDescription A description of the audiobook (may contain HTML tags).
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setHtmlDescription(String htmlDescription) {
      this.htmlDescription = htmlDescription;
      return this;
    }

    /**
     * Set the edition.
     *
     * @param edition The edition of the audiobook.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setEdition(String edition) {
      this.edition = edition;
      return this;
    }

    /**
     * Set whether the audiobook is explicit.
     *
     * @param explicit Whether or not the audiobook has explicit content.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setExplicit(Boolean explicit) {
      this.explicit = explicit;
      return this;
    }

    /**
     * Set the external URLs.
     *
     * @param externalUrls The {@link ExternalUrl} for the audiobook object.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set the href.
     *
     * @param href A link to the Web API endpoint providing full details of the audiobook.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set the Spotify ID.
     *
     * @param id The Spotify ID for the audiobook.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the cover art images.
     *
     * @param images {@link Image} objects.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    /**
     * Set the languages.
     *
     * @param languages ISO 639 language codes.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setLanguages(String... languages) {
      this.languages = languages;
      return this;
    }

    /**
     * Set the media type.
     *
     * @param mediaType The media type of the audiobook.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setMediaType(String mediaType) {
      this.mediaType = mediaType;
      return this;
    }

    /**
     * Set the name.
     *
     * @param name The name of the audiobook.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the narrators.
     *
     * @param narrators The narrator(s) for the audiobook.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setNarrators(Narrator... narrators) {
      this.narrators = narrators;
      return this;
    }

    /**
     * Set the publisher.
     *
     * @param publisher The publisher of the audiobook.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setPublisher(String publisher) {
      this.publisher = publisher;
      return this;
    }

    /**
     * Set the model object type.
     *
     * @param type The {@link ModelObjectType}.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify URI.
     *
     * @param uri The Spotify URI for the audiobook.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    /**
     * Set the total number of chapters.
     *
     * @param totalChapters The number of chapters in this audiobook.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setTotalChapters(Integer totalChapters) {
      this.totalChapters = totalChapters;
      return this;
    }

    /**
     * Set the chapters paging object.
     *
     * @param chapters A {@link Paging} object containing {@link ChapterSimplified} objects.
     * @return A {@link Audiobook.Builder}.
     */
    public Builder setChapters(Paging<ChapterSimplified> chapters) {
      this.chapters = chapters;
      return this;
    }

    @Override
    public Audiobook build() {
      return new Audiobook(this);
    }
  }

  /**
   * JsonUtil class for building {@link Audiobook} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Audiobook> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    @Override
    public Audiobook createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Builder()
        .setAuthors(
          hasAndNotNull(jsonObject, "authors")
            ? new Author.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("authors"))
            : null)
        .setAvailableMarkets(
          hasAndNotNull(jsonObject, "available_markets")
            ? new Gson().fromJson(jsonObject.getAsJsonArray("available_markets"), String[].class)
            : null)
        .setCopyrights(
          hasAndNotNull(jsonObject, "copyrights")
            ? new Copyright.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("copyrights"))
            : null)
        .setDescription(
          hasAndNotNull(jsonObject, "description")
            ? jsonObject.get("description").getAsString()
            : null)
        .setHtmlDescription(
          hasAndNotNull(jsonObject, "html_description")
            ? jsonObject.get("html_description").getAsString()
            : null)
        .setEdition(
          hasAndNotNull(jsonObject, "edition")
            ? jsonObject.get("edition").getAsString()
            : null)
        .setExplicit(
          hasAndNotNull(jsonObject, "explicit")
            ? jsonObject.get("explicit").getAsBoolean()
            : null)
        .setExternalUrls(
          hasAndNotNull(jsonObject, "external_urls")
            ? new ExternalUrl.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls"))
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
            ? new Image.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("images"))
            : null)
        .setLanguages(
          hasAndNotNull(jsonObject, "languages")
            ? new Gson().fromJson(jsonObject.getAsJsonArray("languages"), String[].class)
            : null)
        .setMediaType(
          hasAndNotNull(jsonObject, "media_type")
            ? jsonObject.get("media_type").getAsString()
            : null)
        .setName(
          hasAndNotNull(jsonObject, "name")
            ? jsonObject.get("name").getAsString()
            : null)
        .setNarrators(
          hasAndNotNull(jsonObject, "narrators")
            ? new Narrator.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("narrators"))
            : null)
        .setPublisher(
          hasAndNotNull(jsonObject, "publisher")
            ? jsonObject.get("publisher").getAsString()
            : null)
        .setType(
          hasAndNotNull(jsonObject, "type")
            ? ModelObjectType.keyOf(jsonObject.get("type").getAsString().toLowerCase())
            : null)
        .setUri(
          hasAndNotNull(jsonObject, "uri")
            ? jsonObject.get("uri").getAsString()
            : null)
        .setTotalChapters(
          hasAndNotNull(jsonObject, "total_chapters")
            ? jsonObject.get("total_chapters").getAsInt()
            : null)
        .setChapters(
          hasAndNotNull(jsonObject, "chapters")
            ? new ChapterSimplified.JsonUtil().createModelObjectPaging(
                jsonObject.getAsJsonObject("chapters"))
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
    Audiobook audiobook = (Audiobook) o;
    return Objects.equals(id, audiobook.id) && Objects.equals(name, audiobook.name)
        && Objects.equals(uri, audiobook.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, uri);
  }
}
