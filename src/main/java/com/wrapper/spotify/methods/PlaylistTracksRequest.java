package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.Paging;
import com.wrapper.spotify.models.PlaylistTrack;
import net.sf.json.JSONObject;

import java.io.IOException;

public class PlaylistTracksRequest extends AbstractRequest {

  public PlaylistTracksRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<Paging<PlaylistTrack>> getAsync() {
    final SettableFuture<Paging<PlaylistTrack>> playlistFuture = SettableFuture.create();

    try {
      final JSONObject jsonObject = JSONObject.fromObject(getJson());
      playlistFuture.set(JsonUtil.createPlaylistTrackPage(jsonObject));
    } catch (Exception e) {
      playlistFuture.setException(e);
    }

    return playlistFuture;
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
    final JSONObject jsonObject = JSONObject.fromObject(getJson());

    return JsonUtil.createPlaylistTrackPage(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder fields(String fields) {
      assert (fields != null);
      return setParameter("fields", fields);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return setParameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return setParameter("offset", String.valueOf(offset));
    }

    public PlaylistTracksRequest build() {
      return new PlaylistTracksRequest(this);
    }

  }
}
