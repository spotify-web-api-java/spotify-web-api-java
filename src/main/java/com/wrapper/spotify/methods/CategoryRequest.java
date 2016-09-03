package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Category;
import net.sf.json.JSONObject;

import java.io.IOException;

public class CategoryRequest extends AbstractRequest {
    public CategoryRequest(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public SettableFuture<Category> getAsync() {
        SettableFuture<Category> genresFuture = SettableFuture.create();

        try {
            final String jsonString = getJson();
            final JSONObject jsonObject = JSONObject.fromObject(jsonString);

            genresFuture.set(JsonUtil.createCategory(jsonObject));
        } catch (Exception e) {
            genresFuture.setException(e);
        }

        return genresFuture;
    }

    public Category get() throws IOException, WebApiException {
        final String jsonString = getJson();
        final JSONObject jsonObject = JSONObject.fromObject(jsonString);

        return JsonUtil.createCategory(jsonObject);
    }

    public static final class Builder extends AbstractRequest.Builder<CategoryRequest.Builder> {

        public Builder forCategory(String categoryId) {
            assert (categoryId != null);
            return path(String.format("/v1/browse/categories/%s", categoryId));
        }
        /**
         * Required. A valid access token from the Spotify Accounts service
         */
        public Builder accessToken(String accessToken) {
            return header("Authorization", "Bearer " + accessToken);
        }

        /**
         * Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to narrow the list of returned categories to those relevant to a particular country. If omitted, the returned items will be globally relevant.
         */
        public Builder country(String country) {
            assert (country != null);
            return parameter("country", country);
        }

        /**
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
        public Builder locale(String locale) {
            assert (locale != null);
            return parameter("locale", locale);
        }

        public CategoryRequest build() {
            path("/v1/recommendations/available-genre-seeds");
            return new CategoryRequest(this);
        }

    }
}
