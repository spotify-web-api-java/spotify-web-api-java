package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.models.Page;
import se.michaelthelin.spotify.models.Track;

import java.io.IOException;

public class TrackSearchRequest extends AbstractRequest {

  public TrackSearchRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<Page<Track>> getAsync() {
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

  public Page<Track> get() throws IOException, UnexpectedResponseException {
    return JsonUtil.createTrackPage(getJson());
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder query(String query) {
      assert (query != null);
      path("/v1/search");
      parameter("type","track");
      return parameter("q", query);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return parameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return parameter("offset", String.valueOf(offset));
    }

    public TrackSearchRequest build() {
      return new TrackSearchRequest(this);
    }

  }

}
