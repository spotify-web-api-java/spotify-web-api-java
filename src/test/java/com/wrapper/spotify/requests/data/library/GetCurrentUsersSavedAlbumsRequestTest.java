package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedAlbum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetCurrentUsersSavedAlbumsRequestTest implements ITest<Paging<SavedAlbum>> {
  private final GetCurrentUsersSavedAlbumsRequest successRequest = SPOTIFY_API
          .getCurrentUsersSavedAlbums()
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/library/GetCurrentUsersSavedAlbumsRequest.json"))
          .build();

  public GetCurrentUsersSavedAlbumsRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Paging<SavedAlbum>) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Paging<SavedAlbum> savedAlbumPaging) {
    assertEquals(
            "https://api.spotify.com/v1/me/albums?offset=5&limit=10",
            savedAlbumPaging.getHref());
    assertEquals(
            0,
            savedAlbumPaging.getItems().length);
    assertEquals(
            10,
            (int) savedAlbumPaging.getLimit());
    assertNull(
            savedAlbumPaging.getNext());
    assertEquals(
            5,
            (int) savedAlbumPaging.getOffset());
    assertEquals(
            "https://api.spotify.com/v1/me/albums?offset=0&limit=10",
            savedAlbumPaging.getPrevious());
    assertEquals(
            0,
            (int) savedAlbumPaging.getTotal());
  }
}
