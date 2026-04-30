package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#user-object-private">
 * User objects</a> by building instances from this class. <br>
 * <b>Note:</b> Many methods of this model object may return {@code null}, depending on the scopes specified in the
 * authentication request.
 *
 * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
 */
@JsonDeserialize(builder = User.Builder.class)
public class User extends AbstractModelObject {
  /** An alphanumeric string identifying the account, not publicly visible. */
  private final String accountId;
  /** The user's birthdate. */
  private final String birthdate;
  /** The country of the user, as set in the user's account profile. */
  private final String country;
  /** The name displayed on the user's profile. */
  private final String displayName;
  /** The user's email address, as entered by the user when creating their account. */
  private final String email;
  /** The user's explicit content settings. */
  private final ExplicitContentSettings explicitContent;
  /** External URLs for the user. */
  private final ExternalUrl externalUrls;
  /** Information about the followers of the user. */
  private final Followers followers;
  /** The Spotify Web API endpoint URL for the user. */
  private final String href;
  /** The Spotify user ID for the user. */
  private final String id;
  /** The user's profile image. */
  private final Image[] images;
  /** The user's Spotify subscription level. */
  private final String product;
  /** The object type. */
  private final ModelObjectType type;
  /** The Spotify URI for the user. */
  private final String uri;

  private User(final Builder builder) {
    super(builder);

    this.accountId = builder.accountId;
    this.birthdate = builder.birthdate;
    this.country = builder.country;
    this.displayName = builder.displayName;
    this.email = builder.email;
    this.explicitContent = builder.explicitContent;
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
   * Get the alphanumeric string identifying the account, which is not publicly visible.
   *
   * @return An alphanumeric string identifying the account.
   */
  public String getAccountId() {
    return accountId;
  }

  /**
   * Get the users birthdate. <br>
   * <b>Note:</b> This field is only available when the current user has granted access to the
   * {@code user-read-birthdate} scope.
   *
   * @return The user's date-of-birth.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
   */
  public String getBirthdate() {
    return birthdate;
  }

  /**
   * Get the country of the user, as set in the user's account profile. <br>
   * <b>Note:</b> This field is only available when the current user has granted access to the
   * {@code user-read-private} scope.
   *
   * @return An <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
   */
  public String getCountry() {
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
   * Get the user's email address, as entered by the user when creating their account. <br>
   * <b>Note:</b> This field is only available when the current user has granted access to the
   * {@code user-read-email} scope, and the email address is not necessarily verified.
   *
   * @return The user's email address.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
   */
  public String getEmail() {
    return email;
  }

  /**
   * Get the user's explicit content settings. <br>
   * <b>Note:</b> This field is only available when the current user has granted access to the
   * {@code user-read-private} scope.
   *
   * @return A {@link ExplicitContentSettings} object.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
   */
  public ExplicitContentSettings getExplicitContent() {
    return explicitContent;
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
   * Get information about the followers of the user.
   *
   * @return A {@link Followers} object.
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
   * @return The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify user ID</a>
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
   * Get the user's Spotify subscription level. <br>
   * <b>Note:</b> This field is only available when the current user has granted access to the
   * {@code user-read-private} scope.
   *
   * @return The user's Spotify subscription level: {@code "premium"}, {@code "free"}, or {@code "open"}.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
   */
  public String getProduct() {
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
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URI</a> of the
   * user.
   *
   * @return The Spotify URI for the user.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return "User(accountId=" + accountId + ", birthdate=" + birthdate + ", country=" + country + ", displayName="
        + displayName + ", email=" + email + ", explicitContent=" + explicitContent
        + ", externalUrls=" + externalUrls + ", followers=" + followers + ", href=" + href + ", id=" + id
        + ", images=" + Arrays.toString(images) + ", product=" + product + ", type=" + type + ", uri=" + uri + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link User} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String accountId;
    private String birthdate;
    private String country;
    private String displayName;
    private String email;
    private ExplicitContentSettings explicitContent;
    private ExternalUrl externalUrls;
    private Followers followers;
    private String href;
    private String id;
    private Image[] images;
    private String product;
    private ModelObjectType type;
    private String uri;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set the account ID of the user object to be built.
     *
     * @param accountId An alphanumeric string identifying the account.
     * @return A {@link User.Builder}.
     */
    public Builder setAccountId(String accountId) {
      this.accountId = accountId;
      return this;
    }

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
     * Set the country of the user to be built.
     *
     * @param country An ISO 3166-1 alpha-2 country code.
     * @return A {@link User.Builder}.
     */
    public Builder setCountry(String country) {
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
     * @param email The user's email address.
     * @return A {@link User.Builder}.
     */
    public Builder setEmail(String email) {
      this.email = email;
      return this;
    }

    /**
     * Set the explicit content settings of the user to be built.
     *
     * @param explicitContent A {@link ExplicitContentSettings} object.
     * @return A {@link User.Builder}.
     */
    public Builder setExplicitContent(ExplicitContentSettings explicitContent) {
      this.explicitContent = explicitContent;
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
     * Set information about the followers of the user to be built.
     *
     * @param followers A {@link Followers} object.
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
     * @param id The <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify user ID
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
     * Set the Spotify subscription level of the user to be built.
     *
     * @param product The user's Spotify subscription level: {@code "premium"}, {@code "free"}, or {@code "open"}.
     * @return A {@link User.Builder}.
     */
    public Builder setProduct(String product) {
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

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public User createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new User.Builder()
        .setAccountId(
          hasAndNotNull(jsonObject, "account_id")
            ? jsonObject.get("account_id").getAsString()
            : null)
        .setBirthdate(
          hasAndNotNull(jsonObject, "birthdate")
            ? jsonObject.get("birthdate").getAsString()
            : null)
        .setCountry(
          hasAndNotNull(jsonObject, "country")
            ? jsonObject.get("country").getAsString()
            : null)
        .setDisplayName(
          hasAndNotNull(jsonObject, "display_name")
            ? jsonObject.get("display_name").getAsString()
            : null)
        .setEmail(
          hasAndNotNull(jsonObject, "email")
            ? jsonObject.get("email").getAsString()
            : null)
        .setExplicitContent(
          hasAndNotNull(jsonObject, "explicit_content")
            ? new ExplicitContentSettings.JsonUtil().createModelObject(
            jsonObject.getAsJsonObject("explicit_content"))
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
            ? jsonObject.get("product").getAsString()
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(birthdate, user.birthdate) && Objects.equals(id, user.id) && Objects.equals(uri, user.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(birthdate, id, uri);
  }
}
