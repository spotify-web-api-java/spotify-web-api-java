package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.PlaylistSimplified;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetListOfUsersPlaylistsRequest extends AbstractRequest {

  private GetListOfUsersPlaylistsRequest(final Builder builder) {
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
    return new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson());
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
    return executeAsync(new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder username(final String username) {
      assert (username != null);
      return setPath(String.format("/v1/users/%s/playlists", username));
    }

    public Builder limit(final int limit) {
      assert (limit > 0);
      return setFormParameter("limit", String.valueOf(limit));
    }

    public Builder offset(final int offset) {
      assert (offset >= 0);
      return setFormParameter("offset", String.valueOf(offset));
    }

    @Override
    public GetListOfUsersPlaylistsRequest build() {
      return new GetListOfUsersPlaylistsRequest(this);
    }

  }
}
