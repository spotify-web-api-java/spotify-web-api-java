package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.enums.ProductType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about users by building instances from this class.
 */
public class User extends AbstractModelObject {
  private final String birthdate;
  private final CountryCode country;
  private final String displayName;
  private final String email;
  private final ExternalUrl externalUrls;
  private final Followers followers;
  private final String href;
  private final String id;
  private final Image[] images;
  private final ProductType product;
  private final ModelObjectType type;
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

  /**
   * Get a users birthdate.
   *
   * @return Birthdate as a string.
   */
  public String getBirthdate() {
    return birthdate;
  }

  /**
   * Get the country code of a users home country.
   *
   * @return An ISO 3166-1 alpha-2 country code.
   */
  public CountryCode getCountry() {
    return country;
  }

  /**
   * Get a users display name if available.<br>
   * If the display name is not available, null will be returned.
   *
   * @return Display name if available.
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Get a users email address.
   *
   * @return Email address.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Get the external urls of an user.<br>
   * Example: Spotify-URL.
   *
   * @return The external urls of an user.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get information about the followers of an user.<br>
   * Example: Follower count.
   *
   * @return Followers object.
   */
  public Followers getFollowers() {
    return followers;
  }

  /**
   * Get the full Spotify API endpoint url of an user.
   *
   * @return A Spotify API endpoint url.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify id of an user.
   *
   * @return A Spotify user id.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the profile image of an user in different sizes.
   *
   * @return An array of images in different sizes.
   */
  public Image[] getImages() {
    return images;
  }

  /**
   * Get the product type of a users account.<br>
   * Product type refers to premium account, free account, etc.
   *
   * @return The product type.
   */
  public ProductType getProduct() {
    return product;
  }

  /**
   * Get the model object type. In this case "user".
   *
   * @return A model object type.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify uri of an user.
   *
   * @return Spotify user uri.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building user instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String birthdate;
    private CountryCode country;
    private String displayName;
    private String email;
    private ExternalUrl externalUrls;
    private Followers followers;
    private String href;
    private String id;
    private Image[] images;
    private ProductType product;
    private ModelObjectType type;
    private String uri;

    /**
     * Set the birthday of the user object to be built.
     *
     * @param birthdate A users birthdate.
     * @return A builder object.
     */
    public Builder setBirthdate(String birthdate) {
      this.birthdate = birthdate;
      return this;
    }

    /**
     * Set the home country of the user object to be built.
     *
     * @param country An ISO 3166-1 alpha-2 country code.
     * @return A builder object.
     */
    public Builder setCountry(CountryCode country) {
      this.country = country;
      return this;
    }

    /**
     * Set the display name of the user to be built. If a user hasn't a display
     * name, set null instead.
     *
     * @param displayName Display name of a user or null.
     * @return A builder object.
     */
    public Builder setDisplayName(String displayName) {
      this.displayName = displayName;
      return this;
    }

    /**
     * Set the email address of the user to be built.
     *
     * @param email Email adress.
     * @return A builder object.
     */
    public Builder setEmail(String email) {
      this.email = email;
      return this;
    }

    /**
     * Set external urls of the user to be built.
     *
     * @param externalUrls External urls object.
     * @return A builder object.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set the followers object of the user to be built.
     *
     * @param followers A followers object.
     * @return A builder object.
     */
    public Builder setFollowers(Followers followers) {
      this.followers = followers;
      return this;
    }

    /**
     * Set href of Spotify api endpoint of the user to be built.
     *
     * @param href Spotify api endpoint url.
     * @return A builder object.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set user id of the user to be built.
     *
     * @param id User id.
     * @return A builder object.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the profile image of the user to be built.
     *
     * @param images An array of image objects.
     * @return A builder object.
     */
    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    /**
     * Set the product type of the user to be built.
     *
     * @param product Product type.
     * @return A builder object.
     */
    public Builder setProduct(ProductType product) {
      this.product = product;
      return this;
    }

    /**
     * Set the type of the model object. In this case "user".
     *
     * @param type The model object type.
     * @return A builder object.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify uri of the user to be built.
     *
     * @param uri The Spotify playlist uri.
     * @return A builder object.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public User build() {
      return new User(this);
    }
  }

  /**
   * JsonUtil class for building user instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<User> {
    public User createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new User.Builder()
              .setBirthdate(
                      hasAndNotNull(jsonObject, "birthdate")
                              ? jsonObject.get("birthdate").getAsString()
                              : null)
              .setCountry(
                      hasAndNotNull(jsonObject, "country")
                              ? CountryCode.getByCode(
                              jsonObject.get("country").getAsString())
                              : null)
              .setDisplayName(
                      hasAndNotNull(jsonObject, "display_name")
                              ? jsonObject.get("display_name").getAsString()
                              : null)
              .setEmail(
                      hasAndNotNull(jsonObject, "email")
                              ? jsonObject.get("email").getAsString()
                              : null)
              .setExternalUrls(
                      hasAndNotNull(jsonObject, "external_urls")
                              ? new ExternalUrl.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("external_urls"))
                              : null)
              .setFollowers(
                      hasAndNotNull(jsonObject, "followers")
                              ? new Followers.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("followers"))
                              : null)
              .setHref(
                      hasAndNotNull(jsonObject, "href")
                              ? jsonObject.get("href").getAsString()
                              : null)
              .setId(
                      hasAndNotNull(jsonObject, "id")
                              ? jsonObject.get("id").getAsString()
                              : null)
              .setImages(
                      hasAndNotNull(jsonObject, "images")
                              ? new Image.JsonUtil().createModelObjectArray(
                              jsonObject.getAsJsonArray("images"))
                              : null)
              .setProduct(
                      hasAndNotNull(jsonObject, "product")
                              ? ProductType.valueOf(
                              jsonObject.get("product").getAsString().toUpperCase())
                              : null)
              .setType(
                      hasAndNotNull(jsonObject, "type")
                              ? ModelObjectType.valueOf(
                              jsonObject.get("type").getAsString().toUpperCase())
                              : null)
              .setUri(
                      hasAndNotNull(jsonObject, "uri")
                              ? jsonObject.get("uri").getAsString()
                              : null)
              .build();
    }
  }
}
