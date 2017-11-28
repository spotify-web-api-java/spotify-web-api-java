package com.wrapper.spotify.model_objects;

import com.neovisionaries.i18n.CountryCode;

import java.util.List;

public class AlbumSimplified {

  private AlbumType albumType;
  private List<ArtistSimplified> artists;
  private List<CountryCode> availableMarkets;
  private ExternalUrls externalUrls;
  private String href;
  private String id;
  private List<Image> images;
  private String name;
  private ObjectType type = ObjectType.ALBUM;
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
