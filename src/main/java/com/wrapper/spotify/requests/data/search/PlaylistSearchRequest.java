package com.wrapper.spotify.requests.data.search;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.PlaylistSimplified;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class PlaylistSearchRequest extends AbstractDataRequest {

  private PlaylistSearchRequest(final Builder builder) {
    super(builder);
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
    return executeAsync(new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson(), "playlists"));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }


    public Builder query(String query) {
      assert (query != null);
      setPath("/v1/search");
      setFormParameter("type", "playlist");
      return setFormParameter("q", query);
    }

    public Builder market(String market) {
      assert (market != null);
      return setFormParameter("market", market);
    }

    public Builder limit(Integer limit) {
      assert (limit > 0);
      return setFormParameter("limit", limit);
    }

    public Builder offset(Integer offset) {
      assert (offset >= 0);
      return setFormParameter("offset", offset);
    }

    @Override
    public PlaylistSearchRequest build() {
      return new PlaylistSearchRequest(this);
    }
  }
}
