package se.michaelthelin.spotify.model_objects.special;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.enums.ReleaseDatePrecision;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.requests.data.search.interfaces.ISearchModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#album-object-simplified">
 * simplified Album objects</a> by building instances from this class.
 * <p>
 * This class exists because it includes the property {@code totalTracks}, which is not documented in the official
 * specification, although the albums object as returned by the searches API includes it.
 */
@JsonDeserialize(builder = AlbumSimplifiedSpecial.Builder.class)
public class AlbumSimplifiedSpecial extends AbstractModelObject implements ISearchModelObject {
  /** The type of the album. */
  private final AlbumType albumType;
  /** The artists who performed the album. */
  private final ArtistSimplified[] artists;
  /** The markets in which the album is available. */
  private final CountryCode[] availableMarkets;
  /** Known external URLs for this album. */
  private final ExternalUrl externalUrls;
  /** A link to the Web API endpoint providing full details of the album. */
  private final String href;
  /** The Spotify ID for the album. */
  private final String id;
  /** The cover art for the album in various sizes. */
  private final Image[] images;
  /** The name of the album. */
  private final String name;
  /** The date the album was first released. */
  private final String releaseDate;
  /** The precision with which release_date value is known. */
  private final ReleaseDatePrecision releaseDatePrecision;
  /** The number of tracks in the album. */
  private final Integer totalTracks;
  /** The object type: "album". */
  private final ModelObjectType type;
  /** The Spotify URI for the album. */
  private final String uri;

  private AlbumSimplifiedSpecial(final Builder builder) {
    super(builder);

    this.albumType = builder.albumType;
    this.artists = builder.artists;
    this.availableMarkets = builder.availableMarkets;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.name = builder.name;
    this.releaseDate = builder.releaseDate;
    this.releaseDatePrecision = builder.releaseDatePrecision;
    this.totalTracks = builder.totalTracks;
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
   * Get the external URLs of the album. <br>
   * Example: <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify-URL</a>
   *
   * @return An {@link ExternalUrl} object.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
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
   * @return A Spotify album ID.
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
   * Get the name of the album.
   *
   * @return Album name.
   */
  public String getName() {
    return name;
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
   * Get the total tracks of the album.
   *
   * @return The total tracks of the album.
   */
  public Integer getTotalTracks() {
    return totalTracks;
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
   * @return <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify album URI</a>.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return "AlbumSimplifiedSpecial(albumType=" + albumType + ", artists=" + Arrays.toString(artists)
        + ", availableMarkets=" + Arrays.toString(availableMarkets) + ", externalUrls=" + externalUrls + ", href="
        + href + ", id=" + id + ", images=" + Arrays.toString(images) + ", name=" + name + ", releaseDate="
        + releaseDate + ", releaseDatePrecision=" + releaseDatePrecision + ", totalTracks=" + totalTracks + ", type="
        + type + ", uri=" + uri + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link AlbumSimplifiedSpecial} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private AlbumType albumType;
    private ArtistSimplified[] artists;
    private CountryCode[] availableMarkets;
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private Image[] images;
    private String name;
    private String releaseDate;
    private ReleaseDatePrecision releaseDatePrecision;
    private Integer totalTracks;
    private ModelObjectType type;
    private String uri;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set the type of the album to be built.
     *
     * @param albumType The {@link AlbumType}.
     * @return A {@link AlbumSimplifiedSpecial.Builder}.
     */
    public Builder setAlbumType(AlbumType albumType) {
      this.albumType = albumType;
      return this;

    }

    /**
     * Set the artists of the album to be built.
     *
     * @param artists {@link ArtistSimplified} objects.
     * @return A {@link AlbumSimplifiedSpecial.Builder}.
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
     * @return A {@link AlbumSimplifiedSpecial.Builder}.
     */
    public Builder setAvailableMarkets(CountryCode... availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    /**
     * Set external URLs of the album to be built.
     *
     * @param externalUrls {@link ExternalUrl} object.
     * @return A {@link AlbumSimplifiedSpecial.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set href of Spotify Web API endpoint of the album to be built.
     *
     * @param href Spotify Web API endpoint URL.
     * @return A {@link AlbumSimplifiedSpecial.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set album ID of the album to be built.
     *
     * @param id <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify album ID</a>.
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
     * @return A {@link AlbumSimplifiedSpecial.Builder}.
     */
    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    /**
     * Set the name of the album to be built.
     *
     * @param name The album name.
     * @return A {@link AlbumSimplifiedSpecial.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the release date of the album to be built.
     *
     * @param releaseDate The release date of the album.
     * @return A {@link AlbumSimplifiedSpecial.Builder}.
     */
    public Builder setReleaseDate(String releaseDate) {
      this.releaseDate = releaseDate;
      return this;
    }

    /**
     * Set the release date precision of the album to be built.
     *
     * @param releaseDatePrecision The {@link ReleaseDatePrecision} of the album.
     * @return A {@link AlbumSimplifiedSpecial.Builder}.
     */
    public Builder setReleaseDatePrecision(ReleaseDatePrecision releaseDatePrecision) {
      this.releaseDatePrecision = releaseDatePrecision;
      return this;
    }

    /**
     * Set the number of total tracks of the album to be built.
     *
     * @param totalTracks The number of total tracks of the album.
     * @return A {@link AlbumSimplifiedSpecial.Builder}.
     */
    public Builder setTotalTracks(Integer totalTracks) {
      this.totalTracks = totalTracks;
      return this;
    }

    /**
     * Set the type of the model object. In this case "album".
     *
     * @param type The {@link ModelObjectType}.
     * @return A {@link AlbumSimplifiedSpecial.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify album URI of the album to be built.
     *
     * @param uri <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">
     *            Spotify album URI</a>.
     * @return A {@link AlbumSimplifiedSpecial.Builder}.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public AlbumSimplifiedSpecial build() {
      return new AlbumSimplifiedSpecial(this);
    }
  }

  /**
   * JsonUtil class for building {@link AlbumSimplifiedSpecial} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AlbumSimplifiedSpecial> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public AlbumSimplifiedSpecial createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AlbumSimplifiedSpecial.Builder()
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
            jsonObject.get("available_markets"), CountryCode[].class)
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
        .setName(
          hasAndNotNull(jsonObject, "name")
            ? jsonObject.get("name").getAsString()
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
        .setTotalTracks(
          hasAndNotNull(jsonObject, "total_tracks")
            ? jsonObject.get("total_tracks").getAsInt()
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
    AlbumSimplifiedSpecial that = (AlbumSimplifiedSpecial) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
      Objects.equals(releaseDate, that.releaseDate) && Objects.equals(uri, that.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, releaseDate, uri);
  }
}
