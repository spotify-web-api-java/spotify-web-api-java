package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
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

  public List<Boolean> get() throws
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
    return JsonUtil.createBooleans(getJson());
  }

  public SettableFuture<List<Boolean>> getAsync() throws
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
    return getAsync(JsonUtil.createBooleans(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder tracks(final List<String> trackIds) {
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
