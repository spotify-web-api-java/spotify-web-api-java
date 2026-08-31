package se.michaelthelin.spotify.requests.data.audiobooks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AudiobookSimplified;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for several audiobooks identified by their Spotify IDs.
 *
 * <p><b>Note:</b> Audiobooks are only available for the US, UK, Ireland, New Zealand and Australia markets.
 *
 * @deprecated This endpoint has been deprecated by Spotify.
 */
@Deprecated
@JsonDeserialize(builder = GetSeveralAudiobooksRequest.Builder.class)
public class GetSeveralAudiobooksRequest extends AbstractDataRequest<AudiobookSimplified[]> {

  /**
   * The private {@link GetSeveralAudiobooksRequest} constructor.
   *
   * @param builder A {@link GetSeveralAudiobooksRequest.Builder}.
   */
  private GetSeveralAudiobooksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get several {@link AudiobookSimplified} objects.
   *
   * @return Multiple {@link AudiobookSimplified} objects.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public AudiobookSimplified[] execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new AudiobookSimplified.JsonUtil().createModelObjectArray(getJson(), "audiobooks");
  }

  /**
   * Builder class for building a {@link GetSeveralAudiobooksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<AudiobookSimplified[], Builder> {

    /**
     * Create a new {@link GetSeveralAudiobooksRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The audiobook IDs setter.
     *
     * @param ids Required. A comma-separated list of the Spotify IDs for the audiobooks. Maximum: 50 IDs.
     * @return A {@link GetSeveralAudiobooksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setQueryParameter("ids", ids);
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code.
     * @return A {@link GetSeveralAudiobooksRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetSeveralAudiobooksRequest}.
     */
    @Override
    public GetSeveralAudiobooksRequest build() {
      setPath("/v1/audiobooks");
      return new GetSeveralAudiobooksRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
