package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Playlist;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class PlaylistCreationRequest extends AbstractRequest {

  private PlaylistCreationRequest(final Builder builder) {
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
    return new Playlist.JsonUtil().createModelObject(postJson());
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
    return getAsync(new Playlist.JsonUtil().createModelObject(postJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private JsonObject jsonBody;

    public Builder publicAccess(final boolean publicAccess) {
      if (jsonBody == null) {
        jsonBody = new JsonObject();
      }
      jsonBody.addProperty("public", String.valueOf(publicAccess));
      return setBodyParameter(jsonBody);
    }

    public Builder title(final String title) {
      if (jsonBody == null) {
        jsonBody = new JsonObject();
      }
      jsonBody.addProperty("name", String.valueOf(title));
      return setBodyParameter(jsonBody);
    }

    @Override
    public PlaylistCreationRequest build() {
      setHeaderParameter("Content-Type", "application/json");
      return new PlaylistCreationRequest(this);
    }

  }

}
