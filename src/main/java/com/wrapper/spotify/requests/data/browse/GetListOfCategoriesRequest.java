package com.wrapper.spotify.requests.data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Category;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetListOfCategoriesRequest extends AbstractDataRequest {

  private GetListOfCategoriesRequest(final Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public Paging<Category> execute() throws
          IOException,
          SpotifyWebApiException {
    return new Category.JsonUtil().createModelObjectPaging(getJson(), "categories");
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    /*
     * Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to narrow the list of returned categories to those relevant to a particular country. If omitted, the returned items will be globally relevant.
     */
    public Builder country(final CountryCode country) {
      assert (country != null);
      return setQueryParameter("country", country);
    }

    /*
     * Optional. The desired language, consisting of an ISO 639 language code and an ISO 3166-1 alpha-2 country
     * code, joined by an underscore. For example: es_MX, meaning "Spanish (Mexico)". Provide this parameter if
     * you want the category metadata returned in a particular language.
     *
     * Note that, if locale is not supplied, or if the specified language is not available, all strings will be
     * returned in the Spotify default language (American English).
     *
     * The locale parameter, combined with the country parameter, may give odd results if not carefully matched.
     * For example country=SE&locale=de_DE will return a list of categories relevant to Sweden but as German
     * language strings.
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
     * Optional. The maximum number of categories to return.
     *
     * @param limit Default: 20. Minimum: 1. Maximum: 50
     * @return {@link GetListOfCategoriesRequest.Builder}
     */
    public Builder limit(Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /*
     * Optional. The index of the first item to return.
     * Default: 0 (the first object). Use with limit to get the next set of categories.
     */
    public Builder offset(Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    @Override
    public GetListOfCategoriesRequest build() {
      setPath("/v1/browse/categories");
      return new GetListOfCategoriesRequest(this);
    }
  }
}
