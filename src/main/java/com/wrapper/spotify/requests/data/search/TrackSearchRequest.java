package com.wrapper.spotify.requests.data.search;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.Track;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class TrackSearchRequest extends AbstractRequest {

  private TrackSearchRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Paging<Track> get() throws
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
    return new Track.JsonUtil().createModelObjectPaging(getJson(), "tracks");
  }

  public SettableFuture<Paging<Track>> getAsync() throws
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
    return executeAsync(new Track.JsonUtil().createModelObjectPaging(getJson(), "tracks"));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder query(final String query) {
      assert (query != null);
      setPath("/v1/search");
      setFormParameter("type", "track");
      return setFormParameter("q", query);
    }

    public Builder market(final String market) {
      assert (market != null);
      return setFormParameter("market", market);
    }

    public Builder limit(final Integer limit) {
      assert (limit > 0);
      return setFormParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setFormParameter("offset", offset);
    }

    @Override
    public TrackSearchRequest build() {
      return new TrackSearchRequest(this);
    }

  }

}
