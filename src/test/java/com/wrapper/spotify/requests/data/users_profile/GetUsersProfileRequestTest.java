package com.wrapper.spotify.requests.data.users_profile;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersProfileRequestTest {

  @Test
  public void shouldCreateUser_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetUsersProfileRequest request = api.getUsersProfile("wizzler")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/users_profile/GetUsersProfileRequest.json"))
            .build();

    final Future<User> requestFuture = request.executeAsync();
    final User user = requestFuture.get();

    assertNull(user.getEmail());
    assertEquals("wizzler", user.getId());
    assertEquals("https://open.spotify.com/user/wizzler", user.getExternalUrls().get("spotify"));
    assertNotNull(user.getFollowers());
    assertNotNull(user.getImages());
    assertTrue(user.getFollowers().getTotal() > 0);
    assertEquals("https://api.spotify.com/v1/users/wizzler", user.getHref());
  }

  @Test
  public void shouldCreateUser_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetUsersProfileRequest request = api.getUsersProfile("wizzler")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/users_profile/GetUsersProfileRequest.json"))
            .build();

    final User user = request.execute();

    assertNull(user.getEmail());
    assertEquals("wizzler", user.getId());
    assertEquals("https://open.spotify.com/user/wizzler", user.getExternalUrls().get("spotify"));
    assertNotNull(user.getFollowers());
    assertNotNull(user.getImages());
    assertTrue(user.getFollowers().getTotal() > 0);
    assertEquals("https://api.spotify.com/v1/users/wizzler", user.getHref());
  }

}
