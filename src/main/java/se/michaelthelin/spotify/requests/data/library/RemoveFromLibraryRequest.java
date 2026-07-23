package se.michaelthelin.spotify.requests.data.library;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonArray;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Remove a list of Spotify URIs from the user's library.
 */
@JsonDeserialize(builder = RemoveFromLibraryRequest.Builder.class)
public class RemoveFromLibraryRequest extends AbstractDataRequest<String> {

  /**
   * The private {@link RemoveFromLibraryRequest} constructor.
   *
   * @param builder A {@link RemoveFromLibraryRequest.Builder}.
   */
  private RemoveFromLibraryRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Remove items from the user's library.
   *
   * @return A string. <b>Note:</b> This endpoint doesn't return something in its response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @Override
  public String execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return deleteJson();
  }

  /**
   * Builder class for building a {@link RemoveFromLibraryRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<String, Builder> {

    /**
     * Create a new {@link RemoveFromLibraryRequest.Builder} instance.
     * <p>
     * Modification of the current user's library requires authorization of the
     * {@code user-library-modify} scope.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/scopes">Spotify: Using Scopes</a>
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The URIs setter.
     *
     * @param uris Required. A JSON array of Spotify URIs to remove. Maximum: 50 URIs.
     * @return A {@link RemoveFromLibraryRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder uris(final JsonArray uris) {
      assert (uris != null);
      assert (!uris.isJsonNull());
      assert (uris.size() <= 50);
      return setBodyParameter("uris", uris);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link RemoveFromLibraryRequest}.
     */
    @Override
    public RemoveFromLibraryRequest build() {
      setContentType(ContentType.APPLICATION_JSON);
      setPath("/v1/me/library");
      return new RemoveFromLibraryRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
