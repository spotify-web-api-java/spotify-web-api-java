package com.wrapper.spotify.model_objects.specification;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.miscellaneous.Restrictions;
import com.wrapper.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;
import com.wrapper.spotify.requests.data.search.interfaces.ISearchModelObject;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#track-object-full">
 * Track objects</a> by building instances from this class.
 */
public class Track extends AbstractModelObject implements IArtistTrackModelObject, ISearchModelObject {
  private final AlbumSimplified album;
  private final ArtistSimplified[] artists;
  private final CountryCode[] availableMarkets;
  private final Integer discNumber;
  private final Integer durationMs;
  private final Boolean explicit;
  private final ExternalId externalIds;
  private final ExternalUrl externalUrls;
  private final String href;
  private final String id;
  private final Boolean isPlayable;
  private final TrackLink linkedFrom;
  private final Restrictions restrictions;
  private final String name;
  private final Integer popularity;
  private final String previewUrl;
  private final Integer trackNumber;
  private final ModelObjectType type;
  private final String uri;

  private Track(final Builder builder) {
    super(builder);

    this.album = builder.album;
    this.artists = builder.artists;
    this.availableMarkets = builder.availableMarkets;
    this.discNumber = builder.discNumber;
    this.durationMs = builder.durationMs;
    this.explicit = builder.explicit;
    this.externalIds = builder.externalIds;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.isPlayable = builder.isPlayable;
    this.linkedFrom = builder.linkedFrom;
    this.restrictions = builder.restrictions;
    this.name = builder.name;
    this.popularity = builder.popularity;
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
   * Get the <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify ID</a> of the
   * track.
   *
   * @return The Spotify ID for the track.
   */
  public String getId() {
    return id;
  }

  /**
   * Check whether the track is playable in the market, which may has been specified somewhere before requesting it.
   * Part of the response when <a href="https://developer.spotify.com/web-api/track-relinking-guide/">Track Relinking
   * </a> is applied.
   *
   * @return If {@code true}, the track is playable in the given market. Otherwise {@code false}.
   */
  public Boolean getIsPlayable() {
    return isPlayable;
  }

  /**
   * Get the track link object of the track if <a href="https://developer.spotify.com/web-api/track-relinking-guide/">
   * Track Relinking</a> was applied and the requested track has been replaced with a different track. The track in the
   * {@code linked_from} object contains information about the originally requested track.
   *
   * @return The track in the {@code linked_from} object contains information about the originally requested track.
   */
  public TrackLink getLinkedFrom() {
    return linkedFrom;
  }

  /**
   * Get the restrictions of the track. Part of the response when
   * <a href="https://developer.spotify.com/web-api/track-relinking-guide/">Track Relinking</a> is applied, the original
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
  public String getName() {
    return name;
  }

  /**
   * Get the popularity of the track. The value will be between 0 and 100, with 100 being the most popular. <br><br>
   * <p>
   * The popularity of a track is a value between 0 and 100, with 100 being the most popular. The popularity is
   * calculated by algorithm and is based, in the most part, on the total number of plays the track has had and how
   * recent those plays are.<br><br>
   * <p>
   * Generally speaking, songs that are being played a lot now will have a higher popularity than songs that were played
   * a lot in the past. Duplicate tracks (e.g. the same track from a single and an album) are rated independently.
   * Artist and album popularity is derived mathematically from track popularity. Note that the popularity value may lag
   * actual popularity by a few days: the value is not updated in real time.
   *
   * @return The popularity of the track. The value will be between 0 and 100, with 100 being the most popular.
   */
  public Integer getPopularity() {
    return popularity;
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
   * @return The <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URI</a> for
   * the track.
   */
  public String getUri() {
    return uri;
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
    private CountryCode[] availableMarkets;
    private Integer discNumber;
    private Integer durationMs;
    private Boolean explicit;
    private ExternalId externalIds;
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private Boolean isPlayable;
    private TrackLink linkedFrom;
    private Restrictions restrictions;
    private String name;
    private Integer popularity;
    private String previewUrl;
    private Integer trackNumber;
    private ModelObjectType type;
    private String uri;

    /**
     * Set the album of the track to be built.
     *
     * @param album The album on which the track appears.
     * @return A {@link Track.Builder}.
     */
    public Builder setAlbum(AlbumSimplified album) {
      this.album = album;
      return this;
    }

    /**
     * Set the artists of the track to be built.
     *
     * @param artists The artists who performed the track.
     * @return A {@link Track.Builder}.
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
     * @return A {@link Track.Builder}.
     */
    public Builder setAvailableMarkets(CountryCode... availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    /**
     * Set the disc numer of the track to be built.
     *
     * @param discNumber The disc number (usually 1 unless the album consists of more than one disc).
     * @return A {@link Track.Builder}.
     */
    public Builder setDiscNumber(Integer discNumber) {
      this.discNumber = discNumber;
      return this;
    }

    /**
     * Set the duration in milliseconds of the track to be built.
     *
     * @param durationMs The track length in milliseconds.
     * @return A {@link Track.Builder}.
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
     * @return A {@link Track.Builder}.
     */
    public Builder setExplicit(Boolean explicit) {
      this.explicit = explicit;
      return this;
    }

    /**
     * Set the external IDs of the track to be built.
     *
     * @param externalIds Known external IDs for the track.
     * @return A {@link Track.Builder}.
     */
    public Builder setExternalIds(ExternalId externalIds) {
      this.externalIds = externalIds;
      return this;
    }

    /**
     * Set external URLs of the track to be built.
     *
     * @param externalUrls Known external URLs for the track.
     * @return A {@link Track.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set href of Spotify Web API endpoint of the track to be built.
     *
     * @param href A link to the Web API endpoint providing full details of the track.
     * @return A {@link Track.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set track ID of the track to be built.
     *
     * @param id The Spotify ID for the track.
     * @return A {@link Track.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set whether the track to be built is playable in your market region or not.
     *
     * @param isPlayable If {@code true}, the track is playable in the given market. Otherwise {@code false}.
     * @return A {@link Track.Builder}.
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
     * @return A {@link Track.Builder}.
     */
    public Builder setLinkedFrom(TrackLink linkedFrom) {
      this.linkedFrom = linkedFrom;
      return this;
    }

    /**
     * Set the restrictions object of the track to be built.
     *
     * @param restrictions The track response will still contain metadata for the original track, and a restrictions
     *                     object containing the reason why the track is not available.
     * @return A {@link Track.Builder}.
     */
    public Builder setRestrictions(Restrictions restrictions) {
      this.restrictions = restrictions;
      return this;
    }

    /**
     * Set the name of the track to be built.
     *
     * @param name Track name.
     * @return A {@link Track.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the popularity of the track to be built.
     *
     * @param popularity The popularity of the track. The value will be between 0 and 100, with 100 being the most
     *                   popular.
     * @return A {@link Track.Builder}.
     */
    public Builder setPopularity(Integer popularity) {
      this.popularity = popularity;
      return this;
    }

    /**
     * Set the preview URL of the track to be built.
     *
     * @param previewUrl A link to a 30 second preview (MP3 format) of the track. {@code null} if not available.
     * @return A {@link Track.Builder}.
     */
    public Builder setPreviewUrl(String previewUrl) {
      this.previewUrl = previewUrl;
      return this;
    }

    /**
     * Set the track number of the track to be built.
     *
     * @param trackNumber The track number.
     * @return A {@link Track.Builder}.
     */
    public Builder setTrackNumber(Integer trackNumber) {
      this.trackNumber = trackNumber;
      return this;
    }

    /**
     * Set the type of the model object. In this case "track".
     *
     * @param type The object type: "track".
     * @return A {@link Track.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set Spotify URI of the track to be built.
     *
     * @param uri The <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URI</a>
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
    public Track createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Track.Builder()
              .setAlbum(
                      hasAndNotNull(jsonObject, "album")
                              ? new AlbumSimplified.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("album"))
                              : null)
              .setArtists(
                      hasAndNotNull(jsonObject, "artists")
                              ? new ArtistSimplified.JsonUtil().createModelObjectArray(
                              jsonObject.getAsJsonArray("artists"))
                              : null)
              .setAvailableMarkets(
                      hasAndNotNull(jsonObject, "available_markets")
                              ? new Gson().fromJson(
                              jsonObject.getAsJsonArray("available_markets"), CountryCode[].class)
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
              .setExternalIds(
                      hasAndNotNull(jsonObject, "external_ids")
                              ? new ExternalId.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("external_ids"))
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
              .setRestrictions(
                      hasAndNotNull(jsonObject, "restrictions")
                              ? new Restrictions.JsonUtil().createModelObject(
                              jsonObject.get("restrictions").getAsJsonObject())
                              : null)
              .setName(
                      hasAndNotNull(jsonObject, "name")
                              ? jsonObject.get("name").getAsString()
                              : null)
              .setPopularity(
                      hasAndNotNull(jsonObject, "popularity")
                              ? jsonObject.get("popularity").getAsInt()
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
}
