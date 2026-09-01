package se.michaelthelin.spotify.model_objects.special;

import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.enums.ReleaseDatePrecision;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.interfaces.IAlbum;
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
public class AlbumSimplifiedSpecial extends AbstractModelObject implements ISearchModelObject, IAlbum {
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

    public Builder() {
      super();
    }

    public Builder setAlbumType(AlbumType albumType) {
      this.albumType = albumType;
      return this;

    }

    public Builder setArtists(ArtistSimplified... artists) {
      this.artists = artists;
      return this;
    }

    public Builder setAvailableMarkets(CountryCode... availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setImages(Image... images) {
      this.images = images;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setReleaseDate(String releaseDate) {
      this.releaseDate = releaseDate;
      return this;
    }

    public Builder setReleaseDatePrecision(ReleaseDatePrecision releaseDatePrecision) {
      this.releaseDatePrecision = releaseDatePrecision;
      return this;
    }

    public Builder setTotalTracks(Integer totalTracks) {
      this.totalTracks = totalTracks;
      return this;
    }

    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

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

    public JsonUtil() {
      super();
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
