package com.wrapper.spotify.requests.data.follow;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetUsersFollowedArtistsRequest extends AbstractDataRequest {

  private GetUsersFollowedArtistsRequest(final Builder builder) {
    super(builder);
  }

  public PagingCursorbased<Artist> get() throws
          IOException,
          SpotifyWebApiException {
    return new Artist.JsonUtil().createModelObjectPagingCursorbased(getJson(), "artists");
  }

  public SettableFuture<PagingCursorbased<Artist>> getAsync() throws
          IOException,
          SpotifyWebApiException {
    return executeAsync(new Artist.JsonUtil().createModelObjectPagingCursorbased(getJson(), "artists"));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder type(final ModelObjectType type) {
      assert (type != null);
      assert (type.getType().equals("artist"));
      return setQueryParameter("type", type);
    }

    public Builder limit(final Integer limit) {
      assert (limit != null);
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    public Builder after(final String after) {
      assert (after != null);
      return setQueryParameter("after", after);
    }

    @Override
    public GetUsersFollowedArtistsRequest build() {
      setPath("/v1/me/following");
      return new GetUsersFollowedArtistsRequest(this);
    }
  }
}
