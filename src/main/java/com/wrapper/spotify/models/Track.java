package com.wrapper.spotify.models;

import com.neovisionaries.i18n.CountryCode;

import java.util.List;

public class Track {

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
  private ObjectType type = ObjectType.TRACK;
  private String uri;

  public AlbumSimplified getAlbum() {
    return album;
  }

  public void setAlbum(AlbumSimplified album) {
    this.album = album;
  }

  public List<ArtistSimplified> getArtists() {
    return artists;
  }

  public void setArtists(List<ArtistSimplified> artists) {
    this.artists = artists;
  }

  public List<CountryCode> getAvailableMarkets() {
    return availableMarkets;
  }

  public void setAvailableMarkets(List<CountryCode> availableMarkets) {
    this.availableMarkets = availableMarkets;
  }

  public int getDiscNumber() {
    return discNumber;
  }

  public void setDiscNumber(int discNumber) {
    this.discNumber = discNumber;
  }

  public int getDurationMs() {
    return durationMs;
  }

  public void setDurationMs(int durationMs) {
    this.durationMs = durationMs;
  }

  public boolean getIsExplicit() {
    return explicit;
  }

  public void setExplicit(boolean explicit) {
    this.explicit = explicit;
  }

  public ExternalIds getExternalIds() {
    return externalIds;
  }

  public void setExternalIds(ExternalIds externalIds) {
    this.externalIds = externalIds;
  }

  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  public void setExternalUrls(ExternalUrls externalUrls) {
    this.externalUrls = externalUrls;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean getIsPlayable() {
    return isPlayable;
  }

  public void setIsPlayable(boolean isPlayable) {
    this.isPlayable = isPlayable;
  }

  public TrackLink getLinkedFrom() {
    return linkedFrom;
  }

  public void setLinkedFrom(TrackLink linkedFrom) {
    this.linkedFrom = linkedFrom;
  }

  public Restrictions getRestrictions() {
    return restrictions;
  }

  public void setRestrictions(Restrictions restrictions) {
    this.restrictions = restrictions;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPopularity() {
    return popularity;
  }

  public void setPopularity(int popularity) {
    this.popularity = popularity;
  }

  public String getPreviewUrl() {
    return previewUrl;
  }

  public void setPreviewUrl(String previewUrl) {
    this.previewUrl = previewUrl;
  }

  public int getTrackNumber() {
    return trackNumber;
  }

  public void setTrackNumber(int trackNumber) {
    this.trackNumber = trackNumber;
  }

  public ObjectType getType() {
    return type;
  }

  public void setType(ObjectType type) {
    this.type = type;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }
}
