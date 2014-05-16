package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.models.Album;
import se.michaelthelin.spotify.models.Page;

import java.io.IOException;

public class AlbumSearchRequest extends AbstractRequest {

  protected AlbumSearchRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<Page<Album>> getAlbumsPageAsync() {
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

  public Page<Album> getAlbumsPage() throws IOException, UnexpectedResponseException {
    return JsonUtil.createAlbumPage(getJson());
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder query(String query) {
      assert (query != null);
      path("/v1/search");
      String massagedQuery = query.replace(" ", "+");
      parameter("type","album");
      return parameter("q", massagedQuery);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return parameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return parameter("offset", String.valueOf(offset));
    }

    public AlbumSearchRequest build() {
      return new AlbumSearchRequest(this);
    }

  }

}
