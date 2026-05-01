package se.michaelthelin.spotify.model_objects.interfaces;

import se.michaelthelin.spotify.model_objects.IPlaylistItem;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;

/**
 * Represents the common properties of Spotify Track objects (full and simplified).
 *
 * <p>This interface extends {@link IPlaylistItem} and provides a common base for
 * {@link Track} and
 * {@link TrackSimplified}
 * objects.
 */
public interface ITrack extends IPlaylistItem {
  /**
   * Get the artists who performed the track.
   *
   * @return The artists who performed the track. Each artist object includes a link in {@code href} to more detailed
   *         information about the artist.
   */
  ArtistSimplified[] getArtists();

  /**
   * Get the disc number of the track in its album.
   *
   * @return The disc number (usually 1 unless the album consists of more than one disc).
   */
  Integer getDiscNumber();

  /**
   * Check whether the track is explicit or not.
   *
   * @return Whether or not the track has explicit lyrics ({@code true} = yes it does; {@code false} = no it does not
   *         <b>OR</b> unknown).
   */
  Boolean getExplicit();

  /**
   * Check whether the track is playable in the market, which may has been specified somewhere before requesting it.
   * Part of the response when <a href="https://developer.spotify.com/documentation/web-api/concepts/track-relinking">
   * Track Relinking</a> is applied.
   *
   * @return If {@code true}, the track is playable in the given market. Otherwise {@code false}.
   */
  Boolean getIsPlayable();

  /**
   * Get a link to a 30 second preview (MP3 format) of the track. {@code null} if not available.
   *
   * @return A link to a 30 second preview (MP3 format) of the track. {@code null} if not available.
   */
  String getPreviewUrl();

  /**
   * Get the track number of the track. If an album has several discs, the track number is the number on the specified
   * disc.
   *
   * @return The number of the track.
   */
  Integer getTrackNumber();
}



