package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Playlist;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetPlaylistRequest extends AbstractRequest {

  private GetPlaylistRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Playlist get() throws
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
    return new Playlist.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<Playlist> getAsync() throws
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
    return executeAsync(new Playlist.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder fields(final String fields) {
      assert (fields != null);
      return setFormParameter("fields", fields);
    }

    @Override
    public GetPlaylistRequest build() {
      return new GetPlaylistRequest(this);
    }

  }

}