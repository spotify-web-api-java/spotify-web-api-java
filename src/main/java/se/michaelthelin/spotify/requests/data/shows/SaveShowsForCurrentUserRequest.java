package se.michaelthelin.spotify.requests.data.shows;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Save one or more shows to the current user's library.
 *
 * @deprecated Use {@link SaveLibraryItemsRequest} instead.
 */
@Deprecated
@JsonDeserialize(builder = SaveShowsForCurrentUserRequest.Builder.class)
public class SaveShowsForCurrentUserRequest extends AbstractDataRequest<String> {

  private SaveShowsForCurrentUserRequest(final Builder builder) {
    super(builder);
  }

  @Override
  public String execute() throws IOException, SpotifyWebApiException, ParseException {
    return putJson();
  }

  /**
   * Builder class for building a {@link SaveShowsForCurrentUserRequest}.
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
    public SaveShowsForCurrentUserRequest build() {
      setPath("/v1/me/shows");
      return new SaveShowsForCurrentUserRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
