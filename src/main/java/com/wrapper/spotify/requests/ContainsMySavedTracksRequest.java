package com.wrapper.spotify.requests;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;

import java.io.IOException;
import java.util.List;

public class ContainsMySavedTracksRequest extends AbstractRequest {

  private ContainsMySavedTracksRequest(final Builder builder) {
    super(builder);
  }

  public static ContainsMySavedTracksRequest.Builder builder() {
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
    public ContainsMySavedTracksRequest build() {
      setHeaderParameter("Content-Type", "application/json");
      return new ContainsMySavedTracksRequest(this);
    }

  }
}
