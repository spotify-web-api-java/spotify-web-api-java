package com.wrapper.spotify.requests.data.browse.miscellaneous;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetAvailableGenreSeedsRequest extends AbstractRequest {

  private GetAvailableGenreSeedsRequest(final Builder builder) {
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
    public GetAvailableGenreSeedsRequest build() {
      setPath("/v1/recommendations/available-genre-seeds");
      return new GetAvailableGenreSeedsRequest(this);
    }
  }
}
