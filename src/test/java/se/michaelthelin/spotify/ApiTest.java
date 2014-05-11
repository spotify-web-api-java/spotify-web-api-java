package se.michaelthelin.spotify;

import org.junit.Test;
import se.michaelthelin.spotify.methods.Request;
import se.michaelthelin.spotify.UtilProtos.Url.Scheme;
import se.michaelthelin.spotify.UtilProtos.Url.Parameter;
import se.michaelthelin.spotify.UtilProtos.Url;
import se.michaelthelin.spotify.models.AlbumType;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

public class ApiTest {

  @Test
  public void shouldCreateAGetAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("https://api.spotify.com:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
  }

  @Test
  public void shouldCreateAGetArtistUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getArtist("5rSXSAkZ67PYJSvpUpkOr7").build();
    assertEquals("https://api.spotify.com:443/v1/artists/5rSXSAkZ67PYJSvpUpkOr7", request.toString());
  }

  @Test
  public void shouldCreateAGetTrackUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getTrack("6hDH3YWFdcUNQjubYztIsG").build();
    assertEquals("https://api.spotify.com:443/v1/tracks/6hDH3YWFdcUNQjubYztIsG", request.toString());
  }

  @Test
  public void shouldCreateAGetAlbumsUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbums("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv").build();
    assertEquals("https://api.spotify.com:443/v1/albums", request.toString());
    assertHasParameter(request.toUrl(), "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetAlbumsUrlFromAList() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbums(Arrays.asList("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv")).build();
    assertEquals("https://api.spotify.com:443/v1/albums", request.toString());
    assertHasParameter(request.toUrl(), "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetTracksUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getTracks("6hDH3YWFdcUNQjubYztIsG","2IA4WEsWAYpV9eKkwR2UYv").build();
    assertEquals("https://api.spotify.com:443/v1/tracks", request.toString());
    assertHasParameter(request.toUrl(), "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetTracksUrlFromList() {
    Api api = Api.DEFAULT_API;
    Request request = api.getTracks(Arrays.asList("6hDH3YWFdcUNQjubYztIsG","2IA4WEsWAYpV9eKkwR2UYv")).build();
    assertEquals("https://api.spotify.com:443/v1/tracks", request.toString());
    assertHasParameter(request.toUrl(), "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAUrlForArtistsAlbum() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
  }

  @Test
  public void shouldHaveAlbumTypeParametersInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(AlbumType.ALBUM, AlbumType.SINGLE).build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
    assertHasParameter(request.toUrl(), "album_type", "ALBUM,SINGLE");
  }

  @Test
  public void shouldHaveLimitParameterInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").limit(2).build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
    assertHasParameter(request.toUrl(), "limit", "2");
  }

  @Test
  public void shouldHaveOffsetParameterInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").offset(5).build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
    assertHasParameter(request.toUrl(), "offset", "5");
  }

  @Test
  public void shouldHaveSeveralQueryParametersAtTheSameTimeInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(AlbumType.SINGLE).limit(2).offset(5).build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
    assertHasParameter(request.toUrl(), "offset", "5");
    assertHasParameter(request.toUrl(), "limit", "2");
    assertHasParameter(request.toUrl(), "album_type", "SINGLE");
  }

  @Test
  public void shouldCreateAGetArtistsUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getArtists("4AK6F7OLvEQ5QYCBNiQWHq", "6rEzedK7cKWjeQWdAYvWVG").build();
    assertEquals("https://api.spotify.com:443/v1/artists", request.toString());
    assertHasParameter(request.toUrl(), "ids", "4AK6F7OLvEQ5QYCBNiQWHq,6rEzedK7cKWjeQWdAYvWVG");
  }

  @Test
  public void shouldCreateSearchUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchTracks("moulat swalf").build();
    assertEquals("https://api.spotify.com:443/v1/tracks/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "moulat+swalf");
  }

  @Test
  public void shouldCreateSearchUrlForAlbum() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchAlbums("meeep").build();
    assertEquals("https://api.spotify.com:443/v1/albums/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "meeep");
  }

  @Test
  public void shouldCreateSearchUrlForArtist() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchArtists("meeep").build();
    assertEquals("https://api.spotify.com:443/v1/artists/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "meeep");
  }

  @Test
  public void shouldCreateSearchUrlWithLimitParameter() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchTracks("moulat swalf").limit(2).build();
    assertEquals("https://api.spotify.com:443/v1/tracks/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "moulat+swalf");
    assertHasParameter(request.toUrl(), "limit", "2");
  }

  @Test
  public void shouldCreateSearchUrlWithOffsetParameter() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchTracks("moulat swalf").offset(2).build();
    assertEquals("https://api.spotify.com:443/v1/tracks/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "moulat+swalf");
    assertHasParameter(request.toUrl(), "offset", "2");
  }

  @Test
  public void shouldModifySchemeInUrl() {
    Api api = Api.builder().scheme(Scheme.HTTP).build();
    Request request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("http://api.spotify.com:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
  }

  @Test
  public void shouldModifyPortInUrl() {
    Api api = Api.builder().port(8080).build();
    Request request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("https://api.spotify.com:8080/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
  }

  @Test
  public void shouldModifyHostInUrl() {
    Api api = Api.builder().host("www.michaelthelin.se").build();
    Request request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("https://www.michaelthelin.se:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
  }

  void assertHasParameter(Url url, String name, Object value) {
    Parameter expected = Parameter.newBuilder().setName(name).setValue(value.toString()).build();
    for (Parameter actual : url.getParametersList()) {
      if (actual.equals(expected)) {
        return;
      }
    }
    fail(String.format("Actual URL %s does not contain parameter %s", url, expected));
  }

}
