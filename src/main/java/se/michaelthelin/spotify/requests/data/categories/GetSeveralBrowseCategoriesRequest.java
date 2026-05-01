package se.michaelthelin.spotify.requests.data.categories;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Category;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataPagingRequest;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a list of categories used to tag items in Spotify (on, for example, the Spotify player's "Browse" tab).
 *
 * @deprecated Use the Search API instead.
 */
@Deprecated
@JsonDeserialize(builder = GetSeveralBrowseCategoriesRequest.Builder.class)
public class GetSeveralBrowseCategoriesRequest extends AbstractDataRequest<Paging<Category>> {

  /**
   * The private {@link GetSeveralBrowseCategoriesRequest} constructor.
   *
   * @param builder A {@link GetSeveralBrowseCategoriesRequest.Builder}.
   */
  private GetSeveralBrowseCategoriesRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a paging of {@link Category} objects.
   *
   * @return A {@link Category} paging.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Paging<Category> execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Category.JsonUtil().createModelObjectPaging(getJson(), "categories");
  }

  /**
   * Builder class for building a {@link GetSeveralBrowseCategoriesRequest}.
   *
   * @deprecated Use the Search API instead.
   */
  @Deprecated
  public static final class Builder extends AbstractDataPagingRequest.Builder<Category, Builder> {

    /**
     * Create a new {@link GetSeveralBrowseCategoriesRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The locale setter.
     *
     * @param locale Optional. The desired language, consisting of an ISO 639-1 language code and an ISO 3166-1
     *               alpha-2 country code, joined by an underscore. For example: {@code es_MX}. Provide this parameter
     *               if you want the category strings returned in a particular language.
     * @return A {@link GetSeveralBrowseCategoriesRequest.Builder}.
     */
    public Builder locale(final String locale) {
      assert (locale != null);
      return setQueryParameter("locale", locale);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of categories to return. Default: 20. Minimum: 1. Maximum: 50.
     * @return A {@link GetSeveralBrowseCategoriesRequest.Builder}.
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
     * @return A {@link GetSeveralBrowseCategoriesRequest.Builder}.
     */
    @Override
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetSeveralBrowseCategoriesRequest}.
     */
    @Override
    public GetSeveralBrowseCategoriesRequest build() {
      setPath("/v1/browse/categories");
      return new GetSeveralBrowseCategoriesRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
