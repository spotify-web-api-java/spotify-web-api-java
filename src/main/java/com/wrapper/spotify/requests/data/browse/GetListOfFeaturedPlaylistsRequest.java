package com.wrapper.spotify.requests.data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.FeaturedPlaylists;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;
import java.util.Date;

/**
 * Get a list of Spotify featured playlists (shown, for example, on a Spotify player’s "Browse" tab).
 */
public class GetListOfFeaturedPlaylistsRequest extends AbstractDataRequest {

  /**
   * The private {@link GetListOfFeaturedPlaylistsRequest} constructor.
   *
   * @param builder A {@link GetListOfFeaturedPlaylistsRequest.Builder}.
   */
  private GetListOfFeaturedPlaylistsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get {@link FeaturedPlaylists} synchronously.
   *
   * @return A {@link FeaturedPlaylists} object.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public FeaturedPlaylists execute() throws
          IOException,
          SpotifyWebApiException {
    return new FeaturedPlaylists.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetListOfFeaturedPlaylistsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetListOfFeaturedPlaylistsRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The language code setter.
     *
     * @param locale Optional. The desired language, consisting of an ISO 639 language code and an ISO 3166-1 alpha-2
     *               country code, joined by an underscore. For example: es_MX, meaning "Spanish (Mexico)". Provide this
     *               parameter if you want the category metadata returned in a particular language. Note that, if locale
     *               is not supplied, or if the specified language is not available, all strings will be returned in the
     *               Spotify default language (American English). The locale parameter, combined with the country
     *               parameter, may give odd results if not carefully matched. For example
     *               {@code country=SE&locale=de_DE} will return a list of categories relevant to Sweden but as German
     *               language strings.
     * @return A {@link GetListOfFeaturedPlaylistsRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     * @see <a href="https://en.wikipedia.org/wiki/ISO_639">Wikipedia: ISO 639 language code</a>
     */
    public Builder locale(final String locale) {
      assert (locale != null);
      assert (locale.contains("_"));
      String[] localeParts = locale.split("_");
      assert (localeParts.length == 2);
      assert (LanguageCode.getByCode(localeParts[0]) != null);
      assert (CountryCode.getByCode(localeParts[1]) != null);
      return setQueryParameter("locale", locale);
    }

    /**
     * The country code setter.
     *
     * @param country Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to
     *                narrow the list of returned categories to those relevant to a particular country. If omitted, the
     *                returned items will be globally relevant.
     * @return A {@link GetListOfFeaturedPlaylistsRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder country(final CountryCode country) {
      assert (country != null);
      return setQueryParameter("country", country);
    }

    /**
     * The timestamp setter.
     *
     * @param timestamp Optional. A timestamp in ISO 8601 format. Use this parameter to specify the user's local time to
     *                  get results tailored for that specific date and time in the day. If not provided, the response
     *                  defaults to the current UTC time.
     * @return A {@link GetListOfFeaturedPlaylistsRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_8601">Wikipedia: ISO 8601 timestamps</a>
     */
    public Builder timestamp(final Date timestamp) {
      assert (timestamp != null);
      return setQueryParameter("timestamp", SpotifyApi.formatDefaultDate(timestamp));
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetListOfFeaturedPlaylistsRequest.Builder}.
     */
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first item to return. Default: 0 (the first object). Use with
     *               {@link #limit(Integer)} to get the next set of items.
     * @return A {@link GetListOfFeaturedPlaylistsRequest.Builder}.
     */
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetListOfFeaturedPlaylistsRequest}.
     */
    @Override
    public GetListOfFeaturedPlaylistsRequest build() {
      setPath("/v1/browse/featured-playlists");
      return new GetListOfFeaturedPlaylistsRequest(this);
    }
  }
}
