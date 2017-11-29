package com.wrapper.spotify.model_objects;

import com.neovisionaries.i18n.CountryCode;

import java.util.List;

public class Album {

  private AlbumType albumType;
  private List<ArtistSimplified> artists;
  private List<CountryCode> availableMarkets;
  private List<Copyright> copyrights;
  private ExternalIds externalIds;
  private ExternalUrls externalUrls;
  private List<String> genres;
  private String href;
  private String id;
  private List<Image> images;
  private String label;
  private String name;
  private int popularity;
  private String releaseDate;
  private ReleaseDatePrecision releaseDatePrecision;
  private Paging<TrackSimplified> tracks;
  private ModelObjectType type = ModelObjectType.ALBUM;
  private String uri;

  public AlbumType getAlbumType() {
    return albumType;
  }

  public void setAlbumType(AlbumType albumType) {
    this.albumType = albumType;
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

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
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

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public ReleaseDatePrecision getReleaseDatePrecision() {
    return releaseDatePrecision;
  }

  public void setReleaseDatePrecision(ReleaseDatePrecision releaseDatePrecision) {
    this.releaseDatePrecision = releaseDatePrecision;
  }

  public Paging<TrackSimplified> getTracks() {
    return tracks;
  }

  public void setTracks(Paging<TrackSimplified> tracks) {
    this.tracks = tracks;
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
