package se.michaelthelin.spotify.requests.data.audiobooks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.SavedAudiobook;
import se.michaelthelin.spotify.requests.data.AbstractDataPagingRequest;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a list of the audiobooks saved in the current Spotify user's "Your Music" library.
 */
@JsonDeserialize(builder = GetUsersSavedAudiobooksRequest.Builder.class)
public class GetUsersSavedAudiobooksRequest extends AbstractDataRequest<Paging<SavedAudiobook>> {

  /**
   * The private {@link GetUsersSavedAudiobooksRequest} constructor.
   *
   * @param builder A {@link GetUsersSavedAudiobooksRequest.Builder}.
   */
  private GetUsersSavedAudiobooksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the audiobooks from the current user's "Your Music" library.
   *
   * @return A {@link SavedAudiobook} paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Paging<SavedAudiobook> execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new SavedAudiobook.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetUsersSavedAudiobooksRequest}.
   */
  public static final class Builder extends AbstractDataPagingRequest.Builder<SavedAudiobook, Builder> {

    /**
     * Create a new {@link GetUsersSavedAudiobooksRequest.Builder} instance.
     * <p>
     * The {@code user-library-read} scope must have been authorized by the user.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of objects to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetUsersSavedAudiobooksRequest.Builder}.
     */
    @Override
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first object to return. Default: 0.
     * @return A {@link GetUsersSavedAudiobooksRequest.Builder}.
     */
    @Override
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetUsersSavedAudiobooksRequest}.
     */
    @Override
    public GetUsersSavedAudiobooksRequest build() {
      setPath("/v1/me/audiobooks");
      return new GetUsersSavedAudiobooksRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
