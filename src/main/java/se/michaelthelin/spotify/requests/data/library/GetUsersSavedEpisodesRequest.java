package se.michaelthelin.spotify.requests.data.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.miscellaneous.SavedEpisode;
import se.michaelthelin.spotify.requests.data.AbstractDataPagingRequest;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a list of the episodes saved in the current Spotify user's library.
 */
@JsonDeserialize(builder = GetUsersSavedEpisodesRequest.Builder.class)
public class GetUsersSavedEpisodesRequest extends AbstractDataRequest<Paging<SavedEpisode>> {

  /**
   * The private {@link GetUsersSavedEpisodesRequest} constructor.
   *
   * @param builder A {@link GetUsersSavedEpisodesRequest.Builder}.
   */
  private GetUsersSavedEpisodesRequest(GetUsersSavedEpisodesRequest.Builder builder) {
    super(builder);
  }

  /**
   * Get a list of the current user’s saved episodes.
   *
   * @return A {@link SavedEpisode} paging object.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @Override
  public Paging<SavedEpisode> execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new SavedEpisode.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetUsersSavedEpisodesRequest}.
   */
  public static final class Builder extends AbstractDataPagingRequest.Builder<SavedEpisode, GetUsersSavedEpisodesRequest.Builder> {

    /**
     * Create a new {@link GetUsersSavedEpisodesRequest.Builder} instance.
     * <p>
     * The {@code user-library-read} scope must have been authorized by the user.
     * Additionally, if the user has also authorized the {@code user-read-playback-position} scope,
     * {@link se.michaelthelin.spotify.model_objects.specification.ResumePoint ResumePoint} gets set.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of episodes to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetUsersSavedEpisodesRequest.Builder}.
     */
    @Override
    public GetUsersSavedEpisodesRequest.Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first episode to return. Default: 0 (i.e., the first object). Use with
     *               {@link #limit(Integer)} to get the next set of objects.
     * @return A {@link GetUsersSavedEpisodesRequest.Builder}.
     */
    @Override
    public GetUsersSavedEpisodesRequest.Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The market country code setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply
     *               Track Relinking.
     * @return A {@link GetCurrentUsersSavedAlbumsRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/track-relinking">Spotify: Track Relinking Guide</a>
     */
    public GetUsersSavedEpisodesRequest.Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetUsersSavedEpisodesRequest}.
     */
    @Override
    public GetUsersSavedEpisodesRequest build() {
      setPath("/v1/me/episodes");
      return new GetUsersSavedEpisodesRequest(this);
    }

    @Override
    protected GetUsersSavedEpisodesRequest.Builder self() {
      return this;
    }
  }
}
