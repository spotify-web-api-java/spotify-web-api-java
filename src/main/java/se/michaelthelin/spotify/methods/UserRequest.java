package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.*;
import se.michaelthelin.spotify.models.User;

import java.io.IOException;

public class UserRequest extends AbstractRequest {

  public UserRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<User> getAsync() {
    SettableFuture<User> userFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      if (errorInJson(jsonObject)) {
        Exception exception = getExceptionFromJson(jsonObject);
        userFuture.setException(exception);
      } else {
        userFuture.set(JsonUtil.createUser(jsonString));
      }
    } catch (IOException e) {
      userFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      userFuture.setException(e);
    } catch (NoCredentialsException e) {
      userFuture.setException(e);
    } catch (ErrorResponseException e) {
      userFuture.setException(e);
    }

    return userFuture;
  }

  public User get() throws IOException, UnexpectedResponseException, NotFoundException, BadFieldException, NoCredentialsException, ErrorResponseException {
    String jsonString = getJson();
    JSONObject jsonObject = JSONObject.fromObject(jsonString);
    throwIfErrorsInResponse(jsonObject);
    return JsonUtil.createUser(jsonString);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder username(String username) {
      assert (username!= null);
      return path(String.format("/v1/users/%s", username));
    }

    public UserRequest build() {
      return new UserRequest(this);
    }

  }
}
