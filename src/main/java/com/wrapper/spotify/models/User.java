package com.wrapper.spotify.models;

import java.util.List;

public class User {

  private String displayName;
  private String email;
  private ExternalUrls externalUrls;
  private Followers followers;
  private String href;
  private String id;
  private String country;
  private List<Image> images;
  private Product product;
  private SpotifyEntityType type = SpotifyEntityType.USER;
  private String uri;

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
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

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Followers getFollowers() {
    return followers;
  }

  public void setFollowers(Followers followers) {
    this.followers = followers;
  }
}
