package se.michaelthelin.spotify.requests.data.playlists;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreatePlaylistForUserRequestTest extends AbstractDataTest<Playlist> {
  private final CreatePlaylistForUserRequest defaultRequest = ITest.SPOTIFY_API
    .createPlaylistForUser(ITest.ID_USER, ITest.NAME)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/CreatePlaylistForUserRequest.json"))
    .public_(ITest.PUBLIC)
    .collaborative(ITest.COLLABORATIVE)
    .description(ITest.DESCRIPTION)
    .build();

  public CreatePlaylistForUserRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/users/abbaspotify/playlists",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Playlist playlist) {
    assertNotNull(playlist);
    assertEquals(
      "3cEYpjA9oz9GiPac4AsH4n",
      playlist.getId());
    assertEquals(
      "New Playlist",
      playlist.getName());
  }
}
