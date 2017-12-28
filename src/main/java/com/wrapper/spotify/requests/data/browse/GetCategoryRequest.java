package com.wrapper.spotify.requests.data.browse;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Category;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetCategoryRequest extends AbstractDataRequest {

  private GetCategoryRequest(final Builder builder) {
    super(builder);
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

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder category_id(final String category_id) {
      assert (category_id != null);
      assert (category_id.matches("^[a-z]+$"));
      return setPathParameter("category_id", category_id);
    }

    /**
     * @param country Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to
     *                narrow the list of returned categories to those relevant to a particular country. If omitted, the
     *                returned items will be globally relevant.
     * @return {@link GetCategoryRequest.Builder}
     */
    public Builder country(final CountryCode country) {
      assert (country != null);
      return setQueryParameter("country", country);
    }

    /**
     * @param locale Optional. The desired language, consisting of an ISO 639 language code and an ISO 3166-1 alpha-2
     *               country code, joined by an underscore. For example: es_MX, meaning "Spanish (Mexico)". Provide this
     *               parameter if you want the category metadata returned in a particular language. Note that, if locale
     *               is not supplied, or if the specified language is not available, all strings will be returned in the
     *               Spotify default language (American English). The locale parameter, combined with the country
     *               parameter, may give odd results if not carefully matched. For example country=SE&amp;locale=de_DE
     *               will return a list of categories relevant to Sweden but as German language strings.
     * @return {@link GetCategoryRequest.Builder}
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

    @Override
    public GetCategoryRequest build() {
      setPath("/v1/browse/categories/{category_id}");
      return new GetCategoryRequest(this);
    }
  }
}
