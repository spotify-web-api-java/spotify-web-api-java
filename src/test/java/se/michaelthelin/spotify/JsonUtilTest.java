package se.michaelthelin.spotify;

import org.junit.Test;
import se.michaelthelin.spotify.SpotifyProtos.Artist;
import se.michaelthelin.spotify.SpotifyProtos.Image;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class JsonUtilTest {

  @Test
  public void testNewArtist() throws Exception {
    String json = readTestData("artist.json");
    Artist artist = JsonUtil.newArtist(json);

    assertEquals("https://api.spotify.com/v1/artists/0LcJLqbBmaGUft1e9Mm8HV", artist.getApiLink());

    List<String> genres = artist.getGenresList();
    assertTrue(genres.contains("Swedish Pop/Rock"));
    assertTrue(genres.contains("Club/Dance"));

    assertEquals("0LcJLqbBmaGUft1e9Mm8HV", artist.getId());

    Artist.Images artistImages = artist.getImages();
    assertEquals("https://d3rt1990lpmkn.cloudfront.net/original/7cccb8fe572f8d76e7d3e11e249688ed3c028aed", artistImages.getSMALL().getImageUrl());
    assertEquals(64, artistImages.getSMALL().getHeight());
    assertEquals(64, artistImages.getSMALL().getWidth());
    assertEquals("https://d3rt1990lpmkn.cloudfront.net/original/2e4302151e258b731a731d909f148c99606a0e3c", artistImages.getMEDIUM().getImageUrl());
    assertEquals(200, artistImages.getMEDIUM().getHeight());
    assertEquals(200, artistImages.getMEDIUM().getWidth());
    assertEquals("https://d3rt1990lpmkn.cloudfront.net/original/c8bf08f8ffc0402b930154368258369612b67e88", artistImages.getLARGE().getImageUrl());
    assertEquals(639, artistImages.getLARGE().getHeight());
    assertEquals(640, artistImages.getLARGE().getWidth());

    assertEquals("https://open.spotify.com/artist/0LcJLqbBmaGUft1e9Mm8HV", artist.getLink());
    assertEquals("ABBA", artist.getName());
    assertEquals(65, artist.getPopularity());
    assertEquals("spotify:artist:0LcJLqbBmaGUft1e9Mm8HV", artist.getSpotifyUri());
    assertEquals("artist", artist.getType());
  }

  @Test
  public void testNewArtistsList() throws Exception {
    String json = readTestData("artists.json");
    List<Artist> artists = JsonUtil.newArtistList(json);
    assertEquals(2, artists.size());

    Artist firstArtist = artists.get(0);
    assertEquals("https://api.spotify.com/v1/artists/0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getApiLink());

    Artist secondArtist = artists.get(1);
    assertEquals("https://api.spotify.com/v1/artists/3dBVyJ7JuOMt4GE9607Qin", secondArtist.getApiLink());
  }

  private String TEST_DATA_DIR = "src/test/fixtures/";

  private int MAX_TEST_DATA_FILE_SIZE = 65536;

  private String readTestData(String fileName) throws IOException {
    return readFromFile(new File(TEST_DATA_DIR, fileName));
  }

  private String readFromFile(File file) throws IOException {
    Reader reader = new FileReader(file);
    CharBuffer charBuffer = CharBuffer.allocate(MAX_TEST_DATA_FILE_SIZE);
    reader.read(charBuffer);
    charBuffer.position(0);
    return charBuffer.toString();
  }

}
