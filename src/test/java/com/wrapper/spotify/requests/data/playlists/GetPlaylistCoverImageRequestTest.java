package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetPlaylistCoverImageRequestTest implements ITest<Image[]> {
  private final GetPlaylistCoverImageRequest successRequest = SPOTIFY_API
          .getPlaylistCoverImage("user_id", "playlist_id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/GetPlaylistCoverImageRequest.json"))
          .build();

  public GetPlaylistCoverImageRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Image[]) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Image[] images) {
    assertEquals(
            1,
            images.length);
  }
}
