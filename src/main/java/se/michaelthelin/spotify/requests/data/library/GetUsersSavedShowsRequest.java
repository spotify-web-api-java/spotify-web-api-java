package se.michaelthelin.spotify.requests.data.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.SavedShow;
import se.michaelthelin.spotify.requests.data.AbstractDataPagingRequest;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a list of shows saved in the current Spotify user’s library.
 */
@JsonDeserialize(builder = GetUsersSavedShowsRequest.Builder.class)
public class GetUsersSavedShowsRequest extends AbstractDataRequest<Paging<SavedShow>> {

  /**
   * The private {@link GetUsersSavedShowsRequest} constructor.
   *
   * @param builder A {@link GetUsersSavedShowsRequest.Builder}.
   */
  private GetUsersSavedShowsRequest(Builder builder) {
    super(builder);
  }

  /**
   * Get a list of the current user’s saved shows.
   *
   * @return A {@link SavedShow} paging object.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @Override
  public Paging<SavedShow> execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new SavedShow.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetUsersSavedShowsRequest}.
   */
  public static final class Builder extends AbstractDataPagingRequest.Builder<SavedShow, Builder> {

    /**
     * Create a new {@link GetUsersSavedShowsRequest.Builder} instance.
     * <p>
     * The {@code user-library-read} scope must have been authorized by the user.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of shows to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetUsersSavedShowsRequest.Builder}.
     */
    @Override
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }


    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first show to return. Default: 0 (i.e., the first object). Use with
     *               {@link #limit(Integer)} to get the next set of objects.
     * @return A {@link GetUsersSavedShowsRequest.Builder}.
     */
    @Override
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetUsersSavedShowsRequest}.
     */
    @Override
    public GetUsersSavedShowsRequest build() {
      setPath("/v1/me/shows");
      return new GetUsersSavedShowsRequest(this);
    }

    @Override
    protected GetUsersSavedShowsRequest.Builder self() {
      return this;
    }
  }
}
