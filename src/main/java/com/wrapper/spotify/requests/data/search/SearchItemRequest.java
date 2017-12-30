package com.wrapper.spotify.requests.data.search;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.*;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import com.wrapper.spotify.requests.data.search.interfaces.ISearchModelObject;

import java.io.IOException;

public class SearchItemRequest<T extends ISearchModelObject> extends AbstractDataRequest {

  private AbstractModelObject.JsonUtil<T> tClass;

  private SearchItemRequest(final Builder builder, final AbstractModelObject.JsonUtil<T> tClass) {
    super(builder);
    this.tClass = tClass;
  }

  public Paging<T> get() throws
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
    return tClass.createModelObjectPaging(getJson());
  }

  public SettableFuture<Paging<T>> getAsync() throws
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
    return executeAsync(tClass.createModelObjectPaging(getJson()));
  }

  public static final class Builder<T extends ISearchModelObject> extends AbstractDataRequest.Builder<Builder<T>> {

    private AbstractModelObject.JsonUtil<T> tClass;

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder q(final String q) {
      assert (q != null);
      assert (!q.equals(""));
      return setQueryParameter("q", q);
    }

    public Builder type(final String type) {
      assert (type != null);
      assert (type.matches("((^|,)(album|artist|playlist|track))+$"));

      switch (type) {
        case "album":
          tClass = (AbstractModelObject.JsonUtil<T>) new AlbumSimplified.JsonUtil();
          break;
        case "artist":
          tClass = (AbstractModelObject.JsonUtil<T>) new Artist.JsonUtil();
          break;
        case "playlist":
          tClass = (AbstractModelObject.JsonUtil<T>) new PlaylistSimplified.JsonUtil();
          break;
        case "track":
          tClass = (AbstractModelObject.JsonUtil<T>) new Track.JsonUtil();
          break;
      }

      return setQueryParameter("type", type);
    }

    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    public Builder limit(final Integer limit) {
      assert (limit != null);
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset != null);
      assert (0 <= offset && offset <= 100000);
      return setQueryParameter("offset", offset);
    }

    @Override
    public SearchItemRequest<T> build() {
      setPath("/v1/search");
      return new SearchItemRequest<>(this, tClass);
    }
  }
}
