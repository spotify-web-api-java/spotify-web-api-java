package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.PlaylistTrack;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetPlaylistsTracksRequest extends AbstractDataRequest {

  private GetPlaylistsTracksRequest(final Builder builder) {
    super(builder);
  }

  public Paging<PlaylistTrack> get() throws
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
    return new PlaylistTrack.JsonUtil().createModelObjectPaging(getJson());
  }

  public SettableFuture<Paging<PlaylistTrack>> getAsync() throws
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
    return executeAsync(new PlaylistTrack.JsonUtil().createModelObjectPaging(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    public Builder fields(final String fields) {
      assert (fields != null);
      assert (!fields.equals(""));
      return setQueryParameter("fields", fields);
    }

    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 100);
      return setQueryParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    @Override
    public GetPlaylistsTracksRequest build() {
      setPath("/v1/users/{user_id}/playlists/{playlist_id}/tracks");
      return new GetPlaylistsTracksRequest(this);
    }
  }
}
