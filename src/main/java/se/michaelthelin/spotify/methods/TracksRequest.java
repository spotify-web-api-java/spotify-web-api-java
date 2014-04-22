package se.michaelthelin.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.SpotifyProtos;
import se.michaelthelin.spotify.SpotifyProtos.Track;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class TracksRequest extends AbstractRequest {

  public TracksRequest(Builder builder) {
    super(builder);
  }

  public ListenableFuture<List<Track>> getTracks() {
    ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    ListenableFuture<List<Track>> tracksFuture = service.submit(new Callable<List<Track>>() {
      @Override
      public List<Track> call() throws Exception {
        return JsonUtil.createTracks(getJson());
      }
    });
    return tracksFuture;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(String... ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids).toString();
      path("/v1/tracks");
      return parameter("ids", idsParameter);
    }

    public TracksRequest build() {
      return new TracksRequest(this);
    }

  }

}
