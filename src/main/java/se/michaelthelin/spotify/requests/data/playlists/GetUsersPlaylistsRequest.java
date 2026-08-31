package se.michaelthelin.spotify.requests.data.playlists;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.AbstractDataPagingRequest;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a list of the playlists owned or followed by a Spotify user.
 *
 * @deprecated This endpoint is deprecated.
 */
@Deprecated
@JsonDeserialize(builder = GetUsersPlaylistsRequest.Builder.class)
public class GetUsersPlaylistsRequest extends AbstractDataRequest<Paging<PlaylistSimplified>> {

  /**
   * The private {@link GetUsersPlaylistsRequest} constructor.
   *
   * @param builder A {@link GetUsersPlaylistsRequest.Builder}.
   */
  private GetUsersPlaylistsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a paging of {@link PlaylistSimplified} objects for a user.
   *
   * @return A {@link PlaylistSimplified} paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Paging<PlaylistSimplified> execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetUsersPlaylistsRequest}.
   *
   * @deprecated This endpoint is deprecated.
   */
  @Deprecated
  public static final class Builder extends AbstractDataPagingRequest.Builder<PlaylistSimplified, Builder> {

    /**
     * Create a new {@link GetUsersPlaylistsRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The user ID path parameter setter.
     *
     * @param user_id Required. The user's Spotify user ID.
     * @return A {@link GetUsersPlaylistsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.isEmpty());
      return setPathParameter("user_id", user_id);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of playlists to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetUsersPlaylistsRequest.Builder}.
     */
    @Override
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first playlist to return. Default: 0.
     * @return A {@link GetUsersPlaylistsRequest.Builder}.
     */
    @Override
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetUsersPlaylistsRequest}.
     */
    @Override
    public GetUsersPlaylistsRequest build() {
      setPath("/v1/users/{user_id}/playlists");
      return new GetUsersPlaylistsRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
