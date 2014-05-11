package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.models.User;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class UserRequestTest {


  @Test
  public void shouldCreateUser_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("user.json");
    final UserRequest request = api.getUser("wizzler").httpManager(mockedHttpManager).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    SettableFuture<User> userFuture = request.getAsync();

    Futures.addCallback(userFuture, new FutureCallback<User>() {
      @Override
      public void onSuccess(User userResult) {
        assertNull(userResult.getEmail());
        assertEquals("wizzler", userResult.getId());
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
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("user.json");
    final UserRequest request = api.getUser("wizzler").httpManager(mockedHttpManager).build();

    final User user = request.get();
    assertNull(user.getEmail());
    assertEquals("wizzler", user.getId());
    assertEquals("https://api.spotify.com/v1/users/wizzler", user.getHref());
  }

}
