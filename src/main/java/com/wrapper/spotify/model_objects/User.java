package com.wrapper.spotify.model_objects;

import com.neovisionaries.i18n.CountryCode;

import java.util.List;

public class User {

  private String birthdate;
  private CountryCode country;
  private String displayName;
  private String email;
  private ExternalUrls externalUrls;
  private Followers followers;
  private String href;
  private String id;
  private List<Image> images;
  private ProductType product;
  private ModelObjectType type = ModelObjectType.USER;
  private String uri;

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public CountryCode getCountry() {
    return country;
  }

  public void setCountry(CountryCode country) {
    this.country = country;
  }

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

  public Followers getFollowers() {
    return followers;
  }

  public void setFollowers(Followers followers) {
    this.followers = followers;
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

  public ProductType getProduct() {
    return product;
  }

  public void setProduct(ProductType product) {
    this.product = product;
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
