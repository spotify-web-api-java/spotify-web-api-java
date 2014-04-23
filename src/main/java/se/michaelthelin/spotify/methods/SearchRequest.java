package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.SpotifyProtos.AlbumSearchResult;
import se.michaelthelin.spotify.SpotifyProtos.ArtistSearchResult;
import se.michaelthelin.spotify.SpotifyProtos.TrackSearchResult;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class SearchRequest extends AbstractRequest {

  protected SearchRequest(Builder builder) {
    super(builder);
  }

  public ListenableFuture<AlbumSearchResult> getAlbumsAsync() {
    ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    ListenableFuture<AlbumSearchResult> albumSearchResultFuture = service.submit(new Callable<AlbumSearchResult>() {
      public AlbumSearchResult call() {
      return JsonUtil.createAlbumSearchResult(getJson());
      }
    });
    return albumSearchResultFuture;
  }

  public ListenableFuture<TrackSearchResult> getTracksAsync() {
    ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    ListenableFuture<TrackSearchResult> trackSearchResultFuture = service.submit(new Callable<TrackSearchResult>() {
      public TrackSearchResult call() {
      return JsonUtil.createTrackSearchResult(getJson());
      }
    });
    return trackSearchResultFuture;
  }

  public ListenableFuture<ArtistSearchResult> getArtistsAsync() {
    ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    ListenableFuture<ArtistSearchResult> artistSearchResultFuture = service.submit(new Callable<ArtistSearchResult>() {
      public ArtistSearchResult call() {
      return JsonUtil.createArtistSearchResult(getJson());
      }
    });
    return artistSearchResultFuture;
  }

  public AlbumSearchResult getAlbums() {
    return JsonUtil.createAlbumSearchResult(getJson());
  }

  public TrackSearchResult getTracks() {
    return JsonUtil.createTrackSearchResult(getJson());
  }

  public ArtistSearchResult getArtists() {
    return JsonUtil.createArtistSearchResult(getJson());
  }


  public static Builder builder() {
    return new Builder();
  }


  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder query(String query) {
      assert (query != null);
      path("/v1/search");
      String massagedQuery = query.replace(" ", "+");
      return parameter("q", massagedQuery);
    }

    public SearchRequest build() {
      return new SearchRequest(this);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return parameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return parameter("offset", String.valueOf(offset));
    }

    public Builder type(String type) {
      assert (type != null);
      return parameter("type", type);
    }

  }

}
