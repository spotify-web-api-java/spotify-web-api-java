package se.michaelthelin.spotify.methods;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtilTest;
import se.michaelthelin.spotify.SpotifyProtos.Track;
import se.michaelthelin.spotify.SpotifyProtos.Album;
import se.michaelthelin.spotify.SpotifyProtos.Artist;

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
    String responseFixture = JsonUtilTest.readTestData("artists.json");
    SearchRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    List<Artist> artists = spy.getArtists();
    assertEquals(2, artists.size());

    Artist firstArtist = artists.get(0);
    Artist secondArtist = artists.get(1);

    assertEquals("0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getId());
    assertEquals("3dBVyJ7JuOMt4GE9607Qin", secondArtist.getId());
  }

  @Test
  public void shouldGetTracksResult() throws Exception {
    Api api = Api.DEFAULT_API;
    SearchRequest request = api.search().query("Mr. Brightside").type("track").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("tracks.json");
    SearchRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    List<Track> tracks = spy.getTracks();

    assertEquals(2, tracks.size());

    Track firstTrack = tracks.get(0);
    assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

    Track secondTrack = tracks.get(1);
    assertEquals("1lDWb6b6ieDQ2xT7ewTC3G", secondTrack.getId());
  }

  @Test
  public void shouldGetAlbumsResult() throws Exception {
    Api api = Api.DEFAULT_API;
    SearchRequest request = api.search().query("The Best Of Keane").type("album").build();

    // Mock response
    String responseFixture = JsonUtilTest.readTestData("albums.json");
    SearchRequest spy = spy(request);
    when(spy.getJson()).thenReturn(responseFixture);

    List<Album> albums = spy.getAlbums();

    assertEquals(2, albums.size());

    Album firstAlbum = albums.get(0);
    assertEquals("41MnTivkwTO3UUJ8DrqEJJ", firstAlbum.getId());

    Album secondAlbum = albums.get(1);
    assertEquals("6JWc4iAiJ9FjyK0B59ABb4", secondAlbum.getId());
  }

}
