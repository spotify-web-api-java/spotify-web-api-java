package com.wrapper.spotify.requests.data.personalization.simplified;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get the current user’s top tracks based on calculated affinity.
 * <p>
 * Affinity is a measure of the expected preference a user has for a particular track or artist.  It is based on user
 * behavior, including play history, but does not include actions made while in incognito mode.
 * Light or infrequent users of Spotify may not have sufficient play history to generate a full affinity data set.
 * <p>
 * As a user’s behavior is likely to shift over time, this preference data is available over three time spans. See
 * {@link Builder#time_range(String)} for more information.
 * <p>
 * For each time range, the top 50 tracks and artists are available for each user. In the future, it is likely that this
 * restriction will be relaxed. This data is typically updated once each day for each user.
 */
public class GetUsersTopTracksRequest extends AbstractDataRequest {

  /**
   * The private {@link GetUsersTopTracksRequest} constructor.
   *
   * @param builder A {@link GetUsersTopTracksRequest.Builder}.
   */
  private GetUsersTopTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get an user's top tracks.
   *
   * @return An user's top tracks.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Paging<Track> execute() throws
          IOException,
          SpotifyWebApiException {
    return new Track.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetUsersTopTracksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetUsersTopTracksRequest.Builder} instance.
     * <p>
     * Getting details of a user's top tracks requires authorization of the {@code user-top-read} scope.
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
     * @param limit Optional. The number of entities to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetUsersTopTracksRequest.Builder}.
     */
    public Builder limit(final Integer limit) {
      assert (limit != null);
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first entity to return. Default: 0 (i.e., the first track). Use with
     *               {@link #limit(Integer)} to get the next set of entities.
     * @return A {@link GetUsersTopTracksRequest.Builder}.
     */
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The time range setter.
     *
     * @param time_range Optional. Over what time frame the affinities are computed. Valid values: {@code long_term}
     *                   (calculated from several years of data and including all new data as it becomes available),
     *                   {@code medium_term} (approximately last 6 months), {@code short_term} (approximately last 4
     *                   weeks). Default: {@code medium_term}.
     * @return A {@link GetUsersTopTracksRequest.Builder}.
     */
    public Builder time_range(final String time_range) {
      assert (time_range != null);
      assert (time_range.equals("long_term") || time_range.equals("medium_term") || time_range.equals("short_term"));
      return setQueryParameter("time_range", time_range);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetUsersTopTracksRequest}
     */
    @Override
    public GetUsersTopTracksRequest build() {
      setPath("/v1/me/top/tracks");
      return new GetUsersTopTracksRequest(this);
    }
  }
}
