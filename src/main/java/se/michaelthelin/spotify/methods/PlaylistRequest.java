package se.michaelthelin.spotify.methods;

public class PlaylistRequest extends AbstractRequest {

  public PlaylistRequest(Builder builder) {
   super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder username(String username) {
      assert (username != null);
      return path(String.format("/v1/users/%s/playlists", username));
    }

    public PlaylistRequest build() {
      return new PlaylistRequest(this);
    }

  }
}
