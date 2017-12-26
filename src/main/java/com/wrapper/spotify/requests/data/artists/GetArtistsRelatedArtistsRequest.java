package com.wrapper.spotify.requests.data.artists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Artist;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetArtistsRelatedArtistsRequest extends AbstractRequest {

  private GetArtistsRelatedArtistsRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Artist[] get() throws
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
    return new Artist.JsonUtil().createModelObjectArray(getJson(), "artists");
  }

  public SettableFuture<Artist[]> getAsync() throws
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
    return getAsync(new Artist.JsonUtil().createModelObjectArray(getJson(), "artists"));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(final String id) {
      assert (id != null);
      return setPathParameter("id", id);
    }

    @Override
    public GetArtistsRelatedArtistsRequest build() {
      setPath("/v1/artists/{id}/related-artists");
      return new GetArtistsRelatedArtistsRequest(this);
    }
  }
}
