package com.wrapper.spotify.requests.data.browse;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.PlaylistSimplified;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetCategorysPlaylistsRequest extends AbstractRequest {

  private GetCategorysPlaylistsRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Paging<PlaylistSimplified> get() throws
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
    return new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson(), "playlists");
  }

  public SettableFuture<Paging<PlaylistSimplified>> getAsync() throws
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
    return getAsync(new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson(), "playlists"));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {
    public Builder category_id(final String category_id) {
      assert (category_id != null);
      return setPathParameter("category_id", category_id);
    }

    /*
     * Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to narrow the
     * list of returned categories to those relevant to a particular country. If omitted, the returned items will
     * be globally relevant.
     */
    public Builder country(final CountryCode country) {
      assert (country != null);
      return setParameter("country", country.toString());
    }

    public Builder limit(final Integer limit) {
      assert (limit > 0);
      return setParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setParameter("offset", offset);
    }

    @Override
    public GetCategorysPlaylistsRequest build() {
      setPath("/v1/browse/categories/{category_id}/playlists");
      return new GetCategorysPlaylistsRequest(this);
    }
  }
}