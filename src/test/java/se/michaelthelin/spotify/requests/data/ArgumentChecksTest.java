package se.michaelthelin.spotify.requests.data;

import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.ITest;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Pins the argument checks the generated setters assert from the spec, so a spec change that drops a
 * machine-readable bound fails here instead of quietly accepting invalid arguments.
 */
public class ArgumentChecksTest {

  private static String uris(final int count) {
    return Collections.nCopies(count, "spotify:track:" + ITest.ID_TRACK).stream().collect(Collectors.joining(","));
  }

  @Test
  public void volume_percent_accepts_the_documented_range() {
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.setPlaybackVolume(0));
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.setPlaybackVolume(100));
    assertThrows(AssertionError.class, () -> ITest.SPOTIFY_API.setPlaybackVolume(-1));
    assertThrows(AssertionError.class, () -> ITest.SPOTIFY_API.setPlaybackVolume(101));
  }

  @Test
  public void state_accepts_only_the_documented_repeat_states() {
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.setRepeatMode("track"));
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.setRepeatMode("context"));
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.setRepeatMode("off"));
    assertThrows(AssertionError.class, () -> ITest.SPOTIFY_API.setRepeatMode("bogus"));
  }

  @Test
  public void current_users_playlists_offset_accepts_at_most_100000() {
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.getCurrentUsersPlaylists().offset(0));
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.getCurrentUsersPlaylists().offset(100000));
    assertThrows(AssertionError.class, () -> ITest.SPOTIFY_API.getCurrentUsersPlaylists().offset(100001));
  }

  @Test
  @SuppressWarnings("deprecation")
  public void users_playlists_offset_accepts_at_most_100000() {
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.getUsersPlaylists(ITest.ID_USER).offset(0));
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.getUsersPlaylists(ITest.ID_USER).offset(100000));
    assertThrows(AssertionError.class, () -> ITest.SPOTIFY_API.getUsersPlaylists(ITest.ID_USER).offset(100001));
  }

  @Test
  public void library_uris_accept_at_most_40() {
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.checkUsersSavedItems(uris(40)));
    assertThrows(AssertionError.class, () -> ITest.SPOTIFY_API.checkUsersSavedItems(uris(41)));
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.saveItemsToLibrary(uris(40)));
    assertThrows(AssertionError.class, () -> ITest.SPOTIFY_API.saveItemsToLibrary(uris(41)));
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.removeItemsFromLibrary(uris(40)));
    assertThrows(AssertionError.class, () -> ITest.SPOTIFY_API.removeItemsFromLibrary(uris(41)));
  }

  @Test
  @SuppressWarnings("deprecation")
  public void playlist_follow_ids_accept_a_single_id() {
    assertDoesNotThrow(() -> ITest.SPOTIFY_API.checkIfUserFollowsPlaylist(ITest.ID_PLAYLIST).ids(ITest.ID_USER));
    assertThrows(
      AssertionError.class,
      () -> ITest.SPOTIFY_API.checkIfUserFollowsPlaylist(ITest.ID_PLAYLIST).ids(ITest.ID_USER + "," + ITest.ID_USER));
  }
}
