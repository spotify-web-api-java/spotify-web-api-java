package com.wrapper.spotify.requests.data.browse;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetCategorysPlaylistsRequest extends AbstractDataRequest {

  private GetCategorysPlaylistsRequest(final Builder builder) {
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

    public Builder category_id(final String category_id) {
      assert (category_id != null);
      assert (category_id.matches("^[a-z]+$"));
      return setPathParameter("category_id", category_id);
    }

    /*
     * Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to narrow the
     * list of returned categories to those relevant to a particular country. If omitted, the returned items will
     * be globally relevant.
     */
    public Builder country(final CountryCode country) {
      assert (country != null);
      return setQueryParameter("country", country);
    }

    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    @Override
    public GetCategorysPlaylistsRequest build() {
      setPath("/v1/browse/categories/{category_id}/playlists");
      return new GetCategorysPlaylistsRequest(this);
    }
  }
}