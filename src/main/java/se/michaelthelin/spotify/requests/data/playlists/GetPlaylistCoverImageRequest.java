package se.michaelthelin.spotify.requests.data.playlists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get the current image associated with a specific playlist.
 */
@JsonDeserialize(builder = GetPlaylistCoverImageRequest.Builder.class)
public class GetPlaylistCoverImageRequest extends AbstractDataRequest<Image[]> {

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
    SpotifyWebApiException,
    ParseException {
    return new Image.JsonUtil().createModelObjectArray(getJson());
  }

  /**
   * Builder class for building a {@link GetPlaylistCoverImageRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Image[], Builder> {

    /**
     * Create a new {@link GetPlaylistCoverImageRequest.Builder}.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }


    /**
     * The playlist ID setter.
     *
     * @param playlist_id The Spotify ID for the playlist.
     * @return A {@link GetPlaylistCoverImageRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
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

    @Override
    protected Builder self() {
      return this;
    }
  }
}
