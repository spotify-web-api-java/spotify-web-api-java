package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.enums.ReleaseDatePrecision;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.interfaces.IEpisode;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#episode-object-full">
 * episode objects</a> by building instances from this class.
 */
public class Episode extends AbstractModelObject implements IEpisode {
  /** The audio preview URL for the episode. */
  private final String audioPreviewUrl;
  /** The description of the episode. */
  private final String description;
  /** The duration of the episode in milliseconds. */
  private final Integer durationMs;
  /** Whether the episode is explicit. */
  private final Boolean explicit;
  /** External URLs for the episode. */
  private final ExternalUrl externalUrls;
  /** The Spotify Web API endpoint URL for the episode. */
  private final String href;
  /** The Spotify ID for the episode. */
  private final String id;
  /** Images for the episode. */
  private final Image[] images;
  /** A description of the episode, which may contain HTML tags. */
  private final String htmlDescription;
  /** Whether the episode is externally hosted. */
  private final Boolean isExternallyHosted;
  /** Whether the episode is playable. */
  private final Boolean isPlayable;
  /** The languages spoken in the episode. */
  private final String[] languages;
  /** The name of the episode. */
  private final String name;
  /** The release date of the episode. */
  private final String releaseDate;
  /** The precision of the release date. */
  private final ReleaseDatePrecision releaseDatePrecision;
  /** Resume point for the episode. */
  private final ResumePoint resumePoint;
  /** The show the episode belongs to. */
  private final ShowSimplified show;
  /** The object type. */
  private final ModelObjectType type;
  /** The Spotify URI for the episode. */
  private final String uri;

  private Episode(final Builder builder) {
    super(builder);
    this.audioPreviewUrl = builder.audioPreviewUrl;
    this.description = builder.description;
    this.durationMs = builder.durationMs;
    this.explicit = builder.explicit;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.htmlDescription = builder.htmlDescription;
    this.isExternallyHosted = builder.isExternallyHosted;
    this.isPlayable = builder.isPlayable;
    this.languages = builder.languages;
    this.name = builder.name;
    this.releaseDate = builder.releaseDate;
    this.releaseDatePrecision = builder.releaseDatePrecision;
    this.resumePoint = builder.resumePoint;
    this.show = builder.show;
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
   * Get a description of the episode which may contain HTML tags.
   *
   * @return The HTML description of the episode.
   */
  public String getHtmlDescription() {
    return htmlDescription;
  }

  /**
   * Get the duration of the episode in milliseconds.
   *
   * @return The length of the episode in milliseconds.
   */
  @Override
  public Integer getDurationMs() {
    return durationMs;
  }

  /**
   * Check whether the episode is explicit or not.
   *
   * @return Whether or not the episode has explicit content ({@code true} = yes it does; {@code false} = no it does not
   * <b>OR</b> unknown).
   */
  @Override
  public Boolean getExplicit() {
    return explicit;
  }

  /**
   * Get the external URLs of the episode. <br>
   * Example: <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify-URL</a>
   *
   * @return An {@link ExternalUrl} object.
   */
  @Override
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify Web API endpoint URL of the episode.
   *
   * @return A link to the Web API endpoint providing full details of the episode.
   */
  @Override
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify ID of the episode.
   *
   * @return A <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify episode ID</a>.
   */
  @Override
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
  @Override
  public Boolean getIsPlayable() {
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
  @Override
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
   * Get the show on which the episode belongs.
   *
   * @return A {@link Show} object on which the episode belongs.
   */
  public ShowSimplified getShow() {
    return show;
  }

  /**
   * Get the model object type. In this case "episode".
   *
   * @return A {@link ModelObjectType}.
   */
  @Override
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify URI of the episode.
   *
   * @return <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify episode URI</a>.
   */
  @Override
  public String getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return "Episode(name=" + name + ", description=" + description + ", show=" + show + ", audioPreviewUrl="
        + audioPreviewUrl + ", durationMs=" + durationMs + ", explicit=" + explicit + ", externalUrls=" + externalUrls
        + ", href=" + href + ", id=" + id + ", images=" + Arrays.toString(images) + ", htmlDescription="
        + htmlDescription + ", isExternallyHosted=" + isExternallyHosted + ", isPlayable=" + isPlayable
        + ", languages=" + Arrays.toString(languages)
        + ", releaseDate=" + releaseDate + ", releaseDatePrecision=" + releaseDatePrecision + ", resumePoint="
        + resumePoint + ", type=" + type + ", uri=" + uri + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Episode} instances.
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
    private String htmlDescription;
    private Boolean isExternallyHosted;
    private Boolean isPlayable;
    private String[] languages;
    private String name;
    private String releaseDate;
    private ReleaseDatePrecision releaseDatePrecision;
    private ResumePoint resumePoint;
    private ShowSimplified show;
    private ModelObjectType type;
    private String uri;

    public Builder() {
      super();
    }

    public Builder setAudioPreviewUrl(String audioPreviewUrl) {
      this.audioPreviewUrl = audioPreviewUrl;
      return this;
    }

    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder setHtmlDescription(String htmlDescription) {
      this.htmlDescription = htmlDescription;
      return this;
    }

    public Builder setDurationMs(Integer durationMs) {
      this.durationMs = durationMs;
      return this;
    }

    public Builder setExplicit(Boolean explicit) {
      this.explicit = explicit;
      return this;
    }

    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    public Builder setExternallyHosted(Boolean externallyHosted) {
      isExternallyHosted = externallyHosted;
      return this;
    }

    public Builder setPlayable(Boolean playable) {
      isPlayable = playable;
      return this;
    }

    public Builder setLanguages(String... languages) {
      this.languages = languages;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setReleaseDate(String releaseDate) {
      this.releaseDate = releaseDate;
      return this;
    }

    public Builder setReleaseDatePrecision(ReleaseDatePrecision releaseDatePrecision) {
      this.releaseDatePrecision = releaseDatePrecision;
      return this;
    }

    public Builder setResumePoint(ResumePoint resumePoint) {
      this.resumePoint = resumePoint;
      return this;
    }

    public Builder setShow(ShowSimplified show) {
      this.show = show;
      return this;
    }

    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public Episode build() {
      return new Episode(this);
    }
  }

  /**
   * JsonUtil class for building {@link Episode} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Episode> {

    public JsonUtil() {
      super();
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
    Episode episode = (Episode) o;
    return Objects.equals(id, episode.id) && Objects.equals(name, episode.name) &&
      Objects.equals(releaseDate, episode.releaseDate) && Objects.equals(explicit, episode.explicit) &&
      Objects.equals(uri, episode.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, releaseDate, explicit, uri);
  }
}
