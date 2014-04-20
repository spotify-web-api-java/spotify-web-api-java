package se.michaelthelin.spotify.methods;

import com.google.common.base.Joiner;

public class ArtistRequest extends AbstractRequest {

  protected ArtistRequest(Builder builder) {
    super(builder);
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
