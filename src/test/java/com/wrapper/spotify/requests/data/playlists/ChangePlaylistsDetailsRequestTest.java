package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.TestUtil;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class ChangePlaylistsDetailsRequestTest {

  @Test
  public void shouldChangeNameAndPublishedStatus_async() throws Exception {
    final String accessToken = "someAccessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    ChangePlaylistsDetailsRequest request = api
            .changePlaylistsDetails("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .public_(true)
            .name("Testing playlist name change")
            .setHttpManager(TestUtil.MockedHttpManager.returningString(""))
            .build();

    final Future<String> requestFuture = request.executeAsync();
    final String string = requestFuture.get();

    assertEquals("", string);
  }

  @Test
  public void shouldChangeNameAndPublishedStatus_sync() throws Exception {
    final String accessToken = "someAccessToken";

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken("AccessToken").build();

    ChangePlaylistsDetailsRequest request = api
            .changePlaylistsDetails("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .public_(true)
            .name("Testing playlist name change")
            .setHttpManager(TestUtil.MockedHttpManager.returningString(""))
            .build();

    request.execute();
  }
}
