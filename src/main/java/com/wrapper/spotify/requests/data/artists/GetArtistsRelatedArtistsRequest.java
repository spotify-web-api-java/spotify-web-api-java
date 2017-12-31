package com.wrapper.spotify.requests.data.artists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetArtistsRelatedArtistsRequest extends AbstractDataRequest {

  private GetArtistsRelatedArtistsRequest(final Builder builder) {
    super(builder);
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
    return executeAsync(new Artist.JsonUtil().createModelObjectArray(getJson(), "artists"));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    @Override
    public GetArtistsRelatedArtistsRequest build() {
      setPath("/v1/artists/{id}/related-artists");
      return new GetArtistsRelatedArtistsRequest(this);
    }
  }
}
