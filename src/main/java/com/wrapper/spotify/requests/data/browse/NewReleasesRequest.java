package com.wrapper.spotify.requests.data.browse;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.NewReleases;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class NewReleasesRequest extends AbstractRequest {

  private NewReleasesRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public NewReleases get() throws
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
    return new NewReleases.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<NewReleases> getAsync() throws
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
    return getAsync(new NewReleases.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder limit(final int limit) {
      assert (limit > 0);
      return setParameter("limit", String.valueOf(limit));
    }

    public Builder offset(final int offset) {
      assert (offset >= 0);
      return setParameter("offset", String.valueOf(offset));
    }

    public Builder country(final String countryCode) {
      assert (countryCode != null);
      return setParameter("country", countryCode);
    }

    @Override
    public NewReleasesRequest build() {
      setPath("/v1/browse/new-releases");
      return new NewReleasesRequest(this);
    }

  }
}