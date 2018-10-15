package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

/**
 * Replace the image used to represent a specific playlist.
 */
public class UploadCustomPlaylistCoverImageRequest extends AbstractDataRequest {

  /**
   * The private {@link UploadCustomPlaylistCoverImageRequest} constructor.
   *
   * @param builder A {@link UploadCustomPlaylistCoverImageRequest.Builder}.
   */
  private UploadCustomPlaylistCoverImageRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Upload a new playlist image.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return putJson();
  }

  /**
   * Builder class for building an {@link UploadCustomPlaylistCoverImageRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link UploadCustomPlaylistCoverImageRequest.Builder}.
     * <p>
     * This access token must be tied to the user who owns the playlist, and must have the scope
     * {@code ugc-image-upload} granted. In addition, the token must also contain {@code playlist-modify-public}
     * and/or {@code playlist-modify-private}, depending the public status of the playlist you want to update.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
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
     * @return A {@link UploadCustomPlaylistCoverImageRequest.Builder}.
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
     * @return A {@link UploadCustomPlaylistCoverImageRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The image data setter.
     *
     * @param image_data Base64 encoded JPEG image data, maximum payload size is 256 KB.
     * @return A {@link UploadCustomPlaylistCoverImageRequest.Builder}.
     */
    public Builder image_data(final String image_data) {
      assert (image_data != null);
      assert (!image_data.equals(""));
      assert (image_data.getBytes().length <= 256000);
      return setBody(new StringEntity(image_data, ContentType.IMAGE_JPEG));
    }

    /**
     * The request build method.
     *
     * @return A custom {@link UploadCustomPlaylistCoverImageRequest}.
     */
    @Override
    public UploadCustomPlaylistCoverImageRequest build() {
      setContentType(ContentType.IMAGE_JPEG);
      setPath("/v1/playlists/{playlist_id}/images");
      return new UploadCustomPlaylistCoverImageRequest(this);
    }
  }
}
