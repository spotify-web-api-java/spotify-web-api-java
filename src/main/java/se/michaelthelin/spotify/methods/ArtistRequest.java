package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.BadFieldException;
import se.michaelthelin.spotify.exceptions.NotFoundException;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.models.Artist;

import java.io.IOException;

public class ArtistRequest extends AbstractRequest {

  protected ArtistRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<Artist> getArtistAsync() {
    SettableFuture<Artist> artistFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      if (errorInJson(jsonObject)) {
        Exception exception = getExceptionFromJson(jsonObject);
        artistFuture.setException(exception);
      } else {
        artistFuture.set(JsonUtil.createArtist(getJson()));
      }
    } catch (IOException e) {
      artistFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      artistFuture.setException(e);
    }

    return artistFuture;
  }

  public Artist getArtist() throws IOException, UnexpectedResponseException, NotFoundException, BadFieldException {
    String jsonString = getJson();
    JSONObject jsonObject = JSONObject.fromObject(jsonString);
    throwIfErrorsInResponse(jsonObject);
    return JsonUtil.createArtist(getJson());
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /**
     * The artist with the given id.
     *
     * @param id The id for the artist.
     * @return ArtistRequest
     */
    public Builder id(String id) {
      assert (id != null);
      return path(String.format("/v1/artists/%s", id));
    }

    public ArtistRequest build() {
      return new ArtistRequest(this);
    }

  }
}
