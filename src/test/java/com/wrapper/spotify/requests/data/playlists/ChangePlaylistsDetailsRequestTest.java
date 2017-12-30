package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ChangePlaylistsDetailsRequestTest {

  @Test
  public void shouldChangeNameAndPublishedStatus_async() throws Exception {
    final String accessToken = "someAccessToken";

    final Api api = Api.builder().accessToken("AccessToken").build();

    ChangePlaylistsDetailsRequest request = api
            .changePlaylistDetails("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .public_(true)
            .name("Testing playlist name change")
            .setHttpManager(TestUtil.MockedHttpManager.returningString(""))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    ListenableFuture<String> future = request.putAsync();

    Futures.addCallback(future, new FutureCallback<String>() {

      @Override
      public void onSuccess(String response) {
        assertEquals("", response);

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
  public void shouldChangeNameAndPublishedStatus_sync() throws Exception {
    final String accessToken = "someAccessToken";

    final Api api = Api.builder().accessToken("AccessToken").build();

    ChangePlaylistsDetailsRequest request = api
            .changePlaylistDetails("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .public_(true)
            .name("Testing playlist name change")
            .setHttpManager(TestUtil.MockedHttpManager.returningString(""))
            .build();

    request.put();
  }
}
