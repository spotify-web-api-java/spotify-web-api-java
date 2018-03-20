package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;
import com.wrapper.spotify.model_objects.specification.PlayHistory;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;
import java.util.Date;

/**
 * Get tracks from the current user’s recently played tracks.
 * <p>
 * Returns the most recent 50 tracks played by a user. Note that a track currently playing will not be visible in play
 * history until it has completed. A track must be played for more than 30 seconds to be included in play history.
 * <p>
 * Any tracks listened to while the user had "Private Session" enabled in their client will not be returned in the list
 * of recently played tracks.
 * <p>
 * The endpoint uses a bidirectional cursor for paging. Follow the {@code next} field with the {@code before} parameter
 * to move back in time, or use the after parameter to move forward in time. If you supply no {@code before} or
 * {@code after} parameter, the endpoint  will return the most recently played songs, and the {@code next}
 * link will page back in time.
 */
public class GetCurrentUsersRecentlyPlayedTracksRequest extends AbstractDataRequest {

  /**
   * The private {@link GetCurrentUsersRecentlyPlayedTracksRequest} constructor.
   *
   * @param builder A {@link GetCurrentUsersRecentlyPlayedTracksRequest.Builder}.
   */
  private GetCurrentUsersRecentlyPlayedTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get an user's recently played tracks.
   *
   * @return An user's recently played tracks.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public PagingCursorbased<PlayHistory> execute() throws
          IOException,
          SpotifyWebApiException {
    return new PlayHistory.JsonUtil().createModelObjectPagingCursorbased(getJson());
  }

  /**
   * Builder class for building a {@link GetCurrentUsersRecentlyPlayedTracksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetCurrentUsersRecentlyPlayedTracksRequest.Builder}.
     * <p>
     * Your access token must have the {@code user-read-recently-played} scope authorized in order to read
     * the user's recently played track.
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
     * @param limit Optional. The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetCurrentUsersRecentlyPlayedTracksRequest.Builder}.
     */
    public Builder limit(final Integer limit) {
      assert (limit != null);
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The after date setter.
     *
     * @param after Optional. A {@link Date} object. Returns all items after (but not including) this cursor position.
     *              If {@link #after(Date)} is specified, {@link #before(Date)} must not be specified.
     * @return A {@link GetCurrentUsersRecentlyPlayedTracksRequest.Builder}.
     */
    public Builder after(final Date after) {
      assert (after != null);
      return setQueryParameter("after", SpotifyApi.formatDefaultDate(after));
    }

    /**
     * The before date setter.
     *
     * @param before Optional. A {@link Date} object. Returns all items before (but not including) this cursor position.
     *               If {@link #before(Date)} is specified, {@link #after(Date)} must not be specified.
     * @return A {@link GetCurrentUsersRecentlyPlayedTracksRequest.Builder}.
     */
    public Builder before(final Date before) {
      assert (before != null);
      return setQueryParameter("before", SpotifyApi.formatDefaultDate(before));
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetCurrentUsersRecentlyPlayedTracksRequest}.
     */
    @Override
    public GetCurrentUsersRecentlyPlayedTracksRequest build() {
      setPath("/v1/me/player/recently-played");
      return new GetCurrentUsersRecentlyPlayedTracksRequest(this);
    }
  }
}
