package com.wrapper.spotify.requests.data.follow;

import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Remove the current user as a follower of one or more artists or other Spotify users.
 */
public class UnfollowArtistsOrUsersRequest extends AbstractDataRequest {

  /**
   * The private {@link UnfollowArtistsOrUsersRequest} constructor.
   *
   * @param builder A {@link UnfollowArtistsOrUsersRequest.Builder}.
   */
  private UnfollowArtistsOrUsersRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Unfollow an artist or user.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return deleteJson();
  }

  /**
   * Builder class for building an {@link UnfollowArtistsOrUsersRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link UnfollowArtistsOrUsersRequest.Builder} instance.
     * <p>
     * Modifying the list of artists or users the current user follows requires authorization of the
     * {@code user-follow-modify} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The ID type setter.
     *
     * @param type Required. The ID type: either {@code artist} or {@code user}.
     * @return An {@link UnfollowArtistsOrUsersRequest.Builder}.
     */
    public Builder type(final ModelObjectType type) {
      assert (type != null);
      assert (type.getType().equals("artist") || type.getType().equals("user"));
      return setQueryParameter("type", type);
    }

    /**
     * The artist or user IDs setter.
     *
     * @param ids Optional. A comma-separated list of the artist or the user Spotify IDs. A maximum of 50 IDs can be
     *            sent in one request.
     * @return An {@link UnfollowArtistsOrUsersRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link UnfollowArtistsOrUsersRequest}.
     */
    @Override
    public UnfollowArtistsOrUsersRequest build() {
      setPath("/v1/me/following");
      return new UnfollowArtistsOrUsersRequest(this);
    }
  }
}
