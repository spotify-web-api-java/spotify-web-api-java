package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.models.Album;
import se.michaelthelin.spotify.models.Artist;
import se.michaelthelin.spotify.models.Page;
import se.michaelthelin.spotify.models.Track;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class SearchRequestTest {

  @Test
  public void shouldGetArtistsResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-artist.json");
    final SearchRequest request = api.search().query("David Bowie").type("artist").httpManager(mockedHttpManager).build();

    final SettableFuture<Page<Artist>> searchResultFuture = request.getArtistsAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Page<Artist>>() {
      @Override
      public void onSuccess(Page<Artist> artistSearchResult) {
        List<Artist> artists = artistSearchResult.getItems();
        assertEquals(1, artists.size());

        Artist firstArtist = artists.get(0);

        assertEquals("08td7MxkoHQkXnWAYD8d6Q", firstArtist.getId());
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });
  }

  @Test
  public void shouldGetArtistsResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-artist.json");
    final SearchRequest request = api.search().query("David Bowie").type("artist").httpManager(mockedHttpManager).build();

    final Page<Artist> artistSearchResult = request.getArtistsPage();

    final List<Artist> artists = artistSearchResult.getItems();
    assertEquals(1, artists.size());

    final Artist firstArtist = artists.get(0);

    assertEquals("08td7MxkoHQkXnWAYD8d6Q", firstArtist.getId());
  }

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-track-page1.json");
    final SearchRequest request = api.search().query("Mr. Brightside").type("track").httpManager(mockedHttpManager).build();

    SettableFuture<Page<Track>> searchResultFuture = request.getTracksAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Page<Track>>() {
      @Override
      public void onSuccess(Page<Track> trackSearchResult) {
        List<Track> tracks = trackSearchResult.getItems();

        assertEquals(20, tracks.size());

        Track firstTrack = tracks.get(0);
        assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

        Track secondTrack = tracks.get(1);
        assertEquals("5zvJ6DUahHHjeknQPn7iAH", secondTrack.getId());
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });
  }

  @Test
  public void shouldGetTracksResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-track-page1.json");
    final SearchRequest request = api.search().query("Mr. Brightside").type("track").httpManager(mockedHttpManager).build();

    final Page<Track> trackSearchResult = request.getTracksPage();

    final List<Track> tracks = trackSearchResult.getItems();

    assertEquals(20, tracks.size());

    final Track firstTrack = tracks.get(0);
    assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

    final Track secondTrack = tracks.get(1);
    assertEquals("5zvJ6DUahHHjeknQPn7iAH", secondTrack.getId());
  }

  @Test
  public void shouldGetAlbumsResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-album.json");
    final SearchRequest request = api.search().query("The Best Of Keane").type("album").httpManager(mockedHttpManager).build();

    final SettableFuture<Page<Album>> searchResultFuture = request.getAlbumsAsync();

    Futures.addCallback(searchResultFuture, new FutureCallback<Page<Album>>() {
      @Override
      public void onSuccess(Page<Album> albumSearchResult) {
        List<Album> albums = albumSearchResult.getItems();

        assertEquals(1, albums.size());

        Album firstAlbum = albums.get(0);
        assertEquals("68NlXKRuJ1YqrhIbwe864y", firstAlbum.getId());
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });

  }

  @Test
  public void shouldGetAlbumsResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;
    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-album.json");
    final SearchRequest request = api.search().query("The Best Of Keane").type("album").httpManager(mockedHttpManager).build();

    final Page<Album> albumSearchResult = request.getAlbumsPage();

    final List<Album> albums = albumSearchResult.getItems();

    assertEquals(1, albums.size());

    final Album firstAlbum = albums.get(0);
    assertEquals("68NlXKRuJ1YqrhIbwe864y", firstAlbum.getId());
  }

}
