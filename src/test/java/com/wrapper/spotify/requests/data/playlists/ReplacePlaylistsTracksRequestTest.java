package com.wrapper.spotify.requests.data.playlists;

import com.google.gson.JsonParser;
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
public class ReplacePlaylistsTracksRequestTest extends AbstractDataTest<String> {
  private final ReplacePlaylistsTracksRequest defaultRequest = SPOTIFY_API
          .replacePlaylistsTracks(ID_PLAYLIST, new String[]{"spotify:track:" + ID_TRACK, "spotify:track:" + ID_TRACK})
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/ReplacePlaylistsTracksRequest.json"))
          .build();
  private final ReplacePlaylistsTracksRequest bodyRequest = SPOTIFY_API
          .replacePlaylistsTracks(ID_PLAYLIST, new JsonParser()
                  .parse("[\"spotify:track:" + ID_TRACK + "\",\"spotify:track:" + ID_TRACK + "\"]").getAsJsonArray())
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/playlists/ReplacePlaylistsTracksRequest.json"))
          .build();

  public ReplacePlaylistsTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks?uris=spotify%3Atrack%3A01iyCAUm8EvOFqVWYJ3dVX%2Cspotify%3Atrack%3A01iyCAUm8EvOFqVWYJ3dVX",
            defaultRequest.getUri().toString());

    assertHasAuthorizationHeader(bodyRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(
            bodyRequest,
            "uris",
            "[\"spotify:track:" + ID_TRACK + "\",\"spotify:track:" + ID_TRACK + "\"]");
    assertEquals(
            "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks",
            bodyRequest.getUri().toString());
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
