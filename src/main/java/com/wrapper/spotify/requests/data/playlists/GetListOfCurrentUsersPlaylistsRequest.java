package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetListOfCurrentUsersPlaylistsRequest extends AbstractDataRequest {

  private GetListOfCurrentUsersPlaylistsRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public Paging<PlaylistSimplified> execute() throws
          IOException,
          SpotifyWebApiException {
    return new PlaylistSimplified.JsonUtil().createModelObjectPaging(getJson());
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
      assert (0 <= offset && offset >= 100000);
      return setQueryParameter("offset", offset);
    }

    @Override
    public GetListOfCurrentUsersPlaylistsRequest build() {
      setPath("/v1/me/playlists");
      return new GetListOfCurrentUsersPlaylistsRequest(this);
    }
  }
}
