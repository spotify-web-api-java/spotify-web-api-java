package se.michaelthelin.spotify.requests.data.chapters;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Chapter;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for a single audiobook chapter. Chapters are only available within the US, UK,
 * Ireland, New Zealand and Australia markets.
 */
@JsonDeserialize(builder = GetChapterRequest.Builder.class)
public class GetChapterRequest extends AbstractDataRequest<Chapter> {

  /**
   * The private {@link GetChapterRequest} constructor.
   *
   * @param builder A {@link GetChapterRequest.Builder}.
   */
  private GetChapterRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a {@link Chapter} synchronously.
   *
   * @return A {@link Chapter}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Chapter execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Chapter.JsonUtil().createModelObject(getJson());
  }

  /**
   * A builder class for a {@link GetChapterRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Chapter, Builder> {

    /**
     * Create a new {@link GetChapterRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The ID path parameter setter.
     *
     * @param id The Spotify ID for the chapter.
     * @return A {@link GetChapterRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.isEmpty());
      return setPathParameter("id", id);
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code.
     * @return A {@link GetChapterRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetChapterRequest}.
     */
    @Override
    public GetChapterRequest build() {
      setPath("/v1/chapters/{id}");
      return new GetChapterRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
