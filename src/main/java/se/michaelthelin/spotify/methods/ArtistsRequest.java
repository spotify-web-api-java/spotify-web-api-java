package se.michaelthelin.spotify.methods;

import com.google.common.base.Joiner;
import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.SpotifyProtos.Artist;

import java.util.List;

public class ArtistsRequest extends AbstractRequest {

  protected ArtistsRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public List<Artist> getArtists() {
    return JsonUtil.createArtists(getJson());
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(String... ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids).toString();
      path("/v1/artists");
      return parameter("ids", idsParameter);
    }

    public ArtistsRequest build() {
      return new ArtistsRequest(this);
    }

  }
}
