package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.AudioFeature;
import com.wrapper.spotify.models.Modality;
import com.wrapper.spotify.models.ObjectType;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import java.io.IOException;

public class AudioFeatureRequest extends AbstractRequest {

  public AudioFeatureRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<AudioFeature> getAsync() {
    final SettableFuture<AudioFeature> audioFeatureFuture = SettableFuture.create();

    try {
      JSONObject jsonObject = JSONObject.fromObject(getJson());
      audioFeatureFuture.set(JsonUtil.createAudioFeature(jsonObject));
    } catch (Exception e) {
      audioFeatureFuture.setException(e);
    }

    return audioFeatureFuture;
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
    JSONObject jsonObject = JSONObject.fromObject(getJson());
    return JsonUtil.createAudioFeature(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /**
     * The audio request with the given song id.
     *
     * @param id The id for the song.
     * @return AlbumRequest
     */
    public Builder id(String id) {
      assert (id != null);
      return setPath(String.format("/v1/audio-features/%s", id));
    }

    public AudioFeatureRequest build() {
      return new AudioFeatureRequest(this);
    }

  }

}
