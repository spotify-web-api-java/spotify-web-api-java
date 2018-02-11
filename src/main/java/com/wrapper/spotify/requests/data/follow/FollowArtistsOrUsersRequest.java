package com.wrapper.spotify.requests.data.follow;

import com.google.gson.JsonArray;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import org.apache.http.entity.ContentType;

import java.io.IOException;

/**
 * Add the current user as a follower of one or more artists or other Spotify users.
 */
public class FollowArtistsOrUsersRequest extends AbstractDataRequest {

  /**
   * The private {@link FollowArtistsOrUsersRequest} constructor.
   *
   * @param builder A {@link FollowArtistsOrUsersRequest.Builder}.
   */
  private FollowArtistsOrUsersRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Follow an artist or user.
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
   * Builder class for building a {@link FollowArtistsOrUsersRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link FollowArtistsOrUsersRequest}.
     * <p>
     * Modifying the list of artists or users the current user follows requires authorization of the
     * {@code user-follow-modify scope}.
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
     * @param type Required. The ID type: either artist or user.
     * @return A {@link FollowArtistsOrUsersRequest.Builder}.
     */
    public Builder type(final ModelObjectType type) {
      assert (type != null);
      assert (type.getType().equals("artist") || type.getType().equals("user"));
      return setQueryParameter("type", type);
    }

    /**
     * The user or artist IDs setter.
     *
     * @param ids Optional. A comma-separated list of the artist or the user Spotify IDs. A maximum of 50 IDs can be
     *            sent in one request.
     * @return A {@link FollowArtistsOrUsersRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The user or artist IDs setter.
     *
     * @param ids Optional. A json array of the artist or the user Spotify IDs. A maximum of 50 IDs can be
     *            sent in one request.
     * @return A {@link FollowArtistsOrUsersRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder ids(final JsonArray ids) {
      assert (ids != null);
      assert (!ids.isJsonNull());
      assert (ids.size() <= 50);
      return setBodyParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link FollowArtistsOrUsersRequest}.
     */
    @Override
    public FollowArtistsOrUsersRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/following");
      return new FollowArtistsOrUsersRequest(this);
    }
  }
}
