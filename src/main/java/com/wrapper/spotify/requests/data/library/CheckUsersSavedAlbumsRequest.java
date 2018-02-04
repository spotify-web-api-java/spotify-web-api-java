package com.wrapper.spotify.requests.data.library;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Check if one or more albums is already saved in the current Spotify user’s "Your Music" library.
 */
public class CheckUsersSavedAlbumsRequest extends AbstractDataRequest {

  /**
   * The private {@link CheckUsersSavedAlbumsRequest} constructor.
   *
   * @param builder A {@link CheckUsersSavedAlbumsRequest.Builder}.
   */
  private CheckUsersSavedAlbumsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Check whether an album is present in the current user's "Your Music" library.
   *
   * @return Whether an album is present in the current user's "Your Music" library.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Boolean[] execute() throws
          IOException,
          SpotifyWebApiException {
    return new Gson().fromJson(new JsonParser().parse(getJson()).getAsJsonArray(), Boolean[].class);
  }

  /**
   * Builder class for building a {@link CheckUsersSavedAlbumsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link CheckUsersSavedAlbumsRequest.Builder} instance.
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
     * The album IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the albums. Maximum: 50 IDs.
     * @return A {@link CheckUsersSavedAlbumsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link CheckUsersSavedAlbumsRequest}.
     */
    @Override
    public CheckUsersSavedAlbumsRequest build() {
      setPath("/v1/me/albums/contains");
      return new CheckUsersSavedAlbumsRequest(this);
    }
  }
}
