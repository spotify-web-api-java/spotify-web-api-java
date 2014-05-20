package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.*;
import se.michaelthelin.spotify.models.Page;
import se.michaelthelin.spotify.models.SimplePlaylist;

import java.io.IOException;

public class UserPlaylistsRequest extends AbstractRequest {

  public UserPlaylistsRequest(Builder builder) {
   super(builder);
  }

  public Page<SimplePlaylist> get() throws IOException, UnexpectedResponseException, NotFoundException, BadFieldException, NoCredentialsException, ErrorResponseException {
    String jsonString = getJson();
    JSONObject jsonObject = JSONObject.fromObject(jsonString);
    throwIfErrorsInResponse(jsonObject);
    return JsonUtil.createSimplePlaylistsPage(jsonObject);
  }

  public SettableFuture<Page<SimplePlaylist>> getAsync() {
    SettableFuture<Page<SimplePlaylist>> simplePlaylistsPageFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      if (errorInJson(jsonObject)) {
        Exception exception = getExceptionFromJson(jsonObject);
        simplePlaylistsPageFuture.setException(exception);
      } else {
        simplePlaylistsPageFuture.set(JsonUtil.createSimplePlaylistsPage(jsonObject));
      }
    } catch (IOException e) {
      simplePlaylistsPageFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      simplePlaylistsPageFuture.setException(e);
    } catch (NoCredentialsException e) {
      simplePlaylistsPageFuture.setException(e);
    } catch (ErrorResponseException e) {
      simplePlaylistsPageFuture.setException(e);
    }

    return simplePlaylistsPageFuture;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder username(String username) {
      assert (username != null);
      return path(String.format("/v1/users/%s/playlists", username));
    }

    public Builder accessToken(String accessToken) {
      return header("Authorization", "Bearer " + accessToken);
    }

    public UserPlaylistsRequest build() {
      return new UserPlaylistsRequest(this);
    }

  }
}
