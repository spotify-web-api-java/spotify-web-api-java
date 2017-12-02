package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.objects.Track;

import java.io.IOException;
import java.util.List;

public class TopTracksRequest extends AbstractRequest {

  private TopTracksRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Track[] get() throws
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
    return new Track.JsonUtil().createModelObjectArray(getJson());
  }

  public SettableFuture<Track[]> getAsync() throws
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
    return getAsync(new Track.JsonUtil().createModelObjectArray(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(final String id) {
      assert (id != null);
      return setPath(String.format("/v1/artists/%s/toptracks", id));
    }

    public Builder countryCode(final String countryCode) {
      assert (countryCode != null);
      return setParameter("country", countryCode);
    }

    @Override
    public TopTracksRequest build() {
      return new TopTracksRequest(this);
    }

  }

}
