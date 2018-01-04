package com.wrapper.spotify.requests.data.player;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetInformationAboutUsersCurrentPlaybackRequest extends AbstractDataRequest {

  private GetInformationAboutUsersCurrentPlaybackRequest(final Builder builder) {
    super(builder);
  }

  public CurrentlyPlayingContext execute() throws
          IOException,
          SpotifyWebApiException {
    return new CurrentlyPlayingContext.JsonUtil().createModelObject(getJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    @Override
    public GetInformationAboutUsersCurrentPlaybackRequest build() {
      setPath("/v1/me/player");
      return new GetInformationAboutUsersCurrentPlaybackRequest(this);
    }
  }
}
