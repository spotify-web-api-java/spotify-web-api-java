package se.michaelthelin.spotify.model_objects.interfaces;

import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.User;

/**
 * Represents the common properties of Spotify Playlist objects (full and simplified).
 *
 * <p>This interface provides a common base for {@link Playlist} and
 * {@link PlaylistSimplified} objects.
 *
 * @param <T> The concrete type of the items/tracks metadata object. {@link Playlist} uses
 *            {@link se.michaelthelin.spotify.model_objects.specification.Paging} and
 *            {@link PlaylistSimplified} uses
 *            {@link se.michaelthelin.spotify.model_objects.miscellaneous.PlaylistTracksInformation}.
 */
public interface IPlaylist<T extends IHasTotal> {
  /**
   * Check whether the owner allows other users to modify the playlist.
   *
   * @return {@code true} if other users are allowed to modify the playlist, {@code false} otherwise.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/playlists">
   *      Spotify: Working With Playlists</a>
   */
  Boolean getIsCollaborative();

  /**
   * Get the description of the playlist.
   *
   * @return The playlist description. Only returned for modified, verified playlists, otherwise {@code null}.
   */
  String getDescription();

  /**
   * Get the external URLs of the playlist. <br>
   * Example: Spotify-URL.
   *
   * @return Known external URLs for this playlist.
   */
  ExternalUrl getExternalUrls();

  /**
   * Get the full Spotify API endpoint url of the playlist.
   *
   * @return A link to the Web API endpoint providing full details of the playlist.
   */
  String getHref();

  /**
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify ID</a>
   * of a playlist.
   *
   * @return The Spotify ID for the playlist.
   */
  String getId();

  /**
   * Images for the playlist. The array may be empty or contain up to three images. The images are returned by size in
   * descending order. <br>
   * <b>Note:</b> If returned, the source URL for the image is temporary and will expire in less than a day.
   *
   * @return An array of images in different sizes.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/playlists">
   *      Spotify: Working With Playlists</a>
   */
  Image[] getImages();

  /**
   * Get the name of a playlist.
   *
   * @return Playlist name.
   */
  String getName();

  /**
   * Get the owners user object of a playlist.
   *
   * @return A user object.
   */
  User getOwner();

  /**
   * Check whether a playlist is available in public or is private.
   *
   * @return {@code true} the playlist is public, {@code false} the playlist is private, {@code null}
   *         the playlist status is not relevant.
   * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/playlists">
   *      Spotify: Working With Playlists</a>
   */
  Boolean getIsPublicAccess();

  /**
   * Get information about the items in the playlist. The concrete type depends on the specific playlist object type.
   *
   * @return Item information containing total count.
   */
  T getItems();

  /**
   * Get the snapshot ID, the version identifier for the current playlist. Can be supplied in other requests to target
   * a specific playlist version.
   *
   * @return The version identifier for the current playlist.
   * @see se.michaelthelin.spotify.requests.data.playlists.RemoveItemsFromPlaylistRequest
   */
  String getSnapshotId();

  /**
   * Get the model object type. In this case "playlist".
   *
   * @return The object type: "playlist"
   */
  ModelObjectType getType();

  /**
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URI</a>
   * of a playlist.
   *
   * @return Spotify playlist URI.
   */
  String getUri();
}


