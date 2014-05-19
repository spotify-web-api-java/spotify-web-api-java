package se.michaelthelin.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.exceptions.BadFieldException;
import se.michaelthelin.spotify.exceptions.NotFoundException;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.models.AlbumType;
import se.michaelthelin.spotify.models.Page;
import se.michaelthelin.spotify.models.SimpleAlbum;

import java.io.IOException;

public class AlbumsForArtistRequest extends AbstractRequest {

  public AlbumsForArtistRequest(Builder builder) {
    super(builder);
  }

  public SettableFuture<Page<SimpleAlbum>> getAsync() {
    SettableFuture<Page<SimpleAlbum>> searchResultFuture = SettableFuture.create();

    try {
      String jsonString = getJson();
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      searchResultFuture.set(JsonUtil.createSimpleAlbumPage(jsonObject));
    } catch (IOException e) {
      searchResultFuture.setException(e);
    } catch (UnexpectedResponseException e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public Page<SimpleAlbum> get() throws IOException, UnexpectedResponseException, NotFoundException, BadFieldException {
    return JsonUtil.createSimpleAlbumPage(getJson());
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder forArtist(String id) {
      assert (id != null);
      return path(String.format("/v1/artists/%s/albums", id));
    }

    public Builder types(AlbumType... types) {
      assert (types != null);
      assert (types.length > 0);
      String albumsParameter = Joiner.on(",").join(types).toString();
      return parameter("album_type", albumsParameter);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return parameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return parameter("offset", String.valueOf(offset));
    }

    public AlbumsForArtistRequest build() {
      return new AlbumsForArtistRequest(this);
    }

  }

}
