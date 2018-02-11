package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.enums.ProductType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#user-object-private">
 * User objects</a> by building instances from this class. <br>
 * <b>Note:</b> Many methods of this model object may return {@code null}, depending on the scopes specified in the
 * authentication request.
 *
 * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
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

  private User(final Builder builder) {
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
   * Get the users birthdate. <br>
   * <b>Note:</b> This field is only available when the current user has granted access to the
   * {@code user-read-birthdate} scope.
   *
   * @return The user's date-of-birth.
   * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
   */
  public String getBirthdate() {
    return birthdate;
  }

  /**
   * Get the country code of the users set home country. <br>
   * <b>Note:</b> This field is only available when the current user has granted access to the {@code user-read-private}
   * scope.
   *
   * @return An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country code.
   * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
   */
  public CountryCode getCountry() {
    return country;
  }

  /**
   * Get the users display name if available. <br>
   * If the display name is not available, {@code null} will be returned.
   *
   * @return The name displayed on the user's profile. {@code null} if not available.
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Get the users email address. <br>
   * <b>Important!</b> This email address is unverified; there is no proof that it actually belongs to the user. <br>
   * <b>Note:</b> This field is only available when the current user has granted access to the {@code user-read-email}
   * scope.
   *
   * @return The user's email address, as entered by the user when creating their account.
   * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
   */
  public String getEmail() {
    return email;
  }

  /**
   * Get the external URLs of the user. <br>
   * Example: Spotify-URL.
   *
   * @return Known external URLs for this user.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get information about the followers of the user. <br>
   * Example: Follower count.
   *
   * @return Information about the followers of the user.
   */
  public Followers getFollowers() {
    return followers;
  }

  /**
   * Get the Spotify Web API endpoint URL of the user.
   *
   * @return A link to the Spotify Web API endpoint for this user.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify ID of the user.
   *
   * @return The <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify user ID</a>
   * for the user.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the profile image of the user in different sizes.
   *
   * @return The user's profile image.
   */
  public Image[] getImages() {
    return images;
  }

  /**
   * Get the product type of the users account. <br>
   * Product type refers to premium account, free account, etc. <br>
   * <b>Note:</b> This field is only available when the current user has granted access to the {@code user-read-private}
   * scope.
   *
   * @return The user's Spotify subscription level: "premium", "free", etc.
   * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
   */
  public ProductType getProduct() {
    return product;
  }

  /**
   * Get the model object type. In this case "user".
   *
   * @return The object type: "user"
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URI</a> of the
   * user.
   *
   * @return The Spotify URI for the user.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link User} instances.
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
     * @param birthdate The user's date-of-birth.
     * @return A {@link User.Builder}.
     */
    public Builder setBirthdate(String birthdate) {
      this.birthdate = birthdate;
      return this;
    }

    /**
     * Set the home country of the user object to be built.
     *
     * @param country An ISO 3166-1 alpha-2 country code.
     * @return A {@link User.Builder}.
     */
    public Builder setCountry(CountryCode country) {
      this.country = country;
      return this;
    }

    /**
     * Set the display name of the user to be built. If the user hasn't a display name, set {@code null} instead.
     *
     * @param displayName The name displayed on the user's profile. {@code null} if not available.
     * @return A {@link User.Builder}.
     */
    public Builder setDisplayName(String displayName) {
      this.displayName = displayName;
      return this;
    }

    /**
     * Set the email address of the user to be built.
     *
     * @param email The user's email address, as entered by the user when creating their account.
     * @return A {@link User.Builder}.
     */
    public Builder setEmail(String email) {
      this.email = email;
      return this;
    }

    /**
     * Set external urls of the user to be built.
     *
     * @param externalUrls Known external URLs for this user.
     * @return A {@link User.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set the followers object of the user to be built.
     *
     * @param followers Information about the followers of the user.
     * @return A {@link User.Builder}.
     */
    public Builder setFollowers(Followers followers) {
      this.followers = followers;
      return this;
    }

    /**
     * Set href of Spotify api endpoint of the user to be built.
     *
     * @param href A link to the Spotify Web API endpoint for this user.
     * @return A {@link User.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set user ID of the user to be built.
     *
     * @param id The <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify user ID
     *           </a> for the user.
     * @return A {@link User.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the profile image of the user to be built.
     *
     * @param images The user's profile image.
     * @return A {@link User.Builder}.
     */
    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    /**
     * Set the product type of the user to be built.
     *
     * @param product The user's Spotify subscription level: "premium", "free", etc.
     * @return A {@link User.Builder}.
     */
    public Builder setProduct(ProductType product) {
      this.product = product;
      return this;
    }

    /**
     * Set the type of the model object. In this case "user".
     *
     * @param type The object type: "user"
     * @return A {@link User.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify URI of the user to be built.
     *
     * @param uri The Spotify URI for the user.
     * @return A {@link User.Builder}.
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
   * JsonUtil class for building {@link User} instances.
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
                              ? ProductType.keyOf(
                              jsonObject.get("product").getAsString().toLowerCase())
                              : null)
              .setType(
                      hasAndNotNull(jsonObject, "type")
                              ? ModelObjectType.keyOf(
                              jsonObject.get("type").getAsString().toLowerCase())
                              : null)
              .setUri(
                      hasAndNotNull(jsonObject, "uri")
                              ? jsonObject.get("uri").getAsString()
                              : null)
              .build();
    }
  }
}
