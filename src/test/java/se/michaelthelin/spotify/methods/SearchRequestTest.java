package se.michaelthelin.spotify.methods;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtilTest;
import se.michaelthelin.spotify.SpotifyProtos.*;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchRequestTest {

  @Test
  public void shouldGetArtistsResult() throws Exception {
    Api api = Api.DEFAULT_API;
    SearchRequest request = api.search().query("David Bowie").type("artist").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("search-artist.json");
    SearchRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    ArtistSearchResult searchResult = spy.getArtists();

    List<Artist> artists = searchResult.getArtistsList();
    assertEquals(1, artists.size());

    Artist firstArtist = artists.get(0);

    assertEquals("2Bw6FyyzgCc8OD7MX59TEQ", firstArtist.getId());

    PagingInformation pagingInformation = searchResult.getPaging();
    assertEquals("null", pagingInformation.getNext());
    assertEquals("null", pagingInformation.getPrevious());
    assertEquals(1, pagingInformation.getTotalResultCount());
  }

  @Test
  public void shouldGetTracksResult() throws Exception {
    Api api = Api.DEFAULT_API;
    SearchRequest request = api.search().query("Mr. Brightside").type("track").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("search-track-page1.json");
    SearchRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    TrackSearchResult searchResult = spy.getTracks();

    List<Track> tracks = searchResult.getTracksList();

    assertEquals(20, tracks.size());

    Track firstTrack = tracks.get(0);
    assertEquals("3q8WojYJVZsGClFGFBYdTc", firstTrack.getId());

    Track secondTrack = tracks.get(1);
    assertEquals("2jEPQKvz7dh1pfpRyq6G1C", secondTrack.getId());

    PagingInformation pagingInformation = searchResult.getPaging();
    assertEquals("https://api.spotify.com/v1/search?query=Gatan&type=TRACK&offset=20&limit=20", pagingInformation.getNext());
    assertEquals("null", pagingInformation.getPrevious());
    assertEquals(258, pagingInformation.getTotalResultCount());
  }

  @Test
  public void shouldGetAlbumsResult() throws Exception {
    Api api = Api.DEFAULT_API;
    SearchRequest request = api.search().query("The Best Of Keane").type("album").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("search-album.json");
    SearchRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    AlbumSearchResult searchResult = spy.getAlbums();

    List<Album> albums = searchResult.getAlbumsList();

    assertEquals(1, albums.size());

    Album firstAlbum = albums.get(0);
    assertEquals("2fOs6I0CgvaZj9agU8EAlH", firstAlbum.getId());

    PagingInformation pagingInformation = searchResult.getPaging();
    assertEquals("https://api.spotify.com/v1/search?query=Gatan&type=ALBUM&offset=1&limit=1", pagingInformation.getNext());
    assertEquals("null", pagingInformation.getPrevious());
    assertEquals(17, pagingInformation.getTotalResultCount());
  }

}
