package com.wrapper.spotify.requests.data.playlists;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.wrapper.spotify.Assertions.assertHasBodyParameter;
import static com.wrapper.spotify.Assertions.assertHasHeader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ChangePlaylistsDetailsRequestTest extends AbstractDataTest<String> {
  private final ChangePlaylistsDetailsRequest defaultRequest = SPOTIFY_API
          .changePlaylistsDetails(ID_PLAYLIST)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/ChangePlaylistsDetailsRequest.json"))
          .collaborative(COLLABORATIVE)
          .description(DESCRIPTION)
          .name(NAME)
          .public_(PUBLIC)
          .build();

  public ChangePlaylistsDetailsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(
            defaultRequest,
            "name",
            NAME);
    assertHasBodyParameter(
            defaultRequest,
            "public",
            PUBLIC);
    assertHasBodyParameter(
            defaultRequest,
            "collaborative",
            COLLABORATIVE);
    assertHasBodyParameter(
            defaultRequest,
            "description",
            DESCRIPTION);
    assertEquals(
            "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5",
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
