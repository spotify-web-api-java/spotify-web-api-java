package com.wrapper.spotify.requests.data.player;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class StartResumeUsersPlaybackRequest extends AbstractDataRequest {

  private StartResumeUsersPlaybackRequest(final Builder builder) {
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

  public SettableFuture putAsync() throws
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
    return executeAsync(putJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder device_id(final String device_id) {
      assert (device_id != null);
      assert (!device_id.equals(""));
      return setQueryParameter("device_id", device_id);
    }

    public Builder context_uri(final String context_uri) {
      assert (context_uri != null);
      assert (!context_uri.equals(""));
      return setBodyParameter("context_uri", context_uri);
    }

    public Builder uris(final JsonArray uris) {
      assert (uris != null);
      assert (!uris.isJsonNull());
      return setBodyParameter("uris", uris);
    }

    public Builder offset(final JsonObject offset) {
      assert (offset != null);
      assert (!offset.isJsonNull());
      return setBodyParameter("device_id", offset);
    }

    @Override
    public StartResumeUsersPlaybackRequest build() {
      setPath("/v1/me/player/play");
      return new StartResumeUsersPlaybackRequest(this);
    }
  }
}
