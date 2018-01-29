package com.wrapper.spotify.requests.data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.FeaturedPlaylists;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;
import java.util.Date;

public class GetListOfFeaturedPlaylistsRequest extends AbstractDataRequest {

  private GetListOfFeaturedPlaylistsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get Featured Playlists synchronously.
   *
   * @return Featured playlists.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public FeaturedPlaylists execute() throws
          IOException,
          SpotifyWebApiException {
    return new FeaturedPlaylists.JsonUtil().createModelObject(getJson());
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder locale(final String locale) {
      assert (locale != null);
      assert (locale.contains("_"));
      String[] localeParts = locale.split("_");
      assert (localeParts.length == 2);
      assert (LanguageCode.getByCode(localeParts[0]) != null);
      assert (CountryCode.getByCode(localeParts[1]) != null);
      return setQueryParameter("locale", locale);
    }

    public Builder country(final CountryCode country) {
      assert (country != null);
      return setQueryParameter("country", country);
    }

    public Builder timestamp(final Date timestamp) {
      assert (timestamp != null);
      return setQueryParameter("timestamp", SpotifyApi.SIMPLE_DATE_FORMAT.format(timestamp));
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
    public GetListOfFeaturedPlaylistsRequest build() {
      setPath("/v1/browse/featured-playlists");
      return new GetListOfFeaturedPlaylistsRequest(this);
    }
  }
}
