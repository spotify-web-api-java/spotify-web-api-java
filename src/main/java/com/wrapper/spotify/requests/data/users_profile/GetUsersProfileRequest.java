package com.wrapper.spotify.requests.data.users_profile;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get public profile information about a Spotify user.
 */
public class GetUsersProfileRequest extends AbstractDataRequest {

  /**
   * The private {@link GetUsersProfileRequest} constructor.
   *
   * @param builder A {@link GetUsersProfileRequest.Builder}.
   */
  private GetUsersProfileRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the profile of a current user.
   *
   * @return A {@link User}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public User execute() throws
          IOException,
          SpotifyWebApiException {
    return new User.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetUsersProfileRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetUsersProfileRequest.Builder}.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The user ID setter.
     *
     * @param user_id The user's Spotify user ID.
     * @return A {@link GetUsersProfileRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetUsersProfileRequest}.
     */
    @Override
    public GetUsersProfileRequest build() {
      setPath("/v1/users/{user_id}");
      return new GetUsersProfileRequest(this);
    }
  }
}
