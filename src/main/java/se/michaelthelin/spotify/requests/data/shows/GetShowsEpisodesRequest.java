package se.michaelthelin.spotify.requests.data.shows;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.EpisodeSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataPagingRequest;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about an show’s episodes.
 */
@JsonDeserialize(builder = GetShowsEpisodesRequest.Builder.class)
public class GetShowsEpisodesRequest extends AbstractDataRequest<Paging<EpisodeSimplified>> {

  /**
   * The private {@link GetShowsEpisodesRequest} constructor.
   *
   * @param builder A {@link GetShowsEpisodesRequest.Builder}.
   */
  private GetShowsEpisodesRequest(Builder builder) {
    super(builder);
  }

  /**
   * Get episodes of a show.
   *
   * @return An array of {@link EpisodeSimplified} objects wrapped in a {@link Paging} object.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @Override
  public Paging<EpisodeSimplified> execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new EpisodeSimplified.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetShowsEpisodesRequest}.
   */
  public static final class Builder extends AbstractDataPagingRequest.Builder<EpisodeSimplified, Builder> {

    /**
     * Create a new {@link GetShowsEpisodesRequest.Builder} instance.
     * <p>
     * Reading the user’s resume points on episode objects requires the {@code user-read-playback-position} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The show ID setter.
     *
     * @param id The Spotify ID for the show.
     * @return A {@link GetShowsEpisodesRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.isEmpty());
      return setPathParameter("id", id);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of episodes to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetShowsEpisodesRequest.Builder}.
     */
    @Override
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first episode to return. Default: 0 (i.e., the first object). Use with
     *               {@link #limit(Integer)} to get the next set of objects.
     * @return A {@link GetShowsEpisodesRequest.Builder}.
     */
    @Override
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The market country code setter.<p>
     * If a country code is specified, only shows and episodes that are available in that market will be returned.
     * If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.
     * <i>Note: If neither market or user country are provided, the content is considered unavailable for the client.</i><p>
     * Users can view the country that is associated with their account in the account settings.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code.
     * @return A {@link GetShowsEpisodesRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetShowsEpisodesRequest}.
     */
    @Override
    public GetShowsEpisodesRequest build() {
      setPath("/v1/shows/{id}/episodes");
      return new GetShowsEpisodesRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
