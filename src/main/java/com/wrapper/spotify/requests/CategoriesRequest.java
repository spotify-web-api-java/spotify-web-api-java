package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Category;
import com.wrapper.spotify.models.Page;
import net.sf.json.JSONObject;

import java.io.IOException;

public class CategoriesRequest extends AbstractRequest {
    public CategoriesRequest(CategoriesRequest.Builder builder) {
        super(builder);
    }

    public SettableFuture<Page<Category>> getAsync() {
        SettableFuture<Page<Category>> searchResultFuture = SettableFuture.create();

        try {
            final String jsonString = getJson();
            final JSONObject jsonObject = JSONObject.fromObject(jsonString);

            searchResultFuture.set(JsonUtil.createCategoryPage(jsonObject));
        } catch (Exception e) {
            searchResultFuture.setException(e);
        }

        return searchResultFuture;
    }

    public Page<Category> get() throws IOException, WebApiException {
        final String jsonString = getJson();
        final JSONObject jsonObject = JSONObject.fromObject(jsonString);

        return JsonUtil.createCategoryPage(jsonObject);
    }

    public static CategoriesRequest.Builder builder() {
        return new CategoriesRequest.Builder();
    }

    public static final class Builder extends AbstractRequest.Builder<CategoriesRequest.Builder> {

        /*
         * Required. A valid access token from the Spotify Accounts service
         */
        public Builder accessToken(String accessToken) {
            return header("Authorization", "Bearer " + accessToken);
        }

        /*
         * Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to narrow the list of returned categories to those relevant to a particular country. If omitted, the returned items will be globally relevant.
         */
        public CategoriesRequest.Builder country(String country) {
            assert (country != null);
            return parameter("country", country);
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
        public CategoriesRequest.Builder locale(String locale) {
            assert (locale != null);
            return parameter("locale", locale);
        }

        /**
         * Optional. The maximum number of categories to return.
         * @param limit Default: 20. Minimum: 1. Maximum: 50
         * @return
         */
        public CategoriesRequest.Builder limit(int limit) {
            assert (limit > 0 && limit <= 50);
            return parameter("limit", String.valueOf(limit));
        }

        /*
         * Optional. The index of the first item to return.
         * Default: 0 (the first object). Use with limit to get the next set of categories.
         */
        public CategoriesRequest.Builder offset(int offset) {
            assert (offset >= 0);
            return parameter("offset", String.valueOf(offset));
        }

        public CategoriesRequest build() {
            path("/v1/browse/categories");
            return new CategoriesRequest(this);
        }

    }



}
