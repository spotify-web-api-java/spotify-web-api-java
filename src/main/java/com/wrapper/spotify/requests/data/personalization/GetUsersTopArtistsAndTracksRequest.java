package com.wrapper.spotify.requests.data.personalization;

import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import com.wrapper.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;

import java.io.IOException;

/**
 * This class only exists for theoretical purposes. Please use {@link GetUsersTopArtistsRequest} and
 * {@link GetUsersTopTracksRequest} instead.
 *
 * @param <T> The request {@link ModelObjectType}: artist or track.
 */
public class GetUsersTopArtistsAndTracksRequest<T extends IArtistTrackModelObject> extends AbstractDataRequest {

  private final AbstractModelObject.JsonUtil<T> tClass;

  /**
   * The private {@link GetUsersTopArtistsAndTracksRequest} constructor.
   *
   * @param builder A {@link GetUsersTopArtistsAndTracksRequest.Builder}.
   * @param tClass  A {@link AbstractModelObject.JsonUtil}.
   */
  private GetUsersTopArtistsAndTracksRequest(final Builder builder, final AbstractModelObject.JsonUtil<T> tClass) {
    super(builder);
    this.tClass = tClass;
  }

  /**
   * Get the top artists and tracks.
   *
   * @return Top artists and tracks.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Paging<T> execute() throws
          IOException,
          SpotifyWebApiException {
    return tClass.createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetUsersTopArtistsAndTracksRequest}.
   * <p>
   * This class only exists for theoretical purposes. Please use {@link GetUsersTopArtistsRequest.Builder} and
   * {@link GetUsersTopTracksRequest.Builder} instead.
   *
   * @param <T> The request {@link ModelObjectType}: artist or track.
   */
  public static final class Builder<T extends IArtistTrackModelObject> extends AbstractDataRequest.Builder<Builder<T>> {

    private AbstractModelObject.JsonUtil<T> tClass;

    /**
     * Create a new {@link GetUsersTopArtistsAndTracksRequest.Builder} instance.
     * <p>
     * Getting details of a user's top artists and tracks requires authorization of the {@code user-top-read} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The model object type setter.
     *
     * @param type The type of entity to return. Valid values: {@code artists} or {@code tracks}.
     * @return A {@link GetUsersTopArtistsAndTracksRequest.Builder}.
     */
    @SuppressWarnings("unchecked")
    public Builder type(final ModelObjectType type) {
      assert (type != null);
      assert (type.getType().equals("artists") || type.getType().equals("tracks"));

      switch (type.getType()) {
        case "artists":
          tClass = (AbstractModelObject.JsonUtil<T>) new Artist.JsonUtil();
          break;
        case "tracks":
          tClass = (AbstractModelObject.JsonUtil<T>) new Track.JsonUtil();
          break;
      }

      return setPathParameter("type", type.getType());
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The number of entities to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetUsersTopArtistsAndTracksRequest.Builder}.
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
     * @return A {@link GetUsersTopArtistsAndTracksRequest.Builder}.
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
     * @return A {@link GetUsersTopArtistsAndTracksRequest.Builder}.
     */
    public Builder time_range(final String time_range) {
      assert (time_range != null);
      assert (time_range.equals("long_term") || time_range.equals("medium_term") || time_range.equals("short_term"));
      return setQueryParameter("time_range", time_range);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetUsersTopArtistsAndTracksRequest}.
     */
    @Override
    public GetUsersTopArtistsAndTracksRequest<T> build() {
      setPath("/v1/me/top/{type}");
      return new GetUsersTopArtistsAndTracksRequest<>(this, tClass);
    }
  }
}
