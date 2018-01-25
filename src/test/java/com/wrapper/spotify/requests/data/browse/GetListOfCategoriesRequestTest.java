package com.wrapper.spotify.requests.data.browse;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Category;
import com.wrapper.spotify.model_objects.specification.Paging;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetListOfCategoriesRequestTest implements ITest<Paging<Category>> {
  private final GetListOfCategoriesRequest successRequest = SPOTIFY_API.getListOfCategories()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/browse/GetListOfCategoriesRequest.json"))
          .build();

  public GetListOfCategoriesRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Paging<Category>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Paging<Category> categoryPaging) {
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
