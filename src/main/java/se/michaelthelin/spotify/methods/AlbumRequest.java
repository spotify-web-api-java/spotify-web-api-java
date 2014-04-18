package se.michaelthelin.spotify.methods;

public final class AlbumRequest extends AbstractRequest {

  public AlbumRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder {

    /**
     * The album with the given id.
     *
     * @param id The id for the album.
     * @return AlbumRequest
     */
    public Builder id(String id) {
      assert (id != null);
      path(String.format("/albums/%s", id));
      return this;
    }

    public AlbumRequest build() {
      return new AlbumRequest(this);
    }

  }

}
