package com.wrapper.spotify.requests.data.player;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;
import com.wrapper.spotify.model_objects.specification.PlayHistory;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;
import java.util.Date;

public class GetCurrentUsersRecentlyPlayedTracksRequest extends AbstractDataRequest {

  private GetCurrentUsersRecentlyPlayedTracksRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public PagingCursorbased<PlayHistory> execute() throws
          IOException,
          SpotifyWebApiException {
    return new PlayHistory.JsonUtil().createModelObjectPagingCursorbased(getJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder limit(final Integer limit) {
      assert (limit != null);
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    public Builder after(final Date after) {
      assert (after != null);
      return setQueryParameter("after", SpotifyApi.SIMPLE_DATE_FORMAT.format(after));
    }

    public Builder before(final Date before) {
      assert (before != null);
      return setQueryParameter("before", SpotifyApi.SIMPLE_DATE_FORMAT.format(before));
    }

    @Override
    public GetCurrentUsersRecentlyPlayedTracksRequest build() {
      setPath("/v1/me/player/recently-played");
      return new GetCurrentUsersRecentlyPlayedTracksRequest(this);
    }
  }
}
