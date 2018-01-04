package com.wrapper.spotify.requests.data.follow;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class UnfollowPlaylistRequest extends AbstractDataRequest {

  private UnfollowPlaylistRequest(final Builder builder) {
    super(builder);
  }

  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return deleteJson();
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder owner_id(final String owner_id) {
      assert (owner_id != null);
      assert (!owner_id.equals(""));
      return setPathParameter("owner_id", owner_id);
    }

    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    @Override
    public UnfollowPlaylistRequest build() {
      setPath("/v1/users/{owner_id}/playlists/{playlist_id}/followers");
      return new UnfollowPlaylistRequest(this);
    }
  }
}
