package com.wrapper.spotify.requests.data.artists;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for a single artist identified by their unique Spotify ID.
 */
public class GetArtistRequest extends AbstractDataRequest {

  /**
   * The private {@link GetArtistRequest} constructor.
   *
   * @param builder A {@link GetArtistRequest.Builder}.
   */
  private GetArtistRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get an {@link Artist}.
   *
   * @return An {@link Artist}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Artist execute() throws
          IOException,
          SpotifyWebApiException {
    return new Artist.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetArtistRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetArtistRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The artist ID setter.
     *
     * @param id The Spotify ID for the artist.
     * @return A {@link GetArtistRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetArtistRequest}.
     */
    @Override
    public GetArtistRequest build() {
      setPath("/v1/artists/{id}");
      return new GetArtistRequest(this);
    }
  }
}
