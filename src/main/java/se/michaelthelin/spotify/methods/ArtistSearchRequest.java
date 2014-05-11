package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.models.Artist;
import se.michaelthelin.spotify.models.Page;

import java.io.IOException;

public class ArtistSearchRequest extends AbstractRequest {


  public ArtistSearchRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<Page<Artist>> getAsync() {
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

  public Page<Artist> get() throws IOException, UnexpectedResponseException {
    return JsonUtil.createArtistPage(getJson());
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder query(String query) {
      assert (query != null);
      path("/v1/artists/search");
      String massagedQuery = query.replace(" ", "+");
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

    public ArtistSearchRequest build() {
      return new ArtistSearchRequest(this);
    }

  }
}
