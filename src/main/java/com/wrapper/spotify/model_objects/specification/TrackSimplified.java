package com.wrapper.spotify.model_objects.specification;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about simplified tracks by building instances from this class.
 */
public class TrackSimplified extends AbstractModelObject {
  private final ArtistSimplified[] artists;
  private final CountryCode[] availableMarkets;
  private final int discNumber;
  private final int durationMs;
  private final boolean explicit;
  private final ExternalUrl externalUrls;
  private final String href;
  private final String id;
  private final boolean isPlayable;
  private final TrackLink linkedFrom;
  private final String name;
  private final String previewUrl;
  private final int trackNumber;
  private final ModelObjectType type;
  private final String uri;

  private TrackSimplified(final TrackSimplified.Builder builder) {
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
   * Get the artists of a track.
   *
   * @return An array of simplified artists.
   */
  public ArtistSimplified[] getArtists() {
    return artists;
  }

  /**
   * Get the country codes of all countries, in which a track is available.
   *
   * @return An array of ISO 3166-1 alpha-2 country codes.
   */
  public CountryCode[] getAvailableMarkets() {
    return availableMarkets;
  }

  /**
   * Get the disc number of a track in its album.
   *
   * @return A disc number.
   */
  public int getDiscNumber() {
    return discNumber;
  }

  /**
   * Get the duration of a track in milliseconds.
   *
   * @return Duration of track in milliseconds.
   */
  public int getDurationMs() {
    return durationMs;
  }

  /**
   * Check whether a track is explicit or not.
   *
   * @return Returns "true" for explicit, "false" for not explicit.
   */
  public boolean getIsExplicit() {
    return explicit;
  }

  /**
   * Get the external urls of a track.<br>
   * Example: Spotify-URL.
   *
   * @return The external urls of the track.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify API endpoint url of a track.
   *
   * @return A Spotify API endpoint url.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify id of a track.
   *
   * @return A Spotify track id.
   */
  public String getId() {
    return id;
  }

  /**
   * Check whether a track is playable in the market, which may has been specified
   * somewhere before requesting it.
   *
   * @return "true" if the track is playable in specified market, "false" if not.
   */
  public boolean getIsPlayable() {
    return isPlayable;
  }

  /**
   * Get the track link object of a track if the given track has been relinked.<br>
   * This happens mostly when a specific track is not available in a specific market.
   *
   * @return A track link object.
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
   * Get an url to a 30 seconds long track preview in MP3 format.<br>
   * If there is no track preview available, null will be returned.
   *
   * @return A track preview url if available, null if not available.
   */
  public String getPreviewUrl() {
    return previewUrl;
  }

  /**
   * Get the track number of a track.
   *
   * @return A track number.
   */
  public int getTrackNumber() {
    return trackNumber;
  }

  /**
   * Get the model object type, which should be a "track" in this case.
   *
   * @return A model object type.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get a Spotify track uri.
   *
   * @return A Spotify track uri.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building simplified track instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private ArtistSimplified[] artists;
    private CountryCode[] availableMarkets;
    private int discNumber;
    private int durationMs;
    private boolean explicit;
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private boolean isPlayable;
    private TrackLink linkedFrom;
    private String name;
    private String previewUrl;
    private int trackNumber;
    private ModelObjectType type;
    private String uri;

    /**
     * Set the artists of the track to be built.
     *
     * @param artists An array of simplified artists.
     * @return A builder object.
     */
    public Builder setArtists(ArtistSimplified... artists) {
      this.artists = artists;
      return this;
    }

    /**
     * Set the available markets of the track to be built.
     *
     * @param availableMarkets An array of ISO 3166-1 alpha-2 country codes.
     * @return A builder object.
     */
    public Builder setAvailableMarkets(CountryCode... availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    /**
     * Set the disc numer of the track to be built.
     *
     * @param discNumber The disc number.
     * @return A builder object.
     */
    public Builder setDiscNumber(int discNumber) {
      this.discNumber = discNumber;
      return this;
    }

    /**
     * Set the duration in milliseconds of the track to be built.
     *
     * @param durationMs Duration in milliseconds.
     * @return A builder object.
     */
    public Builder setDurationMs(int durationMs) {
      this.durationMs = durationMs;
      return this;
    }

    /**
     * Set whether the track to be built is explicit or not.
     *
     * @param explicit "true" for explicit, "false" for not explicit.
     * @return A builder object.
     */
    public Builder setExplicit(boolean explicit) {
      this.explicit = explicit;
      return this;
    }

    /**
     * Set external urls of the track to be built.
     *
     * @param externalUrls External urls object.
     * @return A builder object.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set href of Spotify api endpoint of the track to be built.
     *
     * @param href Spotify api endpoint url.
     * @return A builder object.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set the Spotify id of the track to be built.
     *
     * @param id Spotify track id.
     * @return A builder object.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set whether the track to be built is playable in your market region or not.
     *
     * @param isPlayable "true" for playable, "false" for not playable.
     * @return A builder object.
     */
    public Builder setIsPlayable(boolean isPlayable) {
      this.isPlayable = isPlayable;
      return this;
    }

    /**
     * Set the track link object of the track to be built.
     *
     * @param linkedFrom Track link object.
     * @return A builder object.
     */
    public Builder setLinkedFrom(TrackLink linkedFrom) {
      this.linkedFrom = linkedFrom;
      return this;
    }

    /**
     * Set the name of the track to be built.
     *
     * @param name Track name.
     * @return A builder object.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the preview url of the track to be built.
     *
     * @param previewUrl The url of a track preview.
     * @return A builder object.
     */
    public Builder setPreviewUrl(String previewUrl) {
      this.previewUrl = previewUrl;
      return this;
    }

    /**
     * Set the track numer of the track to be built.
     *
     * @param trackNumber The track number.
     * @return A builder object.
     */
    public Builder setTrackNumber(int trackNumber) {
      this.trackNumber = trackNumber;
      return this;
    }

    /**
     * Set the type of the model object. In this case "track".
     *
     * @param type Type of the model object.
     * @return A builder object.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set Spotify uri of the track to be built.
     *
     * @param uri The Spotify track uri.
     * @return A builder object.
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
   * JsonUtil class for building simplified track instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<TrackSimplified> {
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
                              ? ModelObjectType.valueOf(
                              jsonObject.get("type").getAsString().toUpperCase())
                              : null)
              .setUri(
                      hasAndNotNull(jsonObject, "uri")
                              ? jsonObject.get("uri").getAsString()
                              : null)
              .build();
    }
  }
}
