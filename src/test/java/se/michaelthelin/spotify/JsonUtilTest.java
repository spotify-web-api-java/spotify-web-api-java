package se.michaelthelin.spotify;

import org.junit.Test;
import se.michaelthelin.spotify.SpotifyProtos.Artist;
import se.michaelthelin.spotify.SpotifyProtos.Album;
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

    List<String> genresFirstArtist = firstArtist.getGenresList();
    assertTrue(genresFirstArtist.contains("Mod"));
    assertTrue(genresFirstArtist.contains("Club/Dance"));

    assertEquals("0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getId());

    Artist.Images artistImages = firstArtist.getImages();

    Image smallImage = artistImages.getSMALL();
    assertEquals(45, smallImage.getHeight());
    assertEquals(64, smallImage.getWidth());
    assertEquals("https://d3rt1990lpmkn.cloudfront.net/original/5b2b602e58962bc09e38ee7aa2b74d261aa5bce0", smallImage.getImageUrl());

    Image mediumImage = artistImages.getMEDIUM();
    assertEquals(141, mediumImage.getHeight());
    assertEquals(199, mediumImage.getWidth());
    assertEquals("https://d3rt1990lpmkn.cloudfront.net/original/3326e1f51f1eb147c3b24a4b828ed5f1191e09db", mediumImage.getImageUrl());

    Image largeImage = artistImages.getLARGE();
    assertEquals(453, largeImage.getHeight());
    assertEquals(640, largeImage.getWidth());
    assertEquals("https://d3rt1990lpmkn.cloudfront.net/original/341f90bcf363b2577dacdf4c4e9b828c51c5b18a", largeImage.getImageUrl());

    Image xLargeImage = artistImages.getXLARGE();
    assertEquals(707, xLargeImage.getHeight());
    assertEquals(1000, xLargeImage.getWidth());
    assertEquals("https://d3rt1990lpmkn.cloudfront.net/original/407f471e15ac91b153b4ec81bac3973bde221bb8", xLargeImage.getImageUrl());

    assertEquals("https://open.spotify.com/artist/0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getLink());
    assertEquals("David Bowie", firstArtist.getName());
    assertEquals(78, firstArtist.getPopularity());
    assertEquals("spotify:artist:0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getSpotifyUri());
    assertEquals("artist", firstArtist.getType());

    Artist secondArtist = artists.get(1);
    assertEquals("https://api.spotify.com/v1/artists/3dBVyJ7JuOMt4GE9607Qin", secondArtist.getApiLink());

    List<String> genresSecondArtist = secondArtist.getGenresList();
    assertTrue(genresSecondArtist.contains("Album Rock"));
    assertTrue(genresSecondArtist.contains("Hard Rock"));
  }

  @Test
  public void testNewAlbum() throws Exception {
    String json = readTestData("album.json");
    Album album = JsonUtil.newAlbum(json);
    assertEquals("https://api.spotify.com/v1/albums/0sNOF9WDwhWunNAHPD3Baj", album.getApiLink());
  }

  @Test
  public void testNewAlbumsList() throws Exception {
    String json = readTestData("albums.json");
    List<Album> albums = JsonUtil.newAlbumsList(json);

    assertEquals(2, albums.size());
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
