package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.objects.AudioFeature;

import java.io.IOException;

public class AudioFeatureRequest extends AbstractRequest {

  private AudioFeatureRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public AudioFeature get() throws
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
    return new AudioFeature.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<AudioFeature> getAsync() throws
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
    return getAsync(new AudioFeature.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /**
     * The audio request with the given song id.
     *
     * @param id The id for the song.
     * @return AlbumRequest
     */
    public Builder id(final String id) {
      assert (id != null);
      return setPath(String.format("/v1/audio-features/%s", id));
    }

    @Override
    public AudioFeatureRequest build() {
      return new AudioFeatureRequest(this);
    }

  }

}
