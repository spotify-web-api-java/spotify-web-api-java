package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.Playlist;
import net.sf.json.JSONObject;

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
    return JsonUtil.createPlaylist(postJson());
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
    return getAsync(JsonUtil.createPlaylist(postJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    private JSONObject jsonBody;

    public Builder publicAccess(final boolean publicAccess) {
      if (jsonBody == null) {
        jsonBody = new JSONObject();
      }
      jsonBody.put("public", String.valueOf(publicAccess));
      return setBodyParameter(jsonBody);
    }

    public Builder title(final String title) {
      if (jsonBody == null) {
        jsonBody = new JSONObject();
      }
      jsonBody.put("name", String.valueOf(title));
      return setBodyParameter(jsonBody);
    }

    @Override
    public PlaylistCreationRequest build() {
      setHeaderParameter("Content-Type", "application/json");
      return new PlaylistCreationRequest(this);
    }

  }

}
