package se.michaelthelin.spotify.requests.data.albums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataPagingRequest;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a list of new album releases featured in Spotify (shown, for example, on a Spotify player's "Browse" tab).
 *
 * @deprecated Use the Search API instead.
 */
@Deprecated
@JsonDeserialize(builder = GetNewReleasesRequest.Builder.class)
public class GetNewReleasesRequest extends AbstractDataRequest<Paging<AlbumSimplified>> {

  /**
   * The private {@link GetNewReleasesRequest} constructor.
   *
   * @param builder A {@link GetNewReleasesRequest.Builder}.
   */
  private GetNewReleasesRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a paging of new {@link AlbumSimplified} releases.
   *
   * @return An {@link AlbumSimplified} paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Paging<AlbumSimplified> execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new AlbumSimplified.JsonUtil().createModelObjectPaging(getJson(), "albums");
  }

  /**
   * Builder class for building a {@link GetNewReleasesRequest}.
   *
   * @deprecated Use the Search API instead.
   */
  @Deprecated
  public static final class Builder extends AbstractDataPagingRequest.Builder<AlbumSimplified, Builder> {

    /**
     * Create a new {@link GetNewReleasesRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetNewReleasesRequest.Builder}.
     */
    @Override
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first item to return. Default: 0.
     * @return A {@link GetNewReleasesRequest.Builder}.
     */
    @Override
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetNewReleasesRequest}.
     */
    @Override
    public GetNewReleasesRequest build() {
      setPath("/v1/browse/new-releases");
      return new GetNewReleasesRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
