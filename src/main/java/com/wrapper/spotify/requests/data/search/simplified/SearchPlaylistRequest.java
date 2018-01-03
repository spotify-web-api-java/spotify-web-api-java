package com.wrapper.spotify.requests.data.search.simplified;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class SearchPlaylistRequest extends AbstractDataRequest {

  private SearchPlaylistRequest(final Builder builder) {
    super(builder);
  }

  public Paging<ArtistSimplified.PlaylistSimplified> get() throws
          IOException,
          SpotifyWebApiException {
    return new ArtistSimplified.PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson(), "playlists");
  }

  public SettableFuture<Paging<ArtistSimplified.PlaylistSimplified>> getAsync() throws
          IOException,
          SpotifyWebApiException {
    return executeAsync(new ArtistSimplified.PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson(), "playlists"));
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
    public SearchPlaylistRequest build() {
      setPath("/v1/search");
      setQueryParameter("type", "playlist");
      return new SearchPlaylistRequest(this);
    }
  }
}
