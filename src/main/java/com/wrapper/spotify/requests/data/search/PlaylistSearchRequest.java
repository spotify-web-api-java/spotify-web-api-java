package com.wrapper.spotify.requests.data.search;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.PlaylistSimplified;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class PlaylistSearchRequest extends AbstractRequest {

  private PlaylistSearchRequest(final Builder builder) {
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
    return executeAsync(new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson(), "playlists"));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

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

    public Builder limit(int limit) {
      assert (limit > 0);
      return setFormParameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return setFormParameter("offset", String.valueOf(offset));
    }

    @Override
    public PlaylistSearchRequest build() {
      return new PlaylistSearchRequest(this);
    }
  }
}
