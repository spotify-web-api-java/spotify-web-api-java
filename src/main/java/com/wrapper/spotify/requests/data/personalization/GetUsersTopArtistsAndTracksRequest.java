package com.wrapper.spotify.requests.data.personalization;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.*;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import com.wrapper.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

public class GetUsersTopArtistsAndTracksRequest<T extends IArtistTrackModelObject> extends AbstractDataRequest {

  private AbstractModelObject.JsonUtil<T> tClass;

  private GetUsersTopArtistsAndTracksRequest(final Builder builder, final AbstractModelObject.JsonUtil<T> tClass) {
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

  public static final class Builder<T extends IArtistTrackModelObject> extends AbstractDataRequest.Builder<Builder<T>> {

    private AbstractModelObject.JsonUtil<T> tClass;

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder type(final ModelObjectType type) {
      assert (type != null);
      assert (type.getType().equals("artists") || type.getType().equals("tracks"));

      switch (type.getType()) {
        case "artists":
          tClass = (AbstractModelObject.JsonUtil<T>) new Artist.JsonUtil();
          break;
        case "tracks":
          tClass = (AbstractModelObject.JsonUtil<T>) new Track.JsonUtil();
          break;
      }

      return setPathParameter("type", type.getType());
    }

    public Builder limit(final Integer limit) {
      assert (limit != null);
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    public Builder time_range(final String time_range) {
      assert (time_range != null);
      assert (time_range.equals("long_term") || time_range.equals("medium_term") || time_range.equals("short_term"));
      return setQueryParameter("time_range", time_range);
    }

    @Override
    public GetUsersTopArtistsAndTracksRequest<T> build() {
      setPath("/v1/me/top/{type}");

      return new GetUsersTopArtistsAndTracksRequest<>(this, tClass);
    }
  }
}
