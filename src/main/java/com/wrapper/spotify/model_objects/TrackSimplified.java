package com.wrapper.spotify.model_objects;

import com.neovisionaries.i18n.CountryCode;

import java.util.List;

public class TrackSimplified {
  private List<ArtistSimplified> artists;
  private List<CountryCode> availableMarkets;
  private int discNumber;
  private int durationMs;
  private boolean explicit;
  private ExternalUrls externalUrls;
  private String href;
  private String id;
  private boolean isPlayable;
  private TrackLink linkedFrom;
  private String name;
  private String previewUrl;
  private int trackNumber;
  private ModelObjectType type = ModelObjectType.TRACK;
  private String uri;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public ModelObjectType getType() {
    return type;
  }

  public void setType(ModelObjectType type) {
    this.type = type;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }
}
