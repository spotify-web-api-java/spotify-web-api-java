package com.wrapper.spotify;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.enums.AlbumType;
import com.wrapper.spotify.model_objects.special.PlaylistTrackPosition;
import com.wrapper.spotify.requests.IRequest;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.wrapper.spotify.Assertions.*;
import static org.junit.Assert.assertEquals;

public class ApiTest {

  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
  private String accessToken = "AccessToken";

  private static JsonObject getJsonFromPlaylistTrackPosition(PlaylistTrackPosition playlistTrackPosition) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("uri", playlistTrackPosition.getUri());
    if (playlistTrackPosition.getPositions() != null && playlistTrackPosition.getPositions().length != 0) {
      JsonArray positionArray = new JsonArray();
      for (int pos : playlistTrackPosition.getPositions()) {
        positionArray.add(pos);
      }
      jsonObject.add("positions", positionArray);
    }
    return jsonObject;
  }

  @Test
  public void shouldCreateAGetAlbumUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getAlbum("5oEljuMoe9MXH6tBIPbd5e")
            .build();
    assertEquals("https://api.spotify.com:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.getUri().toString());
  }

  @Test
  public void shouldCreateAGetAudioFeaturesUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getAudioFeature("1hmNbafW4sAPNaGc7LeXAZ")
            .build();
    assertEquals("https://api.spotify.com:443/v1/audio-features/1hmNbafW4sAPNaGc7LeXAZ", request.getUri().toString());
  }

  @Test
  public void shouldCreateAGetTracksForAlbumUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getTracksForAlbum("5oEljuMoe9MXH6tBIPbd5e")
            .build();
    String test = request.toString();
    assertEquals(
            "https://api.spotify.com:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e/tracks",
            request.getUri().toString()
    );
  }

  @Test
  public void shouldReplacePlaylistsTracks() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .replacePlaylistsTracks("userId", "5oEljuMoe9MXH6tBIPbd5e", new String[]{"spotify:track:4iV5W9uYEdYUVa79Axb7Rh"})
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/users/userId/playlists/5oEljuMoe9MXH6tBIPbd5e/tracks?uris=spotify%3Atrack%3A4iV5W9uYEdYUVa79Axb7Rh",
            request.getUri().toString()
    );
  }

  @Test
  public void shouldUnfollowPlaylist() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .unfollowPlaylist("userId", "5oEljuMoe9MXH6tBIPbd5e")
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/users/userId/playlists/5oEljuMoe9MXH6tBIPbd5e/followers",
            request.getUri().toString()
    );
  }

  @Test
  public void shouldCreateAGetRecentlyPlayedTracksUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getRecentlyPlayedTracks()
            .build();
    assertEquals("https://api.spotify.com:443/v1/me/player/recently-played", request.getUri().toString());
  }

  @Test
  public void shouldCreateAGetArtistUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getArtist("5rSXSAkZ67PYJSvpUpkOr7")
            .build();
    assertEquals("https://api.spotify.com:443/v1/artists/5rSXSAkZ67PYJSvpUpkOr7", request.getUri().toString());
  }

  @Test
  public void shouldCreateAGetTrackUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getTrack("6hDH3YWFdcUNQjubYztIsG")
            .build();
    assertEquals("https://api.spotify.com:443/v1/tracks/6hDH3YWFdcUNQjubYztIsG", request.getUri().toString());
  }

  @Test
  public void shouldCreateAGetAlbumsUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getAlbums("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv")
            .build();
    assertEquals("https://api.spotify.com:443/v1/albums?ids=6hDH3YWFdcUNQjubYztIsG%2C2IA4WEsWAYpV9eKkwR2UYv", request.getUri().toString());
    assertHasQueryParameter(request, "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetAlbumsUrlFromAList() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getAlbums("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv")
            .build();
    assertEquals("https://api.spotify.com:443/v1/albums?ids=6hDH3YWFdcUNQjubYztIsG%2C2IA4WEsWAYpV9eKkwR2UYv", request.getUri().toString());
    assertHasQueryParameter(request, "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetTracksUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getTracks("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv")
            .build();
    assertEquals("https://api.spotify.com:443/v1/tracks?ids=6hDH3YWFdcUNQjubYztIsG%2C2IA4WEsWAYpV9eKkwR2UYv", request.getUri().toString());
    assertHasQueryParameter(request, "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetTracksUrlFromList() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getTracks("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv")
            .build();
    assertEquals("https://api.spotify.com:443/v1/tracks?ids=6hDH3YWFdcUNQjubYztIsG%2C2IA4WEsWAYpV9eKkwR2UYv", request.getUri().toString());
    assertHasQueryParameter(request, "ids", "6hDH3YWFdcUNQjubYztIsG,2IA4WEsWAYpV9eKkwR2UYv");
  }

  @Test
  public void shouldCreateAGetRecommendationsUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getRecommendations()
            .build();
    assertEquals("https://api.spotify.com:443/v1/recommendations", request.getUri().toString());
  }

  @Test
  public void shouldCreateAGetRecommendationsUrlFromList() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getRecommendations()
            .build();
    assertEquals("https://api.spotify.com:443/v1/recommendations", request.getUri().toString());
  }

  @Test
  public void shouldCreateAUrlForArtistsAlbum() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq")
            .build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.getUri().toString());
  }

  @Test
  public void shouldHaveMultipleAlbumTypeParametersInArtistsAlbumUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq")
            .album_type(AlbumType.ALBUM.getType() + "," + AlbumType.SINGLE.getType())
            .market(CountryCode.SE)
            .build();

    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums?album_type=album%2Csingle&market=SE", request.getUri().toString());
    assertHasQueryParameter(request, "album_type", "album,single");
    assertHasQueryParameter(request, "market", "SE");
  }

  @Test
  public void shouldHaveSingleAlbumTypeParametersInArtistsAlbumUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq")
            .album_type(AlbumType.SINGLE.getType())
            .build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums?album_type=single", request.getUri().toString());
    assertHasQueryParameter(request, "album_type", "single");
  }

  @Test
  public void shouldFailIfAlbumTypeParametersIsInArtistsAlbumUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq")
            .album_type(AlbumType.SINGLE.getType())
            .build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums?album_type=single", request.getUri().toString());
    assertHasQueryParameter(request, "album_type", "single");
  }

  @Test
  public void shouldHaveLimitParameterInArtistsAlbumUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq")
            .limit(2)
            .build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums?limit=2", request.getUri().toString());
    assertHasQueryParameter(request, "limit", "2");
  }

  @Test
  public void shouldHaveOffsetParameterInArtistsAlbumUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq")
            .offset(5)
            .build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums?offset=5", request.getUri().toString());
    assertHasQueryParameter(request, "offset", "5");
  }

  @Test
  public void shouldHaveSeveralQueryParametersAtTheSameTimeInArtistsAlbumUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq")
            .album_type(AlbumType.SINGLE.getType())
            .limit(2)
            .offset(5)
            .build();
    assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums?album_type=single&limit=2&offset=5", request.getUri().toString());
    assertHasQueryParameter(request, "offset", "5");
    assertHasQueryParameter(request, "limit", "2");
    assertHasQueryParameter(request, "album_type", "single");
  }

  @Test
  public void shouldCreateAGetArtistsUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getArtists("4AK6F7OLvEQ5QYCBNiQWHq", "6rEzedK7cKWjeQWdAYvWVG")
            .build();
    assertEquals("https://api.spotify.com:443/v1/artists", request.getUri().toString());
    assertHasFormParameter(request, "ids", "4AK6F7OLvEQ5QYCBNiQWHq,6rEzedK7cKWjeQWdAYvWVG");
  }

  @Test
  public void shouldCreateSearchUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .searchTracks("moulat swalf")
            .build();
    assertEquals("https://api.spotify.com:443/v1/search?q=moulat+swalf&type=track", request.getUri().toString());
    assertHasQueryParameter(request, "q", "moulat+swalf");
    assertHasQueryParameter(request, "type", "track");
  }

  @Test
  public void shouldCreateSearchUrlForAlbum() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .searchAlbums("meeep")
            .market(CountryCode.GB)
            .build();
    assertEquals("https://api.spotify.com:443/v1/search?q=meeep&market=GB&type=album", request.getUri().toString());
    assertHasQueryParameter(request, "q", "meeep");
    assertHasQueryParameter(request, "type", "album");
    assertHasQueryParameter(request, "market", "GB");
  }

  @Test
  public void shouldCreateSearchUrlForArtist() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .searchArtists("meeep")
            .market(CountryCode.GB)
            .build();
    assertEquals("https://api.spotify.com:443/v1/search?q=meeep&market=GB&type=artist", request.getUri().toString());
    assertHasQueryParameter(request, "q", "meeep");
    assertHasQueryParameter(request, "type", "artist");
    assertHasQueryParameter(request, "market", "GB");
  }

  @Test
  public void shouldCreateSearchUrlWithLimitParameter() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .searchTracks("moulat swalf")
            .limit(2)
            .market(CountryCode.SE)
            .build();
    assertEquals("https://api.spotify.com:443/v1/search?q=moulat+swalf&limit=2&market=SE&type=track", request.getUri().toString());
    assertHasQueryParameter(request, "q", "moulat+swalf");
    assertHasQueryParameter(request, "limit", "2");
    assertHasQueryParameter(request, "type", "track");
    assertHasQueryParameter(request, "market", "SE");
  }

  @Test
  public void shouldCreateSearchUrlWithOffsetParameter() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .searchTracks("moulat swalf")
            .offset(2)
            .build();
    assertEquals("https://api.spotify.com:443/v1/search?q=moulat+swalf&offset=2&type=track", request.getUri().toString());
    assertHasQueryParameter(request, "q", "moulat+swalf");
    assertHasQueryParameter(request, "offset", "2");
    assertHasQueryParameter(request, "type", "track");
  }

  @Test
  public void shouldModifySchemeInUrl() {
    SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).setScheme("http").build();
    IRequest request = api
            .getAlbum("5oEljuMoe9MXH6tBIPbd5e")
            .build();
    assertEquals("http://api.spotify.com:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.getUri().toString());
  }

  @Test
  public void shouldModifyPortInUrl() {
    SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).setPort(8080).build();
    IRequest request = api
            .getAlbum("5oEljuMoe9MXH6tBIPbd5e")
            .build();
    assertEquals("https://api.spotify.com:8080/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.getUri().toString());
  }

  @Test
  public void shouldModifyHostInUrl() {
    SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).setHost("www.wrapper.se").build();
    IRequest request = api
            .getAlbum("5oEljuMoe9MXH6tBIPbd5e")
            .build();
    assertEquals("https://www.wrapper.se:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.getUri().toString());
  }

  @Test
  public void shouldCreateTopTracksUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getTopTracksForArtist("0LcJLqbBmaGUft1e9Mm8HV", CountryCode.GB)
            .build();
    assertEquals("https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/top-tracks?country=GB", request.getUri().toString());
    assertHasQueryParameter(request, "country", "GB");
  }

  @Test
  public void shouldCreateUserProfileUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();
    IRequest request = api
            .getUser("wizzler")
            .build();
    assertEquals("https://api.spotify.com:443/v1/users/wizzler", request.getUri().toString());
  }

  @Test
  public void shouldCreateUrlForListingAUsersPlaylists() throws Exception {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();

    final IRequest request = api
            .getPlaylistsForUser("wizzler")
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/wizzler/playlists", request.getUri().toString());
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateRequestForTokensUrl() {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";
    final String redirectURI = "myRedirectUri";
    final String code = "returnedCode";

    final SpotifyApi api = SpotifyApi.builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectURI)
            .build();

    final IRequest request = api
            .authorizationCodeGrant(code)
            .build();

    assertEquals("https://accounts.spotify.com:443/api/token", request.getUri().toString());

    final String idSecret = clientId + ":" + clientSecret;
    assertHasHeader(request, "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
  }

  @Test
  public void shouldCreatePlaylistLookupUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();

    final String playlistId = "3ktAYNcRHpazJ9qecm3ptn";
    final String userId = "thelinmichael";

    final IRequest request = api
            .getPlaylist(userId, playlistId)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/" + userId + "/playlists/" + playlistId, request.getUri().toString());
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateCurrentUserLookupUrl() {

    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();

    final IRequest request = api
            .getMe()
            .build();

    assertEquals("https://api.spotify.com:443/v1/me", request.getUri().toString());
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateCreatePlaylistUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String title = "The greatest playlist ever";
    final boolean publicAccess = true;

    final IRequest request = api
            .createPlaylist(myUsername, title)
            .public_(publicAccess)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists", request.getUri().toString());
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateAddTrackToPlaylistUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String[] tracksToAdd = {"spotify:track:4BYGxv4rxSNcTgT3DsFB9o", "spotify:tracks:0BG2iE6McPhmAEKIhfqy1X"};
    final int insertIndex = 3;

    final IRequest request = api
            .addTracksToPlaylist(myUsername, myPlaylistId, tracksToAdd)
            .position(insertIndex)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId + "/tracks?uris=spotify%3Atrack%3A4BYGxv4rxSNcTgT3DsFB9o%2Cspotify%3Atracks%3A0BG2iE6McPhmAEKIhfqy1X&position=3", request.getUri().toString());
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
    assertHasHeader(request, "Content-Type", "application/json");
    assertHasQueryParameter(request, "uris", "spotify:track:4BYGxv4rxSNcTgT3DsFB9o,spotify:tracks:0BG2iE6McPhmAEKIhfqy1X");
    assertHasQueryParameter(request, "position", String.valueOf(insertIndex));
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateRemoveTrackFromPlaylistUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
    final String track1Uri = "spotify:track:4BYGxv4rxSNcTgT3DsFB9o";
    final String track2Uri = "spotify:track:0BG2iE6McPhmAEKIhfqy1X";
    final int track2Position = 5;
    PlaylistTrackPosition playlistTrackPosition1 = new PlaylistTrackPosition(track1Uri);
    PlaylistTrackPosition playlistTrackPosition2 = new PlaylistTrackPosition(track2Uri, new int[]{track2Position});
    final PlaylistTrackPosition[] tracksToRemove = {playlistTrackPosition1, playlistTrackPosition2};

    final JsonArray tracks = new JsonArray();

    for (PlaylistTrackPosition playlistTrackPosition : tracksToRemove) {
      tracks.add(getJsonFromPlaylistTrackPosition(playlistTrackPosition));
    }

    final String expectedJsonBodyTracks = String.format("[{\"uri\":\"%s\"},{\"uri\":\"%s\",\"positions\":[%s]}]",
            track1Uri, track2Uri, String.valueOf(track2Position));

    final IRequest request = api
            .removeTrackFromPlaylist(myUsername, myPlaylistId, tracksToRemove)
            .snapshotId(snapshotId)
            .tracks(tracks)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId + "/tracks", request.getUri().toString());
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
    assertHasBodyParameter(request, "tracks", expectedJsonBodyTracks);
    assertHasBodyParameter(request, "snapshot_id", snapshotId);
  }

  @Test
  public void shouldCreateReorderTracksInPlaylistUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String snapshotId = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
    final int rangeStart = 10;
    final int rangeLength = 2;
    final int insertBefore = 5;

    final String expectedJsonBody = String.format("{\"range_start\":%s,\"insert_before\":%s,\"range_length\":%s,\"snapshot_id\":\"%s\"}",
            String.valueOf(rangeStart), String.valueOf(insertBefore), String.valueOf(rangeLength), snapshotId);

    final IRequest request = api
            .reorderTracksInPlaylist(myUsername, myPlaylistId, rangeStart, insertBefore)
            .range_length(rangeLength)
            .snapshot_id(snapshotId)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId + "/tracks", request.getUri().toString());
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
    assertHasBodyParameter(request, "range_start", rangeStart);
    assertHasBodyParameter(request, "insert_before", insertBefore);
    assertHasBodyParameter(request, "range_length", rangeLength);
    assertHasBodyParameter(request, "snapshot_id", snapshotId);
  }

  @Test
  public void shouldCreateChangePlaylistDetailsUrl() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";

    final boolean isPublic = false;
    final String name = "Testing name change";

    final IRequest request = api
            .changePlaylistDetails(myUsername, myPlaylistId)
            .public_(isPublic)
            .name(name)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId,
            request.getUri().toString());
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);

    assertHasBodyParameter(request, "name", name);
    assertHasBodyParameter(request, "public", isPublic);

    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateClientCredentialsGrantUrl() {
    final String clientId = "myClientId";
    final String clientSecret = "myClientSecret";

    final SpotifyApi api = SpotifyApi.builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();

    final String[] scopes = {"some-scope", "some-other-scope"};

    final IRequest request = api
            .clientCredentialsGrant()
            .build();

    assertEquals("https://accounts.spotify.com:443/api/token", request.getUri().toString());

    final String idSecret = clientId + ":" + clientSecret;
    assertHasHeader(request, "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
  }

  @Test
  public void shouldCreateAGetPlaylistTracksURL() {
    final String accessToken = "myAccessToken";
    final String userId = "thelinmichael";
    final String playlistId = "5ieJqeLJjjI8iJWaxeBLuK";

    final SpotifyApi api = SpotifyApi.builder()
            .setAccessToken(accessToken)
            .build();

    final IRequest request = api
            .getPlaylistTracks(userId, playlistId)
            .fields("items")
            .limit(20)
            .offset(1)
            .build();

    assertEquals("https://api.spotify.com:443/v1/users/" + userId + "/playlists/" + playlistId + "/tracks?fields=items&limit=20&offset=1", request.getUri().toString());
    assertHasQueryParameter(request, "fields", "items");
    assertHasQueryParameter(request, "limit", "20");
    assertHasQueryParameter(request, "offset", "1");
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateRelatedArtistsURL() {
    final SpotifyApi api = new SpotifyApi.Builder().setAccessToken(accessToken).build();

    final String artistId = "0qeei9KQnptjwb8MgkqEoy";

    final IRequest request = api
            .getArtistRelatedArtists(artistId)
            .build();

    assertEquals("https://api.spotify.com:443/v1/artists/" + artistId + "/related-artists", request.getUri().toString());
  }

  @Test
  public void shouldCreateAuthorizeURL() {
    final String redirectURI = "http://www.michaelthelin.se/test-callback";
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";

    final SpotifyApi api = SpotifyApi.builder()
            .setClientId(clientId)
            .setRedirectUri(redirectURI)
            .build();

    final String[] scopes = {"user-read-private", "user-read-email"};
    final String state = "someExpectedStateString";

    String authorizeUrlString = api
            .createAuthorizeUri(scopes, state)
            .toString();
    assertEquals("https://accounts.spotify.com:443/authorize?client_id=fcecfc79122e4cd299473677a17cbd4d&response_type=code&redirect_uri=http%3A%2F%2Fwww.michaelthelin.se%2Ftest-callback&scope=user-read-private+user-read-email&state=someExpectedStateString", authorizeUrlString);
  }

  @Test
  public void shouldCreateAuthorizeUrlWithOptionalParameters() {
    final String redirectURI = "http://www.michaelthelin.se/test-callback";
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";

    final SpotifyApi api = SpotifyApi.builder()
            .setClientId(clientId)
            .setRedirectUri(redirectURI)
            .build();

    final String[] scopes = {"user-read-private", "user-read-email"};
    final String state = "someExpectedStateString";

    String authorizeUrlString = api
            .createAuthorizeUri(scopes)
            .toString();

    assertEquals("https://accounts.spotify.com:443/authorize?client_id=fcecfc79122e4cd299473677a17cbd4d&response_type=code&redirect_uri=http%3A%2F%2Fwww.michaelthelin.se%2Ftest-callback&scope=user-read-private+user-read-email", authorizeUrlString);
  }

  @Test
  public void shouldCreateAuthorizeUrlWithShowDialog() {
    final String redirectURI = "http://www.michaelthelin.se/test-callback";
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";

    final SpotifyApi api = SpotifyApi.builder()
            .setClientId(clientId)
            .setRedirectUri(redirectURI)
            .build();

    final String[] scopes = {"user-read-private", "user-read-email"};
    final String state = "someExpectedStateString";

    String authorizeURLString = api
            .createAuthorizeUri(scopes, state, true)
            .toString();
    assertEquals("https://accounts.spotify.com:443/authorize?client_id=fcecfc79122e4cd299473677a17cbd4d&response_type=code&redirect_uri=http%3A%2F%2Fwww.michaelthelin.se%2Ftest-callback&scope=user-read-private+user-read-email&state=someExpectedStateString&show_dialog=true", authorizeURLString);
  }

  @Test
  public void shouldCreateGetMyTracksURL() {
    final String accessToken = "myAccessToken";

    final SpotifyApi api = SpotifyApi.builder()
            .setAccessToken(accessToken)
            .build();

    final IRequest request = api
            .getMySavedTracks()
            .limit(5)
            .offset(1)
            .build();

    assertEquals("https://api.spotify.com:443/v1/me/tracks?limit=5&offset=1", request.getUri().toString());
    assertHasQueryParameter(request, "limit", "5");
    assertHasQueryParameter(request, "offset", "1");
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreatePutTracksURL() {
    final String accessToken = "myAccessToken";

    final SpotifyApi api = SpotifyApi.builder()
            .setAccessToken(accessToken)
            .build();

    final IRequest request = api
            .addToMySavedTracks("test", "test2")
            .build();

    assertEquals("https://api.spotify.com:443/v1/me/tracks?ids=test%2Ctest2", request.getUri().toString());
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
  }

  @Test
  public void shouldCreateRemoveTracksURL() {
    final String accessToken = "myAccessToken";

    final SpotifyApi api = SpotifyApi.builder()
            .setAccessToken(accessToken)
            .build();

    final IRequest request = api
            .removeFromMySavedTracks("test", "test2")
            .build();

    assertEquals("https://api.spotify.com:443/v1/me/tracks?ids=test%2Ctest2", request.getUri().toString());
  }

  @Test
  public void shouldCreateGetNewReleasesRequest() {
    final String accessToken = "myAccessToken";

    final SpotifyApi api = SpotifyApi.builder()
            .setAccessToken(accessToken)
            .build();

    final IRequest request = api
            .getNewReleases()
            .limit(4)
            .offset(1)
            .country(CountryCode.SE)
            .build();

    assertEquals("https://api.spotify.com:443/v1/browse/new-releases?limit=4&offset=1&country=SE", request.getUri().toString());
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
    assertHasQueryParameter(request, "limit", "4");
    assertHasQueryParameter(request, "offset", "1");
    assertHasQueryParameter(request, "country", "SE");
  }

  @Test
  public void shouldCreateFeaturedPlaylistsRequest() {
    final String accessToken = "myAccessToken";

    final SpotifyApi api = SpotifyApi.builder()
            .setAccessToken(accessToken)
            .build();


    Calendar calendar = Calendar.getInstance();
    calendar.set(2014, Calendar.DECEMBER, 22, 13, 59, 30);
    Date timestamp = calendar.getTime();

    final IRequest request = api
            .getFeaturedPlaylists()
            .country(CountryCode.SE)
            .locale(LanguageCode.es + "_" + CountryCode.MX)
            .limit(5)
            .offset(1)
            .timestamp(timestamp)
            .build();

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    assertEquals("https://api.spotify.com:443/v1/browse/featured-playlists?country=SE&locale=es_MX&limit=5&offset=1&timestamp=2014-12-22T01%3A59%3A30", request.getUri().toString());
    assertHasHeader(request, "Authorization", "Bearer " + accessToken);
    assertHasQueryParameter(request, "limit", "5");
    assertHasQueryParameter(request, "offset", "1");
    assertHasQueryParameter(request, "country", "SE");
    assertHasQueryParameter(request, "locale", "es_MX");
    assertHasQueryParameter(request, "timestamp", simpleDateFormat.format(timestamp));
  }

}
