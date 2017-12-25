package com.wrapper.spotify.model_objects.specification;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;
import com.wrapper.spotify.requests.data.search.interfaces.ISearchModelObject;

/**
 * Retrieve information about artists by building instances from this class.
 */
public class Artist extends AbstractModelObject implements IArtistTrackModelObject, ISearchModelObject {
  private final ExternalUrl externalUrls;
  private final Followers followers;
  private final String[] genres;
  private final String href;
  private final String id;
  private final Image[] images;
  private final String name;
  private final int popularity;
  private final ModelObjectType type;
  private final String uri;

  private Artist(final Artist.Builder builder) {
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
   * Get the external urls of an artist.<br>
   * Example: Spotify-URL.
   *
   * @return The external urls of an artist.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get information about the followers of an artist.<br>
   * Example: Follower count.
   *
   * @return Followers object.
   */
  public Followers getFollowers() {
    return followers;
  }

  /**
   * Get a list of all genres of an artist. <br>
   * A great amount of artists may contain no information about their genres.
   *
   * @return An array of all genres of an artist.
   */
  public String[] getGenres() {
    return genres;
  }

  /**
   * Get the full Spotify API endpoint url of an artist.
   *
   * @return A Spotify API endpoint url.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify id of an artist.
   *
   * @return A Spotify artist id.
   */
  public String getId() {
    return id;
  }

  /**
   * Get all images of an artist (like header image) in different sizes.
   *
   * @return An array of images in different sizes.
   */
  public Image[] getImages() {
    return images;
  }

  /**
   * Get the name of an artist.
   *
   * @return Artist name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the popularity of an artist in a range between 0 and 100. (higher = more popular)<br>
   * The popularity of an artist is based on the popularity of its tracks.
   *
   * @return The popularity of an artist.
   */
  public int getPopularity() {
    return popularity;
  }

  /**
   * Get the model object type. In this case "artist".
   *
   * @return A model object type.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify uri of an artist.
   *
   * @return Spotify artist uri.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building artist instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private ExternalUrl externalUrls;
    private Followers followers;
    private String[] genres;
    private String href;
    private String id;
    private Image[] images;
    private String name;
    private int popularity;
    private ModelObjectType type;
    private String uri;

    /**
     * Set external urls of the artist to be built.
     *
     * @param externalUrls  External urls object.
     * @return              A builder object.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set the followers object of the artist to be built.
     *
     * @param followers A followers object.
     * @return          A builder object
     */
    public Builder setFollowers(Followers followers) {
      this.followers = followers;
      return this;
    }

    /**
     * Set the genres of the artist to be built.
     *
     * @param genres  An array of genres.
     * @return        A builder object.
     */
    public Builder setGenres(String... genres) {
      this.genres = genres;
      return this;
    }

    /**
     * Set href of Spotify api endpoint of the artist to be built.
     *
     * @param href  Spotify api endpoint url.
     * @return      A builder object.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set the Spotify id of the artist to be built.
     *
     * @param id  Artist id.
     * @return    A builder object.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the images of the artist to be built, like the header image.
     *
     * @param images  An array of image objects.
     * @return        A builder object.
     */
    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    /**
     * Set the name of the artist to be built.
     *
     * @param name  The artist name.
     * @return      A builder object.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the popularity of the artist to be built.
     *
     * @param popularity  The popularity of the artist between 0 and 100.
     * @return            A builder object.
     */
    public Builder setPopularity(int popularity) {
      this.popularity = popularity;
      return this;
    }

    /**
     * Set the type of the model object. In this case "artist".
     *
     * @param type  The model object type.
     * @return      A builder object.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify uri of the artist to be built.
     *
     * @param uri The Spotify artist uri.
     * @return    A builder object.
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
   * JsonUtil class for building artist instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Artist> {
    public Artist createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Artist.Builder()
              .setExternalUrls(new ExternalUrl.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
              .setFollowers(new Followers.JsonUtil().createModelObject(jsonObject.getAsJsonObject("followers")))
              .setGenres(new Gson().fromJson(jsonObject.getAsJsonArray("genres"), String[].class))
              .setHref(jsonObject.get("href").getAsString())
              .setId(jsonObject.get("id").getAsString())
              .setImages(new Image.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("images")))
              .setName(jsonObject.get("name").getAsString())
              .setPopularity(jsonObject.get("popularity").getAsInt())
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setUri(jsonObject.get("uri").getAsString())
              .build();
    }
  }

}