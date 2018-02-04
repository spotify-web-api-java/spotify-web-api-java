package com.wrapper.spotify.requests.data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Category;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a single category used to tag items in Spotify (on, for example, the Spotify player’s "Browse" tab).
 */
public class GetCategoryRequest extends AbstractDataRequest {

  /**
   * The private {@link GetCategoryRequest} constructor.
   *
   * @param builder A {@link GetCategoryRequest.Builder}.
   */
  private GetCategoryRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a {@link Category}.
   *
   * @return A {@link Category}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Category execute() throws
          IOException,
          SpotifyWebApiException {
    return new Category.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetCategoryRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetCategoryRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The category ID setter.
     *
     * @param category_id The Spotify category ID for the category.
     * @return A {@link GetCategoryRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder category_id(final String category_id) {
      assert (category_id != null);
      assert (category_id.matches("^[a-z]+$"));
      return setPathParameter("category_id", category_id);
    }

    /**
     * The country code setter.
     *
     * @param country Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to
     *                narrow the list of returned categories to those relevant to a particular country. If omitted, the
     *                returned items will be globally relevant.
     * @return A {@link GetCategoryRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder country(final CountryCode country) {
      assert (country != null);
      return setQueryParameter("country", country);
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
     * @return A {@link GetCategoryRequest.Builder}
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
     * The request build method.
     *
     * @return A custom {@link GetCategoryRequest}.
     */
    @Override
    public GetCategoryRequest build() {
      setPath("/v1/browse/categories/{category_id}");
      return new GetCategoryRequest(this);
    }
  }
}
