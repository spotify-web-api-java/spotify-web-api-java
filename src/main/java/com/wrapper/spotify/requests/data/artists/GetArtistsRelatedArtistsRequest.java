package com.wrapper.spotify.requests.data.artists;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Artist;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class RelatedArtistsRequest extends AbstractRequest {

  private RelatedArtistsRequest(final Builder builder) {
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
    String t = getJson();
    return getAsync(new Artist.JsonUtil().createModelObjectArray(new JsonParser().parse(getJson()).getAsJsonObject().get("artists").getAsJsonArray()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    @Override
    public RelatedArtistsRequest build() {
      return new RelatedArtistsRequest(this);
    }

  }

}
