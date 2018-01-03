package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class ChangePlaylistsDetailsRequest extends AbstractDataRequest {

  private ChangePlaylistsDetailsRequest(final Builder builder) {
    super(builder);
  }

  public void put() throws
          IOException,
          SpotifyWebApiException {
    putJson();
  }

  public SettableFuture putAsync() throws
          IOException,
          SpotifyWebApiException {
    return executeAsync(putJson());
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

    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    public Builder name(final String name) {
      assert (name != null);
      assert (!name.equals(""));
      return setBodyParameter("name", name);
    }

    public Builder public_(final Boolean public_) {
      return setBodyParameter("public", public_);
    }

    public Builder collaborative(final Boolean collaborative) {
      return setBodyParameter("collaborative", collaborative);
    }

    public Builder description(final String description) {
      assert (description != null);
      assert (!description.equals(""));
      return setBodyParameter("name", description);
    }

    @Override
    public ChangePlaylistsDetailsRequest build() {
      setPath("/v1/users/{user_id}/playlists/{playlist_id}");
      return new ChangePlaylistsDetailsRequest(this);
    }
  }
}
