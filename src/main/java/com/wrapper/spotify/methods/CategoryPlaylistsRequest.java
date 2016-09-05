package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimplePlaylist;
import net.sf.json.JSONObject;

import java.io.IOException;

public class CategoryPlaylistsRequest extends AbstractRequest {
    public CategoryPlaylistsRequest(Builder builder) {
        super(builder);
    }

    public SettableFuture<Page<SimplePlaylist>> getAsync() {
        SettableFuture<Page<SimplePlaylist>> simplePlaylistsPageFuture = SettableFuture.create();

        try {
            final String jsonString = getJson();
            final JSONObject jsonObject = JSONObject.fromObject(jsonString);

            simplePlaylistsPageFuture.set(JsonUtil.createSimplePlaylistsPage(jsonObject));
        } catch (Exception e) {
            simplePlaylistsPageFuture.setException(e);
        }

        return simplePlaylistsPageFuture;
    }

    public Page<SimplePlaylist> get() throws IOException, WebApiException {
        final String jsonString = getJson();
        final JSONObject jsonObject = JSONObject.fromObject(jsonString);

        return JsonUtil.createSimplePlaylistsPage(jsonObject);
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractRequest.Builder<Builder> {

        public Builder category(String category) {
            assert (category != null);
            return path(String.format("/v1/browse/categories/%s/playlists", category));
        }

        /*
         * Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to narrow the
         * list of returned categories to those relevant to a particular country. If omitted, the returned items will
         * be globally relevant.
         */
        public Builder country(String country) {
            assert (country != null);
            return parameter("country", country);
        }

        public Builder limit(int limit) {
            assert (limit > 0);
            return parameter("limit", String.valueOf(limit));
        }

        public Builder offset(int offset) {
            assert (offset >= 0);
            return parameter("offset", String.valueOf(offset));
        }

        public Builder accessToken(String accessToken) {
            return header("Authorization", "Bearer " + accessToken);
        }

        public CategoryPlaylistsRequest build() {
            return new CategoryPlaylistsRequest(this);
        }

    }
}
