package se.michaelthelin.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.SpotifyProtos.Artist;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class ArtistsRequest extends AbstractRequest {

  protected ArtistsRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public ListenableFuture<List<Artist>> getArtistsAsync() {
    ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    ListenableFuture<List<Artist>> artistsFuture = service.submit(new Callable<List<Artist>>() {
      @Override
      public List<Artist> call() throws Exception {
      return JsonUtil.createArtists(getJson());
      }
    });
    return artistsFuture;
  }

  public List<Artist> getArtists() {
    return JsonUtil.createArtists(getJson());
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(String... ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids).toString();
      path("/v1/artists");
      return parameter("ids", idsParameter);
    }

    public ArtistsRequest build() {
      return new ArtistsRequest(this);
    }

  }
}
