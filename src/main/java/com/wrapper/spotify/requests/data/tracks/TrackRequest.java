package com.wrapper.spotify.requests.data.tracks;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Track;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class TrackRequest extends AbstractRequest {

  private TrackRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Track get() throws
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
    return new Track.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<Track> getAsync() throws
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
    return getAsync(new Track.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /**
     * The track with the given id.
     *
     * @param id The id for the track.
     * @return Track Request
     */
    public Builder id(final String id) {
      assert (id != null);
      return setPath(String.format("/v1/tracks/%s", id));
    }

    @Override
    public TrackRequest build() {
      return new TrackRequest(this);
    }

  }

}
