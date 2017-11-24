package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.AlbumSimplified;
import com.wrapper.spotify.models.AlbumType;
import com.wrapper.spotify.models.Paging;
import net.sf.json.JSONObject;

import java.io.IOException;

public class AlbumsForArtistRequest extends AbstractRequest {

  public AlbumsForArtistRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<Paging<AlbumSimplified>> getAsync() {
    final SettableFuture<Paging<AlbumSimplified>> searchResultFuture = SettableFuture.create();

    try {
      final JSONObject jsonObject = JSONObject.fromObject(getJson());
      searchResultFuture.set(JsonUtil.createSimpleAlbumPage(jsonObject));
    } catch (Exception e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public Paging<AlbumSimplified> get() throws
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
    return JsonUtil.createSimpleAlbumPage(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder forArtist(String id) {
      assert (id != null);
      return setPath(String.format("/v1/artists/%s/albums", id));
    }

    public Builder types(AlbumType... types) {
      assert (types != null);
      assert (types.length > 0);
      String albumsParameter = Joiner.on(",").join(types);
      return setParameter("album_type", albumsParameter);
    }

    public Builder market(String market) {
      assert (market != null);
      return setParameter("market", market);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return setParameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return setParameter("offset", String.valueOf(offset));
    }

    public AlbumsForArtistRequest build() {
      return new AlbumsForArtistRequest(this);
    }

  }

}
