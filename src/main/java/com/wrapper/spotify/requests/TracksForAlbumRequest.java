package com.wrapper.spotify.methods;

import java.io.IOException;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleTrack;
import net.sf.json.JSONObject;


public class TracksForAlbumRequest extends AbstractRequest {

    public TracksForAlbumRequest(TracksForAlbumRequest.Builder builder) {
        super(builder);
    }

    public SettableFuture<Page<SimpleTrack>> getAsync() {
        SettableFuture<Page<SimpleTrack>> searchResultFuture = SettableFuture.create();

        try {
            final String jsonString = getJson();
            final JSONObject jsonObject = JSONObject.fromObject(jsonString);

            searchResultFuture.set(JsonUtil.createSimpleTrackPage(jsonObject));
        } catch (Exception e) {
            searchResultFuture.setException(e);
        }

        return searchResultFuture;
    }

    public Page<SimpleTrack> get() throws IOException, WebApiException {
        final String jsonString = getJson();
        final JSONObject jsonObject = JSONObject.fromObject(jsonString);

        return JsonUtil.createSimpleTrackPage(jsonObject);
    }

    public static TracksForAlbumRequest.Builder builder() {
        return new TracksForAlbumRequest.Builder();
    }

    public static final class Builder extends AbstractRequest.Builder<TracksForAlbumRequest.Builder> {

        public TracksForAlbumRequest.Builder forAlbum(String id) {
            assert (id != null);
            return path(String.format("/v1/albums/%s/tracks", id));
        }

        public TracksForAlbumRequest.Builder limit(int limit) {
            assert (limit > 0);
            return parameter("limit", String.valueOf(limit));
        }

        public TracksForAlbumRequest.Builder offset(int offset) {
            assert (offset >= 0);
            return parameter("offset", String.valueOf(offset));
        }

        public TracksForAlbumRequest build() {
            return new TracksForAlbumRequest(this);
        }

    }
}
