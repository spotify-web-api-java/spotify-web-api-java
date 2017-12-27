package com.wrapper.spotify.requests.data.browse;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Category;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetCategoryRequest extends AbstractRequest {

  private GetCategoryRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Category get() throws
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
    return new Category.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<Category> getAsync() throws
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
    return executeAsync(new Category.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {
    public Builder category_id(final String category_id) {
      assert (category_id != null);
      return setPathParameter("category_id", category_id);
    }

    /**
     * Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to narrow the list of returned categories to those relevant to a particular country. If omitted, the returned items will be globally relevant.
     */
    public Builder country(final CountryCode country) {
      assert (country != null);
      return setQueryParameter("country", country);
    }

    /**
     * Optional. The desired language, consisting of an ISO 639 language code and an ISO 3166-1 alpha-2 country
     * code, joined by an underscore. For example: es_MX, meaning "Spanish (Mexico)". Provide this parameter if
     * you want the category metadata returned in a particular language.
     * <p>
     * Note that, if locale is not supplied, or if the specified language is not available, all strings will be
     * returned in the Spotify default language (American English).
     * <p>
     * The locale parameter, combined with the country parameter, may give odd results if not carefully matched.
     * For example country=SE&locale=de_DE will return a list of categories relevant to Sweden but as German
     * language strings.
     */
    public Builder locale(final LanguageCode languageCode, final CountryCode countryCode) {
      assert (languageCode != null);
      assert (countryCode != null);
      return setQueryParameter("locale", languageCode + "_" + countryCode);
    }

    @Override
    public GetCategoryRequest build() {
      setPath("/v1/browse/categories/{category_id}");
      return new GetCategoryRequest(this);
    }
  }
}
