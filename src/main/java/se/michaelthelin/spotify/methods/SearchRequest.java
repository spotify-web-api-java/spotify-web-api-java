package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.models.Album;
import se.michaelthelin.spotify.models.Artist;
import se.michaelthelin.spotify.models.Page;
import se.michaelthelin.spotify.models.Track;

import java.io.IOException;

public class SearchRequest extends AbstractRequest {

  protected SearchRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<Page<Album>> getAlbumsAsync() {
    SettableFuture<Page<Album>> searchResultFuture = SettableFuture.create();

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

  public SettableFuture<Page<Track>> getTracksAsync() {
    SettableFuture<Page<Track>> searchResultFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      searchResultFuture.set(JsonUtil.createTrackPage(jsonObject));
    } catch (IOException e) {
      searchResultFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public SettableFuture<Page<Artist>> getArtistsAsync() {
    SettableFuture<Page<Artist>> searchResultFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      searchResultFuture.set(JsonUtil.createArtistPage(jsonObject));
    } catch (IOException e) {
      searchResultFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public Page<Album> getAlbumsPage() throws IOException, UnexpectedResponseException {
    return JsonUtil.createAlbumPage(getJson());
  }

  public Page<Track> getTracksPage() throws IOException, UnexpectedResponseException {
    return JsonUtil.createTrackPage(getJson());
  }

  public Page<Artist> getArtistsPage() throws IOException, UnexpectedResponseException {
    return JsonUtil.createArtistPage(getJson());
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
