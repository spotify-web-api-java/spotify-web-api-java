package com.wrapper.spotify.requests.data.albums;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.TrackSimplified;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about an album's tracks. Optional parameters can be used to limit the number of
 * tracks returned.
 */
public class GetAlbumsTracksRequest extends AbstractDataRequest {

  private GetAlbumsTracksRequest(final Builder builder) {
    super(builder);
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
    return executeAsync(new TrackSimplified.JsonUtil().createModelObjectPaging(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The id path parameter setter.
     *
     * @param id The Spotify ID for the album.
     * @return A GetAlbumsTracksRequest builder.
     */
    public Builder id(final String id) {
      assert (id != null);
      return setPathParameter("id", id);
    }

    /**
     * The limit query parameter setter.
     *
     * @param limit Optional. The maximum number of tracks to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A GetAlbumsTracksRequest builder.
     */
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset query parameter setter.
     *
     * @param offset Optional. The index of the first track to return. Default: 0 (the first object). Use with limit to
     *               get the next set of tracks.
     * @return A GetAlbumsTracksRequest builder.
     */
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply Track
     *               Relinking.
     * @return A GetAlbumsTracksRequest builder.
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    @Override
    public GetAlbumsTracksRequest build() {
      setPath("/v1/albums/{id}/tracks");
      return new GetAlbumsTracksRequest(this);
    }
  }
}
