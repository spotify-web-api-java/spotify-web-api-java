package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.LibraryTrack;
import com.wrapper.spotify.models.Paging;
import net.sf.json.JSONObject;

import java.io.IOException;

public class GetMySavedTracksRequest extends AbstractRequest {

  public GetMySavedTracksRequest(AbstractRequest.Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<Paging<LibraryTrack>> getAsync() {
    final SettableFuture<Paging<LibraryTrack>> libraryTracksFuture = SettableFuture.create();

    try {
      final JSONObject jsonObject = JSONObject.fromObject(getJson());
      libraryTracksFuture.set(JsonUtil.createLibraryTracksPage(jsonObject));
    } catch (Exception e) {
      libraryTracksFuture.setException(e);
    }

    return libraryTracksFuture;
  }

  public Paging<LibraryTrack> get() throws
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
    return JsonUtil.createLibraryTracksPage(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder accessToken(String accessToken) {
      return setHeaderParameter("Authorization", "Bearer " + accessToken);
    }

    public GetMySavedTracksRequest build() {
      return new GetMySavedTracksRequest(this);
    }

    public Builder limit(int limit) {
      assert (limit > 0);
      return setParameter("limit", String.valueOf(limit));
    }

    public Builder offset(int offset) {
      assert (offset >= 0);
      return setParameter("offset", String.valueOf(offset));
    }
  }

}
