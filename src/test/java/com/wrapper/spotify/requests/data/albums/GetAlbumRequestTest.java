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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GetAlbumRequestTest implements ITest<Album> {
  private final GetAlbumRequest successRequest = SPOTIFY_API.getAlbum("4pox3k0CGuwwAknR9GtcoX")
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/albums/GetAlbumRequest.json"))
          .build();

  public GetAlbumRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Album) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Album album) {
    assertEquals(
            AlbumType.ALBUM,
            album.getAlbumType());
    assertNotNull(
            album.getArtists());
    assertEquals(
            62,
            album.getAvailableMarkets().length);
    assertNotNull(
            album.getCopyrights());
    assertNotNull(
            album.getExternalIds());
    assertNotNull(
            album.getExternalUrls());
    assertEquals(
            0,
            album.getGenres().length);
    assertEquals(
            "https://api.spotify.com/v1/albums/4pox3k0CGuwwAknR9GtcoX",
            album.getHref());
    assertEquals(
            "4pox3k0CGuwwAknR9GtcoX",
            album.getId());
    assertEquals(
            3,
            album.getImages().length);
    assertEquals(
            "Epic/Legacy",
            album.getLabel());
    assertEquals(
            "She's So Unusual: A 30th Anniversary Celebration (Deluxe Edition)",
            album.getName());
    assertEquals(
            70,
            (int) album.getPopularity());
    assertEquals(
            "2014-03-28",
            album.getReleaseDate());
    assertEquals(
            ReleaseDatePrecision.DAY,
            album.getReleaseDatePrecision());
    assertEquals(
            22,
            album.getTracks().getItems().length);
    assertEquals(
            ModelObjectType.ALBUM,
            album.getType());
    assertEquals(
            "spotify:album:4pox3k0CGuwwAknR9GtcoX",
            album.getUri());
  }
}
