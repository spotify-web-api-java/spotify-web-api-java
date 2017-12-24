package com.wrapper.spotify.methods;

import java.io.IOException;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.WebApiException;


public class PlaylistUnfollowRequest  extends AbstractRequest {

    public PlaylistUnfollowRequest(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public SettableFuture<String> getAsync() {
        final SettableFuture<String> removeFromMyTracksFuture = SettableFuture.create();

        final String response;
        try {
            response = deleteJson();
            removeFromMyTracksFuture.set(response);
        } catch (IOException e) {
            removeFromMyTracksFuture.setException(e);
        } catch (WebApiException e) {
            removeFromMyTracksFuture.setException(e);
        }

        return removeFromMyTracksFuture;
    }

    public String get() throws IOException, WebApiException {
        return deleteJson();
    }

    public static final class Builder extends AbstractRequest.Builder<Builder> {
        public PlaylistUnfollowRequest build() {
            return new PlaylistUnfollowRequest(this);
        }
    }
}
