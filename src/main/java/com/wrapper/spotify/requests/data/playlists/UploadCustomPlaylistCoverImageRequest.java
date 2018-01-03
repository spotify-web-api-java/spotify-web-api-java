package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class UploadCustomPlaylistCoverImageRequest extends AbstractDataRequest {

  private UploadCustomPlaylistCoverImageRequest(final Builder builder) {
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

    public Builder image_data(final String image_data) {
      assert (image_data != null);
      assert (!image_data.equals(""));
      assert (image_data.getBytes().length <= 256000);
      return setBody(image_data);
    }

    @Override
    public UploadCustomPlaylistCoverImageRequest build() {
      setPath("/v1/users/{user_id}/playlists/{playlist_id}/images");
      return new UploadCustomPlaylistCoverImageRequest(this);
    }
  }
}