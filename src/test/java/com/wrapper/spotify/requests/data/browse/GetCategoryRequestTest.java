package com.wrapper.spotify.requests.data.browse;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetCategoryRequestTest implements ITest<Category> {
  private final GetCategoryRequest successRequest = SPOTIFY_API.getCategory("id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/browse/GetCategoryRequest.json"))
          .build();

  public GetCategoryRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Category) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Category category) {
    assertEquals(
            "https://api.spotify.com/v1/browse/categories/party",
            category.getHref());
    assertEquals(
            1,
            category.getIcons().length);
    assertEquals(
            "party",
            category.getId());
    assertEquals(
            "Party",
            category.getName());
  }
}
