package se.michaelthelin.spotify.requests.data.browse;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Category;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetListOfCategoriesRequestTest extends AbstractDataTest<Paging<Category>> {
  private final GetListOfCategoriesRequest defaultRequest = SPOTIFY_API.getListOfCategories()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/browse/GetListOfCategoriesRequest.json"))
    .country(COUNTRY)
    .limit(LIMIT)
    .locale(LOCALE)
    .offset(OFFSET)
    .build();

  public GetListOfCategoriesRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/browse/categories?country=SE&limit=10&locale=sv_SE&offset=0",
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
      "https://api.spotify.com/v1/browse/categories?offset=0&limit=20",
      categoryPaging.getHref());
    assertEquals(
      5,
      categoryPaging.getItems().length);
    assertEquals(
      20,
      (int) categoryPaging.getLimit());
    assertEquals(
      "https://api.spotify.com/v1/browse/categories?offset=20&limit=20",
      categoryPaging.getNext());
    assertEquals(
      0,
      (int) categoryPaging.getOffset());
    assertNull(
      categoryPaging.getPrevious());
    assertEquals(
      31,
      (int) categoryPaging.getTotal());
  }
}
