package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * @author jonas on 03/09/16.
 */
public class AvailableGenreSeedsRequest extends AbstractRequest {

    public AvailableGenreSeedsRequest(AvailableGenreSeedsRequest.Builder builder) {
        super(builder);
    }

    public static AvailableGenreSeedsRequest.Builder builder() {
        return new AvailableGenreSeedsRequest.Builder();
    }

    public SettableFuture<List<String>> getAsync() {
        SettableFuture<List<String>> genresFuture = SettableFuture.create();

        try {
            final String jsonString = getJson();
            final JSONObject jsonObject = JSONObject.fromObject(jsonString);

            genresFuture.set(JsonUtil.createGenres(jsonObject));
        } catch (Exception e) {
            genresFuture.setException(e);
        }

        return genresFuture;
    }

    public List<String> get() throws IOException, WebApiException {
        final String jsonString = getJson();
        final JSONObject jsonObject = JSONObject.fromObject(jsonString);

        return JsonUtil.createGenres(jsonObject);
    }

    public static final class Builder extends AbstractRequest.Builder<AvailableGenreSeedsRequest.Builder> {

        /**
         * Required. A valid access token from the Spotify Accounts service
         */
        public Builder accessToken(String accessToken) {
            return header("Authorization", "Bearer " + accessToken);
        }

        public AvailableGenreSeedsRequest build() {
            path("/v1/recommendations/available-genre-seeds");
            return new AvailableGenreSeedsRequest(this);
        }

    }
}
