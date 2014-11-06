package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestConfiguration;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.PlaylistTrack;
import com.wrapper.spotify.models.Track;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class PlaylistTracksRequestTest {

   @Test
   public void shouldGetTracksResult_async() throws Exception {
      final Api api = Api.builder().accessToken("BQB80WY65BsA7G0yUzYV_pCVbdfU_gVUgq43M8Dh-Av-70cz1KCMjEEkrSW8AGKCpfAS4eqG3xwcZr5rcvuyEFcGE_PgaEvRt8Ol19oOCdUnXQL3j6bzJ7uiUfMozqmVsqI2TGhjwm2eHa10ppO28ur99W0").build();

      final PlaylistTracksRequest.Builder requestBuilder = api
              .getPlaylistTracks("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn");

      if (TestConfiguration.USE_MOCK_RESPONSES) {
         requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("playlist-tracks.json"));
      }
      final PlaylistTracksRequest request = requestBuilder.build();

      final CountDownLatch asyncCompleted = new CountDownLatch(1);

      final SettableFuture<Page<PlaylistTrack>> playlistTracksPageFuture = request.getAsync();

      Futures.addCallback(playlistTracksPageFuture, new FutureCallback<Page<PlaylistTrack>>() {

         @Override
         public void onSuccess(Page<PlaylistTrack> page) {
            assertNotNull(page);
            assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn/tracks", page.getHref());
            assertEquals(100, page.getLimit());
            assertNull(page.getNext());
            assertEquals(0, page.getOffset());
            assertNull(page.getPrevious());
            assertTrue(page.getTotal() > 0);

            final PlaylistTrack playlistTrack = page.getItems().get(0);
            assertNotNull(playlistTrack.getAddedAt());
            assertNotNull(playlistTrack.getAddedBy());

            final Track track = playlistTrack.getTrack();
            assertTrue(track.getPopularity() >= 0);

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
   public void shouldGetStarredResult_async() throws Exception {
      final Api api = Api.builder().accessToken("BQB80WY65BsA7G0yUzYV_pCVbdfU_gVUgq43M8Dh-Av-70cz1KCMjEEkrSW8AGKCpfAS4eqG3xwcZr5rcvuyEFcGE_PgaEvRt8Ol19oOCdUnXQL3j6bzJ7uiUfMozqmVsqI2TGhjwm2eHa10ppO28ur99W0").build();

      final PlaylistTracksRequest.Builder requestBuilder = api
              .getStarred("thelinmichael");

      if (TestConfiguration.USE_MOCK_RESPONSES) {
         requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("playlist-tracks.json"));
      }
      final PlaylistTracksRequest request = requestBuilder.build();

      final CountDownLatch asyncCompleted = new CountDownLatch(1);

      final SettableFuture<Page<PlaylistTrack>> playlistTracksPageFuture = request.getAsync();

      Futures.addCallback(playlistTracksPageFuture, new FutureCallback<Page<PlaylistTrack>>() {

         @Override
         public void onSuccess(Page<PlaylistTrack> page) {
            assertNotNull(page);
            assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn/tracks", page.getHref());
            assertEquals(100, page.getLimit());
            assertNull(page.getNext());
            assertEquals(0, page.getOffset());
            assertNull(page.getPrevious());
            assertTrue(page.getTotal() > 0);

            final PlaylistTrack playlistTrack = page.getItems().get(0);
            assertNotNull(playlistTrack.getAddedAt());
            assertNotNull(playlistTrack.getAddedBy());

            final Track track = playlistTrack.getTrack();
            assertTrue(track.getPopularity() >= 0);

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
  public void shouldGetTracksResult_sync() throws Exception {
    final Api api = Api.builder().accessToken("BQB80WY65BsA7G0yUzYV_pCVbdfU_gVUgq43M8Dh-Av-70cz1KCMjEEkrSW8AGKCpfAS4eqG3xwcZr5rcvuyEFcGE_PgaEvRt8Ol19oOCdUnXQL3j6bzJ7uiUfMozqmVsqI2TGhjwm2eHa10ppO28ur99W0").build();

    final PlaylistTracksRequest.Builder requestBuilder = api
            .getPlaylistTracks("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn");

    if (TestConfiguration.USE_MOCK_RESPONSES) {
      requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("playlist-tracks.json"));
    }
    final PlaylistTracksRequest request = requestBuilder.build();

    final Page<PlaylistTrack> page = request.get();

    assertNotNull(page);
    assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn/tracks", page.getHref());
    assertEquals(100, page.getLimit());
    assertNull(page.getNext());
    assertEquals(0, page.getOffset());
    assertNull(page.getPrevious());
    assertTrue(page.getTotal() > 0);

    final PlaylistTrack playlistTrack = page.getItems().get(0);
    assertNotNull(playlistTrack.getAddedAt());
    assertNotNull(playlistTrack.getAddedBy());

    final Track track = playlistTrack.getTrack();
    assertTrue(track.getPopularity() >= 0);
  }

   @Test
   public void shouldGetStarredResult_sync() throws Exception {
       final Api api = Api.builder().accessToken("BQB80WY65BsA7G0yUzYV_pCVbdfU_gVUgq43M8Dh-Av-70cz1KCMjEEkrSW8AGKCpfAS4eqG3xwcZr5rcvuyEFcGE_PgaEvRt8Ol19oOCdUnXQL3j6bzJ7uiUfMozqmVsqI2TGhjwm2eHa10ppO28ur99W0").build();

       final PlaylistTracksRequest.Builder requestBuilder = api
               .getStarred("thelinmichael");

       if (TestConfiguration.USE_MOCK_RESPONSES) {
           requestBuilder.httpManager(TestUtil.MockedHttpManager.returningJson("playlist-tracks.json"));
       }
       final PlaylistTracksRequest request = requestBuilder.build();

       final Page<PlaylistTrack> page = request.get();

       assertNotNull(page);
       assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn/tracks", page.getHref());
       assertEquals(100, page.getLimit());
       assertNull(page.getNext());
       assertEquals(0, page.getOffset());
       assertNull(page.getPrevious());
       assertTrue(page.getTotal() > 0);

       final PlaylistTrack playlistTrack = page.getItems().get(0);
       assertNotNull(playlistTrack.getAddedAt());
       assertNotNull(playlistTrack.getAddedBy());

       final Track track = playlistTrack.getTrack();
       assertTrue(track.getPopularity() >= 0);
   }


}
