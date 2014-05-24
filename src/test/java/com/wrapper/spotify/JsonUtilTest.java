package com.wrapper.spotify;

import org.junit.Test;
import com.wrapper.spotify.models.Album;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Track;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonUtilTest {

  @Test
  public void shouldCreateArtist() throws Exception {
    String json = TestUtil.readTestData("artist.json");
    Artist artist = JsonUtil.createArtist(json);
    assertEquals("https://api.spotify.com/v1/artists/0LcJLqbBmaGUft1e9Mm8HV", artist.getHref());
  }

  @Test
  public void shouldCreateSeveralArtists() throws Exception {
    String json = TestUtil.readTestData("artists.json");
    List<Artist> artists = JsonUtil.createArtists(json);
    assertEquals(2, artists.size());
  }

  @Test
  public void shouldCreateAlbum() throws Exception {
    String json = TestUtil.readTestData("album.json");
    Album album = JsonUtil.createAlbum(json);
    assertEquals("https://api.spotify.com/v1/albums/0sNOF9WDwhWunNAHPD3Baj", album.getHref());
  }

  @Test
  public void shouldCreateSeveralAlbums() throws Exception {
    String json = TestUtil.readTestData("albums.json");
    List<Album> albums = JsonUtil.createAlbums(json);
    assertEquals(1, albums.size());
  }

  @Test
  public void shouldCreateTrack() throws Exception {
    String json = TestUtil.readTestData("track.json");
    Track track = JsonUtil.createTrack(json);
    assertEquals("0eGsygTp906u18L0Oimnem", track.getId());
  }

  @Test
  public void shouldCreateSeveralTracks() throws Exception {
    String json = TestUtil.readTestData("tracks.json");
    List<Track> tracks = JsonUtil.createTracks(json);
    assertEquals(2, tracks.size());
  }

}
