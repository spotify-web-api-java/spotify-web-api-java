package se.michaelthelin.spotify.model_objects.interfaces;

import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.enums.ReleaseDatePrecision;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.model_objects.special.AlbumSimplifiedSpecial;

/**
 * Represents the common properties of Spotify Album objects (full and simplified).
 *
 * <p>This interface provides a common base for {@link Album}, {@link AlbumSimplified},
 * and {@link AlbumSimplifiedSpecial} objects.
 */
public interface IAlbum {
  /**
   * Get the type of the album.
   *
   * @return The {@link AlbumType}.
   */
  AlbumType getAlbumType();

  /**
   * Get the artists of the album.
   *
   * @return An array of {@link ArtistSimplified} objects.
   */
  ArtistSimplified[] getArtists();

  /**
   * Get the external URLs of the album. <br>
   * Example: <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify-URL</a>
   *
   * @return An {@link ExternalUrl} object.
   */
  ExternalUrl getExternalUrls();

  /**
   * Get the full Spotify Web API endpoint URL of the album.
   *
   * @return A Spotify Web API endpoint URL.
   */
  String getHref();

  /**
   * Get the Spotify ID of the album.
   *
   * @return A <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify album ID</a>.
   */
  String getId();

  /**
   * Get the album cover art of the album in different sizes.
   *
   * @return An array of {@link Image} objects.
   */
  Image[] getImages();

  /**
   * Get the name of the album.
   *
   * @return Album name.
   */
  String getName();

  /**
   * Get the release date of the album with the highest precision available.
   *
   * @return The release date of the album.
   */
  String getReleaseDate();

  /**
   * Get the precision of the albums release date. This is needed when the exact release day of an album is not known.
   *
   * @return The precision of the albums release date.
   */
  ReleaseDatePrecision getReleaseDatePrecision();

  /**
   * Get the model object type. In this case "album".
   *
   * @return A {@link ModelObjectType}.
   */
  ModelObjectType getType();

  /**
   * Get the Spotify URI of the album.
   *
   * @return <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify album URI</a>.
   */
  String getUri();
}


