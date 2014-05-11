package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.SpotifyProtos.AlbumSearchResult;
import se.michaelthelin.spotify.SpotifyProtos.ArtistSearchResult;
import se.michaelthelin.spotify.SpotifyProtos.TrackSearchResult;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;

import java.io.IOException;

public class SearchRequest extends AbstractRequest {

  protected SearchRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<AlbumSearchResult> getAlbumsAsync() {
    SettableFuture<AlbumSearchResult> searchResultFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      searchResultFuture.set(JsonUtil.createAlbumPage(jsonObject));
    } catch (IOException e) {
      searchResultFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public SettableFuture<TrackSearchResult> getTracksAsync() {
    SettableFuture<TrackSearchResult> searchResultFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      searchResultFuture.set(JsonUtil.createTrackSearchResult(jsonObject));
    } catch (IOException e) {
      searchResultFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public SettableFuture<ArtistSearchResult> getArtistsAsync() {
    SettableFuture<ArtistSearchResult> searchResultFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      searchResultFuture.set(JsonUtil.createArtistSearchResult(jsonObject));
    } catch (IOException e) {
      searchResultFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public AlbumSearchResult getAlbums() throws IOException, UnexpectedResponseException {
    return JsonUtil.createAlbumPage(getJson());
  }

  public TrackSearchResult getTracks() throws IOException, UnexpectedResponseException {
    return JsonUtil.createTrackSearchResult(getJson());
  }

  public ArtistSearchResult getArtists() throws IOException, UnexpectedResponseException {
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
