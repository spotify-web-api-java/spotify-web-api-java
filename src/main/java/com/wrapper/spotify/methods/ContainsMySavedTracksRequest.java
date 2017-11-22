package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;

import java.io.IOException;
import java.util.List;

public class ContainsMySavedTracksRequest extends AbstractRequest {

  public ContainsMySavedTracksRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<List<Boolean>> getAsync() {
    final SettableFuture<List<Boolean>> containsTracksFuture = SettableFuture.create();

    final String response;
    try {
      response = getJson();
      List<Boolean> containedTracks = JsonUtil.createBooleans(response);
      containsTracksFuture.set(containedTracks);
    } catch (Exception e) {
      containsTracksFuture.setException(e);
    }

    return containsTracksFuture;
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

  public static ContainsMySavedTracksRequest.Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder>  {

    public Builder tracks(List<String> trackIds) {
      String idsParameter = Joiner.on(",").join(trackIds);
      setParameter("ids", idsParameter);
      return this;
    }

    public ContainsMySavedTracksRequest build() {
      setHeaderParameter("Content-Type", "application/json");
      return new ContainsMySavedTracksRequest(this);
    }

  }
}
