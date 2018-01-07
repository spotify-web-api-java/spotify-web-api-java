package com.wrapper.spotify.requests.data.personalization.simplified;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetUsersTopTracksRequest extends AbstractDataRequest {

  private GetUsersTopTracksRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public Paging<Track> execute() throws
          IOException,
          SpotifyWebApiException {
    return new Track.JsonUtil().createModelObjectPaging(getJson());
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

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    public Builder time_range(final String time_range) {
      assert (time_range != null);
      assert (time_range.equals("long_term") || time_range.equals("medium_term") || time_range.equals("short_term"));
      return setQueryParameter("time_range", time_range);
    }

    @Override
    public GetUsersTopTracksRequest build() {
      setPath("/v1/me/top/tracks");
      return new GetUsersTopTracksRequest(this);
    }
  }
}
