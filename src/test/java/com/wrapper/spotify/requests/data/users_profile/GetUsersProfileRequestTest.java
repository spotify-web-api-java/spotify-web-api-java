package com.wrapper.spotify.requests.data.users_profile;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersProfileRequestTest {

  @Test
  public void shouldCreateUser_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetUsersProfileRequest request = api.getUser("wizzler")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/users_profile/GetUsersProfileRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    SettableFuture<User> userFuture = request.getAsync();

    Futures.addCallback(userFuture, new FutureCallback<User>() {
      @Override
      public void onSuccess(User userResult) {
        assertNull(userResult.getEmail());
        assertEquals("wizzler", userResult.getId());
        assertEquals("https://open.spotify.com/user/wizzler", userResult.getExternalUrls().get("spotify"));
        assertNotNull(userResult.getFollowers());
        assertNotNull(userResult.getImages());
        assertTrue(userResult.getFollowers().getTotal() > 0);
        assertEquals("https://api.spotify.com/v1/users/wizzler", userResult.getHref());
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldCreateUser_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetUsersProfileRequest request = api.getUser("wizzler")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/users_profile/GetUsersProfileRequest.json"))
            .build();

    final User user = request.get();

    assertNull(user.getEmail());
    assertEquals("wizzler", user.getId());
    assertEquals("https://open.spotify.com/user/wizzler", user.getExternalUrls().get("spotify"));
    assertNotNull(user.getFollowers());
    assertNotNull(user.getImages());
    assertTrue(user.getFollowers().getTotal() > 0);
    assertEquals("https://api.spotify.com/v1/users/wizzler", user.getHref());
  }

}
