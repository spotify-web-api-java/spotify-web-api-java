package com.wrapper.spotify.requests.data.tracks;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.AudioFeature;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetAudioFeaturesForTrackRequest extends AbstractDataRequest {

  private GetAudioFeaturesForTrackRequest(final Builder builder) {
    super(builder);
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
    return executeAsync(new AudioFeature.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

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
    public GetAudioFeaturesForTrackRequest build() {
      return new GetAudioFeaturesForTrackRequest(this);
    }

  }

}
