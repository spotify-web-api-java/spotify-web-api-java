package se.michaelthelin.spotify.model_objects;

import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.model_objects.special.PlaybackQueue;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;
import se.michaelthelin.spotify.model_objects.specification.PlayHistory;
import se.michaelthelin.spotify.model_objects.specification.SavedTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ModelObjectsTest {

  @Test
  public void readsAnObject() throws IOException {
    Track track = ModelObjects.fromJson(
      TestUtil.readTestData("requests/data/tracks/GetTrackRequest.json"), Track.class);

    assertEquals("Mr. Brightside", track.getName());
  }

  @Test
  public void readsAnObjectThroughItsTypeAdapter() throws IOException {
    PlaybackQueue queue = ModelObjects.fromJson(
      TestUtil.readTestData("requests/data/player/GetQueueRequest.json"), PlaybackQueue.class);

    assertInstanceOf(Track.class, queue.getCurrentlyPlaying());
    assertEquals(20, queue.getQueue().size());
  }

  @Test
  public void readsNullAsNull() {
    assertNull(ModelObjects.fromJson(null, CurrentlyPlaying.class));
  }

  @Test
  public void readsAnArrayKeepingNullElements() {
    Image[] images = ModelObjects.arrayFromJson(
      "[{\"url\": \"https://i.scdn.co/image/a\", \"height\": 640, \"width\": 480}, null]", Image.class);

    assertEquals(2, images.length);
    assertEquals(640, (int) images[0].getHeight());
    assertNull(images[1]);
  }

  @Test
  public void readsAPaging() throws IOException {
    Paging<SavedTrack> paging = ModelObjects.pagingFromJson(
      TestUtil.readTestData("requests/data/tracks/GetUsersSavedTracksRequest.json"), SavedTrack.class);

    assertEquals(53, (int) paging.getTotal());
    assertEquals(1, paging.getItems().length);
    assertInstanceOf(SavedTrack.class, paging.getItems()[0]);
  }

  @Test
  public void readsACursorBasedPaging() throws IOException {
    PagingCursorbased<PlayHistory> paging = ModelObjects.pagingCursorbasedFromJson(
      TestUtil.readTestData("requests/data/player/GetRecentlyPlayedRequest.json"), PlayHistory.class);

    assertEquals(2, (int) paging.getLimit());
    assertEquals(2, paging.getItems().length);
    assertEquals("1695397413750", paging.getCursors()[0].getAfter());
  }
}
