package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetListOfUsersPlaylistsRequest extends AbstractDataRequest {

  private GetListOfUsersPlaylistsRequest(final Builder builder) {
    super(builder);
  }

  public Paging<PlaylistSimplified> get() throws
          IOException,
          SpotifyWebApiException {
    return new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson());
  }

  public SettableFuture<Paging<PlaylistSimplified>> getAsync() throws
          IOException,
          SpotifyWebApiException {
    return executeAsync(new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (0 <= offset && offset <= 100000);
      return setQueryParameter("offset", offset);
    }

    @Override
    public GetListOfUsersPlaylistsRequest build() {
      setPath("/v1/users/{user_id}/playlists");
      return new GetListOfUsersPlaylistsRequest(this);
    }
  }
}
