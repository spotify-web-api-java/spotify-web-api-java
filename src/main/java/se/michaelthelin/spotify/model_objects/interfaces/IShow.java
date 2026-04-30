package se.michaelthelin.spotify.model_objects.interfaces;

import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.specification.Copyright;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.model_objects.specification.Show;
import se.michaelthelin.spotify.model_objects.specification.ShowSimplified;

/**
 * Represents the common properties of Spotify Show objects (full and simplified).
 *
 * <p>This interface provides a common base for {@link Show} and
 * {@link ShowSimplified} objects.
 */
public interface IShow {
  /**
   * Get all copyright texts of the show.
   *
   * @return An array of {@link Copyright} objects.
   */
  Copyright[] getCopyrights();

  /**
   * Get a description of the show.
   *
   * @return The description of the show.
   */
  String getDescription();

  /**
   * Check whether the show is explicit or not.
   *
   * @return Whether or not the show has explicit content ({@code true} = yes it does; {@code false} = no it does not
   *         <b>OR</b> unknown).
   */
  Boolean getExplicit();

  /**
   * Get the external URLs of the show. <br>
   * Example: <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify-URL</a>
   *
   * @return An {@link ExternalUrl} object.
   */
  ExternalUrl getExternalUrls();

  /**
   * Get the full Spotify Web API endpoint URL of the show.
   *
   * @return A link to the Web API endpoint providing full details of the show.
   */
  String getHref();

  /**
   * Get the Spotify ID of the show.
   *
   * @return A <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify show ID</a>.
   */
  String getId();

  /**
   * Get the cover art for the show in various sizes, widest first.
   *
   * @return An array of {@link Image} objects.
   */
  Image[] getImages();

  /**
   * Check whether the show is hosted outside of Spotify's CDN.
   *
   * @return True if the show is hosted outside of Spotify's CDN. Might be {@code null} in some cases.
   */
  Boolean getExternallyHosted();

  /**
   * Get a list of the languages used in the show, identified by their ISO 639 code.
   *
   * @return An array of <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country codes</a>.
   */
  String[] getLanguages();

  /**
   * Get the media type of the show.
   *
   * @return The media type of the show.
   */
  String getMediaType();

  /**
   * Get the name of the show.
   *
   * @return The name of the show.
   */
  String getName();

  /**
   * Get the model object type. In this case "show".
   *
   * @return A {@link ModelObjectType}.
   */
  ModelObjectType getType();

  /**
   * Get the Spotify URI of the show.
   *
   * @return <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify show URI</a>.
   */
  String getUri();
}
