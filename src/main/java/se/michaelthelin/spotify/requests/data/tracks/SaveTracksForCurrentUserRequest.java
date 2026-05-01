package se.michaelthelin.spotify.requests.data.tracks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Save one or more tracks to the current user's 'Your Music' library.
 *
 * @deprecated Use {@link SaveLibraryItemsRequest} instead.
 */
@Deprecated
@JsonDeserialize(builder = SaveTracksForCurrentUserRequest.Builder.class)
public class SaveTracksForCurrentUserRequest extends AbstractDataRequest<String> {

  private SaveTracksForCurrentUserRequest(final Builder builder) {
    super(builder);
  }

  @Override
  public String execute() throws IOException, SpotifyWebApiException, ParseException {
    return putJson();
  }

  /**
   * Builder class for building a {@link SaveTracksForCurrentUserRequest}.
   *
   * @deprecated Use {@link SaveLibraryItemsRequest} instead.
   */
  @Deprecated
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    @Override
    public SaveTracksForCurrentUserRequest build() {
      setPath("/v1/me/tracks");
      return new SaveTracksForCurrentUserRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
