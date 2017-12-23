package com.wrapper.spotify.model_objects.specification;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.miscellaneous.Restrictions;
import com.wrapper.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;
import com.wrapper.spotify.requests.data.search.interfaces.ISearchModelObject;

/**
 * Retrieve information about tracks by building instances from this class.
 */
public class Track extends AbstractModelObject implements IArtistTrackModelObject, ISearchModelObject {
  private final AlbumSimplified album;
  private final ArtistSimplified[] artists;
  private final CountryCode[] availableMarkets;
  private final int discNumber;
  private final int durationMs;
  private final boolean explicit;
  private final ExternalId externalIds;
  private final ExternalUrl externalUrls;
  private final String href;
  private final String id;
  private final boolean isPlayable;
  private final TrackLink linkedFrom;
  private final Restrictions restrictions;
  private final String name;
  private final int popularity;
  private final String previewUrl;
  private final int trackNumber;
  private final ModelObjectType type;
  private final String uri;

  private Track(final Track.Builder builder) {
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
   * Get the album of a track.
   *
   * @return A simplified album.
   */
  public AlbumSimplified getAlbum() {
    return album;
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
   * Get the disc number of a track in it's album.
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
   * Get the external ids of a track.<br>
   * Example: isrc -&gt; "International Standard Recording Code"
   *
   * @return All external ids of the track.
   */
  public ExternalId getExternalIds() {
    return externalIds;
  }

  /**
   * Get the external urls of a track.<br>
   * Example: Spotify-URL
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
   * @return Return "true" if the track is playable in specified market,
   */
  public boolean getIsPlayable() {
    return isPlayable;
  }

  /**
   * Get the track link of a track if the given track has been relinked.<br>
   * This happens mostly when a specific track is not available in a specific market.
   *
   * @return The original Spotify url of the track
   */
  public TrackLink getLinkedFrom() {
    return linkedFrom;
  }

  /**
   * Get the restrictions of a track.<br>
   * Example: The reason why a track may not be available.
   *
   * @return The restrictions of the track
   */
  public Restrictions getRestrictions() {
    return restrictions;
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
   * Get the popularity of a track in a range between 0 and 100. (higher = more popular)<br>
   * The popularity of a track is based on how often a track has been played recently.
   *
   * @return The popularity of the track.
   */
  public int getPopularity() {
    return popularity;
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
   * Builder class for building track instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private AlbumSimplified album;
    private ArtistSimplified[] artists;
    private CountryCode[] availableMarkets;
    private int discNumber;
    private int durationMs;
    private boolean explicit;
    private ExternalId externalIds;
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private boolean isPlayable;
    private TrackLink linkedFrom;
    private Restrictions restrictions;
    private String name;
    private int popularity;
    private String previewUrl;
    private int trackNumber;
    private ModelObjectType type;
    private String uri;

    public Builder setAlbum(AlbumSimplified album) {
      this.album = album;
      return this;
    }

    public Builder setArtists(ArtistSimplified... artists) {
      this.artists = artists;
      return this;
    }

    public Builder setAvailableMarkets(CountryCode... availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    public Builder setDiscNumber(int discNumber) {
      this.discNumber = discNumber;
      return this;
    }

    public Builder setDurationMs(int durationMs) {
      this.durationMs = durationMs;
      return this;
    }

    public Builder setExplicit(boolean explicit) {
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

    public Builder setIsPlayable(boolean isPlayable) {
      this.isPlayable = isPlayable;
      return this;
    }

    public Builder setLinkedFrom(TrackLink linkedFrom) {
      this.linkedFrom = linkedFrom;
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

    public Builder setPopularity(int popularity) {
      this.popularity = popularity;
      return this;
    }

    public Builder setPreviewUrl(String previewUrl) {
      this.previewUrl = previewUrl;
      return this;
    }

    public Builder setTrackNumber(int trackNumber) {
      this.trackNumber = trackNumber;
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
    public Track build() {
      return new Track(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Track> {
    public Track createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      if (jsonObject.has("available_markets")) {
        return new Track.Builder()
                .setAlbum(new AlbumSimplified.JsonUtil().createModelObject(jsonObject.getAsJsonObject("album")))
                .setArtists(new ArtistSimplified.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("artists")))
                .setAvailableMarkets(new Gson().fromJson(jsonObject.getAsJsonArray("available_markets"), CountryCode[].class))
                .setDiscNumber(jsonObject.get("disc_number").getAsInt())
                .setDurationMs(jsonObject.get("duration_ms").getAsInt())
                .setExplicit(jsonObject.get("explicit").getAsBoolean())
                .setExternalIds(new ExternalId.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_ids")))
                .setExternalUrls(new ExternalUrl.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
                .setHref((jsonObject.get("href") instanceof JsonNull) ? null : jsonObject.get("href").getAsString())
                .setId((jsonObject.get("id") instanceof JsonNull) ? null : jsonObject.get("id").getAsString())
                .setName(jsonObject.get("name").getAsString())
                .setPopularity(jsonObject.get("popularity").getAsInt())
                .setPreviewUrl((jsonObject.get("preview_url") instanceof JsonNull) ? null : jsonObject.get("preview_url").getAsString())
                .setTrackNumber(jsonObject.get("track_number").getAsInt())
                .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
                .setUri(jsonObject.get("uri").getAsString())
                .build();
      } else if (jsonObject.has("is_playable")) {
        return new Track.Builder()
                .setAlbum(new AlbumSimplified.JsonUtil().createModelObject(jsonObject.getAsJsonObject("album")))
                .setArtists(new ArtistSimplified.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("artists")))
                .setDiscNumber(jsonObject.get("disc_number").getAsInt())
                .setDurationMs(jsonObject.get("duration_ms").getAsInt())
                .setExplicit(jsonObject.get("explicit").getAsBoolean())
                .setExternalIds(new ExternalId.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_ids")))
                .setExternalUrls(new ExternalUrl.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
                .setHref(jsonObject.get("href").getAsString())
                .setId(jsonObject.get("id").getAsString())
                .setIsPlayable(jsonObject.get("is_playable").getAsBoolean())
                .setLinkedFrom(new TrackLink.JsonUtil().createModelObject(jsonObject.get("linked_from").getAsJsonObject()))
                .setRestrictions(new Restrictions.JsonUtil().createModelObject(jsonObject.get("restrictions").getAsJsonObject()))
                .setName(jsonObject.get("name").getAsString())
                .setPopularity(jsonObject.get("popularity").getAsInt())
                .setPreviewUrl((jsonObject.get("preview_url") instanceof JsonNull) ? null : jsonObject.get("preview_url").getAsString())
                .setTrackNumber(jsonObject.get("track_number").getAsInt())
                .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
                .setUri(jsonObject.get("uri").getAsString())
                .build();
      } else {
        return null;
      }
    }
  }
}
