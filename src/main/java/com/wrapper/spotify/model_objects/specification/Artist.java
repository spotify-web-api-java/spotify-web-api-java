package com.wrapper.spotify.model_objects.specification;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;
import com.wrapper.spotify.requests.data.search.interfaces.ISearchModelObject;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#artist-object-full">
 * Artist objects</a> by building instances from this class.
 */
public class Artist extends AbstractModelObject implements IArtistTrackModelObject, ISearchModelObject {
  private final ExternalUrl externalUrls;
  private final Followers followers;
  private final String[] genres;
  private final String href;
  private final String id;
  private final Image[] images;
  private final String name;
  private final Integer popularity;
  private final ModelObjectType type;
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
   * Example: <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify-URL</a>
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
   * @return A <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify artist ID</a>.
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
   * @return <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify artist URI</a>.
   */
  public String getUri() {
    return uri;
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
     * @param id <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify artist ID</a>.
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
     * @param uri <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">
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

}