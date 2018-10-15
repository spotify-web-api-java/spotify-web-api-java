package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Image;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get the current image associated with a specific playlist.
 */
public class GetPlaylistCoverImageRequest extends AbstractDataRequest {

  /**
   * The private {@link GetPlaylistCoverImageRequest} constructor.
   *
   * @param builder A {@link GetPlaylistCoverImageRequest.Builder}.
   */
  private GetPlaylistCoverImageRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the cover image of a playlist.
   *
   * @return Multiple {@link Image} objects.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Image[] execute() throws
          IOException,
          SpotifyWebApiException {
    return new Image.JsonUtil().createModelObjectArray(getJson());
  }

  /**
   * Builder class for building a {@link GetPlaylistCoverImageRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetPlaylistCoverImageRequest.Builder}.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The user ID setter.
     *
     * @deprecated Playlist IDs are unique for themselves. This parameter is thus no longer used.
     * (https://developer.spotify.com/community/news/2018/06/12/changes-to-playlist-uris/)
     * @param user_id The user's Spotify user ID.
     * @return A {@link GetPlaylistCoverImageRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    @Deprecated
    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    /**
     * The playlist ID setter.
     *
     * @param playlist_id The Spotify ID for the playlist.
     * @return A {@link GetPlaylistCoverImageRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetPlaylistCoverImageRequest}.
     */
    @Override
    public GetPlaylistCoverImageRequest build() {
      setPath("/v1/playlists/{playlist_id}/images");
      return new GetPlaylistCoverImageRequest(this);
    }
  }
}
