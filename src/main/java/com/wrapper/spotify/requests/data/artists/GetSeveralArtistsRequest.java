package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetSeveralArtistsRequest extends AbstractDataRequest {

  private GetSeveralArtistsRequest(final Builder builder) {
    super(builder);
  }

  public Artist[] execute() throws
          IOException,
          SpotifyWebApiException {
    return new Artist.JsonUtil().createModelObjectArray(getJson(), "artists");
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    @Override
    public GetSeveralArtistsRequest build() {
      setPath("/v1/artists");
      return new GetSeveralArtistsRequest(this);
    }
  }
}
