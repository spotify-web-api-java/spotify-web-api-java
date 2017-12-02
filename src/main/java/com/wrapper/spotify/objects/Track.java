package com.wrapper.spotify.objects;

import com.neovisionaries.i18n.CountryCode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.List;

public class Track extends AbstractModelObject {
  private final AlbumSimplified album;
  private final List<ArtistSimplified> artists;
  private final List<CountryCode> availableMarkets;
  private final int discNumber;
  private final int durationMs;
  private final boolean explicit;
  private final ExternalIds externalIds;
  private final ExternalUrls externalUrls;
  private final String href;
  private final String id;
  private final boolean isPlayable;
  private final TrackLink linkedFrom;
  private final Restrictions restrictions;
  private final String name;
  private final int popularity;
  private final String previewUrl;
  private final int trackNumber;
  private final ObjectType type;
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

  public AlbumSimplified getAlbum() {
    return album;
  }

  public List<ArtistSimplified> getArtists() {
    return artists;
  }

  public List<CountryCode> getAvailableMarkets() {
    return availableMarkets;
  }

  public int getDiscNumber() {
    return discNumber;
  }

  public int getDurationMs() {
    return durationMs;
  }

  public boolean getIsExplicit() {
    return explicit;
  }

  public ExternalIds getExternalIds() {
    return externalIds;
  }

  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  public String getHref() {
    return href;
  }

  public String getId() {
    return id;
  }

  public boolean getIsPlayable() {
    return isPlayable;
  }

  public TrackLink getLinkedFrom() {
    return linkedFrom;
  }

  public Restrictions getRestrictions() {
    return restrictions;
  }

  public String getName() {
    return name;
  }

  public int getPopularity() {
    return popularity;
  }

  public String getPreviewUrl() {
    return previewUrl;
  }

  public int getTrackNumber() {
    return trackNumber;
  }

  public ObjectType getType() {
    return type;
  }

  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private AlbumSimplified album;
    private List<ArtistSimplified> artists;
    private List<CountryCode> availableMarkets;
    private int discNumber;
    private int durationMs;
    private boolean explicit;
    private ExternalIds externalIds;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private boolean isPlayable;
    private TrackLink linkedFrom;
    private Restrictions restrictions;
    private String name;
    private int popularity;
    private String previewUrl;
    private int trackNumber;
    private ObjectType type;
    private String uri;

    public Builder setAlbum(AlbumSimplified album) {
      this.album = album;
      return this;
    }

    public Builder setArtists(List<ArtistSimplified> artists) {
      this.artists = artists;
      return this;
    }

    public Builder setAvailableMarkets(List<CountryCode> availableMarkets) {
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

    public Builder setExternalIds(ExternalIds externalIds) {
      this.externalIds = externalIds;
      return this;
    }

    public Builder setExternalUrls(ExternalUrls externalUrls) {
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

    public Builder setType(ObjectType type) {
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
    public Track createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new Track.Builder()
              .setAlbum(new AlbumSimplified.JsonUtil().createModelObject(jsonObject.getJSONObject("album")))
              .setArtists(new ArtistSimplified.JsonUtil().createModelObjectList(jsonObject.getJSONArray("artists")))
              .setAvailableMarkets(JSONArray.toList(jsonObject.getJSONArray("available_markets"), new Object(), new JsonConfig()))
              .setDiscNumber(jsonObject.getInt("disc_number"))
              .setDurationMs(jsonObject.getInt("duration_ms"))
              .setExplicit(jsonObject.getBoolean("explicit"))
              .setExternalIds(new ExternalIds.JsonUtil().createModelObject(jsonObject.getJSONObject("external_ids")))
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getJSONObject("external_urls")))
              .setHref(jsonObject.getString("href"))
              .setId(jsonObject.getString("id"))
              .setIsPlayable(jsonObject.getBoolean("is_playable"))
              .setLinkedFrom(new TrackLink.JsonUtil().createModelObject(jsonObject.getJSONObject("linked_from")))
              .setRestrictions(new Restrictions.JsonUtil().createModelObject(jsonObject.getJSONObject("restrictions")))
              .setName(jsonObject.getString("name"))
              .setPopularity(jsonObject.getInt("popularity"))
              .setPreviewUrl(jsonObject.getString("preview_url"))
              .setTrackNumber(jsonObject.getInt(("track_number")))
              .setType(ObjectType.valueOf(jsonObject.getString("type")))
              .setUri(jsonObject.getString("uri"))
              .build();
    }
  }
}
