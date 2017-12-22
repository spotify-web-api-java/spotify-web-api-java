package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.CurrentlyPlayingTrack;
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

public class CurrentlyPlayingTrackRequest extends AbstractRequest {
    public CurrentlyPlayingTrackRequest(CurrentlyPlayingTrackRequest.Builder builder) {
        super(builder);
    }

    public SettableFuture<CurrentlyPlayingTrack> getAsync() {
        final SettableFuture<CurrentlyPlayingTrack> trackFuture = SettableFuture.create();

        try {
            final JSONObject jsonObject = JSONObject.fromObject(getJson());

            trackFuture.set(JsonUtil.createCurrentlyPlayingTrack(jsonObject));
        } catch (Exception e) {
            trackFuture.setException(e);
        }

        return trackFuture;
    }

    public CurrentlyPlayingTrack get() throws IOException, WebApiException {
        JSONObject jsonObject = JSONObject.fromObject(getJson());

        return JsonUtil.createCurrentlyPlayingTrack(jsonObject);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractRequest.Builder<CurrentlyPlayingTrackRequest.Builder> {

        public CurrentlyPlayingTrackRequest.Builder accessToken(String accessToken) {
            return header("Authorization", "Bearer " + accessToken);
        }

        public CurrentlyPlayingTrackRequest build() {
            path("/v1/me/player/currently-playing");
            return new CurrentlyPlayingTrackRequest(this);
        }

    }
}
