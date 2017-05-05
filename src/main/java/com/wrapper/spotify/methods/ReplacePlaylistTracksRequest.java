package com.wrapper.spotify.methods;

import java.io.IOException;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.WebApiException;


public class ReplacePlaylistTracksRequest  extends AbstractRequest {
    public ReplacePlaylistTracksRequest(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public SettableFuture<String> getAsync() {
        final SettableFuture<String> replacePlaylistTracksFuture = SettableFuture.create();

        final String response;
        try {
            response = putJson();
            replacePlaylistTracksFuture.set(response);
        } catch (IOException e) {
            replacePlaylistTracksFuture.setException(e);
        } catch (WebApiException e) {
            replacePlaylistTracksFuture.setException(e);
        }

        return replacePlaylistTracksFuture;
    }

    public String get() throws IOException, WebApiException {
        return putJson();
    }

    public static final class Builder extends AbstractRequest.Builder<Builder> {

        public ReplacePlaylistTracksRequest build() {
            header("Content-Type", "application/json");
            return new ReplacePlaylistTracksRequest(this);
        }

    }
}
