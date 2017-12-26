package com.wrapper.spotify.requests.data.library;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class CheckUsersSavedTracksRequest extends AbstractRequest {

  private CheckUsersSavedTracksRequest(final Builder builder) {
    super(builder);
  }

  public static CheckUsersSavedTracksRequest.Builder builder() {
    return new Builder();
  }

  public Boolean[] get() throws
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
    return new Gson().fromJson(new JsonParser().parse(getJson()).getAsJsonArray(), Boolean[].class);
  }

  public SettableFuture<Boolean[]> getAsync() throws
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
    return getAsync(new Gson().fromJson(new JsonParser().parse(getJson()).getAsJsonArray(), Boolean[].class));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder tracks(final String[] trackIds) {
      String idsParameter = Joiner.on(",").join(trackIds);
      setParameter("ids", idsParameter);
      return this;
    }

    @Override
    public CheckUsersSavedTracksRequest build() {
      setHeaderParameter("Content-Type", "application/json");
      return new CheckUsersSavedTracksRequest(this);
    }

  }
}
