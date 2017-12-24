package com.wrapper.spotify.model_objects;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;

/**
 * Retrieve information about simplified albums by building instances from this class.
 */
public class AlbumSimplified extends AbstractModelObject {
  private final AlbumType albumType;
  private final ArtistSimplified[] artists;
  private final CountryCode[] availableMarkets;
  private final ExternalUrls externalUrls;
  private final String href;
  private final String id;
  private final Image[] images;
  private final String name;
  private final ModelObjectType type;
  private final String uri;

  private AlbumSimplified(final AlbumSimplified.Builder builder) {
    super(builder);

    this.albumType = builder.albumType;
    this.artists = builder.artists;
    this.availableMarkets = builder.availableMarkets;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.name = builder.name;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  /**
   * Get the type of an album.
   *
   * @return The album type.
   */
  public AlbumType getAlbumType() {
    return albumType;
  }

  /**
   * Get the artists of an album.
   *
   * @return An array of simplified artists.
   */
  public ArtistSimplified[] getArtists() {
    return artists;
  }

  /**
   * Get the country codes of all countries, in which an album is available.
   *
   * @return An array of ISO 3166-1 alpha-2 country codes.
   */
  public CountryCode[] getAvailableMarkets() {
    return availableMarkets;
  }

  /**
   * Get the external urls of an album.<br>
   * Example: Spotify-URL.
   *
   * @return The external urls of an album.
   */
  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify API endpoint url of an album.
   *
   * @return A Spotify API endpoint url.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify id of an album.
   *
   * @return A Spotify album id.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the album cover art of an album in different sizes.
   *
   * @return An array of album cover arts in different sizes.
   */
  public Image[] getImages() {
    return images;
  }

  /**
   * Get the name of an album.
   *
   * @return Album name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the model object type. In this case "album".
   *
   * @return A model object type.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify uri of an album.
   *
   * @return Spotify album uri.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building simplified album instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {

    private AlbumType albumType;
    private ArtistSimplified[] artists;
    private CountryCode[] availableMarkets;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private Image[] images;
    private String name;
    private ModelObjectType type;
    private String uri;

    /**
     * Set the type of the album to be built.
     *
     * @param albumType Type of the album.
     * @return A builder object.
     */
    public Builder setAlbumType(AlbumType albumType) {
      this.albumType = albumType;
      return this;
    }

    /**
     * Set the artists of the album to be built.
     *
     * @param artists An array of simplified artists.
     * @return A builder object.
     */
    public Builder setArtists(ArtistSimplified[] artists) {
      this.artists = artists;
      return this;
    }

    /**
     * Set the available markets of the album to be built.
     *
     * @param availableMarkets An array of ISO 3166-1 alpha-2 country codes.
     * @return A builder object.
     */
    public Builder setAvailableMarkets(CountryCode[] availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    /**
     * Set external urls of the album to be built.
     *
     * @param externalUrls External urls object.
     * @return A builder object.
     */
    public Builder setExternalUrls(ExternalUrls externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set href of Spotify api endpoint of the album to be built.
     *
     * @param href Spotify api endpoint url.
     * @return A builder object.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set album id of the album to be built.
     *
     * @param id Album id.
     * @return A builder object.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the cover art in different sizes of the album to be built.
     *
     * @param images An array of image objects.
     * @return A builder object.
     */
    public Builder setImages(Image[] images) {
      this.images = images;
      return this;
    }

    /**
     * Set the name of the album to be built.
     *
     * @param name The album name.
     * @return A builder object.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the type of the model object. In this case "album".
     *
     * @param type The model object type.
     * @return A builder object.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify uri of the album to be built.
     *
     * @param uri The Spotify album uri.
     * @return A builder object.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public AlbumSimplified build() {
      return new AlbumSimplified(this);
    }
  }

  /**
   * JsonUtil class for building simplified album instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AlbumSimplified> {
    public AlbumSimplified createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AlbumSimplified.Builder()
              .setAlbumType((jsonObject.get("album_type") instanceof JsonNull) ? null : AlbumType.valueOf(jsonObject.get("album_type").getAsString().toUpperCase()))
              .setArtists(new ArtistSimplified.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("artists")))
              .setAvailableMarkets(new Gson().fromJson(jsonObject.get("available_markets"), CountryCode[].class))
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
              .setHref((jsonObject.get("href") instanceof JsonNull) ? null : jsonObject.get("href").getAsString())
              .setId((jsonObject.get("id") instanceof JsonNull) ? null : jsonObject.get("id").getAsString())
              .setImages(new Image.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("images")))
              .setName(jsonObject.get("name").getAsString())
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setUri((jsonObject.get("uri") instanceof JsonNull) ? null : jsonObject.get("uri").getAsString())
              .build();
    }
  }
}
