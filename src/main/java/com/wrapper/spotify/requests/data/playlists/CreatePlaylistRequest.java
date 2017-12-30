package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Playlist;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class CreatePlaylistRequest extends AbstractDataRequest {

  private CreatePlaylistRequest(final Builder builder) {
    super(builder);
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

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    public Builder name(final String name) {
      assert (name != null);
      assert (!name.equals(""));
      return setBodyParameter("name", name);
    }

    public Builder public_(final Boolean public_) {
      return setBodyParameter("public", public_);
    }

    public Builder collaborative(final Boolean collaborative) {
      return setBodyParameter("collaborative", collaborative);
    }

    public Builder description(final String description) {
      assert (description != null);
      assert (!description.equals(""));
      return setBodyParameter("name", description);
    }

    @Override
    public CreatePlaylistRequest build() {
      setPath("/v1/users/{user_id}/playlists");
      return new CreatePlaylistRequest(this);
    }
  }
}
