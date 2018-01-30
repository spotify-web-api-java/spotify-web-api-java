package com.wrapper.spotify.requests.data.follow;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Check to see if the current user is following one or more artists or other Spotify users.
 */
public class CheckCurrentUserFollowsArtistsOrUsersRequest extends AbstractDataRequest {

  /**
   * The private {@link CheckCurrentUserFollowsArtistsOrUsersRequest} constructor.
   *
   * @param builder A {@link CheckCurrentUserFollowsArtistsOrUsersRequest.Builder}.
   */
  private CheckCurrentUserFollowsArtistsOrUsersRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Check whether the user is following one or more users or artist or not.
   *
   * @return If the user is following more users or artists.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Boolean[] execute() throws
          IOException,
          SpotifyWebApiException {
    return new Gson().fromJson(new JsonParser().parse(getJson()).getAsJsonArray(), Boolean[].class);
  }

  /**
   * Builder class for building a {@link CheckCurrentUserFollowsArtistsOrUsersRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link CheckCurrentUserFollowsArtistsOrUsersRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The ID type setter.
     *
     * @param type Required. The ID type: either {@code artist} or {@code user}.
     * @return A {@link CheckCurrentUserFollowsArtistsOrUsersRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder type(final ModelObjectType type) {
      assert (type != null);
      assert (type.getType().equals("artist") || type.getType().equals("user"));
      return setQueryParameter("type", type);
    }

    /**
     * The artist or user IDs setter.
     *
     * @param ids Required. A comma-separated list of the artist or the user Spotify IDs to check. A maximum of 50 IDs
     *            can be sent in one request.
     * @return A {@link CheckCurrentUserFollowsArtistsOrUsersRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link CheckCurrentUserFollowsArtistsOrUsersRequest}.
     */
    @Override
    public CheckCurrentUserFollowsArtistsOrUsersRequest build() {
      setPath("/v1/me/following/contains");
      return new CheckCurrentUserFollowsArtistsOrUsersRequest(this);
    }
  }
}
