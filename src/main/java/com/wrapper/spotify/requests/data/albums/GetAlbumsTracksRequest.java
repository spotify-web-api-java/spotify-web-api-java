package com.wrapper.spotify.requests.data.albums;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.TrackSimplified;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about an album's tracks. Optional parameters can be used to limit the number of tracks returned.
 */
public class GetAlbumsTracksRequest extends AbstractRequest {

  private GetAlbumsTracksRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Paging<TrackSimplified> get() throws
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
    return new TrackSimplified.JsonUtil().createModelObjectPaging(getJson());
  }

  public SettableFuture<Paging<TrackSimplified>> getAsync() throws
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
    return getAsync(new TrackSimplified.JsonUtil().createModelObjectPaging(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {
    /**
     * The id path parameter setter.
     *
     * @param id The Spotify ID for the album.
     * @return A GetAlbumsTracksRequest builder.
     */
    public Builder id(String id) {
      assert (id != null);
      return setPath(String.format("/v1/albums/%s/tracks", id));
    }

    /**
     * The limit query parameter setter.
     *
     * @param limit Optional. The maximum number of tracks to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A GetAlbumsTracksRequest builder.
     */
    public Builder limit(int limit) {
      assert (limit > 0);
      return setParameter("limit", String.valueOf(limit));
    }

    /**
     * The offset query parameter setter.
     *
     * @param offset Optional. The index of the first track to return. Default: 0 (the first object). Use with limit to get the next set of tracks.
     * @return A GetAlbumsTracksRequest builder.
     */
    public Builder offset(int offset) {
      assert (offset >= 0);
      return setParameter("offset", String.valueOf(offset));
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply Track Relinking.
     * @return A GetAlbumsTracksRequest builder.
     */
    public Builder market(CountryCode market) {
      assert (market != null);
      return setParameter("market", market.toString());
    }

    @Override
    public GetAlbumsTracksRequest build() {
      return new GetAlbumsTracksRequest(this);
    }
  }
}
