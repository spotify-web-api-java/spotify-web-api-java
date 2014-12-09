package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.FeaturedPlaylists;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Playlist;
import com.wrapper.spotify.models.SimplePlaylist;
import net.sf.json.JSONObject;

import java.io.IOException;

public class FeaturedPlaylistsRequest extends AbstractRequest {

    public FeaturedPlaylistsRequest(Builder builder) {
        super(builder);
    }

    public FeaturedPlaylists get() throws IOException, WebApiException {
        final String jsonString = getJson();
        final JSONObject jsonObject = JSONObject.fromObject(jsonString);

        FeaturedPlaylists featuredPlaylists = new FeaturedPlaylists();
        featuredPlaylists.setMessage(jsonObject.getString("message"));
        featuredPlaylists.setPlaylists(JsonUtil.createPlaylistPage(jsonObject.getJSONObject("playlists")));

        return featuredPlaylists;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractRequest.Builder<Builder> {

        public Builder limit(int limit) {
            assert (limit > 0);
            return parameter("limit", String.valueOf(limit));
        }

        public Builder offset(int offset) {
            assert (offset >= 0);
            return parameter("offset", String.valueOf(offset));
        }

        public Builder countryCode(String countryCode) {
            assert (countryCode != null);
            return parameter("country", countryCode);
        }

        public Builder locale(String locale) {
            assert (locale != null);
            return parameter("locale", locale);
        }

        /* todo: should probably be a date object */
        public Builder timestamp(String timestamp) {
            assert (timestamp != null);
            return parameter("timestamp", timestamp);
        }

        public Builder accessToken(String accessToken) {
            return header("Authorization", "Bearer " + accessToken);
        }

        public FeaturedPlaylistsRequest build() {
            path("/v1/browse/featured-playlists");
            return new FeaturedPlaylistsRequest(this);
        }

    }
}
