package se.michaelthelin.spotify.requests.data.playlists;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class GetPlaylistRequestTest extends AbstractDataTest<Playlist> {
  private final GetPlaylistRequest defaultRequest = ITest.SPOTIFY_API
    .getPlaylist(ITest.ID_PLAYLIST)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/playlists/GetPlaylistRequest.json"))
    .fields(ITest.FIELDS)
    .market(ITest.MARKET)
    .additionalTypes(ITest.ADDITIONAL_TYPES)
    .build();

  public GetPlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/playlists/3AGOiaoRXMSjswCLtuNqv5?fields=description&market=SE&additional_types=track%2Cepisode",
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
    assertFalse(
      playlist.getIsCollaborative());
    assertEquals(
      "Having friends over for dinner? Here´s the perfect playlist.",
      playlist.getDescription());
    assertNotNull(
      playlist.getExternalUrls());
    assertNotNull(
      playlist.getFollowers());
    assertEquals(
      "https://api.spotify.com/v1/users/spotify/playlists/59ZbFPES4DQwEjBpWHzrtC",
      playlist.getHref());
    assertEquals(
      "59ZbFPES4DQwEjBpWHzrtC",
      playlist.getId());
    assertEquals(
      1,
      playlist.getImages().length);
    assertEquals(
      "Dinner with Friends",
      playlist.getName());
    assertNotNull(
      playlist.getOwner());
    assertNull(
      playlist.getIsPublicAccess());
    assertEquals(
      "bNLWdmhh+HDsbHzhckXeDC0uyKyg4FjPI/KEsKjAE526usnz2LxwgyBoMShVL+z+",
      playlist.getSnapshotId());
    assertNotNull(
      playlist.getTracks());
    assertEquals(
      ModelObjectType.PLAYLIST,
      playlist.getType());
    assertEquals(
      "spotify:user:spotify:playlist:59ZbFPES4DQwEjBpWHzrtC",
      playlist.getUri());
  }
}
