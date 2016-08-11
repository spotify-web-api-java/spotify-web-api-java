package com.wrapper.spotify;

import com.wrapper.spotify.UtilProtos.Url.Scheme;
import com.wrapper.spotify.methods.Request;
import com.wrapper.spotify.models.AlbumType;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.wrapper.spotify.Assertions.*;
import static junit.framework.TestCase.assertEquals;

public class ApiTest {

  @Test
  public void shouldCreateAGetAlbumUrl() {
    Api api = Api.DEFAULT_API;
    Request request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
    assertEquals("https://api.spotify.com:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
  }

  @Test
  public void shouldCreateGetAudioFeaturesUrl(){
    Api api = Api.DEFAULT_API;
    Request request = api.getAudioFeature("1hmNbafW4sAPNaGc7LeXAZ").build();
    assertEquals("https://api.spotify.com:443/v1/audio-features/1hmNbafW4sAPNaGc7LeXAZ", request.toString());
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
    Request request = api.getTracks("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv").build();
    assertEquals("https://api.spotify.com:443/v1/tracks", request.toString());
    assertHasParameter(request.toUrl(), "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetTracksUrlFromList() {
    Api api = Api.DEFAULT_API;
    Request request = api.getTracks(Arrays.asList("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv")).build();
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
    Request request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq")
        .types(AlbumType.ALBUM, AlbumType.SINGLE)
        .market("SE")
        .build();

    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
    assertHasParameter(request.toUrl(), "album_type", "ALBUM,SINGLE");
    assertHasParameter(request.toUrl(), "market", "SE");
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
    Request request = api.searchAlbums("meeep").market("from_token").build();
    assertEquals("https://api.spotify.com:443/v1/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "meeep");
    assertHasParameter(request.toUrl(), "type", "album");
    assertHasParameter(request.toUrl(), "market", "from_token");
  }

  @Test
  public void shouldCreateSearchUrlForArtist() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchArtists("meeep").market("GB").build();
    assertEquals("https://api.spotify.com:443/v1/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "meeep");
    assertHasParameter(request.toUrl(), "type", "artist");
    assertHasParameter(request.toUrl(), "market", "GB");
  }

  @Test
  public void shouldCreateSearchUrlWithLimitParameter() {
    Api api = Api.DEFAULT_API;
    Request request = api.searchTracks("moulat swalf").limit(2).market("SE").build();
    assertEquals("https://api.spotify.com:443/v1/search", request.toString());
    assertHasParameter(request.toUrl(), "q", "moulat swalf");
    assertHasParameter(request.toUrl(), "limit", "2");
    assertHasParameter(request.toUrl(), "type", "track");
    assertHasParameter(request.toUrl(), "market", "SE");
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
    final Api api = Api.builder().accessToken(accessToken).build();

    final Request request = api.getPlaylistsForUser("wizzler").build();

    assertEquals("https://api.spotify.com:443/v1/users/wizzler/playlists", request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateRequestForTokensUrl() {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String redirectURI = "myRedirectUri";
    final String code = "returnedCode";

    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .redirectURI(redirectURI)
            .build();

    final Request request = api.authorizationCodeGrant(code).build();

    assertEquals("https://accounts.spotify.com:443/api/token", request.toString());
    assertHasBodyParameter(request.toUrl(), "grant_type", "authorization_code");
    assertHasBodyParameter(request.toUrl(), "code", code);
    assertHasBodyParameter(request.toUrl(), "redirect_uri", redirectURI);

    final String idSecret = clientId + ":" + clientSecret;
    assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
  }

  @Test
  public void shouldCreateRefreshAccessTokenUrl() {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String refreshToken = "myRefreshToken";

    final Api api = Api
            .builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .refreshToken(refreshToken)
            .build();

    final Request request = api.refreshAccessToken().build();

    assertEquals("https://accounts.spotify.com:443/api/token", request.toString());
    assertHasBodyParameter(request.toUrl(), "grant_type", "refresh_token");
    assertHasBodyParameter(request.toUrl(), "refresh_token", refreshToken);

    final String idSecret = clientId + ":" + clientSecret;
    assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
  }

  @Test
  public void shouldCreatePlaylistLookupUrl() {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().accessToken(accessToken).build();

    final String playlistId = "3ktAYNcRHpazJ9qecm3ptn";
    final String userId = "thelinmichael";

    final Request request = api.getPlaylist(userId, playlistId).build();

    assertEquals("https://api.spotify.com:443/v1/users/" + userId + "/playlists/" + playlistId, request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateCurrentUserLookupUrl() {
    final String accessToken = "myVeryLongAccessToken";

    final Api api = Api.builder().accessToken(accessToken).build();

    final Request request = api.getMe().build();

    assertEquals("https://api.spotify.com:443/v1/me", request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateCreatePlaylistUrl() {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().accessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String title = "The greatest playlist ever";
    final boolean publicAccess = true;

    final Request request = api.createPlaylist(myUsername, title).publicAccess(publicAccess).build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists", request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasHeader(request.toUrl(), "Content-Type", "application/json");
    assertHasJsonBody(request.toUrl(), "{\"name\":\"The greatest playlist ever\",\"public\":\"true\"}");
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateAddTrackToPlaylistUrl() {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().accessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final List<String> tracksToAdd = Arrays.asList("spotify:track:4BYGxv4rxSNcTgT3DsFB9o","spotify:tracks:0BG2iE6McPhmAEKIhfqy1X");
    final int insertIndex = 3;

    final Request request = api.addTracksToPlaylist(myUsername, myPlaylistId, tracksToAdd).position(insertIndex).build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId + "/tracks", request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasHeader(request.toUrl(), "Content-Type", "application/json");
    assertHasJsonBody(request.toUrl(), "[\"spotify:track:4BYGxv4rxSNcTgT3DsFB9o\",\"spotify:tracks:0BG2iE6McPhmAEKIhfqy1X\"]");
    assertHasParameter(request.toUrl(), "position", String.valueOf(insertIndex));
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateChangePlaylistDetailsUrl() {
    final String accessToken = "myVeryLongAccessToken";
    final Api api = Api.builder().accessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";

    final boolean isPublic = false;
    final String name = "Testing name change";

    final Request request = api.changePlaylistDetails(myUsername, myPlaylistId)
        .publicAccess(isPublic)
        .name(name)
        .build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId,
                 request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasHeader(request.toUrl(), "Content-Type", "application/json");

    JSONObject jsonBody = JSONObject.fromObject(request.toUrl().getJsonBody().toString());
    assertEquals(name, jsonBody.getString("name"));
    assertEquals(isPublic, jsonBody.getBoolean("public"));

    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateClientCredentialsGrantUrl() {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";

    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();

    final List<String> scopes = Arrays.asList("some-scope", "some-other-scope");

    final Request request = api.clientCredentialsGrant().scopes(scopes).build();

    assertEquals("https://accounts.spotify.com:443/api/token", request.toString());

    assertHasBodyParameter(request.toUrl(), "grant_type", "client_credentials");
    assertHasBodyParameter(request.toUrl(), "scope", "some-scope some-other-scope");

    final String idSecret = clientId + ":" + clientSecret;
    assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
  }

  @Test
  public void shouldCreateAGetPlaylistTracksURL() {
    final String accessToken = "myAccessToken";
    final String userId = "thelinmichael";
    final String playlistId = "5ieJqeLJjjI8iJWaxeBLuK";

    final Api api = Api.builder()
            .accessToken(accessToken)
            .build();

    final Request request = api
            .getPlaylistTracks(userId, playlistId)
            .fields("items")
            .limit(20)
            .offset(1)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/" + userId + "/playlists/" + playlistId + "/tracks", request.toString());
    assertHasParameter(request.toUrl(), "fields", "items");
    assertHasParameter(request.toUrl(), "limit", "20");
    assertHasParameter(request.toUrl(), "offset", "1");
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateRelatedArtistsURL() {
    final Api api = Api.DEFAULT_API;

    final String artistId = "0qeei9KQnptjwb8MgkqEoy";

    final Request request = api.getArtistRelatedArtists(artistId).build();

    assertEquals("https://api.spotify.com:443/v1/artists/" + artistId + "/related-artists", request.toString());
  }

  @Test
  public void shouldCreateAuthorizeURL() {
    final String redirectURI = "http://www.michaelthelin.se/test-callback";
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";

    final Api api = Api.builder()
            .clientId(clientId)
            .redirectURI(redirectURI)
            .build();

    final List<String> scopes = Arrays.asList("user-read-private", "user-read-email");
    final String state = "someExpectedStateString";

    String authorizeURL = api.createAuthorizeURL(scopes, state);
    assertEquals("https://accounts.spotify.com:443/authorize?client_id=fcecfc79122e4cd299473677a17cbd4d&response_type=code&redirect_uri=http://www.michaelthelin.se/test-callback&scope=user-read-private%20user-read-email&state=someExpectedStateString", authorizeURL);
  }

  @Test
  public void shouldCreateAuthorizeUrlWithOptionalParameters() {
    final String redirectURI = "http://www.michaelthelin.se/test-callback";
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";

    final Api api = Api.builder()
        .clientId(clientId)
        .redirectURI(redirectURI)
        .build();

    final List<String> scopes = Arrays.asList("user-read-private", "user-read-email");
    final String state = "someExpectedStateString";

    String authorizeURL = api.createAuthorizeURL(scopes).
        state(state).
        showDialog(false).
        build().
        toStringWithQueryParameters();

    assertEquals("https://accounts.spotify.com:443/authorize?client_id=fcecfc79122e4cd299473677a17cbd4d&response_type=code&redirect_uri=http://www.michaelthelin.se/test-callback&scope=user-read-private%20user-read-email&state=someExpectedStateString&show_dialog=false", authorizeURL);
  }

  @Test
  public void shouldCreateGetMyTracksURL() {
    final String accessToken = "myAccessToken";

    final Api api = Api.builder()
        .accessToken(accessToken)
        .build();

    final Request request = api
        .getMySavedTracks()
        .limit(5)
        .offset(1)
        .build();

    assertEquals("https://api.spotify.com:443/v1/me/tracks", request.toString());
    assertHasParameter(request.toUrl(), "limit", "5");
    assertHasParameter(request.toUrl(), "offset", "1");
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreatePutTracksURL() {
    final String accessToken = "myAccessToken";

    final Api api = Api.builder()
        .accessToken(accessToken)
        .build();

    final Request request = api
        .addToMySavedTracks(Arrays.asList("test", "test2"))
        .build();

    assertEquals("https://api.spotify.com:443/v1/me/tracks", request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateRemoveTracksURL() {
    final String accessToken = "myAccessToken";

    final Api api = Api.builder()
        .accessToken(accessToken)
        .build();

    final Request request = api
        .removeFromMySavedTracks(Arrays.asList("test", "test2"))
        .build();

    assertEquals("https://api.spotify.com:443/v1/me/tracks", request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateGetNewReleasesRequest() {
    final String accessToken = "myAccessToken";

    final Api api = Api.builder()
            .accessToken(accessToken)
            .build();

    final Request request = api.getNewReleases().limit(4).offset(1).country("SE").build();

    assertEquals("https://api.spotify.com:443/v1/browse/new-releases", request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasParameter(request.toUrl(), "limit", "4");
    assertHasParameter(request.toUrl(), "offset", "1");
    assertHasParameter(request.toUrl(), "country", "SE");
  }

  @Test
  public void shouldCreateFeaturedPlaylistsRequest() {
    final String accessToken = "myAccessToken";

    final Api api = Api.builder()
        .accessToken(accessToken)
        .build();


    Calendar calendar = Calendar.getInstance();
    calendar.set(2014, 11, 22, 13, 59, 30);
    Date timestamp = calendar.getTime();

    final Request request = api
        .getFeaturedPlaylists()
        .country("SE")
        .locale("es_MX")
        .limit(5)
        .offset(1)
        .timestamp(timestamp)
        .build();

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    assertEquals("https://api.spotify.com:443/v1/browse/featured-playlists", request.toString());
    assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
    assertHasParameter(request.toUrl(), "limit", "5");
    assertHasParameter(request.toUrl(), "offset", "1");
    assertHasParameter(request.toUrl(), "country", "SE");
    assertHasParameter(request.toUrl(), "locale", "es_MX");
    assertHasParameter(request.toUrl(), "timestamp", format.format(timestamp));
  }

}
