package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetArtistRequest extends AbstractDataRequest {

  private GetArtistRequest(final Builder builder) {
    super(builder);
  }

  public Artist execute() throws
          IOException,
          SpotifyWebApiException {
    return new Artist.JsonUtil().createModelObject(getJson());
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

    @Override
    public GetArtistRequest build() {
      setPath("/v1/artists/{id}");
      return new GetArtistRequest(this);
    }
  }
}
