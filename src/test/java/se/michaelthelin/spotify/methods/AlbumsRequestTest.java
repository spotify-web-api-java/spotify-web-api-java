package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtilTest;
import se.michaelthelin.spotify.SpotifyProtos.Album;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlbumsRequestTest {

  @Test
  public void shouldGetAlbumResultForIds() throws Exception {
    Api api = Api.DEFAULT_API;
    AlbumsRequest request = api.albums().id("41MnTivkwTO3UUJ8DrqEJJ", "6JWc4iAiJ9FjyK0B59ABb4").build();

    // Mock response
    String albumResponseFixture = JsonUtilTest.readTestData("albums.json");
    AlbumsRequest spy = spy(request);
    when(spy.getJson()).thenReturn(albumResponseFixture);

    ListenableFuture<List<Album>> albumsFuture = spy.getAlbums();
    Futures.addCallback(albumsFuture, new FutureCallback<List<Album>>() {

      @Override
      public void onSuccess(List<Album> albums) {
        assertEquals(2, albums.size());

        Album firstAlbum = albums.get(0);
        assertEquals("41MnTivkwTO3UUJ8DrqEJJ", firstAlbum.getId());

        Album secondAlbum = albums.get(1);
        assertEquals("6JWc4iAiJ9FjyK0B59ABb4", secondAlbum.getId());
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });


  }

  @Test
  public void shouldGetAlbumResultForArtistId() throws Exception {
    Api api = Api.DEFAULT_API;
    AlbumsRequest request = api.albums().forArtist("0oSGxfWSnnOXhD2fKuz2Gy").build();

    // Mock response
    String albumResponseFixture = JsonUtilTest.readTestData("albums.json");
    AlbumsRequest spy = spy(request);
    when(spy.getJson()).thenReturn(albumResponseFixture);

    ListenableFuture<List<Album>> albumsFuture = spy.getAlbums();

    Futures.addCallback(albumsFuture, new FutureCallback<List<Album>>() {
      @Override
      public void onSuccess(List<Album> albums) {
        assertEquals(2, albums.size());

        Album firstAlbum = albums.get(0);
        assertEquals("41MnTivkwTO3UUJ8DrqEJJ", firstAlbum.getId());

        Album secondAlbum = albums.get(1);
        assertEquals("6JWc4iAiJ9FjyK0B59ABb4", secondAlbum.getId());
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });
  }

}
