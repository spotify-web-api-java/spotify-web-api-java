package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.requests.PlaylistUnfollowRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class UnfollowPlaylistRequestTest {

  @Test
  public void shouldCreatePlaylist_async() throws Exception {
    final Api api = Api.builder().accessToken("someAccessToken").build();

    final PlaylistUnfollowRequest request = api.unfollowPlaylist(
            "userId", "5oEljuMoe9MXH6tBIPbd5e"
    ).setHttpManager(TestUtil.MockedHttpManager.returningString("")).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<String> result = request.getAsync();

    Futures.addCallback(result, new FutureCallback<String>() {
      @Override
      public void onSuccess(String result) {
        assertNotNull(result);
        assertEquals("", result);
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future: " + throwable.getMessage());
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldCreatePlaylist_sync() throws Exception {
    final Api api = Api.builder().accessToken("someAccessToken").build();

    final PlaylistUnfollowRequest request = api.unfollowPlaylist(
            "userId", "5oEljuMoe9MXH6tBIPbd5e"
    ).setHttpManager(TestUtil.MockedHttpManager.returningString("")).build();
    final String response = request.get();
    assertNotNull(response);
    assertEquals("", response);
  }
}
