package com.wrapper.spotify.requests.data.browse;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Category;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetListOfCategoriesRequest extends AbstractRequest {

  private GetListOfCategoriesRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Paging<Category> get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return new Category.JsonUtil().createModelObjectPaging(getJson(), "categories");
  }

  public SettableFuture<Paging<Category>> getAsync() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return executeAsync(new Category.JsonUtil().createModelObjectPaging(getJson(), "categories"));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /*
     * Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to narrow the list of returned categories to those relevant to a particular country. If omitted, the returned items will be globally relevant.
     */
    public Builder country(CountryCode country) {
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
    public Builder locale(final LanguageCode languageCode, final CountryCode countryCode) {
      assert (languageCode != null);
      assert (countryCode != null);
      return setQueryParameter("locale", languageCode + "_" + countryCode);
    }

    /**
     * Optional. The maximum number of categories to return.
     *
     * @param limit Default: 20. Minimum: 1. Maximum: 50
     * @return
     */
    public Builder limit(Integer limit) {
      assert (limit > 0 && limit <= 50);
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
