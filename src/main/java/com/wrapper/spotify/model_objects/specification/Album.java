package com.wrapper.spotify.model_objects.specification;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.enums.AlbumType;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.enums.ReleaseDatePrecision;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#album-object-full">
 * Album objects</a> by building instances from this class.
 */
public class Album extends AbstractModelObject {
  private final AlbumType albumType;
  private final ArtistSimplified[] artists;
  private final CountryCode[] availableMarkets;
  private final Copyright[] copyrights;
  private final ExternalId externalIds;
  private final ExternalUrl externalUrls;
  private final String[] genres;
  private final String href;
  private final String id;
  private final Image[] images;
  private final String label;
  private final String name;
  private final Integer popularity;
  private final String releaseDate;
  private final ReleaseDatePrecision releaseDatePrecision;
  private final Paging<TrackSimplified> tracks;
  private final ModelObjectType type;
  private final String uri;

  private Album(final Builder builder) {
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
   * Get the type of the album.
   *
   * @return The {@link AlbumType}.
   */
  public AlbumType getAlbumType() {
    return albumType;
  }

  /**
   * Get the artists of the album.
   *
   * @return An array of {@link ArtistSimplified} objects.
   */
  public ArtistSimplified[] getArtists() {
    return artists;
  }

  /**
   * Get the country codes of all countries, in which the album is available.
   *
   * @return An array of <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country
   * codes</a>.
   */
  public CountryCode[] getAvailableMarkets() {
    return availableMarkets;
  }

  /**
   * Get all copyright texts of the album.
   *
   * @return An array of {@link Copyright} objects.
   */
  public Copyright[] getCopyrights() {
    return copyrights;
  }

  /**
   * Get the external IDs of the album. <br>
   * Example: upc -&gt; "Universal Product Code".
   *
   * @return An array of {@link ExternalId} objects.
   */
  public ExternalId getExternalIds() {
    return externalIds;
  }

  /**
   * Get the external URLs of the album. <br>
   * Example: <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify-URL</a>
   *
   * @return An {@link ExternalUrl} object.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get a list of all genres of the album. <br>
   * A great amount of albums may contain no information about their genres.
   *
   * @return An array of all genres of an album.
   */
  public String[] getGenres() {
    return genres;
  }

  /**
   * Get the full Spotify Web API endpoint URL of the album.
   *
   * @return A Spotify Web API endpoint URL.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify ID of the album.
   *
   * @return A <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify album ID</a>.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the album cover art of the album in different sizes.
   *
   * @return An array of {@link Image} objects.
   */
  public Image[] getImages() {
    return images;
  }

  /**
   * Get the label for the album.
   *
   * @return The label for the album.
   */
  public String getLabel() {
    return label;
  }

  /**
   * Get the name of the album.
   *
   * @return Album name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the popularity of the album in a range between 0 and 100. (higher = more popular) <br>
   * The popularity of the album is based on the popularity of its individual tracks.
   *
   * @return The popularity of the album.
   */
  public Integer getPopularity() {
    return popularity;
  }

  /**
   * Get the release date of the album with the highest precision available.
   *
   * @return The release date of the album.
   */
  public String getReleaseDate() {
    return releaseDate;
  }

  /**
   * Get the precision of the albums release date. This is needed when the exact release day of an album is not known.
   *
   * @return The precision of the albums release date.
   */
  public ReleaseDatePrecision getReleaseDatePrecision() {
    return releaseDatePrecision;
  }

  /**
   * Get a page of tracks of the album.
   *
   * @return A {@link Paging} object containing {@link TrackSimplified} objects.
   */
  public Paging<TrackSimplified> getTracks() {
    return tracks;
  }

  /**
   * Get the model object type. In this case "album".
   *
   * @return A {@link ModelObjectType}.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify URI of the album.
   *
   * @return <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify album URI</a>.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Album} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {

    private AlbumType albumType;
    private ArtistSimplified[] artists;
    private CountryCode[] availableMarkets;
    private Copyright[] copyrights;
    private ExternalId externalIds;
    private ExternalUrl externalUrls;
    private String[] genres;
    private String href;
    private String id;
    private Image[] images;
    private String label;
    private String name;
    private Integer popularity;
    private String releaseDate;
    private ReleaseDatePrecision releaseDatePrecision;
    private Paging<TrackSimplified> tracks;
    private ModelObjectType type;
    private String uri;

    /**
     * Set the type of the album to be built.
     *
     * @param albumType The {@link AlbumType}.
     * @return A {@link Album.Builder}.
     */
    public Builder setAlbumType(AlbumType albumType) {
      this.albumType = albumType;
      return this;
    }

    /**
     * Set the artists of the album to be built.
     *
     * @param artists {@link ArtistSimplified} objects.
     * @return A {@link Album.Builder}.
     */
    public Builder setArtists(ArtistSimplified... artists) {
      this.artists = artists;
      return this;
    }

    /**
     * Set the available markets of the album to be built.
     *
     * @param availableMarkets <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">
     *                         ISO 3166-1 alpha-2 country codes</a>.
     * @return A {@link Album.Builder}.
     */
    public Builder setAvailableMarkets(CountryCode... availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    /**
     * Set the copyrights of the album to be built.
     *
     * @param copyrights {@link Copyright} objects.
     * @return A {@link Album.Builder}.
     */
    public Builder setCopyrights(Copyright... copyrights) {
      this.copyrights = copyrights;
      return this;
    }

    /**
     * Set the external IDs of the album to be built.
     *
     * @param externalIds {@link ExternalId} object.
     * @return A {@link Album.Builder}.
     */
    public Builder setExternalIds(ExternalId externalIds) {
      this.externalIds = externalIds;
      return this;
    }

    /**
     * Set external URLs of the album to be built.
     *
     * @param externalUrls {@link ExternalUrl} object.
     * @return A {@link Album.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set the genres of the album to be built.
     *
     * @param genres Genre names.
     * @return A {@link Album.Builder}.
     */
    public Builder setGenres(String... genres) {
      this.genres = genres;
      return this;
    }

    /**
     * Set href of Spotify Web API endpoint of the album to be built.
     *
     * @param href Spotify Web API endpoint URL.
     * @return A {@link Album.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set album ID of the album to be built.
     *
     * @param id <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify album ID</a>.
     * @return A {@link Album.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the cover art in different sizes of the album to be built.
     *
     * @param images {@link Image} objects.
     * @return A {@link Album.Builder}.
     */
    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    /**
     * Set the label of the album to be built.
     *
     * @param label The album label.
     * @return A {@link Album.Builder}.
     */
    public Builder setLabel(String label) {
      this.label = label;
      return this;
    }

    /**
     * Set the name of the album to be built.
     *
     * @param name The album name.
     * @return A {@link Album.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the popularity of the album to be built.
     *
     * @param popularity The popularity of the album between 0 and 100.
     * @return A {@link Album.Builder}.
     */
    public Builder setPopularity(Integer popularity) {
      this.popularity = popularity;
      return this;
    }

    /**
     * Set the release date of the album to be built.
     *
     * @param releaseDate The release date of the album.
     * @return A {@link Album.Builder}.
     */
    public Builder setReleaseDate(String releaseDate) {
      this.releaseDate = releaseDate;
      return this;
    }

    /**
     * Set the release date precision of the album to be built.
     *
     * @param releaseDatePrecision The {@link ReleaseDatePrecision} of the album.
     * @return A {@link Album.Builder}.
     */
    public Builder setReleaseDatePrecision(ReleaseDatePrecision releaseDatePrecision) {
      this.releaseDatePrecision = releaseDatePrecision;
      return this;
    }

    /**
     * Set the tracks of the album to be built.
     *
     * @param tracks A {@link Paging} object containing {@link TrackSimplified} objects.
     * @return A {@link Album.Builder}.
     */
    public Builder setTracks(Paging<TrackSimplified> tracks) {
      this.tracks = tracks;
      return this;
    }

    /**
     * Set the type of the model object. In this case "album".
     *
     * @param type The {@link ModelObjectType}.
     * @return A {@link Album.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify album URI of the album to be built.
     *
     * @param uri <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">
     *            Spotify album URI</a>.
     * @return A {@link Album.Builder}.
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
   * JsonUtil class for building {@link Album} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Album> {
    public Album createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Album.Builder()
              .setAlbumType(
                      hasAndNotNull(jsonObject, "album_type")
                              ? AlbumType.keyOf(
                              jsonObject.get("album_type").getAsString().toLowerCase())
                              : null)
              .setArtists(
                      hasAndNotNull(jsonObject, "artists")
                              ? new ArtistSimplified.JsonUtil().createModelObjectArray(
                              jsonObject.getAsJsonArray("artists"))
                              : null)
              .setAvailableMarkets(
                      hasAndNotNull(jsonObject, "available_markets")
                              ? new Gson().fromJson(
                              jsonObject.getAsJsonArray("available_markets"), CountryCode[].class)
                              : null)
              .setCopyrights(
                      hasAndNotNull(jsonObject, "copyrights")
                              ? new Copyright.JsonUtil().createModelObjectArray(
                              jsonObject.getAsJsonArray("copyrights"))
                              : null)
              .setExternalIds(
                      hasAndNotNull(jsonObject, "external_ids")
                              ? new ExternalId.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("external_ids"))
                              : null)
              .setExternalUrls(
                      hasAndNotNull(jsonObject, "external_urls")
                              ? new ExternalUrl.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("external_urls"))
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
              .setLabel(
                      hasAndNotNull(jsonObject, "label")
                              ? jsonObject.get("label").getAsString()
                              : null)
              .setName(
                      hasAndNotNull(jsonObject, "name")
                              ? jsonObject.get("name").getAsString()
                              : null)
              .setPopularity(
                      hasAndNotNull(jsonObject, "popularity")
                              ? jsonObject.get("popularity").getAsInt()
                              : null)
              .setReleaseDate(
                      hasAndNotNull(jsonObject, "release_date")
                              ? jsonObject.get("release_date").getAsString()
                              : null)
              .setReleaseDatePrecision(
                      hasAndNotNull(jsonObject, "release_date_precision")
                              ? ReleaseDatePrecision.keyOf(
                              jsonObject.get("release_date_precision").getAsString().toLowerCase())
                              : null)
              .setTracks(
                      hasAndNotNull(jsonObject, "tracks")
                              ? new TrackSimplified.JsonUtil().createModelObjectPaging(
                              jsonObject.getAsJsonObject("tracks"))
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
