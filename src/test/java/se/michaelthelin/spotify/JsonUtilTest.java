package se.michaelthelin.spotify;

import org.junit.Test;
import se.michaelthelin.spotify.SpotifyProtos.Artist;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

import static org.junit.Assert.assertEquals;

public class JsonUtilTest {

  @Test
  public void testNewArtistList() throws IOException {
    String json = readTestData("artist.json");
    Artist artist = JsonUtil.newArtist(json);
    assertEquals("https://api.spotify.com/v1/artists/0LcJLqbBmaGUft1e9Mm8HV", artist.getApiLink());
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
