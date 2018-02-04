package com.wrapper.spotify.requests.data.follow;

import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get the current user’s followed artists.
 */
public class GetUsersFollowedArtistsRequest extends AbstractDataRequest {

  /**
   * The private {@link GetUsersFollowedArtistsRequest} constructor.
   *
   * @param builder A {@link GetUsersFollowedArtistsRequest.Builder}.
   */
  private GetUsersFollowedArtistsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a list of artists the user is following.
   *
   * @return An {@link Artist} paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public PagingCursorbased<Artist> execute() throws
          IOException,
          SpotifyWebApiException {
    return new Artist.JsonUtil().createModelObjectPagingCursorbased(getJson(), "artists");
  }

  /**
   * Builder class for building a {@link GetUsersFollowedArtistsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetUsersFollowedArtistsRequest.Builder} instance.
     * <p>
     * Getting details of the artists or users the current user follows requires authorization of the
     * {@code user-follow-read} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The type setter.
     *
     * @param type Required. The ID type: currently only {@code artist} is supported.
     * @return A {@link GetUsersFollowedArtistsRequest.Builder}.
     */
    public Builder type(final ModelObjectType type) {
      assert (type != null);
      assert (type.getType().equals("artist"));
      return setQueryParameter("type", type);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetUsersFollowedArtistsRequest.Builder}.
     */
    public Builder limit(final Integer limit) {
      assert (limit != null);
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The after value setter.
     *
     * @param after Optional. The last artist ID retrieved from the previous request.
     * @return A {@link GetUsersFollowedArtistsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder after(final String after) {
      assert (after != null);
      return setQueryParameter("after", after);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetUsersFollowedArtistsRequest}.
     */
    @Override
    public GetUsersFollowedArtistsRequest build() {
      setPath("/v1/me/following");
      return new GetUsersFollowedArtistsRequest(this);
    }
  }
}
