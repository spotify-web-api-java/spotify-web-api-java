package se.michaelthelin.spotify.requests.data.categories;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Category;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get a single category used to tag items in Spotify (on, for example, the Spotify player's "Browse" tab).
 *
 * @deprecated Use the Search API instead.
 */
@Deprecated
@JsonDeserialize(builder = GetSingleBrowseCategoryRequest.Builder.class)
public class GetSingleBrowseCategoryRequest extends AbstractDataRequest<Category> {

  /**
   * The private {@link GetSingleBrowseCategoryRequest} constructor.
   *
   * @param builder A {@link GetSingleBrowseCategoryRequest.Builder}.
   */
  private GetSingleBrowseCategoryRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a {@link Category} object.
   *
   * @return A {@link Category}.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  public Category execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Category.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetSingleBrowseCategoryRequest}.
   *
   * @deprecated Use the Search API instead.
   */
  @Deprecated
  public static final class Builder extends AbstractDataRequest.Builder<Category, Builder> {

    /**
     * Create a new {@link GetSingleBrowseCategoryRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The category ID path parameter setter.
     *
     * @param category_id Required. The Spotify category ID for the category.
     * @return A {@link GetSingleBrowseCategoryRequest.Builder}.
     */
    public Builder category_id(final String category_id) {
      assert (category_id != null);
      assert (!category_id.isEmpty());
      return setPathParameter("category_id", category_id);
    }

    /**
     * The locale setter.
     *
     * @param locale Optional. The desired language, consisting of an ISO 639-1 language code and an ISO 3166-1
     *               alpha-2 country code, joined by an underscore. For example: {@code es_MX}.
     * @return A {@link GetSingleBrowseCategoryRequest.Builder}.
     */
    public Builder locale(final String locale) {
      assert (locale != null);
      return setQueryParameter("locale", locale);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetSingleBrowseCategoryRequest}.
     */
    @Override
    public GetSingleBrowseCategoryRequest build() {
      setPath("/v1/browse/categories/{category_id}");
      return new GetSingleBrowseCategoryRequest(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
