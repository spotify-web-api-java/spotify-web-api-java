package se.michaelthelin.spotify.methods;

import com.google.common.base.Joiner;
import se.michaelthelin.spotify.SpotifyProtos.AlbumType;

public final class AlbumRequest extends AbstractRequest {

  public AlbumRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /**
     * The album with the given id.
     *
     * @param id The id for the album.
     * @return AlbumRequest
     */
    public Builder id(String id) {
      assert (id != null);
      return path(String.format("/v1/albums/%s", id));
    }

    /**
     * The albums with the given ids.
     *
     * @param ids The ids for the albums.
     * @return AlbumRequest
     */
    public Builder id(String... ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids).toString();
      path("/v1/albums");
      return parameter("ids", idsParameter);
    }

    public Builder forArtist(String id) {
      assert (id != null);
      return path(String.format("/v1/artists/%s/albums", id));
    }

    public Builder types(AlbumType... types) {
      assert (types != null);
      assert (types.length > 0);
      String albumsParameter = Joiner.on(",").join(types).toString();
      return parameter("album_type", albumsParameter);
    }

    public AlbumRequest build() {
      return new AlbumRequest(this);
    }

  }

}
