package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.interfaces.ITrack;
import se.michaelthelin.spotify.model_objects.miscellaneous.Restrictions;
import se.michaelthelin.spotify.requests.data.users.interfaces.IArtistTrackModelObject;
import se.michaelthelin.spotify.requests.data.search.interfaces.ISearchModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#track-object-full">
 * Track objects</a> by building instances from this class.
 */
public class Track extends AbstractModelObject implements IArtistTrackModelObject, ISearchModelObject, ITrack {
  /** The album on which the track appears. */
  private final AlbumSimplified album;
  /** The artists who performed the track. */
  private final ArtistSimplified[] artists;
  /** The disc number. */
  private final Integer discNumber;
  /** The track length in milliseconds. */
  private final Integer durationMs;
  /** Whether the track is explicit. */
  private final Boolean explicit;
  /** Known external IDs for the track. */
  private final ExternalId externalIds;
  /** External URLs for the track. */
  private final ExternalUrl externalUrls;
  /** The Spotify Web API endpoint URL for the track. */
  private final String href;
  /** The Spotify ID for the track. */
  private final String id;
  /** Whether the track is playable. */
  private final Boolean isPlayable;
  /** Restrictions on the track. */
  private final Restrictions restrictions;
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

  private Track(final Builder builder) {
    super(builder);

    this.album = builder.album;
    this.artists = builder.artists;
    this.discNumber = builder.discNumber;
    this.durationMs = builder.durationMs;
    this.explicit = builder.explicit;
    this.externalIds = builder.externalIds;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.isPlayable = builder.isPlayable;
    this.restrictions = builder.restrictions;
    this.name = builder.name;
    this.previewUrl = builder.previewUrl;
    this.trackNumber = builder.trackNumber;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  /**
   * Get the album on which the track appears.
   *
   * @return The album on which the track appears. The (simplified) album object includes a link in href to full
   * information about the album.
   */
  public AlbumSimplified getAlbum() {
    return album;
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
  @Override
  public Integer getDurationMs() {
    return durationMs;
  }

  /**
   * Check whether the track is explicit or not.
   *
   * @return Whether or not the track has explicit lyrics ({@code true} = yes it does; {@code false} = no it does not
   * <b>OR</b> unknown).
   */
  public Boolean getExplicit() {
    return explicit;
  }

  /**
   * Get the external IDs of the track.<br>
   * Example: isrc -&gt; "International Standard Recording Code".
   *
   * @return Known external IDs for the track.
   */
  public ExternalId getExternalIds() {
    return externalIds;
  }

  /**
   * Get the external URLs of the track.<br>
   * Example: Spotify-URL.
   *
   * @return Known external URLs for this track.
   */
  @Override
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify Web API endpoint URL of the track.
   *
   * @return A link to the Web API endpoint providing full details of the track.
   */
  @Override
  public String getHref() {
    return href;
  }

  /**
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify ID</a> of the
   * track.
   *
   * @return The Spotify ID for the track.
   */
  @Override
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
   * Get the restrictions of the track. Part of the response when
   * <a href="https://developer.spotify.com/documentation/web-api/concepts/track-relinking">Track Relinking</a> is applied, the original
   * track is not available in the given market, and Spotify did not have any tracks to relink it with. The track
   * response will still contain metadata for the original track, and a restrictions object containing the reason why
   * the track is not available. <br>
   * Example: {@code "restrictions" : {"reason" : "market"}}
   *
   * @return The track response will still contain metadata for the original track, and a restrictions object containing
   * the reason why the track is not available.
   */
  public Restrictions getRestrictions() {
    return restrictions;
  }

  /**
   * Get the name of the track.
   *
   * @return Track name.
   */
  @Override
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
  @Override
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify track URI.
   *
   * @return The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URI</a> for
   * the track.
   */
  @Override
  public String getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return "Track(name=" + name + ", artists=" + Arrays.toString(artists) + ", album=" + album
      + ", discNumber=" + discNumber + ", durationMs=" + durationMs
      + ", explicit=" + explicit + ", externalIds=" + externalIds + ", externalUrls=" + externalUrls + ", href="
      + href + ", id=" + id + ", isPlayable=" + isPlayable + ", restrictions="
      + restrictions + ", previewUrl=" + previewUrl + ", trackNumber=" + trackNumber
      + ", type=" + type + ", uri=" + uri + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Track} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private AlbumSimplified album;
    private ArtistSimplified[] artists;
    private Integer discNumber;
    private Integer durationMs;
    private Boolean explicit;
    private ExternalId externalIds;
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private Boolean isPlayable;
    private Restrictions restrictions;
    private String name;
    private String previewUrl;
    private Integer trackNumber;
    private ModelObjectType type;
    private String uri;

    public Builder() {
      super();
    }

    public Builder setAlbum(AlbumSimplified album) {
      this.album = album;
      return this;
    }

    public Builder setArtists(ArtistSimplified... artists) {
      this.artists = artists;
      return this;
    }

    public Builder setDiscNumber(Integer discNumber) {
      this.discNumber = discNumber;
      return this;
    }

    public Builder setDurationMs(Integer durationMs) {
      this.durationMs = durationMs;
      return this;
    }

    /**
     * Set whether the track to be built is explicit or not.
     *
     * @param explicit Whether or not the track has explicit lyrics ({@code true} = yes it does; {@code false} = no it
     *                 does not <b>OR</b> unknown).
     * @return A {@link Track.Builder}.
     */
    public Builder setExplicit(Boolean explicit) {
      this.explicit = explicit;
      return this;
    }

    public Builder setExternalIds(ExternalId externalIds) {
      this.externalIds = externalIds;
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

    public Builder setIsPlayable(Boolean isPlayable) {
      this.isPlayable = isPlayable;
      return this;
    }

    public Builder setRestrictions(Restrictions restrictions) {
      this.restrictions = restrictions;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setPreviewUrl(String previewUrl) {
      this.previewUrl = previewUrl;
      return this;
    }

    public Builder setTrackNumber(Integer trackNumber) {
      this.trackNumber = trackNumber;
      return this;
    }

    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set Spotify URI of the track to be built.
     *
     * @param uri The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URI</a>
     *            for the track.
     * @return A {@link Track.Builder}.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public Track build() {
      return new Track(this);
    }
  }

  /**
   * JsonUtil class for building {@link Track} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Track> {

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
    Track track = (Track) o;
    return Objects.equals(explicit, track.explicit) && Objects.equals(id, track.id) &&
      Objects.equals(name, track.name) && Objects.equals(uri, track.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(explicit, id, name, uri);
  }
}
