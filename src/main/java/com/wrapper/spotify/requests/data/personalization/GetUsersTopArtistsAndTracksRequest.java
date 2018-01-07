package com.wrapper.spotify.requests.data.personalization;

import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import com.wrapper.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;

import java.io.IOException;

public class GetUsersTopArtistsAndTracksRequest<T extends IArtistTrackModelObject> extends AbstractDataRequest {

  private final AbstractModelObject.JsonUtil<T> tClass;

  private GetUsersTopArtistsAndTracksRequest(final Builder builder, final AbstractModelObject.JsonUtil<T> tClass) {
    super(builder);
    this.tClass = tClass;
  }

  @SuppressWarnings("unchecked")
  public Paging<T> execute() throws
          IOException,
          SpotifyWebApiException {
    return tClass.createModelObjectPaging(getJson());
  }

  public static final class Builder<T extends IArtistTrackModelObject> extends AbstractDataRequest.Builder<Builder<T>> {

    private AbstractModelObject.JsonUtil<T> tClass;

    public Builder(final String accessToken) {
      super(accessToken);
    }

    @SuppressWarnings("unchecked")
    public Builder type(final ModelObjectType type) {
      assert (type != null);
      assert (type.getType().equals("artists") || type.getType().equals("tracks"));

      switch (type.getType()) {
        case "artists":
          tClass = (AbstractModelObject.JsonUtil<T>) new Artist.JsonUtil();
          break;
        case "tracks":
          tClass = (AbstractModelObject.JsonUtil<T>) new Track.JsonUtil();
          break;
      }

      return setPathParameter("type", type.getType());
    }

    public Builder limit(final Integer limit) {
      assert (limit != null);
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    public Builder time_range(final String time_range) {
      assert (time_range != null);
      assert (time_range.equals("long_term") || time_range.equals("medium_term") || time_range.equals("short_term"));
      return setQueryParameter("time_range", time_range);
    }

    @Override
    public GetUsersTopArtistsAndTracksRequest<T> build() {
      setPath("/v1/me/top/{type}");
      return new GetUsersTopArtistsAndTracksRequest<>(this, tClass);
    }
  }
}
