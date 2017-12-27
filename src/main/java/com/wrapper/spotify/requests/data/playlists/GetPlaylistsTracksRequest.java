package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.PlaylistTrack;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetPlaylistsTracksRequest extends AbstractRequest {

  private GetPlaylistsTracksRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Paging<PlaylistTrack> get() throws
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
    return new PlaylistTrack.JsonUtil().createModelObjectPaging(getJson());
  }

  public SettableFuture<Paging<PlaylistTrack>> getAsync() throws
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
    return executeAsync(new PlaylistTrack.JsonUtil().createModelObjectPaging(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder fields(final String fields) {
      assert (fields != null);
      return setFormParameter("fields", fields);
    }

    public Builder limit(final Integer limit) {
      assert (limit > 0);
      return setFormParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setFormParameter("offset", offset);
    }

    @Override
    public GetPlaylistsTracksRequest build() {
      return new GetPlaylistsTracksRequest(this);
    }

  }
}
