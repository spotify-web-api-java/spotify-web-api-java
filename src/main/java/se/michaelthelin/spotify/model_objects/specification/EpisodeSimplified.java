package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.enums.ReleaseDatePrecision;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.requests.data.search.interfaces.ISearchModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#episode-object-simplified">
 * simplified Episode objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = EpisodeSimplified.Builder.class)
public class EpisodeSimplified extends AbstractModelObject implements ISearchModelObject {
  private final String audioPreviewUrl;
  private final String description;
  private final Integer durationMs;
  private final Boolean explicit;
  private final ExternalUrl externalUrls;
  private final String href;
  private final String id;
  private final Image[] images;
  private final Boolean isExternallyHosted;
  private final Boolean isPlayable;
  private final String[] languages;
  private final String name;
  private final String releaseDate;
  private final ReleaseDatePrecision releaseDatePrecision;
  private final ResumePoint resumePoint;
  private final ModelObjectType type;
  private final String uri;

  private EpisodeSimplified(final Builder builder) {
    super(builder);
    this.audioPreviewUrl = builder.audioPreviewUrl;
    this.description = builder.description;
    this.durationMs = builder.durationMs;
    this.explicit = builder.explicit;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.isExternallyHosted = builder.isExternallyHosted;
    this.isPlayable = builder.isPlayable;
    this.languages = builder.languages;
    this.name = builder.name;
    this.releaseDate = builder.releaseDate;
    this.releaseDatePrecision = builder.releaseDatePrecision;
    this.resumePoint = builder.resumePoint;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  /**
   * Get a URL to a 30 second preview (MP3 format) of the episode. {@code null} if not available.
   *
   * @return A URL to an audio preview.
   */
  public String getAudioPreviewUrl() {
    return audioPreviewUrl;
  }

  /**
   * Get a description of the episode.
   *
   * @return The description of the episode.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get the duration of the episode in milliseconds.
   *
   * @return The length of the episode in milliseconds.
   */
  public Integer getDurationMs() {
    return durationMs;
  }

  /**
   * Check whether the episode is explicit or not.
   *
   * @return Whether or not the episode has explicit content ({@code true} = yes it does; {@code false} = no it does not
   * <b>OR</b> unknown).
   */
  public Boolean getExplicit() {
    return explicit;
  }

  /**
   * Get the external URLs of the episode. <br>
   * Example: <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify-URL</a>
   *
   * @return An {@link ExternalUrl} object.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify Web API endpoint URL of the episode.
   *
   * @return A link to the Web API endpoint providing full details of the episode.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify ID of the episode.
   *
   * @return A <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify episode ID</a>.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the cover art for the episode in various sizes, widest first.
   *
   * @return An array of {@link Image} objects.
   */
  public Image[] getImages() {
    return images;
  }

  /**
   * Check whether the episode is hosted outside of Spotify's CDN.
   *
   * @return True if the episode is hosted outside of Spotify’s CDN.
   */
  public Boolean getExternallyHosted() {
    return isExternallyHosted;
  }

  /**
   * Check whether the episode is playable in the given market.
   *
   * @return True if the episode is playable in the given market. Otherwise false.
   */
  public Boolean getPlayable() {
    return isPlayable;
  }

  /**
   * Get a list of the languages used in the episode, identified by their ISO 639 code.
   *
   * @return An array of <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country codes</a>.
   */
  public String[] getLanguages() {
    return languages;
  }

  /**
   * Get the name of the episode.
   *
   * @return The name of the episode.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the date the episode was first released, for example "1981-12-15". Depending on the precision, it might be shown as "1981" or "1981-12".
   *
   * @return The release date of the episode.
   */
  public String getReleaseDate() {
    return releaseDate;
  }

  /**
   * Get the precision with which the release date is known.
   *
   * @return A {@link ReleaseDatePrecision} object.
   */
  public ReleaseDatePrecision getReleaseDatePrecision() {
    return releaseDatePrecision;
  }

  /**
   * Get the user’s most recent position in the episode. Set if the supplied access token is a user token and has the scope {@code user-read-playback-position}.
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
   * Get the Spotify URI of the episode.
   *
   * @return <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify episode URI</a>.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return "EpisodeSimplified(name=" + name + ", audioPreviewUrl=" + audioPreviewUrl + ", description=" + description
        + ", durationMs=" + durationMs + ", explicit=" + explicit + ", externalUrls=" + externalUrls + ", href=" + href
        + ", id=" + id + ", images=" + Arrays.toString(images) + ", isExternallyHosted=" + isExternallyHosted
        + ", isPlayable=" + isPlayable + ", languages=" + Arrays.toString(languages) + ", releaseDate=" + releaseDate
        + ", releaseDatePrecision=" + releaseDatePrecision + ", resumePoint=" + resumePoint + ", type=" + type
        + ", uri=" + uri + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link EpisodeSimplified} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String audioPreviewUrl;
    private String description;
    private Integer durationMs;
    private Boolean explicit;
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private Image[] images;
    private Boolean isExternallyHosted;
    private Boolean isPlayable;
    private String[] languages;
    private String name;
    private String releaseDate;
    private ReleaseDatePrecision releaseDatePrecision;
    private ResumePoint resumePoint;
    private ModelObjectType type;
    private String uri;

    /**
     * Set the URL to a audio preview for the episode to be built.
     *
     * @param audioPreviewUrl The URL to an audio preview.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setAudioPreviewUrl(String audioPreviewUrl) {
      this.audioPreviewUrl = audioPreviewUrl;
      return this;
    }

    /**
     * Set the description for the episode to be built.
     *
     * @param description The description of the episode.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    /**
     * Set the duration for the episode to be built.
     *
     * @param durationMs The duration of the episode in milliseconds.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setDurationMs(Integer durationMs) {
      this.durationMs = durationMs;
      return this;
    }

    /**
     * Set whether the episode to be built is explicit or not.
     *
     * @param explicit Whether or not the episode has explicit content (true = yes it does; false = no it does not OR unknown).
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setExplicit(Boolean explicit) {
      this.explicit = explicit;
      return this;
    }

    /**
     * Set the external URLs for the episode to be built.
     *
     * @param externalUrls The {@link ExternalUrl} for the episode object.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set the link to the Web API endpoint providing full details of the episode to be built.
     *
     * @param href The link to the Web API endpoint providing full details of the episode.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set the Spotify ID for the episode to be built.
     *
     * @param id <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify episode ID</a>.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the cover art for the episode to be built.
     *
     * @param images {@link Image} objects.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    /**
     * Set whether the episode to be built is hosted outside of Spotify's CDN.
     *
     * @param externallyHosted True if the episode is hosted outside of Spotify’s CDN.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setExternallyHosted(Boolean externallyHosted) {
      isExternallyHosted = externallyHosted;
      return this;
    }

    /**
     * Set whether the episode to be built is playable in the given market.
     *
     * @param playable True if the episode is playable in the given market. Otherwise false.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setPlayable(Boolean playable) {
      isPlayable = playable;
      return this;
    }

    /**
     * Set a list of the languages used in the episode to be built.
     *
     * @param languages An array of <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country codes</a>.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setLanguages(String... languages) {
      this.languages = languages;
      return this;
    }

    /**
     * Set the name for the episode to be built.
     *
     * @param name The name of the episode.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the release date for the episode to be built.
     *
     * @param releaseDate The release date of the episode.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setReleaseDate(String releaseDate) {
      this.releaseDate = releaseDate;
      return this;
    }

    /**
     * Set the release date precision for the episode to be built.
     *
     * @param releaseDatePrecision The {@link ReleaseDatePrecision} of the episode.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setReleaseDatePrecision(ReleaseDatePrecision releaseDatePrecision) {
      this.releaseDatePrecision = releaseDatePrecision;
      return this;
    }

    /**
     * Set the user's most recent resume point for the episode to be built.
     *
     * @param resumePoint The {@link ResumePoint} of the episode.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setResumePoint(ResumePoint resumePoint) {
      this.resumePoint = resumePoint;
      return this;
    }

    /**
     * Set the type of model object. In this case "episode".
     *
     * @param type The {@link ModelObjectType}.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify URI for the episode to be built.
     *
     * @param uri The <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URI</a> for the episode.
     * @return A {@link EpisodeSimplified.Builder}.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public EpisodeSimplified build() {
      return new EpisodeSimplified(this);
    }
  }

  /**
   * JsonUtil class for building {@link EpisodeSimplified} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<EpisodeSimplified> {
    @Override
    public EpisodeSimplified createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Builder()
        .setAudioPreviewUrl(
          hasAndNotNull(jsonObject, "audio_preview_url")
            ? jsonObject.get("audio_preview_url").getAsString()
            : null)
        .setDescription(
          hasAndNotNull(jsonObject, "description")
            ? jsonObject.get("description").getAsString()
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
        .setPlayable(
          hasAndNotNull(jsonObject, "is_playable")
            ? jsonObject.get("is_playable").getAsBoolean()
            : null)
        .setLanguages(
          hasAndNotNull(jsonObject, "languages")
            ? new Gson().fromJson(
            jsonObject.getAsJsonArray("languages"), String[].class)
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
            ? ReleaseDatePrecision.keyOf(
            jsonObject.get("release_date_precision").getAsString().toLowerCase())
            : null)
        .setResumePoint(
          hasAndNotNull(jsonObject, "resume_point")
            ? new ResumePoint.JsonUtil().createModelObject(
            jsonObject.getAsJsonObject("resume_point"))
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
    EpisodeSimplified episode = (EpisodeSimplified) o;
    return Objects.equals(id, episode.id) && Objects.equals(name, episode.name) &&
      Objects.equals(releaseDate, episode.releaseDate) && Objects.equals(explicit, episode.explicit) &&
      Objects.equals(uri, episode.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, releaseDate, explicit, uri);
  }
}
