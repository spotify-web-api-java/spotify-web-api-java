package com.wrapper.spotify.requests.data.albums;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Album;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetAlbumRequest extends AbstractRequest {

  private GetAlbumRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Album get() throws
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
    return new Album.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<Album> getAsync() throws
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
    return getAsync(new Album.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {
    /**
     * The album with the given id.
     *
     * @param id The id for the album.
     * @return AlbumRequest
     */
    public Builder id(final String id) {
      assert (id != null);
      return setPath(String.format("/v1/albums/%s", id));
    }

    @Override
    public GetAlbumRequest build() {
      return new GetAlbumRequest(this);
    }
  }
}
