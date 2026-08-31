package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.enums.ReleaseDatePrecision;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/get-a-chapter">
 * simplified Chapter objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = ChapterSimplified.Builder.class)
public class ChapterSimplified extends AbstractModelObject {
  /** A URL to a 30 second preview (MP3 format) of the chapter. {@code null} if not available. */
  private final String audioPreviewUrl;
  /** A list of the countries in which the chapter can be played. */
  private final String[] availableMarkets;
  /** The number of the chapter. */
  private final Integer chapterNumber;
  /** A description of the chapter. HTML tags are stripped away from this field. */
  private final String description;
  /** A description of the chapter. This field may contain HTML tags. */
  private final String htmlDescription;
  /** The chapter length in milliseconds. */
  private final Integer durationMs;
  /** Whether or not the chapter has explicit content. */
  private final Boolean explicit;
  /** External URLs for this chapter. */
  private final ExternalUrl externalUrls;
  /** A link to the Web API endpoint providing full details of the chapter. */
  private final String href;
  /** The Spotify ID for the chapter. */
  private final String id;
  /** The cover art for the chapter in various sizes, widest first. */
  private final Image[] images;
  /** True if the chapter is playable in the given market. Otherwise false. */
  private final Boolean isPlayable;
  /** A list of the languages used in the chapter. */
  private final String[] languages;
  /** The name of the chapter. */
  private final String name;
  /** The date the chapter was first released. */
  private final String releaseDate;
  /** The precision with which {@code release_date} value is known. */
  private final ReleaseDatePrecision releaseDatePrecision;
  /** The user's most recent position in the chapter. */
  private final ResumePoint resumePoint;
  /** The object type. */
  private final ModelObjectType type;
  /** The Spotify URI for the chapter. */
  private final String uri;
  /** Included in the response when a content restriction is applied. */
  private final ChapterRestriction restrictions;

  private ChapterSimplified(final Builder builder) {
    super(builder);
    this.audioPreviewUrl = builder.audioPreviewUrl;
    this.availableMarkets = builder.availableMarkets;
    this.chapterNumber = builder.chapterNumber;
    this.description = builder.description;
    this.htmlDescription = builder.htmlDescription;
    this.durationMs = builder.durationMs;
    this.explicit = builder.explicit;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.isPlayable = builder.isPlayable;
    this.languages = builder.languages;
    this.name = builder.name;
    this.releaseDate = builder.releaseDate;
    this.releaseDatePrecision = builder.releaseDatePrecision;
    this.resumePoint = builder.resumePoint;
    this.type = builder.type;
    this.uri = builder.uri;
    this.restrictions = builder.restrictions;
  }

  /**
   * Get a URL to a 30 second preview (MP3 format) of the chapter. {@code null} if not available.
   *
   * @return A URL to an audio preview.
   */
  public String getAudioPreviewUrl() {
    return audioPreviewUrl;
  }

  /**
   * Get a list of the countries in which the chapter can be played.
   *
   * @return An array of <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country codes</a>.
   */
  public String[] getAvailableMarkets() {
    return availableMarkets;
  }

  /**
   * Get the number of the chapter.
   *
   * @return The number of the chapter.
   */
  public Integer getChapterNumber() {
    return chapterNumber;
  }

  /**
   * Get a description of the chapter. HTML tags are stripped away from this field.
   *
   * @return The description of the chapter.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get a description of the chapter. This field may contain HTML tags.
   *
   * @return The HTML description of the chapter.
   */
  public String getHtmlDescription() {
    return htmlDescription;
  }

  /**
   * Get the duration of the chapter in milliseconds.
   *
   * @return The chapter length in milliseconds.
   */
  public Integer getDurationMs() {
    return durationMs;
  }

  /**
   * Check whether the chapter is explicit or not.
   *
   * @return Whether or not the chapter has explicit content.
   */
  public Boolean getExplicit() {
    return explicit;
  }

  /**
   * Get the external URLs of the chapter.
   *
   * @return An {@link ExternalUrl} object.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify Web API endpoint URL of the chapter.
   *
   * @return A link to the Web API endpoint providing full details of the chapter.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify ID of the chapter.
   *
   * @return A <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify chapter ID</a>.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the cover art for the chapter in various sizes, widest first.
   *
   * @return An array of {@link Image} objects.
   */
  public Image[] getImages() {
    return images;
  }

  /**
   * Check whether the chapter is playable in the given market.
   *
   * @return True if the chapter is playable in the given market. Otherwise false.
   */
  public Boolean getPlayable() {
    return isPlayable;
  }

  /**
   * Get a list of the languages used in the chapter.
   *
   * @return An array of ISO 639 language codes.
   */
  public String[] getLanguages() {
    return languages;
  }

  /**
   * Get the name of the chapter.
   *
   * @return The name of the chapter.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the release date of the chapter.
   *
   * @return The date the chapter was first released.
   */
  public String getReleaseDate() {
    return releaseDate;
  }

  /**
   * Get the precision of the release date.
   *
   * @return The precision with which {@code release_date} value is known.
   */
  public ReleaseDatePrecision getReleaseDatePrecision() {
    return releaseDatePrecision;
  }

  /**
   * Get the user's most recent position in the chapter.
   *
   * @return A {@link ResumePoint} object.
   */
  public ResumePoint getResumePoint() {
    return resumePoint;
  }

  /**
   * Get the model object type. In this case "episode".
   *
   * @return A {@link ModelObjectType}.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify URI of the chapter.
   *
   * @return <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify chapter URI</a>.
   */
  public String getUri() {
    return uri;
  }

  /**
   * Get the content restriction applied, if any.
   *
   * @return A {@link ChapterRestriction} object, or {@code null}.
   */
  public ChapterRestriction getRestrictions() {
    return restrictions;
  }

  @Override
  public String toString() {
    return "ChapterSimplified(audioPreviewUrl=" + audioPreviewUrl + ", chapterNumber=" + chapterNumber
        + ", description=" + description + ", durationMs=" + durationMs + ", explicit=" + explicit
        + ", externalUrls=" + externalUrls + ", href=" + href + ", id=" + id + ", images=" + Arrays.toString(images)
        + ", isPlayable=" + isPlayable + ", languages=" + Arrays.toString(languages) + ", name=" + name
        + ", releaseDate=" + releaseDate + ", releaseDatePrecision=" + releaseDatePrecision
        + ", resumePoint=" + resumePoint + ", type=" + type + ", uri=" + uri + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link ChapterSimplified} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String audioPreviewUrl;
    private String[] availableMarkets;
    private Integer chapterNumber;
    private String description;
    private String htmlDescription;
    private Integer durationMs;
    private Boolean explicit;
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private Image[] images;
    private Boolean isPlayable;
    private String[] languages;
    private String name;
    private String releaseDate;
    private ReleaseDatePrecision releaseDatePrecision;
    private ResumePoint resumePoint;
    private ModelObjectType type;
    private String uri;
    private ChapterRestriction restrictions;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set the audio preview URL.
     *
     * @param audioPreviewUrl A URL to a 30 second preview (MP3 format) of the chapter.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setAudioPreviewUrl(String audioPreviewUrl) {
      this.audioPreviewUrl = audioPreviewUrl;
      return this;
    }

    /**
     * Set the available markets.
     *
     * @param availableMarkets ISO 3166-1 alpha-2 country codes.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setAvailableMarkets(String... availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    /**
     * Set the chapter number.
     *
     * @param chapterNumber The number of the chapter.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setChapterNumber(Integer chapterNumber) {
      this.chapterNumber = chapterNumber;
      return this;
    }

    /**
     * Set the description.
     *
     * @param description A description of the chapter.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    /**
     * Set the HTML description.
     *
     * @param htmlDescription A description of the chapter (may contain HTML tags).
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setHtmlDescription(String htmlDescription) {
      this.htmlDescription = htmlDescription;
      return this;
    }

    /**
     * Set the duration in milliseconds.
     *
     * @param durationMs The chapter length in milliseconds.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setDurationMs(Integer durationMs) {
      this.durationMs = durationMs;
      return this;
    }

    /**
     * Set whether the chapter is explicit.
     *
     * @param explicit Whether or not the chapter has explicit content.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setExplicit(Boolean explicit) {
      this.explicit = explicit;
      return this;
    }

    /**
     * Set the external URLs.
     *
     * @param externalUrls The {@link ExternalUrl} for the chapter object.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set the href.
     *
     * @param href A link to the Web API endpoint providing full details of the chapter.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set the Spotify ID.
     *
     * @param id The Spotify ID for the chapter.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the cover art images.
     *
     * @param images {@link Image} objects.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    /**
     * Set whether the chapter is playable.
     *
     * @param isPlayable True if the chapter is playable in the given market.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setPlayable(Boolean isPlayable) {
      this.isPlayable = isPlayable;
      return this;
    }

    /**
     * Set the languages.
     *
     * @param languages ISO 639 language codes.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setLanguages(String... languages) {
      this.languages = languages;
      return this;
    }

    /**
     * Set the name.
     *
     * @param name The name of the chapter.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the release date.
     *
     * @param releaseDate The date the chapter was first released.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setReleaseDate(String releaseDate) {
      this.releaseDate = releaseDate;
      return this;
    }

    /**
     * Set the release date precision.
     *
     * @param releaseDatePrecision The precision with which {@code release_date} value is known.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setReleaseDatePrecision(ReleaseDatePrecision releaseDatePrecision) {
      this.releaseDatePrecision = releaseDatePrecision;
      return this;
    }

    /**
     * Set the resume point.
     *
     * @param resumePoint The user's most recent position in the chapter.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setResumePoint(ResumePoint resumePoint) {
      this.resumePoint = resumePoint;
      return this;
    }

    /**
     * Set the model object type.
     *
     * @param type The {@link ModelObjectType}.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify URI.
     *
     * @param uri The Spotify URI for the chapter.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    /**
     * Set the content restrictions.
     *
     * @param restrictions A {@link ChapterRestriction} object.
     * @return A {@link ChapterSimplified.Builder}.
     */
    public Builder setRestrictions(ChapterRestriction restrictions) {
      this.restrictions = restrictions;
      return this;
    }

    @Override
    public ChapterSimplified build() {
      return new ChapterSimplified(this);
    }
  }

  /**
   * JsonUtil class for building {@link ChapterSimplified} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ChapterSimplified> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    @Override
    public ChapterSimplified createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Builder()
        .setAudioPreviewUrl(
          hasAndNotNull(jsonObject, "audio_preview_url")
            ? jsonObject.get("audio_preview_url").getAsString()
            : null)
        .setAvailableMarkets(
          hasAndNotNull(jsonObject, "available_markets")
            ? new Gson().fromJson(jsonObject.getAsJsonArray("available_markets"), String[].class)
            : null)
        .setChapterNumber(
          hasAndNotNull(jsonObject, "chapter_number")
            ? jsonObject.get("chapter_number").getAsInt()
            : null)
        .setDescription(
          hasAndNotNull(jsonObject, "description")
            ? jsonObject.get("description").getAsString()
            : null)
        .setHtmlDescription(
          hasAndNotNull(jsonObject, "html_description")
            ? jsonObject.get("html_description").getAsString()
            : null)
        .setDurationMs(
          hasAndNotNull(jsonObject, "duration_ms")
            ? jsonObject.get("duration_ms").getAsInt()
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
        .setPlayable(
          hasAndNotNull(jsonObject, "is_playable")
            ? jsonObject.get("is_playable").getAsBoolean()
            : null)
        .setLanguages(
          hasAndNotNull(jsonObject, "languages")
            ? new Gson().fromJson(jsonObject.getAsJsonArray("languages"), String[].class)
            : null)
        .setName(
          hasAndNotNull(jsonObject, "name")
            ? jsonObject.get("name").getAsString()
            : null)
        .setReleaseDate(
          hasAndNotNull(jsonObject, "release_date")
            ? jsonObject.get("release_date").getAsString()
            : null)
        .setReleaseDatePrecision(
          hasAndNotNull(jsonObject, "release_date_precision")
            ? ReleaseDatePrecision.keyOf(jsonObject.get("release_date_precision").getAsString().toLowerCase())
            : null)
        .setResumePoint(
          hasAndNotNull(jsonObject, "resume_point")
            ? new ResumePoint.JsonUtil().createModelObject(jsonObject.getAsJsonObject("resume_point"))
            : null)
        .setType(
          hasAndNotNull(jsonObject, "type")
            ? ModelObjectType.keyOf(jsonObject.get("type").getAsString().toLowerCase())
            : null)
        .setUri(
          hasAndNotNull(jsonObject, "uri")
            ? jsonObject.get("uri").getAsString()
            : null)
        .setRestrictions(
          hasAndNotNull(jsonObject, "restrictions")
            ? new ChapterRestriction.JsonUtil().createModelObject(jsonObject.getAsJsonObject("restrictions"))
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
    ChapterSimplified chapter = (ChapterSimplified) o;
    return Objects.equals(id, chapter.id) && Objects.equals(name, chapter.name)
        && Objects.equals(uri, chapter.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, uri);
  }
}
