package com.wrapper.spotify.requests.data.albums;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.AlbumType;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.enums.ReleaseDatePrecision;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAlbumRequestTest implements ITest<Album> {
  private final GetAlbumRequest defaultRequest = SPOTIFY_API.getAlbum("id")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/albums/GetAlbumRequest.json"))
          .build();

  public GetAlbumRequestTest() throws Exception {
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((Album) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Album album) {
    assertEquals(
            AlbumType.ALBUM,
            album.getAlbumType());
    assertNotNull(
            album.getArtists());
    assertEquals(
            57,
            album.getAvailableMarkets().length);
    assertEquals(
            1,
            album.getCopyrights().length);
    assertNotNull(
            album.getExternalIds());
    assertNotNull(
            album.getExternalUrls());
    assertEquals(
            0,
            album.getGenres().length);
    assertEquals(
            "https://api.spotify.com/v1/albums/0sNOF9WDwhWunNAHPD3Baj",
            album.getHref());
    assertEquals(
            "0sNOF9WDwhWunNAHPD3Baj",
            album.getId());
    assertEquals(
            3,
            album.getImages().length);
    assertNull(
            album.getLabel());
    assertEquals(
            "She's So Unusual",
            album.getName());
    assertEquals(
            39,
            (int) album.getPopularity());
    assertEquals(
            "1983",
            album.getReleaseDate());
    assertEquals(
            ReleaseDatePrecision.YEAR,
            album.getReleaseDatePrecision());
    assertNotNull(
            album.getTracks());
    assertEquals(
            ModelObjectType.ALBUM,
            album.getType());
    assertEquals(
            "spotify:album:0sNOF9WDwhWunNAHPD3Baj",
            album.getUri());
  }
}
