package se.michaelthelin.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.SpotifyProtos.Track;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;

import java.io.IOException;
import java.util.List;

public class TracksRequest extends AbstractRequest {

  public TracksRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<List<Track>> getTracksAsync() {
    SettableFuture<List<Track>> tracksFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      if (errorInJson(jsonObject)) {
        Exception exception = getExceptionFromJson(jsonObject);
        tracksFuture.setException(exception);
      } else {
        tracksFuture.set(JsonUtil.createTracks(getJson()));
      }
    } catch (IOException e) {
      tracksFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      tracksFuture.setException(e);
    }

    return tracksFuture;
  }

  public List<Track> getTracks() throws IOException, UnexpectedResponseException {
    return JsonUtil.createTracks(getJson());
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
