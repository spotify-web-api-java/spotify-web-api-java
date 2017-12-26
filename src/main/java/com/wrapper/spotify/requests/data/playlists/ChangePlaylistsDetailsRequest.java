package com.wrapper.spotify.requests.data.playlists;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;
import java.util.Map;

public class ChangePlaylistsDetailsRequest extends AbstractRequest {

  private ChangePlaylistsDetailsRequest(final Builder builder) {
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
    return putJson();
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
    return getAsync(putJson());
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    final private Map<String, Object> properties = Maps.newHashMap();

    public Builder name(final String name) {
      assert (name != null);
      properties.put("name", name);
      return this;
    }

    public Builder publicAccess(final boolean isPublic) {
      properties.put("public", isPublic);
      return this;
    }

    @Override
    public ChangePlaylistsDetailsRequest build() {
      setHeaderParameter("Content-Type", "application/json");
      setBodyParameter(new JsonParser().parse(new Gson().toJson(properties)).getAsJsonObject());
      return new ChangePlaylistsDetailsRequest(this);
    }

  }

}
