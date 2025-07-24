package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#track-object-simplified">
 * simplified Track objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = TrackSimplified.Builder.class)
public class TrackSimplified extends AbstractModelObject {
  /** The artists who performed the track. */
  private final ArtistSimplified[] artists;
  /** A list of the countries in which the track can be played. */
  private final CountryCode[] availableMarkets;
  /** The disc number. */
  private final Integer discNumber;
  /** The track length in milliseconds. */
  private final Integer durationMs;
  /** Whether the track is explicit. */
  private final Boolean explicit;
  /** External URLs for the track. */
  private final ExternalUrl externalUrls;
  /** The Spotify Web API endpoint URL for the track. */
  private final String href;
  /** The Spotify ID for the track. */
  private final String id;
  /** Whether the track is playable. */
  private final Boolean isPlayable;
  /** Information about the original track. */
  private final TrackLink linkedFrom;
  /** The name of the track. */
  private final String name;
  /** A link to a 30 second preview of the track. */
  private final String previewUrl;
  /** The number of the track. */
  private final Integer trackNumber;
  /** The object type. */
  private final ModelObjectType type;
  /** The Spotify URI for the track. */
  private final String uri;

  private TrackSimplified(final Builder builder) {
    super(builder);

    this.artists = builder.artists;
    this.availableMarkets = builder.availableMarkets;
    this.discNumber = builder.discNumber;
    this.durationMs = builder.durationMs;
    this.explicit = builder.explicit;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.isPlayable = builder.isPlayable;
    this.linkedFrom = builder.linkedFrom;
    this.name = builder.name;
    this.previewUrl = builder.previewUrl;
    this.trackNumber = builder.trackNumber;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  /**
   * Get the artists who performed the track.
   *
   * @return The artists who performed the track. Each artist object includes a link in {@code href} to more detailed
   * information about the artist.
   */
  public ArtistSimplified[] getArtists() {
    return artists;
  }

  /**
   * Get the country codes of all countries, in which the track is available.
   *
   * @return A list of the countries in which the track can be played, identified by their
   * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> code.
   */
  public CountryCode[] getAvailableMarkets() {
    return availableMarkets;
  }

  /**
   * Get the disc number of the track in its album.
   *
   * @return The disc number (usually 1 unless the album consists of more than one disc).
   */
  public Integer getDiscNumber() {
    return discNumber;
  }

  /**
   * Get the duration of the track in milliseconds.
   *
   * @return The track length in milliseconds.
   */
  public Integer getDurationMs() {
    return durationMs;
  }

  /**
   * Check whether the track is explicit or not.
   *
   * @return Whether or not the track has explicit lyrics ({@code true} = yes it does; {@code false} = no it does not
   * <b>OR</b> unknown).
   */
  public Boolean getIsExplicit() {
    return explicit;
  }

  /**
   * Get the external URLs of the track.<br>
   * Example: Spotify-URL.
   *
   * @return Known external URLs for this track.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify Web API endpoint URL of the track.
   *
   * @return A link to the Web API endpoint providing full details of the track.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify ID</a> of the
   * track.
   *
   * @return The Spotify ID for the track.
   */
  public String getId() {
    return id;
  }

  /**
   * Check whether the track is playable in the market, which may has been specified somewhere before requesting it.
   * Part of the response when <a href="https://developer.spotify.com/documentation/web-api/concepts/track-relinking">Track Relinking
   * </a> is applied.
   *
   * @return If {@code true}, the track is playable in the given market. Otherwise {@code false}.
   */
  public Boolean getIsPlayable() {
    return isPlayable;
  }

  /**
   * Get the track link object of the track if <a href="https://developer.spotify.com/documentation/web-api/concepts/track-relinking">
   * Track Relinking</a> was applied and the requested track has been replaced with a different track. The track in the
   * {@code linked_from} object contains information about the originally requested track.
   *
   * @return The track in the {@code linked_from} object contains information about the originally requested track.
   */
  public TrackLink getLinkedFrom() {
    return linkedFrom;
  }

  /**
   * Get the name of a track.
   *
   * @return Track name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get a link to a 30 second preview (MP3 format) of the track. {@code null} if not available.
   *
   * @return A link to a 30 second preview (MP3 format) of the track. {@code null} if not available.
   */
  public String getPreviewUrl() {
    return previewUrl;
  }

  /**
   * Get the track number of the track. If an album has several discs, the track number is the number on the specified
   * disc.
   *
   * @return The number of the track.
   */
  public Integer getTrackNumber() {
    return trackNumber;
  }

  /**
   * Get the model object type, which should be a "track" in this case.
   *
   * @return The object type: "track".
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify track URI.
   *
   * @return The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URI</a> for
   * the track.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return "TrackSimplified(name=" + name + ", artists=" + Arrays.toString(artists) + ", availableMarkets="
        + Arrays.toString(availableMarkets) + ", discNumber=" + discNumber + ", durationMs=" + durationMs
        + ", explicit=" + explicit + ", externalUrls=" + externalUrls + ", href=" + href + ", id=" + id
        + ", isPlayable=" + isPlayable + ", linkedFrom=" + linkedFrom + ", previewUrl=" + previewUrl + ", trackNumber="
        + trackNumber + ", type=" + type + ", uri=" + uri + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link TrackSimplified} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private ArtistSimplified[] artists;
    private CountryCode[] availableMarkets;
    private Integer discNumber;
    private Integer durationMs;
    private Boolean explicit;
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private Boolean isPlayable;
    private TrackLink linkedFrom;
    private String name;
    private String previewUrl;
    private Integer trackNumber;
    private ModelObjectType type;
    private String uri;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set the artists of the track to be built.
     *
     * @param artists The artists who performed the track.
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setArtists(ArtistSimplified... artists) {
      this.artists = artists;
      return this;
    }

    /**
     * Set the available markets of the track to be built.
     *
     * @param availableMarkets A list of the countries in which the track can be played, identified by their
     *                         <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> code.
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setAvailableMarkets(CountryCode... availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    /**
     * Set the disc number of the track to be built.
     *
     * @param discNumber The disc number (usually 1 unless the album consists of more than one disc).
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setDiscNumber(Integer discNumber) {
      this.discNumber = discNumber;
      return this;
    }

    /**
     * Set the duration in milliseconds of the track to be built.
     *
     * @param durationMs The track length in milliseconds.
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setDurationMs(Integer durationMs) {
      this.durationMs = durationMs;
      return this;
    }

    /**
     * Set whether the track to be built is explicit or not.
     *
     * @param explicit Whether or not the track has explicit lyrics ({@code true} = yes it does; {@code false} = no it
     *                 does not <b>OR</b> unknown).
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setExplicit(Boolean explicit) {
      this.explicit = explicit;
      return this;
    }

    /**
     * Set external URLs of the track to be built.
     *
     * @param externalUrls Known external URLs for the track.
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set href of Spotify Web API endpoint of the track to be built.
     *
     * @param href A link to the Web API endpoint providing full details of the track.
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set track ID of the track to be built.
     *
     * @param id The Spotify ID for the track.
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set whether the track to be built is playable in your market region or not.
     *
     * @param isPlayable If {@code true}, the track is playable in the given market. Otherwise {@code false}.
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setIsPlayable(Boolean isPlayable) {
      this.isPlayable = isPlayable;
      return this;
    }

    /**
     * Set the track link object of the track to be built.
     *
     * @param linkedFrom The track in the {@code linked_from} object contains information about the originally requested
     *                   track.
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setLinkedFrom(TrackLink linkedFrom) {
      this.linkedFrom = linkedFrom;
      return this;
    }

    /**
     * Set the name of the track to be built.
     *
     * @param name Track name.
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the preview URL of the track to be built.
     *
     * @param previewUrl A link to a 30 second preview (MP3 format) of the track. {@code null} if not available.
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setPreviewUrl(String previewUrl) {
      this.previewUrl = previewUrl;
      return this;
    }

    /**
     * Set the track number of the track to be built.
     *
     * @param trackNumber The track number.
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setTrackNumber(Integer trackNumber) {
      this.trackNumber = trackNumber;
      return this;
    }

    /**
     * Set the type of the model object. In this case "track".
     *
     * @param type The object type: "track".
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set Spotify URI of the track to be built.
     *
     * @param uri The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URI</a>
     *            for the track.
     * @return A {@link TrackSimplified.Builder}.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public TrackSimplified build() {
      return new TrackSimplified(this);
    }
  }

  /**
   * JsonUtil class for building {@link TrackSimplified} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<TrackSimplified> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public TrackSimplified createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Builder()
        .setArtists(
          hasAndNotNull(jsonObject, "artists")
            ? new ArtistSimplified.JsonUtil().createModelObjectArray(
            jsonObject.getAsJsonArray("artists"))
            : null)
        .setAvailableMarkets(
          hasAndNotNull(jsonObject, "available_markets")
            ? new Gson().fromJson(jsonObject.getAsJsonArray(
            "available_markets"), CountryCode[].class)
            : null)
        .setDiscNumber(
          hasAndNotNull(jsonObject, "disc_number")
            ? jsonObject.get("disc_number").getAsInt()
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
        .setIsPlayable(
          hasAndNotNull(jsonObject, "is_playable")
            ? jsonObject.get("is_playable").getAsBoolean()
            : null)
        .setLinkedFrom(
          hasAndNotNull(jsonObject, "linked_from")
            ? new TrackLink.JsonUtil().createModelObject(
            jsonObject.get("linked_from").getAsJsonObject())
            : null)
        .setName(
          hasAndNotNull(jsonObject, "name")
            ? jsonObject.get("name").getAsString()
            : null)
        .setPreviewUrl(
          hasAndNotNull(jsonObject, "preview_url")
            ? jsonObject.get("preview_url").getAsString()
            : null)
        .setTrackNumber(
          hasAndNotNull(jsonObject, "track_number")
            ? jsonObject.get("track_number").getAsInt()
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
    TrackSimplified track = (TrackSimplified) o;
    return Objects.equals(explicit, track.explicit) && Objects.equals(id, track.id) &&
      Objects.equals(name, track.name) && Objects.equals(uri, track.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(explicit, id, name, uri);
  }
}
