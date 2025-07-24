package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;
import se.michaelthelin.spotify.requests.data.search.interfaces.ISearchModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#artist-object-full">
 * Artist objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = Artist.Builder.class)
public class Artist extends AbstractModelObject implements IArtistTrackModelObject, ISearchModelObject {
  /** Known external URLs for this artist. */
  private final ExternalUrl externalUrls;
  /** Information about the followers of the artist. */
  private final Followers followers;
  /** A list of the genres the artist is associated with. */
  private final String[] genres;
  /** A link to the Web API endpoint providing full details of the artist. */
  private final String href;
  /** The Spotify ID for the artist. */
  private final String id;
  /** Images of the artist in various sizes. */
  private final Image[] images;
  /** The name of the artist. */
  private final String name;
  /** The popularity of the artist (0-100). */
  private final Integer popularity;
  /** The object type: "artist". */
  private final ModelObjectType type;
  /** The Spotify URI for the artist. */
  private final String uri;

  private Artist(final Builder builder) {
    super(builder);

    this.externalUrls = builder.externalUrls;
    this.followers = builder.followers;
    this.genres = builder.genres;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.name = builder.name;
    this.popularity = builder.popularity;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  /**
   * Get the external URLs of the artist. <br>
   * Example: <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify-URL</a>
   *
   * @return An {@link ExternalUrl} object.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get information about the followers of the artist. <br>
   * Example: Follower count.
   *
   * @return A {@link Followers} object.
   */
  public Followers getFollowers() {
    return followers;
  }

  /**
   * Get a list of all genres of the artist. <br>
   * A great amount of artists may contain no information about their genres.
   *
   * @return An array of genre names.
   */
  public String[] getGenres() {
    return genres;
  }

  /**
   * Get the full Spotify Web API endpoint URL of the artist.
   *
   * @return A Spotify Web API endpoint URL.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify ID of the artist.
   *
   * @return A <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify artist ID</a>.
   */
  public String getId() {
    return id;
  }

  /**
   * Get all images of the artist (like header image) in different sizes.
   *
   * @return An array of {@link Image} objects.
   */
  public Image[] getImages() {
    return images;
  }

  /**
   * Get the name of the artist.
   *
   * @return Artist name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the popularity of the artist in a range between 0 and 100. (higher = more popular)<br>
   * The popularity of the artist is based on the popularity of its tracks.
   *
   * @return The popularity of the artist.
   */
  public Integer getPopularity() {
    return popularity;
  }

  /**
   * Get the model object type. In this case "artist".
   *
   * @return A {@link ModelObjectType}.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify URI of the artist.
   *
   * @return <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify artist URI</a>.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return "Artist(name=" + name + ", externalUrls=" + externalUrls + ", followers=" + followers + ", genres="
        + Arrays.toString(genres) + ", href=" + href + ", id=" + id + ", images=" + Arrays.toString(images)
        + ", popularity=" + popularity + ", type=" + type + ", uri=" + uri + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Artist} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private ExternalUrl externalUrls;
    private Followers followers;
    private String[] genres;
    private String href;
    private String id;
    private Image[] images;
    private String name;
    private Integer popularity;
    private ModelObjectType type;
    private String uri;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set external URLs of the artist to be built.
     *
     * @param externalUrls {@link ExternalUrl} object.
     * @return A {@link Artist.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set the followers object of the artist to be built.
     *
     * @param followers A {@link Followers} object.
     * @return A {@link Artist.Builder}.
     */
    public Builder setFollowers(Followers followers) {
      this.followers = followers;
      return this;
    }

    /**
     * Set the genres of the artist to be built.
     *
     * @param genres Genre names.
     * @return A {@link Artist.Builder}.
     */
    public Builder setGenres(String... genres) {
      this.genres = genres;
      return this;
    }

    /**
     * Set href of Spotify Web API endpoint of the artist to be built.
     *
     * @param href Spotify Web API endpoint URL.
     * @return A {@link Artist.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set artist ID of the artist to be built.
     *
     * @param id <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify artist ID</a>.
     * @return A {@link Artist.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the images of the artist to be built, like the header image.
     *
     * @param images {@link Image} objects.
     * @return A {@link Artist.Builder}.
     */
    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    /**
     * Set the name of the artist to be built.
     *
     * @param name The artist name.
     * @return A {@link Artist.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the popularity of the artist to be built.
     *
     * @param popularity The popularity of the artist between 0 and 100.
     * @return A {@link Artist.Builder}.
     */
    public Builder setPopularity(Integer popularity) {
      this.popularity = popularity;
      return this;
    }

    /**
     * Set the type of the model object. In this case "artist".
     *
     * @param type The {@link ModelObjectType}.
     * @return A {@link Artist.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify artist URI of the artist to be built.
     *
     * @param uri <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">
     *            Spotify artist URI</a>.
     * @return A {@link Artist.Builder}.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public Artist build() {
      return new Artist(this);
    }
  }

  /**
   * JsonUtil class for building {@link Artist} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Artist> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public Artist createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Artist.Builder()
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
        .setGenres(
          hasAndNotNull(jsonObject, "genres")
            ? new Gson().fromJson(
            jsonObject.getAsJsonArray("genres"), String[].class)
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
        .setName(
          hasAndNotNull(jsonObject, "name")
            ? jsonObject.get("name").getAsString()
            : null)
        .setPopularity(
          hasAndNotNull(jsonObject, "popularity")
            ? jsonObject.get("popularity").getAsInt()
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
    Artist artist = (Artist) o;
    return Objects.equals(id, artist.id) && Objects.equals(name, artist.name) && Objects.equals(uri, artist.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, uri);
  }
}
