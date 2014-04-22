package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.SpotifyProtos;
import se.michaelthelin.spotify.SpotifyProtos.Artist;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class ArtistRequest extends AbstractRequest {

  protected ArtistRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public ListenableFuture<Artist> getArtist() {
    ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    ListenableFuture<Artist> artistFuture = service.submit(new Callable<Artist>() {
      @Override
      public Artist call() throws Exception {
        return JsonUtil.createArtist(getJson());
      }
    });
    return artistFuture;
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
