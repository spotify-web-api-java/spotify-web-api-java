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
  /** The user's birthdate. */
  private final String birthdate;
  /** The name displayed on the user's profile. */
  private final String displayName;
  /** External URLs for the user. */
  private final ExternalUrl externalUrls;
  /** The Spotify Web API endpoint URL for the user. */
  private final String href;
  /** The Spotify user ID for the user. */
  private final String id;
  /** The user's profile image. */
  private final Image[] images;
  /** The object type. */
  private final ModelObjectType type;
  /** The Spotify URI for the user. */
  private final String uri;

  private User(final Builder builder) {
    super(builder);

    this.birthdate = builder.birthdate;
    this.displayName = builder.displayName;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.type = builder.type;
    this.uri = builder.uri;
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
   * Get the users display name if available. <br>
   * If the display name is not available, {@code null} will be returned.
   *
   * @return The name displayed on the user's profile. {@code null} if not available.
   */
  public String getDisplayName() {
    return displayName;
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
    return "User(birthdate=" + birthdate + ", displayName=" + displayName
        + ", externalUrls=" + externalUrls + ", href=" + href + ", id=" + id + ", images="
        + Arrays.toString(images) + ", type=" + type + ", uri=" + uri + ")";
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
    private String displayName;
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private Image[] images;
    private ModelObjectType type;
    private String uri;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
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
        .setBirthdate(
          hasAndNotNull(jsonObject, "birthdate")
            ? jsonObject.get("birthdate").getAsString()
            : null)
        .setDisplayName(
          hasAndNotNull(jsonObject, "display_name")
            ? jsonObject.get("display_name").getAsString()
            : null)
        .setExternalUrls(
          hasAndNotNull(jsonObject, "external_urls")
            ? new ExternalUrl.JsonUtil().createModelObject(
            jsonObject.getAsJsonObject("external_urls"))
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
