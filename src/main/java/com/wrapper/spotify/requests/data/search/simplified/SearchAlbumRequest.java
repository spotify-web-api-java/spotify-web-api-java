package com.wrapper.spotify.requests.data.search.simplified;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class SearchAlbumRequest extends AbstractDataRequest {

  private SearchAlbumRequest(final Builder builder) {
    super(builder);
  }

  public Paging<AlbumSimplified> get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return new AlbumSimplified.JsonUtil().createModelObjectPaging(getJson(), "albums");
  }

  public SettableFuture<Paging<AlbumSimplified>> getAsync() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return executeAsync(new AlbumSimplified.JsonUtil().createModelObjectPaging(getJson(), "albums"));
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
    public SearchAlbumRequest build() {
      setPath("/v1/search");
      setQueryParameter("type", "album");
      return new SearchAlbumRequest(this);
    }
  }
}
