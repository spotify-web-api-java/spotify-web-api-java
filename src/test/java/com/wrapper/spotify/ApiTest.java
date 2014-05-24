package com.wrapper.spotify;

import com.wrapper.spotify.UtilProtos.Url.Scheme;
import com.wrapper.spotify.methods.Request;
import com.wrapper.spotify.models.AlbumType;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static com.wrapper.spotify.Assertions.*;

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
  public void shouldHaveMultipleAlbumTypeParametersInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(AlbumType.ALBUM, AlbumType.SINGLE).build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
    assertHasParameter(request.toUrl(), "album_type", "ALBUM,SINGLE");
  }

  @Test
  public void shouldHaveSingleAlbumTypeParametersInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(AlbumType.SINGLE).build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
    assertHasParameter(request.toUrl(), "album_type", "SINGLE");
  }

  @Test
  public void shouldFailIfAlbumTypeParametersIsInArtistsAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(AlbumType.SINGLE).build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
    assertHasParameter(request.toUrl(), "album_type", "SINGLE");
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
    assertEquals("https://api.spotify.com:443/v1/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "moulat swalf");
    assertHasParameter(request.toUrl(), "type", "track");
  }

  @Test
  public void shouldCreateSearchUrlForAlbum() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchAlbums("meeep").build();
    assertEquals("https://api.spotify.com:443/v1/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "meeep");
    assertHasParameter(request.toUrl(), "type", "album");
  }

  @Test
  public void shouldCreateSearchUrlForArtist() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchArtists("meeep").build();
    assertEquals("https://api.spotify.com:443/v1/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "meeep");
    assertHasParameter(request.toUrl(), "type", "artist");
  }

  @Test
  public void shouldCreateSearchUrlWithLimitParameter() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchTracks("moulat swalf").limit(2).build();
    assertEquals("https://api.spotify.com:443/v1/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "moulat swalf");
    assertHasParameter(request.toUrl(), "limit", "2");
    assertHasParameter(request.toUrl(), "type", "track");
  }

  @Test
  public void shouldCreateSearchUrlWithOffsetParameter() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchTracks("moulat swalf").offset(2).build();
    assertEquals("https://api.spotify.com:443/v1/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "moulat swalf");
    assertHasParameter(request.toUrl(), "offset", "2");
    assertHasParameter(request.toUrl(), "type", "track");
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
    Api api = Api.builder().host("www.wrapper.se").build();
    Request request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("https://www.wrapper.se:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
  }

  @Test
  public void shouldCreateTopTracksUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getTopTracksForArtist("0LcJLqbBmaGUft1e9Mm8HV", "GB").build();
    assertEquals("https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/toptracks", request.toString());
    assertHasParameter(request.toUrl(), "country", "GB");
  }

  @Test
  public void shouldCreateUserProfileUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getUser("wizzler").build();
    assertEquals("https://api.spotify.com:443/v1/users/wizzler", request.toString());
  }

  @Test
  public void shouldCreateUrlForListingAUsersPlaylists() throws Exception {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().build();

    final Request request = api.getPlaylistsForUser("wizzler").accessToken(accessToken).build();

    assertEquals("https://api.spotify.com:443/v1/users/wizzler/playlists", request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateRequestForTokensUrl() {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String code = "returnedCode";
    final String redirectUri = "myRedirectUri";

    final Api api = Api.DEFAULT_API;
    final Request request = api.getTokens(clientId, clientSecret, code, redirectUri).build();

    assertEquals("https://accounts.spotify.com:443/api/token", request.toString());
    assertHasBodyParameter(request.toUrl(), "grant_type", "authorization_code");
    assertHasBodyParameter(request.toUrl(), "code", code);
    assertHasBodyParameter(request.toUrl(), "redirect_uri", redirectUri);

    final String idSecret = clientId + ":" + clientSecret;
    assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
  }

  @Test
  public void shouldCreateRefreshAccessTokenUrl() {
    final Api api = Api.DEFAULT_API;

    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String refreshToken = "myRefreshToken";

    final Request request = api.refreshAccessToken(clientId, clientSecret, refreshToken).build();

    assertEquals("https://accounts.spotify.com:443/api/token", request.toString());
    assertHasBodyParameter(request.toUrl(), "grant_type", "refresh_token");
    assertHasBodyParameter(request.toUrl(), "refresh_token", refreshToken);

    final String idSecret = clientId + ":" + clientSecret;
    assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
  }

  @Test
  public void shouldCreatePlaylistLookupUrl() {
    final Api api = Api.DEFAULT_API;

    final String accessToken = "myVeryLongAccessToken";
    final String playlistId = "3ktAYNcRHpazJ9qecm3ptn";
    final String userId = "thelinmichael";

    final Request request = api.getPlaylist(playlistId, userId)
            .accessToken(accessToken)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/" + userId + "/playlists/" + playlistId, request.toString());
  }

  @Test
  public void shouldCreateCurrentUserLookupUrl() {
    final Api api = Api.DEFAULT_API;

    final String accessToken = "myVeryLongAccessToken";

    final Request request = api.getCurrentUser()
            .accessToken(accessToken)
            .build();

    assertEquals("https://api.spotify.com:443/v1/me", request.toString());
  }

  @Test
  public void shouldCreateCreatePlaylistUrl() {
    final Api api = Api.DEFAULT_API;

    final String accessToken = "myVeryLongAccessToken";
    final String myUsername = "thelinmichael";
    final String title = "The greatest playlist ever";
    final boolean publicAccess = true;

    final Request request = api.createPlaylist()
            .username(myUsername)
            .title(title)
            .publicAccess(publicAccess)
            .accessToken(accessToken)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists", request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasHeader(request.toUrl(), "Content-Type", "application/json");
    assertHasBodyParameter(request.toUrl(), "name", title);
    assertHasBodyParameter(request.toUrl(), "public", String.valueOf(publicAccess));
  }

  @Test
  public void shouldCreateAddTrackToPlaylistUrl() {
    final Api api = Api.DEFAULT_API;

    final String accessToken = "myVeryLongAccessToken";
    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final List<String> tracksToAdd = Arrays.asList("spotify:track:4BYGxv4rxSNcTgT3DsFB9o","spotify:tracks:0BG2iE6McPhmAEKIhfqy1X");
    final int insertIndex = 3;

    final Request request = api.addTracksToPlaylist(myPlaylistId, myUsername)
            .tracks(tracksToAdd)
            .position(insertIndex)
            .accessToken(accessToken)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId + "/tracks", request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasHeader(request.toUrl(), "Content-Type", "application/json");
    assertHasJsonBody(request.toUrl(), "[\"spotify:track:4BYGxv4rxSNcTgT3DsFB9o\",\"spotify:tracks:0BG2iE6McPhmAEKIhfqy1X\"]");
    assertHasParameter(request.toUrl(), "position", String.valueOf(insertIndex));
  }

}
