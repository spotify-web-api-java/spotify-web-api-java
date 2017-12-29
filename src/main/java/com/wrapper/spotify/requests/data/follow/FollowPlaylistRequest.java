package com.wrapper.spotify.requests.data.follow;

import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class FollowPlaylistRequest extends AbstractDataRequest {

  private FollowPlaylistRequest(final Builder builder) {
    super(builder);
  }

  public void put() throws
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
    putJson();
  }

  public void putAsync() throws
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
    executeAsync(putJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder owner_id(final String owner_id) {
      assert (owner_id != null);
      assert (!owner_id.equals(""));
      return setPathParameter("owner_id", owner_id);
    }

    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    public Builder public_(final Boolean public_) {
      assert (public_ != null);
      return setBodyParameter("public", public_);
    }

    @Override
    public FollowPlaylistRequest build() {
      setPath("/v1/users/{owner_id}/playlists/{playlist_id}/followers");
      return new FollowPlaylistRequest(this);
    }
  }
}
