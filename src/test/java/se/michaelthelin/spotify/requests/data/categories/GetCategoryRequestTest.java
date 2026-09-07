package se.michaelthelin.spotify.requests.data.categories;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Category;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetCategoryRequestTest extends AbstractDataTest<Category> {
  private final GetSingleBrowseCategoryRequest defaultRequest = ITest.SPOTIFY_API
    .getSingleBrowseCategory(ITest.CATEGORY_ID)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/categories/GetCategoryRequest.json"))
    .locale(ITest.LOCALE)
    .build();

  public GetCategoryRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/browse/categories/dinner?locale=sv_SE",
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

  public void shouldReturnDefault(final Category category) {
    assertEquals(
      "https://api.spotify.com/v1/browse/categories/toplists",
      category.getHref());
    assertNotNull(
      category.getIcons());
    assertEquals(
      "toplists",
      category.getId());
    assertEquals(
      "Top Lists",
      category.getName());
  }
}
