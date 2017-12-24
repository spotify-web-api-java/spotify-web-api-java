package com.wrapper.spotify.model_objects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;

/**
 * Retrieve information about albums by building instances from this class.
 * TODO: Implement a getLabel() method
 */
public class Album extends AbstractModelObject {
  private final AlbumType albumType;
  private final ArtistSimplified[] artists;
  private final CountryCode[] availableMarkets;
  private final Copyright[] copyrights;
  private final ExternalIds externalIds;
  private final ExternalUrls externalUrls;
  private final String[] genres;
  private final String href;
  private final String id;
  private final Image[] images;
  private final String label;
  private final String name;
  private final int popularity;
  private final String releaseDate;
  private final ReleaseDatePrecision releaseDatePrecision;
  private final Paging<TrackSimplified> tracks;
  private final ModelObjectType type;
  private final String uri;

  private Album(final Album.Builder builder) {
    super(builder);

    this.albumType = builder.albumType;
    this.artists = builder.artists;
    this.availableMarkets = builder.availableMarkets;
    this.copyrights = builder.copyrights;
    this.externalIds = builder.externalIds;
    this.externalUrls = builder.externalUrls;
    this.genres = builder.genres;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.label = builder.label;
    this.name = builder.name;
    this.popularity = builder.popularity;
    this.releaseDate = builder.releaseDate;
    this.releaseDatePrecision = builder.releaseDatePrecision;
    this.tracks = builder.tracks;
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
   * Get all copyright texts of an album.
   *
   * @return An array of copyright objects.
   */
  public Copyright[] getCopyrights() {
    return copyrights;
  }

  /**
   * Get the external ids of an album.<br>
   * Example: upc -&gt; "Universal Product Code".
   *
   * @return All external ids of an album.
   */
  public ExternalIds getExternalIds() {
    return externalIds;
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
   * Get a list of all genres of an album. <br>
   * A great amount of albums may contain no information about their genres.
   *
   * @return An array of all genres of an album.
   */
  public String[] getGenres() {
    return genres;
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
   * Get the popularity of an album in a range between 0 and 100. (higher = more popular)<br>
   * The popularity of an album is based on the popularity of its individual tracks.
   *
   * @return The popularity of an album.
   */
  public int getPopularity() {
    return popularity;
  }

  /**
   * Get the release date of an album with the highest precision available.
   *
   * @return The release date of an album.
   */
  public String getReleaseDate() {
    return releaseDate;
  }

  /**
   * Get the precision of an albums release date. This is needed when the exact release day
   * of an album is not known.
   *
   * @return The precision of an albums release date.
   */
  public ReleaseDatePrecision getReleaseDatePrecision() {
    return releaseDatePrecision;
  }

  /**
   * Get a page of tracks of an album.
   *
   * @return A page of simplified tracks.
   */
  public Paging<TrackSimplified> getTracks() {
    return tracks;
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
   * Builder class for building album instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {

    private AlbumType albumType;
    private ArtistSimplified[] artists;
    private CountryCode[] availableMarkets;
    private Copyright[] copyrights;
    private ExternalIds externalIds;
    private ExternalUrls externalUrls;
    private String[] genres;
    private String href;
    private String id;
    private Image[] images;
    private String label;
    private String name;
    private int popularity;
    private String releaseDate;
    private ReleaseDatePrecision releaseDatePrecision;
    private Paging<TrackSimplified> tracks;
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
     * Set the copyrights of the album to be built.
     *
     * @param copyrights Copyright objects.
     * @return A builder object.
     */
    public Builder setCopyrights(Copyright[] copyrights) {
      this.copyrights = copyrights;
      return this;
    }

    /**
     * Set the external ids of the album to be built.
     *
     * @param externalIds External ids of the album.
     * @return A builder object.
     */
    public Builder setExternalIds(ExternalIds externalIds) {
      this.externalIds = externalIds;
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
     * Set the genres of the album to be built.
     *
     * @param genres An array of genres.
     * @return A builder object.
     */
    public Builder setGenres(String[] genres) {
      this.genres = genres;
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
     * Set the label of the album to be built.
     *
     * @param label The album label.
     * @return A builder object.
     */
    public Builder setLabel(String label) {
      this.label = label;
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
     * Set the popularity of the album to be built.
     *
     * @param popularity The popularity of the album between 0 and 100.
     * @return A builder object.
     */
    public Builder setPopularity(int popularity) {
      this.popularity = popularity;
      return this;
    }

    /**
     * Set the release date of the album to be built.
     *
     * @param releaseDate The release date of the album.
     * @return A builder object.
     */
    public Builder setReleaseDate(String releaseDate) {
      this.releaseDate = releaseDate;
      return this;
    }

    /**
     * Set the release date precision of the album to be built.
     *
     * @param releaseDatePrecision The release date precision of the album.
     * @return A builder object.
     */
    public Builder setReleaseDatePrecision(ReleaseDatePrecision releaseDatePrecision) {
      this.releaseDatePrecision = releaseDatePrecision;
      return this;
    }

    /**
     * Set the tracks of the album to be built.
     *
     * @param tracks A page of tracks of the album.
     * @return A builder object.
     */
    public Builder setTracks(Paging<TrackSimplified> tracks) {
      this.tracks = tracks;
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
    public Album build() {
      return new Album(this);
    }
  }

  /**
   * JsonUtil class for building album instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Album> {
    public Album createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Album.Builder()
              .setAlbumType(AlbumType.valueOf(jsonObject.get("album_type").getAsString().toUpperCase()))
              .setArtists(new ArtistSimplified.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("artists")))
              .setAvailableMarkets(new Gson().fromJson(jsonObject.getAsJsonArray("available_markets"), CountryCode[].class))
              .setCopyrights(new Copyright.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("copyrights")))
              .setExternalIds(new ExternalIds.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_ids")))
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
              .setGenres(new Gson().fromJson(jsonObject.getAsJsonArray("genres"), String[].class))
              .setHref(jsonObject.get("href").getAsString())
              .setId(jsonObject.get("id").getAsString())
              .setImages(new Image.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("images")))
              .setLabel(jsonObject.get("label").getAsString())
              .setName(jsonObject.get("name").getAsString())
              .setPopularity(jsonObject.get("popularity").getAsInt())
              .setReleaseDate(jsonObject.get("release_date").getAsString())
              .setReleaseDatePrecision(ReleaseDatePrecision.valueOf(jsonObject.get("release_date_precision").getAsString().toUpperCase()))
              .setTracks(new TrackSimplified.JsonUtil().createModelObjectPaging(jsonObject.getAsJsonObject("tracks")))
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setUri(jsonObject.get("uri").getAsString())
              .build();
    }
  }
}
