package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.Artist;
import net.sf.json.JSONObject;

import java.io.IOException;

public class ArtistRequest extends AbstractRequest {

  protected ArtistRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<Artist> getAsync() {
    final SettableFuture<Artist> artistFuture = SettableFuture.create();

    try {
      final JSONObject jsonObject = JSONObject.fromObject(getJson());
      artistFuture.set(JsonUtil.createArtist(jsonObject));
    } catch (Exception e) {
      artistFuture.setException(e);
    }

    return artistFuture;
  }

  public Artist get() throws
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
    return JsonUtil.createArtist(JSONObject.fromObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public ArtistRequest build() {
      return new ArtistRequest(this);
    }

  }
}
