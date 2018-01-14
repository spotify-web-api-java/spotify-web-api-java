package com.wrapper.spotify.requests.data.albums;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetSeveralAlbumsRequestTest implements ITest<Album[]> {

  private final GetSeveralAlbumsRequest successRequest = SPOTIFY_API.getSeveralAlbums("id")
          .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetSeveralAlbumsRequest.json"))
          .build();

  private final GetSeveralAlbumsRequest failureRequest = SPOTIFY_API.getSeveralAlbums("id")
          .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/albums/GetSeveralAlbumsRequest_None.json"))
          .build();

  public GetSeveralAlbumsRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Album[]) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Album[] albums) {
    assertEquals(
            3,
            albums.length);
  }

  @Test
  public void shouldFail() throws Exception {
    final Album[] albums = (Album[]) failureRequest.executeAsync().get();

    assertEquals(
            1,
            albums.length);
    assertNull(
            albums[0]);
  }
}
