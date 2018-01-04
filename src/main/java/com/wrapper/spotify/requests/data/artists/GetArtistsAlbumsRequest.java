package com.wrapper.spotify.requests.data.artists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetArtistsAlbumsRequest extends AbstractDataRequest {

  private GetArtistsAlbumsRequest(final Builder builder) {
    super(builder);
  }

  public Paging<AlbumSimplified> execute() throws
          IOException,
          SpotifyWebApiException {
    return new AlbumSimplified.JsonUtil().createModelObjectPaging(getJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    public Builder album_type(final String album_type) {
      assert (album_type != null);
      assert (album_type.matches("((^|,)(single|album|appears_on|compilation))+$"));
      return setQueryParameter("album_type", album_type);
    }

    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    @Override
    public GetArtistsAlbumsRequest build() {
      setPath("/v1/artists/{id}/albums");
      return new GetArtistsAlbumsRequest(this);
    }
  }
}
