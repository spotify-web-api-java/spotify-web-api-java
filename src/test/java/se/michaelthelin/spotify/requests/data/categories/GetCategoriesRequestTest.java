package se.michaelthelin.spotify.requests.data.categories;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Category;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCategoriesRequestTest extends AbstractDataTest<Paging<Category>> {
  private final GetSeveralBrowseCategoriesRequest defaultRequest = ITest.SPOTIFY_API
    .getSeveralBrowseCategories()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/categories/GetCategoriesRequest.json"))
    .locale(ITest.LOCALE)
    .limit(ITest.LIMIT)
    .offset(ITest.OFFSET)
    .build();

  public GetCategoriesRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/browse/categories?locale=sv_SE&limit=10&offset=0",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Paging<Category> categoryPaging) {
    assertEquals(
      "https://api.spotify.com/v1/browse/categories?offset=0&limit=2",
      categoryPaging.getHref());
    assertEquals(
      2,
      categoryPaging.getItems().length);
    assertEquals(
      2,
      categoryPaging.getLimit());
    assertEquals(
      0,
      (int) categoryPaging.getOffset());
    assertEquals(
      31,
      (int) categoryPaging.getTotal());
  }
}
