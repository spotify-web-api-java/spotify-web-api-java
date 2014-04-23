package se.michaelthelin.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.SpotifyProtos;
import se.michaelthelin.spotify.SpotifyProtos.Album;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class AlbumsRequest extends AbstractRequest {

  public AlbumsRequest(Builder builder) {
    super(builder);
  }

  public ListenableFuture<List<Album>> getAlbumsAsync() {
    ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    ListenableFuture<List<Album>> albumsFuture = service.submit(new Callable<List<Album>>() {
      public List<Album> call() {
        return JsonUtil.createAlbums(getJson());
      }
    });
    return albumsFuture;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

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

    public Builder types(SpotifyProtos.AlbumType... types) {
      assert (types != null);
      assert (types.length > 0);
      String albumsParameter = Joiner.on(",").join(types).toString();
      return parameter("album_type", albumsParameter);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return parameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return parameter("offset", String.valueOf(offset));
    }

    public AlbumsRequest build() {
      return new AlbumsRequest(this);
    }

  }
}
