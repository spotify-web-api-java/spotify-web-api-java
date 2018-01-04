package com.wrapper.spotify.requests.data.tracks;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetTrackRequest extends AbstractDataRequest {

  private GetTrackRequest(final Builder builder) {
    super(builder);
  }

  public Track execute() throws
          IOException,
          SpotifyWebApiException {
    return new Track.JsonUtil().createModelObject(getJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    @Override
    public GetTrackRequest build() {
      setPath("/v1/tracks/{id}");
      return new GetTrackRequest(this);
    }
  }
}
