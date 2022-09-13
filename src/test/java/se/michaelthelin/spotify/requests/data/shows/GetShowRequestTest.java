package se.michaelthelin.spotify.requests.data.shows;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Show;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class GetShowRequestTest extends AbstractDataTest<Show> {
  private final GetShowRequest defaultRequest = SPOTIFY_API.getShow(ID_SHOW)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/shows/GetShowRequest.json"))
    .market(MARKET)
    .build();

  public GetShowRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/shows/5AvwZVawapvyhJUIx71pdJ?market=SE",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Show show) {
    assertEquals(
      77,
      show.getAvailableMarkets().length);
    assertEquals(
      0,
      show.getCopyrights().length);
    assertEquals(
      249,
      show.getDescription().length());
    assertNotNull(
      show.getEpisodes());
    assertEquals(
      1,
      show.getEpisodes().getItems().length);
    assertFalse(
      show.getExplicit());
    assertNotNull(
      show.getExternalUrls());
    assertEquals(
      "https://api.spotify.com/v1/shows/7iZEp9f2Gg8JvKNqK3t905",
      show.getHref());
    assertEquals(
      "7iZEp9f2Gg8JvKNqK3t905",
      show.getId());
    assertEquals(
      3,
      show.getImages().length);
    assertFalse(
      show.getExternallyHosted());
    assertEquals(
      1,
      show.getLanguages().length);
    assertEquals(
      "audio",
      show.getMediaType());
    assertEquals(
      "Acht Milliarden – Der Auslands-Podcast",
      show.getName());
    assertEquals(
      "DER SPIEGEL",
      show.getPublisher());
    assertEquals(
      ModelObjectType.SHOW,
      show.getType());
    assertEquals(
      "spotify:show:7iZEp9f2Gg8JvKNqK3t905",
      show.getUri());

  }
}
