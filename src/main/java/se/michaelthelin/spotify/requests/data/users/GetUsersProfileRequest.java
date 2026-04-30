package se.michaelthelin.spotify.requests.data.users;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get public profile information about a Spotify user.
 *
 * @deprecated This endpoint is deprecated.
 */
@Deprecated
@JsonDeserialize(builder = GetUsersProfileRequest.Builder.class)
public class GetUsersProfileRequest extends AbstractDataRequest<User> {

  /**
   * The private {@link GetUsersProfileRequest} constructor.
   *
   * @param builder A {@link GetUsersProfileRequest.Builder}.
   */
  private GetUsersProfileRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the user's profile.
   *
   * @return A {@link User} object.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public User execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new User.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetUsersProfileRequest}.
   *
   * @deprecated This endpoint is deprecated.
   */
  @Deprecated
  public static final class Builder extends AbstractDataRequest.Builder<User, Builder> {

    /**
     * Create a new {@link GetUsersProfileRequest.Builder} instance.
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
     * @return A {@link GetUsersProfileRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.isEmpty());
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

    @Override
    protected Builder self() {
      return this;
    }
  }
}
