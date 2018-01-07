package com.wrapper.spotify.requests.data.search.simplified;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class SearchPlaylistsRequest extends AbstractDataRequest {

  private SearchPlaylistsRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public Paging<PlaylistSimplified> execute() throws
          IOException,
          SpotifyWebApiException {
    return new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson(), "playlists");
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
    public SearchPlaylistsRequest build() {
      setPath("/v1/search");
      setQueryParameter("type", "playlist");
      return new SearchPlaylistsRequest(this);
    }
  }
}
