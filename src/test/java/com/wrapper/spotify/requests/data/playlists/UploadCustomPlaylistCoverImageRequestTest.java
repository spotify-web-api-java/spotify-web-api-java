package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.wrapper.spotify.Assertions.assertHasHeader;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UploadCustomPlaylistCoverImageRequestTest extends AbstractDataTest<String> {
  private final UploadCustomPlaylistCoverImageRequest defaultRequest = SPOTIFY_API
          .uploadCustomPlaylistCoverImage(ID_PLAYLIST)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/UploadCustomPlaylistCoverImageRequest.json"))
          .image_data(IMAGE_DATA)
          .build();

  public UploadCustomPlaylistCoverImageRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "image/jpeg");
    assertNotNull(
            defaultRequest.getBody());
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
    shouldReturnDefault((String) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final String string) {
    assertNull(
            string);
  }
}
