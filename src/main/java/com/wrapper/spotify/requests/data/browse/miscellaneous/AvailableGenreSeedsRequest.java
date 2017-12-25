package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;

import java.io.IOException;

public class AvailableGenreSeedsRequest extends AbstractRequest {

  private AvailableGenreSeedsRequest(final Builder builder) {
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
    return getJson();
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
    return getAsync(getJson());
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {
    /**
     * Required. A valid access token from the Spotify Accounts service
     */
    public Builder accessToken(String accessToken) {
      return setHeaderParameter("Authorization", "Bearer " + accessToken);
    }

    @Override
    public AvailableGenreSeedsRequest build() {
      setPath("/v1/recommendations/available-genre-seeds");
      return new AvailableGenreSeedsRequest(this);
    }
  }
}
