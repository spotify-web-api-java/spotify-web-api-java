package se.michaelthelin.spotify.requests.data.albums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Save one or more albums to the current user's 'Your Music' library.
 *
 * @deprecated Use {@link SaveLibraryItemsRequest} instead.
 */
@Deprecated
@JsonDeserialize(builder = SaveAlbumsForCurrentUserRequest.Builder.class)
public class SaveAlbumsForCurrentUserRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link SaveAlbumsForCurrentUserRequest} constructor.
   *
   * @param builder A {@link SaveAlbumsForCurrentUserRequest.Builder}.
   */
  private SaveAlbumsForCurrentUserRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Save albums to the current user's library.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @Override
  public String execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return putJson();
  }

  /**
   * Builder class for building a {@link SaveAlbumsForCurrentUserRequest}.
   *
   * @deprecated Use {@link SaveLibraryItemsRequest} instead.
   */
  @Deprecated
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link SaveAlbumsForCurrentUserRequest.Builder} instance.
     * <p>
     * The {@code user-library-modify} scope must have been authorized by the user.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The album IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the albums. Maximum: 50 IDs.
     * @return A {@link SaveAlbumsForCurrentUserRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link SaveAlbumsForCurrentUserRequest}.
     */
    @Override
    public SaveAlbumsForCurrentUserRequest build() {
      setPath("/v1/me/albums");
      return new SaveAlbumsForCurrentUserRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
