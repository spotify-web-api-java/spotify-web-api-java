package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.EmptyResponseException;
import se.michaelthelin.spotify.exceptions.NoCredentialsException;
import se.michaelthelin.spotify.exceptions.WebApiException;
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
      final String jsonString = getJson();
      final JSONObject jsonObject = JSONObject.fromObject(jsonString);

      throwIfErrorsInResponse(jsonObject);

      tracksFuture.set(JsonUtil.createTracks(jsonString));
    } catch (Exception e) {
      tracksFuture.setException(e);
    }

    return tracksFuture;
  }

  public List<Track> get() throws IOException, WebApiException {
    final String jsonString = getJson();
    final JSONObject jsonObject = JSONObject.fromObject(jsonString);

    throwIfErrorsInResponse(jsonObject);

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
