package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;

import java.io.IOException;

public class AddToMySavedTracksRequest extends AbstractRequest {

  private AddToMySavedTracksRequest(final Builder builder) {
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

    public Builder tracks(final String[] trackIds) {
      setBodyParameter(new JsonParser().parse(new Gson().toJson(trackIds)).getAsJsonArray());
      return this;
    }

    @Override
    public AddToMySavedTracksRequest build() {
      return new AddToMySavedTracksRequest(this);
    }
  }
}
