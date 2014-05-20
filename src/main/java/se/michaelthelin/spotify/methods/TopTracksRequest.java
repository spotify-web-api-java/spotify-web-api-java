package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.ErrorResponseException;
import se.michaelthelin.spotify.exceptions.NoCredentialsException;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.models.Track;

import java.io.IOException;
import java.util.List;

public class TopTracksRequest extends AbstractRequest {

  public TopTracksRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<List<Track>> getAsync() {
    SettableFuture<List<Track>> tracksFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      if (errorInJson(jsonObject)) {
        Exception exception = getExceptionFromJson(jsonObject);
        tracksFuture.setException(exception);
      } else {
        tracksFuture.set(JsonUtil.createTracks(jsonString));
      }
    } catch (IOException e) {
      tracksFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      tracksFuture.setException(e);
    } catch (NoCredentialsException e) {
      tracksFuture.setException(e);
    } catch (ErrorResponseException e) {
      tracksFuture.setException(e);
    }

    return tracksFuture;
  }

  public List<Track> get() throws IOException, UnexpectedResponseException, NoCredentialsException, ErrorResponseException {
    return JsonUtil.createTracks(getJson());
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(String id) {
      assert (id != null);
      return path(String.format("/v1/artists/%s/toptracks", id));
    }

    public Builder countryCode(String countryCode) {
      assert (countryCode != null);
      return parameter("country", countryCode);
    }

    public TopTracksRequest build() {
      return new TopTracksRequest(this);
    }

  }

}
