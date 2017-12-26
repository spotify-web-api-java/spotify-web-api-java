package com.wrapper.spotify.requests.data.artists;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Track;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetArtistsTopTracksRequest extends AbstractRequest {

  private GetArtistsTopTracksRequest(final Builder builder) {
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
    return new Track.JsonUtil().createModelObjectArray(getJson(), "tracks");
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
    return getAsync(new Track.JsonUtil().createModelObjectArray(getJson(), "tracks"));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(final String id) {
      assert (id != null);
      return setPathParameter("id", id);
    }

    public Builder country(final CountryCode country) {
      assert (country != null);
      return setParameter("country", country.toString());
    }

    @Override
    public GetArtistsTopTracksRequest build() {
      setPath("/v1/artists/{id}/top-tracks");
      return new GetArtistsTopTracksRequest(this);
    }
  }
}
