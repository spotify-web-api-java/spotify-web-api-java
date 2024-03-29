package se.michaelthelin.spotify.requests.data.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.Device;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get information about a user’s available devices.
 */
@JsonDeserialize(builder = GetUsersAvailableDevicesRequest.Builder.class)
public class GetUsersAvailableDevicesRequest extends AbstractDataRequest<Device[]> {

  /**
   * The private {@link GetUsersAvailableDevicesRequest} constructor.
   *
   * @param builder A {@link GetUsersAvailableDevicesRequest.Builder}.
   */
  private GetUsersAvailableDevicesRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get an user's available devices.
   *
   * @return An user's available devices.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Device[] execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Device.JsonUtil().createModelObjectArray(getJson(), "devices");
  }

  /**
   * Builder class for building a {@link GetUsersAvailableDevicesRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Device[], Builder> {

    /**
     * Create a new {@link GetUsersAvailableDevicesRequest.Builder}.
     * <p>
     * Your access token must have the {@code user-read-playback-state} scope authorized in order to read information.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetUsersAvailableDevicesRequest}.
     */
    @Override
    public GetUsersAvailableDevicesRequest build() {
      setPath("/v1/me/player/devices");
      return new GetUsersAvailableDevicesRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
