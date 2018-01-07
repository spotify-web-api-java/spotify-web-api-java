package com.wrapper.spotify.requests.data.playlists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetPlaylistRequest extends AbstractDataRequest {

  private GetPlaylistRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public Playlist execute() throws
          IOException,
          SpotifyWebApiException {
    return new Playlist.JsonUtil().createModelObject(getJson());
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

    public Builder fields(final String fields) {
      assert (fields != null);
      assert (!fields.equals(""));
      return setQueryParameter("fields", fields);
    }

    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    @Override
    public GetPlaylistRequest build() {
      setPath("/v1/users/{user_id}/playlists/{playlist_id}");
      return new GetPlaylistRequest(this);
    }
  }
}