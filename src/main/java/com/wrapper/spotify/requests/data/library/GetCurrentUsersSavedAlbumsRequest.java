package com.wrapper.spotify.requests.data.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedAlbum;
import com.wrapper.spotify.requests.data.AbstractDataPagingRequest;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a list of the albums saved in the current Spotify user’s "Your Music" library.
 */
@JsonDeserialize(builder = GetCurrentUsersSavedAlbumsRequest.Builder.class)
public class GetCurrentUsersSavedAlbumsRequest extends AbstractDataRequest<Paging<SavedAlbum>> {

  /**
   * The private {@link GetCurrentUsersSavedAlbumsRequest} constructor.
   *
   * @param builder A {@link GetCurrentUsersSavedAlbumsRequest.Builder}.
   */
  private GetCurrentUsersSavedAlbumsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the saved albums of the current user.
   *
   * @return A {@link SavedAlbum} paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Paging<SavedAlbum> execute() throws
          IOException,
          SpotifyWebApiException {
    return new SavedAlbum.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetCurrentUsersSavedAlbumsRequest}.
   */
  public static final class Builder extends AbstractDataPagingRequest.Builder<SavedAlbum, Builder> {

    /**
     * Create a new {@link GetCurrentUsersSavedAlbumsRequest.Builder} instance.
     * <p>
     * The {@code user-library-read} scope must have been authorized by the user.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/web-api/using-scopes/">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of objects to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetCurrentUsersSavedAlbumsRequest.Builder}.
     */
    @Override
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first object to return. Default: 0 (i.e., the first object). Use with
     *               {@link #limit(Integer)} to get the next set of objects.
     * @return A {@link GetCurrentUsersSavedAlbumsRequest.Builder}.
     */
    @Override
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The market country code setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply
     *               Track Relinking.
     * @return A {@link GetCurrentUsersSavedAlbumsRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     * @see <a href="https://developer.spotify.com/web-api/track-relinking-guide/">Spotify: Track Relinking Guide</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetCurrentUsersSavedAlbumsRequest}.
     */
    @Override
    public GetCurrentUsersSavedAlbumsRequest build() {
      setPath("/v1/me/albums");
      return new GetCurrentUsersSavedAlbumsRequest(this);
    }
  }
}
