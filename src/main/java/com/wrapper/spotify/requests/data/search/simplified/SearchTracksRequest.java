package com.wrapper.spotify.requests.data.search.simplified;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class SearchTracksRequest extends AbstractDataRequest {

  private SearchTracksRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public Paging<Track> execute() throws
          IOException,
          SpotifyWebApiException {
    return new Track.JsonUtil().createModelObjectPaging(getJson(), "tracks");
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder q(final String q) {
      assert (q != null);
      assert (!q.equals(""));
      return setQueryParameter("q", q);
    }

    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    public Builder limit(final Integer limit) {
      assert (limit != null);
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset != null);
      assert (0 <= offset && offset <= 100000);
      return setQueryParameter("offset", offset);
    }

    @Override
    public SearchTracksRequest build() {
      setPath("/v1/search");
      setQueryParameter("type", "track");
      return new SearchTracksRequest(this);
    }
  }
}
