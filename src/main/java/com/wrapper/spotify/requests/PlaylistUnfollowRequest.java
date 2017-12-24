package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Artist;

import java.io.IOException;

public class PlaylistUnfollowRequest extends AbstractRequest {

    private PlaylistUnfollowRequest(final Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String get() throws
            IOException,
            NoContentException,
            BadRequestException,
            UnauthorizedException,
            ForbiddenException,
            NotFoundException,
            TooManyRequestsException,
            InternalServerErrorException,
            BadGatewayException,
            ServiceUnavailableException {
        return getJson();
    }

    public SettableFuture<String> getAsync() throws
            IOException,
            NoContentException,
            BadRequestException,
            UnauthorizedException,
            ForbiddenException,
            NotFoundException,
            TooManyRequestsException,
            InternalServerErrorException,
            BadGatewayException,
            ServiceUnavailableException {
        return getAsync(getJson());
    }

    public static final class Builder extends AbstractRequest.Builder<Builder> {

        @Override
        public PlaylistUnfollowRequest build() {
            return new PlaylistUnfollowRequest(this);
        }

    }
}
