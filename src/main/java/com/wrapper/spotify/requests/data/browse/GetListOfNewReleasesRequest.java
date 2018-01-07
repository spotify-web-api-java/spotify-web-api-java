package com.wrapper.spotify.requests.data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetListOfNewReleasesRequest extends AbstractDataRequest {

  private GetListOfNewReleasesRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public Paging<AlbumSimplified> execute() throws
          IOException,
          SpotifyWebApiException {
    return new AlbumSimplified.JsonUtil().createModelObjectPaging(getJson(), "albums");
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

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
    public GetListOfNewReleasesRequest build() {
      setPath("/v1/browse/new-releases");
      return new GetListOfNewReleasesRequest(this);
    }
  }
}