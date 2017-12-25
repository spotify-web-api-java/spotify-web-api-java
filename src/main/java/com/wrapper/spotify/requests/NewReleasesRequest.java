package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.AlbumSimplified;
import com.wrapper.spotify.model_objects.Paging;

import java.io.IOException;

public class NewReleasesRequest extends AbstractRequest {

  private NewReleasesRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Paging<AlbumSimplified> get() throws
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
    return new AlbumSimplified.JsonUtil().createModelObjectPaging(new JsonParser().parse(getJson()).getAsJsonObject().get("albums").getAsJsonObject());
  }

  public SettableFuture<Paging<AlbumSimplified>> getAsync() throws
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
    return getAsync(new AlbumSimplified.JsonUtil().createModelObjectPaging(new JsonParser().parse(getJson()).getAsJsonObject().get("albums").getAsJsonObject()));
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