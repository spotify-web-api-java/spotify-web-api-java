package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.WebApiException;

import java.io.IOException;

public class ReplaceTracksInPlaylistRequest extends AbstractRequest
{
    public ReplaceTracksInPlaylistRequest(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public SettableFuture<Boolean> getAsync() {
        final SettableFuture<Boolean> replaceTrackFuture = SettableFuture.create();

        try {
            final String jsonString = putJson();
            replaceTrackFuture.set("".equals(jsonString));
        } catch (Exception e) {
            replaceTrackFuture.setException(e);
        }

        return replaceTrackFuture;
    }

    public boolean get() throws IOException, WebApiException
    {
        return "".equals(putJson());
    }

    public static final class Builder extends AbstractRequest.Builder<Builder> {

        public ReplaceTracksInPlaylistRequest build() {
            header("Content-Type", "application/json");
            return new ReplaceTracksInPlaylistRequest(this);
        }

    }
}
