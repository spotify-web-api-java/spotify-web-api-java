package se.michaelthelin.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.BadFieldException;
import se.michaelthelin.spotify.exceptions.NotFoundException;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.models.Album;

import java.io.IOException;
import java.util.List;

public class AlbumsRequest extends AbstractRequest {

  public AlbumsRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<List<Album>> getAsync() {
    SettableFuture<List<Album>> albumsFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      if (errorInJson(jsonObject)) {
        Exception exception = getExceptionFromJson(jsonObject);
        albumsFuture.setException(exception);
      } else {
        albumsFuture.set(JsonUtil.createAlbums(getJson()));
      }
    } catch (IOException e) {
      albumsFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      albumsFuture.setException(e);
    }

    return albumsFuture;
  }

  public List<Album> get() throws IOException, UnexpectedResponseException, NotFoundException, BadFieldException {
    String jsonString = getJson();
    JSONObject jsonObject = JSONObject.fromObject(jsonString);
    throwIfErrorsInResponse(jsonObject);
    return JsonUtil.createAlbums(getJson());
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(List<String> ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids).toString();
      path("/v1/albums");
      return parameter("ids", idsParameter);
    }

    public AlbumsRequest build() {
      return new AlbumsRequest(this);
    }

  }
}
