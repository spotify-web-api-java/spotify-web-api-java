package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Image;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetPlaylistCoverImageRequestTest extends AbstractDataTest<Image[]> {
  private final GetPlaylistCoverImageRequest defaultRequest = SPOTIFY_API
          .getPlaylistCoverImage(ID_PLAYLIST)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/GetPlaylistCoverImageRequest.json"))
          .build();

  public GetPlaylistCoverImageRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/images",
            defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Image[]) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Image[] images) {
    assertEquals(
            1,
            images.length);
  }
}
