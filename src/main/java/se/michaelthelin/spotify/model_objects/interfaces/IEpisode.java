package se.michaelthelin.spotify.model_objects.interfaces;

import se.michaelthelin.spotify.enums.ReleaseDatePrecision;
import se.michaelthelin.spotify.model_objects.IPlaylistItem;
import se.michaelthelin.spotify.model_objects.specification.Episode;
import se.michaelthelin.spotify.model_objects.specification.EpisodeSimplified;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.model_objects.specification.ResumePoint;

/**
 * Represents the common properties of Spotify Episode objects (full and simplified).
 *
 * <p>This interface extends {@link IPlaylistItem} and provides a common base for
 * {@link Episode} and
 * {@link EpisodeSimplified}
 * objects.
 */
public interface IEpisode extends IPlaylistItem {
  /**
   * Get a link to a 30 second preview (MP3 format) of the episode. {@code null} if not available.
   *
   * @return A link to a 30 second preview (MP3 format) of the episode. {@code null} if not available.
   */
  String getAudioPreviewUrl();

  /**
   * Get a description of the episode.
   *
   * @return The description of the episode.
   */
  String getDescription();

  /**
   * Check whether the episode is explicit or not.
   *
   * @return Whether or not the episode has explicit content ({@code true} = yes it does; {@code false} = no it does not
   *         <b>OR</b> unknown).
   */
  Boolean getExplicit();

  /**
   * Get the cover art for the episode in various sizes, widest first.
   *
   * @return An array of {@link Image} objects.
   */
  Image[] getImages();

  /**
   * Check whether the episode is hosted outside of Spotify's CDN.
   *
   * @return True if the episode is hosted outside of Spotify's CDN.
   */
  Boolean getExternallyHosted();

  /**
   * Check whether the episode is playable in the given market.
   *
   * @return True if the episode is playable in the given market. Otherwise false.
   */
  Boolean getPlayable();

  /**
   * Get a list of the languages used in the episode, identified by their ISO 639 code.
   *
   * @return An array of <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country codes</a>.
   */
  String[] getLanguages();

  /**
   * Get the date the episode was first released, for example "1981-12-15". Depending on the precision, it might be shown as "1981" or "1981-12".
   *
   * @return The release date of the episode.
   */
  String getReleaseDate();

  /**
   * Get the precision with which the release date is known.
   *
   * @return A {@link ReleaseDatePrecision} object.
   */
  ReleaseDatePrecision getReleaseDatePrecision();

  /**
   * Get the user's most recent position in the episode. Set if the supplied access token is a user token and has the scope {@code user-read-playback-position}.
   *
   * @return A {@link ResumePoint} object.
   */
  ResumePoint getResumePoint();
}


