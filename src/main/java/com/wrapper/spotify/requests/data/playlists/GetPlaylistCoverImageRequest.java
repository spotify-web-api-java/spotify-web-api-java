package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Image;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetPlaylistCoverImageRequest extends AbstractDataRequest {

  private GetPlaylistCoverImageRequest(final Builder builder) {
    super(builder);
  }

  public Image[] get() throws
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
    return new Image.JsonUtil().createModelObjectArray(getJson());
  }

  public SettableFuture<Image[]> getAsync() throws
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
    return executeAsync(new Image.JsonUtil().createModelObjectArray(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    @Override
    public GetPlaylistCoverImageRequest build() {
      setPath("/v1/users/{user_id}/playlists/{playlist_id}/images");
      return new GetPlaylistCoverImageRequest(this);
    }
  }
}