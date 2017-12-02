package com.wrapper.spotify.objects;

import com.neovisionaries.i18n.CountryCode;
import net.sf.json.JSONObject;

import java.util.List;

public class User extends AbstractModelObject {
  private final String birthdate;
  private final CountryCode country;
  private final String displayName;
  private final String email;
  private final ExternalUrls externalUrls;
  private final Followers followers;
  private final String href;
  private final String id;
  private final List<Image> images;
  private final ProductType product;
  private final ObjectType type;
  private final String uri;

  private User(final User.Builder builder) {
    super(builder);

    this.birthdate = builder.birthdate;
    this.country = builder.country;
    this.displayName = builder.displayName;
    this.email = builder.email;
    this.externalUrls = builder.externalUrls;
    this.followers = builder.followers;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.product = builder.product;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public CountryCode getCountry() {
    return country;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getEmail() {
    return email;
  }

  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  public Followers getFollowers() {
    return followers;
  }

  public String getHref() {
    return href;
  }

  public String getId() {
    return id;
  }

  public List<Image> getImages() {
    return images;
  }

  public ProductType getProduct() {
    return product;
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
    private ObjectType type;
    private String uri;

    public Builder setBirthdate(String birthdate) {
      this.birthdate = birthdate;
      return this;
    }

    public Builder setCountry(CountryCode country) {
      this.country = country;
      return this;
    }

    public Builder setDisplayName(String displayName) {
      this.displayName = displayName;
      return this;
    }

    public Builder setEmail(String email) {
      this.email = email;
      return this;
    }

    public Builder setExternalUrls(ExternalUrls externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    public Builder setFollowers(Followers followers) {
      this.followers = followers;
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

    public Builder setImages(List<Image> images) {
      this.images = images;
      return this;
    }

    public Builder setProduct(ProductType product) {
      this.product = product;
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
    public User build() {
      return new User(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<User> {
    public User createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new User.Builder()
              .setBirthdate(jsonObject.getString("birthdate"))
              .setCountry(CountryCode.getByCode(jsonObject.getString("country")))
              .setDisplayName(jsonObject.getString("display_name"))
              .setEmail(jsonObject.getString("email"))
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getJSONObject("external_urls")))
              .setFollowers(new Followers.JsonUtil().createModelObject(jsonObject.getJSONObject("followers")))
              .setHref(jsonObject.getString("href"))
              .setId(jsonObject.getString("id"))
              .setImages(new Image.JsonUtil().createModelObjectList(jsonObject.getJSONArray("images")))
              .setProduct(ProductType.valueOf(jsonObject.getString("product")))
              .setType(ObjectType.valueOf(jsonObject.getString("type")))
              .setUri(jsonObject.getString("uri"))
              .build();
    }
  }
}
