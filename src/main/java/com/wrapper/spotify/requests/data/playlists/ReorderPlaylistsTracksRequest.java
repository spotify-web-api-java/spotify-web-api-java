package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class ReorderPlaylistsTracksRequest extends AbstractDataRequest {

  private ReorderPlaylistsTracksRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public SnapshotResult execute() throws
          IOException,
          SpotifyWebApiException {
    return new SnapshotResult.JsonUtil().createModelObject(putJson());
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

    public Builder range_start(final Integer range_start) {
      assert (range_start != null);
      assert (range_start >= 0);
      return setBodyParameter("range_start", range_start);
    }

    public Builder range_length(final Integer range_length) {
      assert (range_length != null);
      assert (range_length >= 1);
      return setBodyParameter("range_length", range_length);
    }

    public Builder insert_before(final Integer insert_before) {
      assert (insert_before != null);
      assert (insert_before >= 0);
      return setBodyParameter("insert_before", insert_before);
    }

    public Builder snapshot_id(final String snapshot_id) {
      assert (snapshot_id != null);
      assert (!snapshot_id.equals(""));
      return setBodyParameter("snapshot_id", snapshot_id);
    }

    @Override
    public ReorderPlaylistsTracksRequest build() {
      setPath("/v1/users/{user_id}/playlists/{playlist_id}/tracks");
      return new ReorderPlaylistsTracksRequest(this);
    }
  }
}