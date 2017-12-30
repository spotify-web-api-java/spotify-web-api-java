package com.wrapper.spotify.requests.data.player;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Device;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetUsersAvailableDevicesRequest extends AbstractDataRequest {

  private GetUsersAvailableDevicesRequest(final Builder builder) {
    super(builder);
  }

  public Device[] get() throws
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
    return new Device.JsonUtil().createModelObjectArray(getJson(), "devices");
  }

  public SettableFuture<Device[]> getAsync() throws
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
    return executeAsync(new Device.JsonUtil().createModelObjectArray(getJson(), "devices"));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    @Override
    public GetUsersAvailableDevicesRequest build() {
      setPath("/v1/me/player/devices");
      return new GetUsersAvailableDevicesRequest(this);
    }
  }
}
