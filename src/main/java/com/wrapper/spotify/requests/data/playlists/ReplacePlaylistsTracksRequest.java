package com.wrapper.spotify.requests.data.playlists;

import com.google.gson.JsonArray;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class ReplacePlaylistsTracksRequest extends AbstractDataRequest {

  private ReplacePlaylistsTracksRequest(final Builder builder) {
    super(builder);
  }

  public String execute() throws
          IOException,
          SpotifyWebApiException {
    return putJson();
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

    public Builder uris(final String uris) {
      assert (uris != null);
      assert (!uris.equals(""));
      return setQueryParameter("uris", uris);
    }

    public Builder uris(final JsonArray uris) {
      assert (uris != null);
      assert (!uris.isJsonNull());
      assert (uris.size() <= 100);
      return setBodyParameter("uris", uris);
    }

    @Override
    public ReplacePlaylistsTracksRequest build() {
      setHeader("Content-Type", "application/json");
      setPath("/v1/users/{user_id}/playlists/{playlist_id}/tracks");
      return new ReplacePlaylistsTracksRequest(this);
    }
  }
}
