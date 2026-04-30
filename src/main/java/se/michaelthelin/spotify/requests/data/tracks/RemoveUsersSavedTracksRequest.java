package se.michaelthelin.spotify.requests.data.tracks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Remove one or more tracks from the current user's 'Your Music' library.
 *
 * @deprecated Use {@link RemoveLibraryItemsRequest} instead.
 */
@Deprecated
@JsonDeserialize(builder = RemoveUsersSavedTracksRequest.Builder.class)
public class RemoveUsersSavedTracksRequest extends AbstractDataRequest<String> {

  private RemoveUsersSavedTracksRequest(final Builder builder) {
    super(builder);
  }

  @Override
  public String execute() throws IOException, SpotifyWebApiException, ParseException {
    return deleteJson();
  }

  /**
   * Builder class for building a {@link RemoveUsersSavedTracksRequest}.
   *
   * @deprecated Use {@link RemoveLibraryItemsRequest} instead.
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
    public RemoveUsersSavedTracksRequest build() {
      setPath("/v1/me/tracks");
      return new RemoveUsersSavedTracksRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
