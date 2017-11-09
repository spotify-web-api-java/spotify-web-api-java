package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.RecentlyPlayedTrack;
import com.wrapper.spotify.models.Track;

import net.sf.json.JSONObject;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;

import java.io.IOException;

import net.sf.json.JSONObject;

public class RecentlyPlayedTracksRequest extends AbstractRequest {
    public RecentlyPlayedTracksRequest(RecentlyPlayedTracksRequest.Builder builder) {
        super(builder);
    }

    public SettableFuture<Page<RecentlyPlayedTrack>> getAsync() {
        final SettableFuture<Page<RecentlyPlayedTrack>> trackPageFuture = SettableFuture.create();

        try {
            final JSONObject jsonObject = JSONObject.fromObject(getJson());

            trackPageFuture.set(JsonUtil.createRecentlyPlayedTrackPage(jsonObject));
        } catch (Exception e) {
            trackPageFuture.setException(e);
        }

        return trackPageFuture;
    }

    public Page<RecentlyPlayedTrack> get() throws IOException, WebApiException {
        JSONObject jsonObject = JSONObject.fromObject(getJson());

        return JsonUtil.createRecentlyPlayedTrackPage(jsonObject);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractRequest.Builder<RecentlyPlayedTracksRequest.Builder> {

        public RecentlyPlayedTracksRequest.Builder accessToken(String accessToken) {
            return header("Authorization", "Bearer " + accessToken);
        }

        public RecentlyPlayedTracksRequest build() {
            path("/v1/me/player/recently-played");
            return new RecentlyPlayedTracksRequest(this);
        }

        public RecentlyPlayedTracksRequest build(String after) {
            path("/v1/me/player/recently-played");
            parameter("after", after);
            return new RecentlyPlayedTracksRequest(this);
        }
    }
}
