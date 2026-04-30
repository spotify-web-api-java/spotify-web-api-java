package se.michaelthelin.spotify.requests.data.audiobooks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ChapterSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataPagingRequest;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information about an audiobook's chapters.
 *
 * <p><b>Note:</b> Audiobooks are only available for the US, UK, Ireland, New Zealand and Australia markets.
 */
@JsonDeserialize(builder = GetAudiobookChaptersRequest.Builder.class)
public class GetAudiobookChaptersRequest extends AbstractDataRequest<Paging<ChapterSimplified>> {

  /**
   * The private {@link GetAudiobookChaptersRequest} constructor.
   *
   * @param builder A {@link GetAudiobookChaptersRequest.Builder}.
   */
  private GetAudiobookChaptersRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the chapters from the audiobook.
   *
   * @return A chapters paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Paging<ChapterSimplified> execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new ChapterSimplified.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetAudiobookChaptersRequest}.
   */
  public static final class Builder extends AbstractDataPagingRequest.Builder<ChapterSimplified, Builder> {

    /**
     * Create a new {@link GetAudiobookChaptersRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The ID path parameter setter.
     *
     * @param id The Spotify ID for the audiobook.
     * @return A {@link GetAudiobookChaptersRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.isEmpty());
      return setPathParameter("id", id);
    }

    /**
     * The limit query parameter setter.
     *
     * @param limit Optional. The maximum number of chapters to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetAudiobookChaptersRequest.Builder}.
     */
    @Override
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 50);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset query parameter setter.
     *
     * @param offset Optional. The index of the first chapter to return. Default: 0 (the first object).
     * @return A {@link GetAudiobookChaptersRequest.Builder}.
     */
    @Override
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code.
     * @return A {@link GetAudiobookChaptersRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetAudiobookChaptersRequest}.
     */
    @Override
    public GetAudiobookChaptersRequest build() {
      setPath("/v1/audiobooks/{id}/chapters");
      return new GetAudiobookChaptersRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
