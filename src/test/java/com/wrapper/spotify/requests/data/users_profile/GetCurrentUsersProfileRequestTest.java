package com.wrapper.spotify.requests.data.users_profile;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ProductType;
import com.wrapper.spotify.model_objects.specification.User;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class GetCurrentUsersProfileRequestTest {

  @Test
  public void shouldGetCurrentUser_async() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetCurrentUsersProfileRequest request = api.getCurrentUsersProfile()
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/users_profile/GetCurrentUsersProfileRequest.json"))
            .build();

    final Future<User> requestFuture = request.executeAsync();
    final User user = requestFuture.get();

    assertNotNull(user);
    assertEquals("Michael", user.getDisplayName());
    assertEquals("thelinmichael+test@gmail.com", user.getEmail());
    assertEquals("https://open.spotify.com/user/thelinmichael", user.getExternalUrls().get(
            "spotify"));
    assertEquals("https://api.spotify.com/v1/users/thelinmichael", user.getHref());
    assertEquals("thelinmichael", user.getId());
    assertEquals(CountryCode.SE, user.getCountry());
    assertNotNull(user.getFollowers());
    assertNull(user.getImages()[0].getHeight());
    assertNull(user.getImages()[0].getWidth());
    assertEquals("http://media.giphy.com/media/Aab07O5PYOmQ/giphy.gif", user.getImages()[0].getUrl());
    assertEquals(ProductType.PREMIUM, user.getProduct());
    assertEquals("spotify:user:thelinmichael", user.getUri());
    assertEquals("1989-07-04", user.getBirthdate());
  }

  @Test
  public void shouldGetCurrentUser_sync() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    final GetCurrentUsersProfileRequest request = api.getCurrentUsersProfile()
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/users_profile/GetCurrentUsersProfileRequest.json"))
            .build();

    final User user = request.execute();

    assertNotNull(user);
    assertEquals("Michael", user.getDisplayName());
    assertEquals("thelinmichael+test@gmail.com", user.getEmail());
    assertEquals("https://open.spotify.com/user/thelinmichael", user.getExternalUrls().get(
            "spotify"));
    assertEquals("https://api.spotify.com/v1/users/thelinmichael", user.getHref());
    assertEquals("thelinmichael", user.getId());
    assertEquals(CountryCode.SE, user.getCountry());
    assertNotNull(user.getFollowers());
    assertNull(user.getImages()[0].getHeight());
    assertNull(user.getImages()[0].getWidth());
    assertEquals("http://media.giphy.com/media/Aab07O5PYOmQ/giphy.gif", user.getImages()[0].getUrl());
    assertEquals(ProductType.PREMIUM, user.getProduct());
    assertEquals("spotify:user:thelinmichael", user.getUri());
    assertEquals("1989-07-04", user.getBirthdate());
  }
}
