package com.wrapper.spotify.requests.data.browse;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.PlaylistSimplified;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetCategoriesPlaylistsRequest extends AbstractRequest {

  private GetCategoriesPlaylistsRequest(final Builder builder) {
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
    return new PlaylistSimplified.JsonUtil().createModelObjectPaging(new JsonParser().parse(getJson()).getAsJsonObject().get("playlists").getAsJsonObject());
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
    return getAsync(new PlaylistSimplified.JsonUtil().createModelObjectPaging(new JsonParser().parse(getJson()).getAsJsonObject().get("playlists").getAsJsonObject()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {
    public Builder category(String category) {
      assert (category != null);
      return setPath(String.format("/v1/browse/categories/%s/playlists", category));
    }

    /*
     * Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to narrow the
     * list of returned categories to those relevant to a particular country. If omitted, the returned items will
     * be globally relevant.
     */
    public Builder country(String country) {
      assert (country != null);
      return setParameter("country", country);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return setParameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return setParameter("offset", String.valueOf(offset));
    }

    public Builder accessToken(String accessToken) {
      return setHeaderParameter("Authorization", "Bearer " + accessToken);
    }

    @Override
    public GetCategoriesPlaylistsRequest build() {
      return new GetCategoriesPlaylistsRequest(this);
    }
  }
}