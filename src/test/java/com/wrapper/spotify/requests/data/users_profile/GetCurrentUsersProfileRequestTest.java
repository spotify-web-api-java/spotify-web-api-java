package com.wrapper.spotify.requests.data.users_profile;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.enums.ProductType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GetCurrentUsersProfileRequestTest implements ITest<User> {
  private final GetCurrentUsersProfileRequest successRequest = SPOTIFY_API
          .getCurrentUsersProfile()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/users_profile/GetCurrentUsersProfileRequest.json"))
          .build();

  public GetCurrentUsersProfileRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((User) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final User user) {
    assertEquals(
            "1937-06-01",
            user.getBirthdate());
    assertEquals(
            CountryCode.SE,
            user.getCountry());
    assertEquals(
            "JM Wizzler",
            user.getDisplayName());
    assertEquals(
            "email@example.com",
            user.getEmail());
    assertNotNull(
            user.getExternalUrls());
    assertNotNull(
            user.getFollowers());
    assertEquals(
            "https://api.spotify.com/v1/users/wizzler",
            user.getHref());
    assertEquals(
            "wizzler",
            user.getId());
    assertEquals(
            1,
            user.getImages().length);
    assertEquals(
            ProductType.PREMIUM,
            user.getProduct());
    assertEquals(
            ModelObjectType.USER,
            user.getType());
    assertEquals(
            "spotify:user:wizzler",
            user.getUri());
  }
}
