package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Playlist;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class CreatePlaylistRequest extends AbstractRequest {

  private CreatePlaylistRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Playlist post() throws
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
    return new Playlist.JsonUtil().createModelObject(postJson());
  }

  public SettableFuture<Playlist> postAsync() throws
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
    return executeAsync(new Playlist.JsonUtil().createModelObject(postJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder publicAccess(final boolean publicAccess) {
      return setQueryParameter("public", publicAccess);
    }

    public Builder name(final String name) {
      assert(name != null);
      return setQueryParameter("name", name);
    }

    @Override
    public CreatePlaylistRequest build() {
      return new CreatePlaylistRequest(this);
    }
  }
}
