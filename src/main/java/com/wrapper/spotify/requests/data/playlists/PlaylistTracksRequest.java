package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.PlaylistTrack;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class PlaylistTracksRequest extends AbstractRequest {

  private PlaylistTracksRequest(final Builder builder) {
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
    return getAsync(new PlaylistTrack.JsonUtil().createModelObjectPaging(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder fields(final String fields) {
      assert (fields != null);
      return setParameter("fields", fields);
    }

    public Builder limit(final int limit) {
      assert (limit > 0);
      return setParameter("limit", String.valueOf(limit));
    }

    public Builder offset(final int offset) {
      assert (offset >= 0);
      return setParameter("offset", String.valueOf(offset));
    }

    @Override
    public PlaylistTracksRequest build() {
      return new PlaylistTracksRequest(this);
    }

  }
}
