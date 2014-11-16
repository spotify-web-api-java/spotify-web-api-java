package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class UserRequestTest {

  @Test
  public void shouldCreateUser_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final UserRequest request = api.getUser("wizzler")
        .httpManager(TestUtil.MockedHttpManager.returningJson("user.json"))
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
    final Api api = Api.DEFAULT_API;

    final UserRequest request = api.getUser("wizzler")
        .httpManager(TestUtil.MockedHttpManager.returningJson("user.json"))
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
