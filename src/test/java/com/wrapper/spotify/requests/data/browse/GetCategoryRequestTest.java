package com.wrapper.spotify.requests.data.browse;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Category;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetCategoryRequestTest extends AbstractDataTest<Category> {
  private final GetCategoryRequest defaultRequest = SPOTIFY_API.getCategory(CATEGORY_ID)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/browse/GetCategoryRequest.json"))
          .country(COUNTRY)
          .locale(LOCALE)
          .build();

  public GetCategoryRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/browse/categories/dinner?country=SE&locale=sv_SE",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Category) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Category category) {
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
