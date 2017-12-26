package com.wrapper.spotify.requests.data.artists;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Artist;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;
import java.util.List;

public class GetSeveralArtistsRequest extends AbstractRequest {

  private GetSeveralArtistsRequest(final Builder builder) {
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
    return new Artist.JsonUtil().createModelObjectArray(new JsonParser().parse(getJson()).getAsJsonObject().get("artists").getAsJsonArray());
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
    return getAsync(new Artist.JsonUtil().createModelObjectArray(new JsonParser().parse(getJson()).getAsJsonObject().get("artists").getAsJsonArray()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(final List<String> ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids);
      setPath("/v1/artists");
      return setParameter("ids", idsParameter);
    }

    @Override
    public GetSeveralArtistsRequest build() {
      return new GetSeveralArtistsRequest(this);
    }

  }
}
