package se.michaelthelin.spotify.model_objects.interfaces;

import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;

/**
 * Represents the common properties of Spotify Artist objects (full and simplified).
 *
 * <p>This interface provides a common base for {@link Artist} and
 * {@link ArtistSimplified} objects.
 */
public interface IArtist {
  /**
   * Get the external URLs of the artist. <br>
   * Example: <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify-URL</a>
   *
   * @return An {@link ExternalUrl} object.
   */
  ExternalUrl getExternalUrls();

  /**
   * Get the full Spotify Web API endpoint URL of the artist.
   *
   * @return A Spotify Web API endpoint URL.
   */
  String getHref();

  /**
   * Get the Spotify ID of the artist.
   *
   * @return A <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify artist ID</a>.
   */
  String getId();

  /**
   * Get the name of the artist.
   *
   * @return Artist name.
   */
  String getName();

  /**
   * Get the model object type. In this case "artist".
   *
   * @return A {@link ModelObjectType}.
   */
  ModelObjectType getType();

  /**
   * Get the Spotify URI of the artist.
   *
   * @return <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify artist URI</a>.
   */
  String getUri();
}


