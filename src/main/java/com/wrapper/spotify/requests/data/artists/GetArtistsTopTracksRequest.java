package com.wrapper.spotify.requests.data.artists;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetArtistsTopTracksRequest extends AbstractDataRequest {

  private GetArtistsTopTracksRequest(final Builder builder) {
    super(builder);
  }

  public Track[] get() throws
          IOException,
          SpotifyWebApiException {
    return new Track.JsonUtil().createModelObjectArray(getJson(), "tracks");
  }

  public SettableFuture<Track[]> getAsync() throws
          IOException,
          SpotifyWebApiException {
    return executeAsync(new Track.JsonUtil().createModelObjectArray(getJson(), "tracks"));
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

    public Builder country(final CountryCode country) {
      assert (country != null);
      return setQueryParameter("country", country);
    }

    @Override
    public GetArtistsTopTracksRequest build() {
      setPath("/v1/artists/{id}/top-tracks");
      return new GetArtistsTopTracksRequest(this);
    }
  }
}
