package com.wrapper.spotify.models;

import java.util.List;

public class Album {

  private AlbumType albumType;
  private List<SimpleArtist> artists;
  private List<String> availableMarkets;
  private List<Copyright> copyrights;
  private ExternalIds externalIds;
  private ExternalUrls externalUrls;
  private List<String> genres;
  private String href;
  private String id;
  private List<Image> images;
  private String name;
  private int popularity;
  private String releaseDate;
  private String releaseDatePrecision;
  private Page<SimpleTrack> tracks;
  private SpotifyEntityType type = SpotifyEntityType.ALBUM;
  private String uri;

  public AlbumType getAlbumType() {
    return albumType;
  }

  public void setAlbumType(AlbumType albumType) {
    this.albumType = albumType;
  }

  public List<SimpleArtist> getArtists() {
    return artists;
  }

  public void setArtists(List<SimpleArtist> artists) {
    this.artists = artists;
  }

  public List<String> getAvailableMarkets() {
    return availableMarkets;
  }

  public void setAvailableMarkets(List<String> availableMarkets) {
    this.availableMarkets = availableMarkets;
  }

  public List<Copyright> getCopyrights() {
    return copyrights;
  }

  public void setCopyrights(List<Copyright> copyrights) {
    this.copyrights = copyrights;
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

  public List<String> getGenres() {
    return genres;
  }

  public void setGenres(List<String> genres) {
    this.genres = genres;
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

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
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

  public Page<SimpleTrack> getTracks() {
    return tracks;
  }

  public void setTracks(Page<SimpleTrack> tracks) {
    this.tracks = tracks;
  }

  public SpotifyEntityType getType() {
    return type;
  }

  public void setType(SpotifyEntityType type) {
    this.type = type;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public String getReleaseDatePrecision() {
    return releaseDatePrecision;
  }

  public void setReleaseDatePrecision(String releaseDatePrecision) {
    this.releaseDatePrecision = releaseDatePrecision;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }
}
