package se.michaelthelin.spotify.methods;

import se.michaelthelin.spotify.JsonUtil;
import se.michaelthelin.spotify.SpotifyProtos.AlbumSearchResult;
import se.michaelthelin.spotify.SpotifyProtos.ArtistSearchResult;
import se.michaelthelin.spotify.SpotifyProtos.TrackSearchResult;

public class SearchRequest extends AbstractRequest {

  protected SearchRequest(Builder builder) {
    super(builder);
  }

  public AlbumSearchResult getAlbums() {
    return JsonUtil.createAlbumSearchResult(getJson());
  }

  public TrackSearchResult getTracks() {
    return JsonUtil.createTrackSearchResult(getJson());
  }

  public ArtistSearchResult getArtists() {
    return JsonUtil.createArtistSearchResult(getJson());
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder query(String query) {
      assert (query != null);
      path("/v1/search");
      String massagedQuery = query.replace(" ", "+");
      return parameter("q", massagedQuery);
    }

    public SearchRequest build() {
      return new SearchRequest(this);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return parameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return parameter("offset", String.valueOf(offset));
    }

    public Builder type(String type) {
      assert (type != null);
      return parameter("type", type);
    }

  }

}
