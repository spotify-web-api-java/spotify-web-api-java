package com.wrapper.spotify.requests.data.tracks;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.specification.AudioFeature;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetAudioFeaturesForSeveralTracksRequest extends AbstractDataRequest {

  private GetAudioFeaturesForSeveralTracksRequest(final Builder builder) {
    super(builder);
  }

  public AudioFeature[] get() throws
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
    return new AudioFeature.JsonUtil().createModelObjectArray(getJson(), "audio_features");
  }

  public SettableFuture<AudioFeature[]> getAsync() throws
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
    return executeAsync(new AudioFeature.JsonUtil().createModelObjectArray(getJson(), "audio_features"));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 100);
      return setQueryParameter("ids", ids);
    }

    @Override
    public GetAudioFeaturesForSeveralTracksRequest build() {
      setPath("/v1/audio-features");
      return new GetAudioFeaturesForSeveralTracksRequest(this);
    }
  }
}
