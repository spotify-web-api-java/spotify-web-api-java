package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.enums.ReleaseDatePrecision;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#album-object-full">
 * Album objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = Album.Builder.class)
public class Album extends AbstractModelObject {
  /** The type of the album. */
  private final AlbumType albumType;
  /** The artists who performed the album. */
  private final ArtistSimplified[] artists;
  /** The copyright statements of the album. */
  private final Copyright[] copyrights;
  /** Known external URLs for this album. */
  private final ExternalUrl externalUrls;
  /** A list of the genres the album is associated with. */
  private final String[] genres;
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
  /** The tracks of the album. */
  private final Paging<TrackSimplified> tracks;
  /** The object type: "album". */
  private final ModelObjectType type;
  /** The Spotify URI for the album. */
  private final String uri;

  private Album(final Builder builder) {
    super(builder);

    this.albumType = builder.albumType;
    this.artists = builder.artists;
    this.copyrights = builder.copyrights;
    this.externalUrls = builder.externalUrls;
    this.genres = builder.genres;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.name = builder.name;
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
   * Get all copyright texts of the album.
   *
   * @return An array of {@link Copyright} objects.
   */
  public Copyright[] getCopyrights() {
    return copyrights;
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
   * @return A <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify album ID</a>.
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
   * @return <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify album URI</a>.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return "Album(artists=" + Arrays.toString(artists) + ", name=" + name + ", albumType=" + albumType
        + ", copyrights=" + Arrays.toString(copyrights)
        + ", externalUrls=" + externalUrls + ", genres=" + Arrays.toString(genres)
        + ", href=" + href + ", id=" + id + ", images=" + Arrays.toString(images)
        + ", releaseDate=" + releaseDate + ", releaseDatePrecision=" + releaseDatePrecision + ", tracks="
        + tracks + ", type=" + type + ", uri=" + uri + ")";
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
    private Copyright[] copyrights;
    private ExternalUrl externalUrls;
    private String[] genres;
    private String href;
    private String id;
    private Image[] images;
    private String name;
    private String releaseDate;
    private ReleaseDatePrecision releaseDatePrecision;
    private Paging<TrackSimplified> tracks;
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
     * @return A {@link Album.Builder}.
     */
    public Builder setImages(Image... images) {
      this.images = images;
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
     * @param uri <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">
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

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

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
        .setCopyrights(
          hasAndNotNull(jsonObject, "copyrights")
            ? new Copyright.JsonUtil().createModelObjectArray(
            jsonObject.getAsJsonArray("copyrights"))
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Album album = (Album) o;
    return Objects.equals(id, album.id) && Objects.equals(name, album.name) &&
      Objects.equals(releaseDate, album.releaseDate) && Objects.equals(uri, album.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, releaseDate, uri);
  }
}
