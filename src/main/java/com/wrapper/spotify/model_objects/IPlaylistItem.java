package com.wrapper.spotify.model_objects;

import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.Episode;
import com.wrapper.spotify.model_objects.specification.ExternalUrl;
import com.wrapper.spotify.model_objects.specification.Track;


/**
 * This interface represents objects returned by the API that can be played, saved in a playlist, etc,
 * currently {@link Episode} and {@link Track}.
 */
public interface IPlaylistItem extends IModelObject {

  /**
   * Get the type of the IPlaylistItem.
   * Possible values: {@code ModelObjectType.TRACK} or {@code ModelObjectType.EPISODE}
   *
   * @return The type of the IPlaylistItem.
   */
  ModelObjectType getType();

  /**
   * Get a URL to a 30 second preview (MP3 format) of the playlist item. {@code null} if not available.
   *
   * @return A URL to an audio preview.
   */
  String getPreviewUrl();

  /**
   * Get the duration of the playlist item in milliseconds.
   *
   * @return The length of the playlist item in milliseconds.
   */
  Integer getDurationMs();

  /**
   * Check whether the playlist item is explicit or not.
   *
   * @return Whether or not the playlist item has explicit content ({@code true} = yes it does; {@code false} = no it does not
   * <b>OR</b> unknown).
   */
  Boolean isExplicit();

  /**
   * Get the external URLs of the playlist item. <br>
   * Example: <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify-URL</a>
   *
   * @return An {@link ExternalUrl} object.
   */
  ExternalUrl getExternalUrls();

  /**
   * Get the full Spotify Web API endpoint URL of the playlist item.
   *
   * @return A link to the Web API endpoint providing full details of the playlist item.
   */
  String getHref();

  /**
   * Get the Spotify ID of the playlist item.
   *
   * @return A <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify ID</a>.
   */
  String getId();

  /**
   * Check whether the playlist item is playable in the given market.
   *
   * @return True if the playlist item is playable in the given market. Otherwise false.
   */
  Boolean isPlayable();

  /**
   * Get the name of the playlist item.
   *
   * @return The name of the playlist item.
   */
  String getName();
}
