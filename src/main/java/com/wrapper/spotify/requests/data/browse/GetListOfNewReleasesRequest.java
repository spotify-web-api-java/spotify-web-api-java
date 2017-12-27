package com.wrapper.spotify.requests.data.browse;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.NewReleases;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetListOfNewReleasesRequest extends AbstractRequest {

  private GetListOfNewReleasesRequest(final Builder builder) {
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
    return executeAsync(new NewReleases.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder country(final CountryCode countryCode) {
      assert (countryCode != null);
      return setFormParameter("country", countryCode.toString());
    }

    public Builder limit(final Integer limit) {
      assert (limit > 0);
      return setFormParameter("limit", String.valueOf(limit));
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setFormParameter("offset", String.valueOf(offset));
    }

    @Override
    public GetListOfNewReleasesRequest build() {
      setPath("/v1/browse/new-releases");
      return new GetListOfNewReleasesRequest(this);
    }
  }
}