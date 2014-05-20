package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.*;
import se.michaelthelin.spotify.models.Page;
import se.michaelthelin.spotify.models.SimplePlaylist;
import se.michaelthelin.spotify.models.SpotifyEntityType;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.*;

public class UserPlaylistsRequestTest {

  @Test
  public void shouldGetPlaylistsForUser_async() throws UnexpectedResponseException, BadFieldException, NotFoundException, IOException, InterruptedException, ErrorResponseException {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().build();

    final UserPlaylistsRequest request = api
            .getPlaylistsForUser("wizzler")
            .accessToken(accessToken)
            .httpManager(TestUtil.MockedHttpManager.returningJson("user-playlists.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Page<SimplePlaylist>> playlistsPageFuture = request.getAsync();

    Futures.addCallback(playlistsPageFuture, new FutureCallback<Page<SimplePlaylist>>() {
      @Override
      public void onSuccess(Page<SimplePlaylist> playlistsPage) {
        assertTrue(playlistsPage.getTotal() >= 0);
        assertNotNull(playlistsPage.getNext());
        assertNotNull(playlistsPage.getPrevious());
        assertTrue(playlistsPage.getLimit() >= 0);
        assertEquals(0, playlistsPage.getOffset());
        assertEquals("https://api.spotify.com/v1/users/wizzler/playlists", playlistsPage.getHref());

        final SimplePlaylist simplePlaylist = playlistsPage.getItems().get(0);
        final String playlistId = simplePlaylist.getId();
        assertNotNull(playlistId);
        assertTrue(playlistId.length() > 0);
        assertEquals(false, simplePlaylist.isCollaborative());
        assertEquals("http://open.spotify.com/user/wizzler/playlists/" + playlistId, simplePlaylist.getExternalUrls().get("spotify"));
        assertNotNull(simplePlaylist.getName());
        assertNotNull(simplePlaylist.getOwner());
        assertNotNull(simplePlaylist.isPublicAccess());
        assertNotNull(simplePlaylist.getTracks().getHref());
        assertNotNull(simplePlaylist.getTracks().getTotal());
        assertEquals(SpotifyEntityType.PLAYLIST, simplePlaylist.getType());
        assertEquals("spotify:user:wizzler:playlist:" + playlistId, simplePlaylist.getUri());

        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }

    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetPlaylistsForUser_sync() throws UnexpectedResponseException, BadFieldException, NotFoundException, IOException, ErrorResponseException, NoCredentialsException {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().build();

    final UserPlaylistsRequest request = api
            .getPlaylistsForUser("wizzler")
            .accessToken(accessToken)
            .httpManager(TestUtil.MockedHttpManager.returningJson("user-playlists.json"))
            .build();


    final Page<SimplePlaylist> playlistsPage = request.get();

    assertTrue(playlistsPage.getTotal() >= 0);
    assertNotNull(playlistsPage.getNext());
    assertNotNull(playlistsPage.getPrevious());
    assertTrue(playlistsPage.getLimit() >= 0);
    assertEquals(0, playlistsPage.getOffset());
    assertEquals("https://api.spotify.com/v1/users/wizzler/playlists", playlistsPage.getHref());

    final SimplePlaylist simplePlaylist = playlistsPage.getItems().get(0);
    final String playlistId = simplePlaylist.getId();
    assertNotNull(playlistId);
    assertTrue(playlistId.length() > 0);
    assertEquals(false, simplePlaylist.isCollaborative());
    assertEquals("http://open.spotify.com/user/wizzler/playlists/" + playlistId, simplePlaylist.getExternalUrls().get("spotify"));
    assertNotNull(simplePlaylist.getName());
    assertNotNull(simplePlaylist.getOwner());
    assertNotNull(simplePlaylist.isPublicAccess());
    assertNotNull(simplePlaylist.getTracks().getHref());
    assertNotNull(simplePlaylist.getTracks().getTotal());
    assertEquals(SpotifyEntityType.PLAYLIST, simplePlaylist.getType());
    assertEquals("spotify:user:wizzler:playlist:" + playlistId, simplePlaylist.getUri());
  }

}
