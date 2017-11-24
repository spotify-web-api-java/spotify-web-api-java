package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.Paging;
import com.wrapper.spotify.models.PlaylistSimplified;
import net.sf.json.JSONObject;

import java.io.IOException;

public class UserPlaylistsRequest extends AbstractRequest {

  public UserPlaylistsRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<Paging<PlaylistSimplified>> getAsync() {
    final SettableFuture<Paging<PlaylistSimplified>> simplePlaylistsPageFuture = SettableFuture.create();

    try {
      final JSONObject jsonObject = JSONObject.fromObject(getJson());
      simplePlaylistsPageFuture.set(JsonUtil.createSimplePlaylistsPage(jsonObject));
    } catch (Exception e) {
      simplePlaylistsPageFuture.setException(e);
    }

    return simplePlaylistsPageFuture;
  }

  public Paging<PlaylistSimplified> get() throws
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
    return JsonUtil.createSimplePlaylistsPage(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder username(String username) {
      assert (username != null);
      return setPath(String.format("/v1/users/%s/playlists", username));
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return setParameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return setParameter("offset", String.valueOf(offset));
    }

    public Builder accessToken(String accessToken) {
      return setHeaderParameter("Authorization", "Bearer " + accessToken);
    }

    public UserPlaylistsRequest build() {
      return new UserPlaylistsRequest(this);
    }

  }
}
