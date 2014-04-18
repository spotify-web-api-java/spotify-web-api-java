package se.michaelthelin.spotify.methods;

import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.SpotifyProtos.Artist;

import java.util.List;

public class ArtistRequest extends AbstractRequest {

  protected ArtistRequest(Builder builder) {
    super(builder);
  }

  public Artist get() {
    return JsonUtil.newArtist(getJson());
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /**
     * The artist with the given id.
     *
     * @param id The id for the artist.
     * @return ArtistRequest
     */
    public Builder id(String id) {
      assert (id != null);
      return path(String.format("/v1/artists/%s", id));
    }

    public ArtistRequest build() {
      return new ArtistRequest(this);
    }

  }
}
