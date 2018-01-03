package com.wrapper.spotify.requests.data.library;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedTrack;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetUsersSavedTracksRequest extends AbstractDataRequest {

  private GetUsersSavedTracksRequest(final Builder builder) {
    super(builder);
  }

  public Paging<SavedTrack> get() throws
          IOException,
          SpotifyWebApiException {
    return new SavedTrack.JsonUtil().createModelObjectPaging(getJson());
  }

  public SettableFuture<Paging<SavedTrack>> getAsync() throws
          IOException,
          SpotifyWebApiException {
    return executeAsync(new SavedTrack.JsonUtil().createModelObjectPaging(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    @Override
    public GetUsersSavedTracksRequest build() {
      setPath("/v1/me/tracks");
      return new GetUsersSavedTracksRequest(this);
    }
  }
}
