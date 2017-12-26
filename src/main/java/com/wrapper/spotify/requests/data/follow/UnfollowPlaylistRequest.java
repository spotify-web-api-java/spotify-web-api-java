package com.wrapper.spotify.requests.data.follow;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class UnfollowPlaylistRequest extends AbstractRequest {

  private UnfollowPlaylistRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public String get() throws
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
    return getJson();
  }

  public SettableFuture<String> getAsync() throws
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
    return getAsync(getJson());
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder owner_id(final String owner_id) {
      assert (owner_id != null);
      return setPathParameter("owner_id", owner_id);
    }

    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      return setPathParameter("playlist_id", playlist_id);
    }

    @Override
    public UnfollowPlaylistRequest build() {
      setPath("/v1/users/{owner_id}/playlists/{playlist_id}/followers");
      return new UnfollowPlaylistRequest(this);
    }
  }
}
