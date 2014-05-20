package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.WebApiException;
import se.michaelthelin.spotify.models.Album;

import java.io.IOException;

public class AlbumRequest extends AbstractRequest {

  public AlbumRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<Album> getAsync() {
    SettableFuture<Album> albumFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);

      throwIfErrorsInResponse(jsonObject);

      albumFuture.set(JsonUtil.createAlbum(jsonString));
    } catch (Exception e) {
      albumFuture.setException(e);
    }

    return albumFuture;
  }

  public Album get() throws IOException, WebApiException {
    String jsonString = getJson();
    JSONObject jsonObject = JSONObject.fromObject(jsonString);

    throwIfErrorsInResponse(jsonObject);

    return JsonUtil.createAlbum(jsonString);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /**
     * The album with the given id.
     *
     * @param id The id for the album.
     * @return AlbumRequest
     */
    public Builder id(String id) {
      assert (id != null);
      return path(String.format("/v1/albums/%s", id));
    }

    public AlbumRequest build() {
      return new AlbumRequest(this);
    }

  }

}
