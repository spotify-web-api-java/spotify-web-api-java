package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.models.Track;

import java.io.IOException;

public class TrackRequest extends AbstractRequest {

  public TrackRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<Track> getAsync() {
    SettableFuture<Track> trackFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      if (errorInJson(jsonObject)) {
        Exception exception = getExceptionFromJson(jsonObject);
        trackFuture.setException(exception);
      } else {
        trackFuture.set(JsonUtil.createTrack(getJson()));
      }
    } catch (IOException e) {
      trackFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      trackFuture.setException(e);
    }

    return trackFuture;
  }

  public Track get() throws IOException, UnexpectedResponseException {
    return JsonUtil.createTrack(getJson());
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
