package se.michaelthelin.spotify.requests.data.shows;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Remove one or more shows from the current user's library.
 *
 * @deprecated Use {@link RemoveLibraryItemsRequest} instead.
 */
@Deprecated
@JsonDeserialize(builder = RemoveShowsForCurrentUserRequest.Builder.class)
public class RemoveShowsForCurrentUserRequest extends AbstractDataRequest<String> {

  private RemoveShowsForCurrentUserRequest(final Builder builder) {
    super(builder);
  }

  @Override
  public String execute() throws IOException, SpotifyWebApiException, ParseException {
    return deleteJson();
  }

  /**
   * Builder class for building a {@link RemoveShowsForCurrentUserRequest}.
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
    public RemoveShowsForCurrentUserRequest build() {
      setPath("/v1/me/shows");
      return new RemoveShowsForCurrentUserRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
