package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.SpotifyProtos.Track;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class TrackRequest extends AbstractRequest {

  public TrackRequest(Builder builder) {
    super(builder);
  }

  public ListenableFuture<Track> getTrackAsync() {
    ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    ListenableFuture<Track> trackFuture = service.submit(new Callable<Track>() {
      public Track call() {
        return JsonUtil.createTrack(getJson());
      }
    });
    return trackFuture;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /**
     * The track with the given id.
     *
     * @param id The id for the track.
     * @return Track Request
     */
    public Builder id(String id) {
      assert (id != null);
      return path(String.format("/v1/tracks/%s", id));
    }

    public TrackRequest build() {
      return new TrackRequest(this);
    }

  }

}
