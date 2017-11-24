package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Paging;
import net.sf.json.JSONObject;

import java.io.IOException;

public class ArtistSearchRequest extends AbstractRequest {

  public ArtistSearchRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<Paging<Artist>> getAsync() {
    final SettableFuture<Paging<Artist>> searchResultFuture = SettableFuture.create();

    try {
      final JSONObject jsonObject = JSONObject.fromObject(getJson());
      searchResultFuture.set(JsonUtil.createArtistPage(jsonObject.getJSONObject("artists")));
    } catch (Exception e) {
      searchResultFuture.setException(e);
    }

    return searchResultFuture;
  }

  public Paging<Artist> get() throws
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
    return JsonUtil.createArtistPage(jsonObject.getJSONObject("artists"));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder query(String query) {
      assert (query != null);
      setPath("/v1/search");
      setParameter("type", "artist");
      return setParameter("q", query);
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

    public ArtistSearchRequest build() {
      return new ArtistSearchRequest(this);
    }

  }
}
