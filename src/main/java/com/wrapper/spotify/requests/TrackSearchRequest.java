package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.objects.model_objects.Paging;
import com.wrapper.spotify.objects.model_objects.Track;

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
    return new Track.JsonUtil().createModelObjectPaging(new JsonParser().parse(getJson()).getAsJsonObject().get("tracks").getAsJsonObject());
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
    return getAsync(new Track.JsonUtil().createModelObjectPaging(new JsonParser().parse(getJson()).getAsJsonObject().get("tracks").getAsJsonObject()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder query(final String query) {
      assert (query != null);
      setPath("/v1/search");
      setParameter("type", "track");
      return setParameter("q", query);
    }

    public Builder market(final String market) {
      assert (market != null);
      return setParameter("market", market);
    }

    public Builder limit(final int limit) {
      assert (limit > 0);
      return setParameter("limit", String.valueOf(limit));
    }

    public Builder offset(final int offset) {
      assert (offset >= 0);
      return setParameter("offset", String.valueOf(offset));
    }

    @Override
    public TrackSearchRequest build() {
      return new TrackSearchRequest(this);
    }

  }

}
