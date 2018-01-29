package com.wrapper.spotify;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.enums.AlbumType;
import com.wrapper.spotify.model_objects.special.PlaylistTrackPosition;
import com.wrapper.spotify.requests.IRequest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static com.wrapper.spotify.Assertions.*;
import static org.junit.Assert.assertEquals;

public class ApiTest {

  private static final SpotifyApi SPOTIFY_API = new SpotifyApi.Builder()
          .setClientId("zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g")
          .setClientSecret("zudknyqbh3wunbhcvg9uyvo7uwzeu6nne")
          .setRedirectUri(SpotifyHttpManager.makeUri("https://example.com/spotify-redirect"))
          .setAccessToken("taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk")
          .setRefreshToken("b0KuPuLw77Z0hQhCsK-GTHoEx_kethtn357V7iqwEpCTIsLgqbBC_vQBTGC6M5rINl0FrqHK-D3cbOsMOlfyVKuQPvpyGcLcxAoLOTpYXc28nVwB7iBq2oKj9G9lHkFOUKn")
          .build();
  private static final String AUTHORIZATION_CODE = "c-oGaPdYJF3tu3oUZRUiBHWQvm4oHnBrsxfHackYzzomKJiy5te1k04LJdr6XxjACe9TonpJR8NPOQ3o5btASx_oMw4trmXLYdkda77wY0NJ9Scl69lKvGiOfdnRi5Q0IbBu185Y0TZgyUJz3Auqqv-Wk7zjRke4DzqYEc3ucyUBOq08j5223te-G2K72aL9PxgVJaEHBbLvhdJscCy-zcyU29EZoNlG_E5";
  private static final String[] SCOPES = new String[]{"user-read-birthday", "user-read-email"};
  private static final String STATE = "x4xkmn9pu3j6ukrs8n";
  private static final String ID_ARTIST = "0LcJLqbBmaGUft1e9Mm8HV";
  private static final String ID_ALBUM = "5zT1JLIj9E57p3e1rFm9Uq";
  private static final String ID_PLAYLIST = "3AGOiaoRXMSjswCLtuNqv5";
  private static final String ID_TRACK = "01iyCAUm8EvOFqVWYJ3dVX";
  private static final String ID_USER = "abbaspotify";
  private static final String SNAPSHOT_ID = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
  private static final boolean PUBLIC = false;
  private static final String NAME = "Abba";
  private static final boolean SHOW_DIALOG = true;
  private static final String Q = "Abba";
  private static final CountryCode MARKET = CountryCode.SE;
  private static final int LIMIT = 10;
  private static final int OFFSET = 0;
  private static final Date TIMESTAMP = new Date(1414054800000L);
  private static final String LOCALE = LanguageCode.sv + "_" + CountryCode.SE;
  private static final String ALBUM_TYPE = AlbumType.ALBUM.getType();
  private static final int POSITION = 0;
  private static final int RANGE_START = 0;
  private static final int RANGE_LENGTH = 1;
  private static final int INSERT_BEFORE = 0;

  @Test
  public void shouldCreateAGetAlbumUrl() {
    IRequest request = SPOTIFY_API
            .getAlbum(ID_ALBUM)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/albums/5zT1JLIj9E57p3e1rFm9Uq",
            request.getUri().toString());
  }

  @Test
  public void shouldCreateAGetAudioFeaturesUrl() {
    IRequest request = SPOTIFY_API
            .getAudioFeaturesForTrack(ID_TRACK)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/audio-features/01iyCAUm8EvOFqVWYJ3dVX",
            request.getUri().toString());
  }

  @Test
  public void shouldCreateAGetTracksForAlbumUrl() {
    IRequest request = SPOTIFY_API
            .getAlbumsTracks(ID_ALBUM)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/albums/5zT1JLIj9E57p3e1rFm9Uq/tracks",
            request.getUri().toString());
  }

  @Test
  public void shouldReplacePlaylistsTracks() {
    IRequest request = SPOTIFY_API
            .replacePlaylistsTracks(ID_USER, ID_PLAYLIST, new String[]{"spotify:track:" + ID_TRACK})
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks?uris=spotify%3Atrack%3A01iyCAUm8EvOFqVWYJ3dVX",
            request.getUri().toString());
  }

  @Test
  public void shouldUnfollowPlaylist() {
    IRequest request = SPOTIFY_API
            .unfollowPlaylist(ID_USER, ID_PLAYLIST)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists/3AGOiaoRXMSjswCLtuNqv5/followers",
            request.getUri().toString());
  }

  @Test
  public void shouldCreateAGetRecentlyPlayedTracksUrl() {
    IRequest request = SPOTIFY_API
            .getCurrentUsersRecentlyPlayedTracks()
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/me/player/recently-played",
            request.getUri().toString());
  }

  @Test
  public void shouldCreateAGetArtistUrl() {
    IRequest request = SPOTIFY_API
            .getArtist(ID_ARTIST)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV",
            request.getUri().toString());
  }

  @Test
  public void shouldCreateAGetTrackUrl() {
    IRequest request = SPOTIFY_API
            .getTrack(ID_TRACK)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/tracks/01iyCAUm8EvOFqVWYJ3dVX",
            request.getUri().toString());
  }

  @Test
  public void shouldCreateAGetAlbumsUrl() {
    IRequest request = SPOTIFY_API
            .getSeveralAlbums(ID_ALBUM, ID_ALBUM)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/albums?ids=5zT1JLIj9E57p3e1rFm9Uq%2C5zT1JLIj9E57p3e1rFm9Uq",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "ids",
            "5zT1JLIj9E57p3e1rFm9Uq,5zT1JLIj9E57p3e1rFm9Uq");
  }

  @Test
  public void shouldCreateAGetTracksUrl() {
    IRequest request = SPOTIFY_API
            .getSeveralTracks(ID_TRACK, ID_TRACK)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/tracks?ids=01iyCAUm8EvOFqVWYJ3dVX%2C01iyCAUm8EvOFqVWYJ3dVX",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "ids",
            "01iyCAUm8EvOFqVWYJ3dVX,01iyCAUm8EvOFqVWYJ3dVX");
  }

  @Test
  public void shouldCreateAGetRecommendationsUrl() {
    IRequest request = SPOTIFY_API
            .getRecommendations()
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/recommendations",
            request.getUri().toString());
  }

  @Test
  public void shouldCreateAUrlForArtistsAlbum() {
    IRequest request = SPOTIFY_API
            .getArtistsAlbums(ID_ARTIST)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/albums",
            request.getUri().toString());
  }

  @Test
  public void shouldHaveMultipleAlbumTypeParametersInArtistsAlbumUrl() {
    IRequest request = SPOTIFY_API
            .getArtistsAlbums(ID_ARTIST)
            .album_type(ALBUM_TYPE)
            .market(MARKET)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/albums?album_type=album&market=SE",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "album_type",
            "album");
    assertHasQueryParameter(
            request,
            "market",
            "SE");
  }

  @Test
  public void shouldHaveSingleAlbumTypeParametersInArtistsAlbumUrl() {
    IRequest request = SPOTIFY_API
            .getArtistsAlbums(ID_ARTIST)
            .album_type(ALBUM_TYPE)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/albums?album_type=album",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "album_type",
            "album");
  }

  @Test
  public void shouldHaveLimitParameterInArtistsAlbumUrl() {
    IRequest request = SPOTIFY_API
            .getArtistsAlbums(ID_ARTIST)
            .limit(LIMIT)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/albums?limit=10",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "limit",
            "10");
  }

  @Test
  public void shouldHaveOffsetParameterInArtistsAlbumUrl() {
    IRequest request = SPOTIFY_API
            .getArtistsAlbums(ID_ARTIST)
            .offset(OFFSET)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/albums?offset=0",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "offset",
            "0");
  }

  @Test
  public void shouldHaveSeveralQueryParametersAtTheSameTimeInArtistsAlbumUrl() {
    IRequest request = SPOTIFY_API
            .getArtistsAlbums(ID_ARTIST)
            .album_type(ALBUM_TYPE)
            .limit(LIMIT)
            .offset(OFFSET)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/albums?album_type=album&limit=10&offset=0",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "offset",
            "0");
    assertHasQueryParameter(
            request,
            "limit",
            "10");
    assertHasQueryParameter(
            request,
            "album_type",
            "album");
  }

  @Test
  public void shouldCreateAGetArtistsUrl() {
    IRequest request = SPOTIFY_API
            .getSeveralArtists(ID_ARTIST, ID_ARTIST)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/artists",
            request.getUri().toString());
    assertHasFormParameter(
            request,
            "ids",
            "0LcJLqbBmaGUft1e9Mm8HV,0LcJLqbBmaGUft1e9Mm8HV");
  }

  @Test
  public void shouldCreateSearchUrl() {
    IRequest request = SPOTIFY_API
            .searchTracks(Q)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/search?q=Abba&type=track",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "q",
            "Abba");
    assertHasQueryParameter(
            request,
            "type",
            "track");
  }

  @Test
  public void shouldCreateSearchUrlForAlbum() {
    IRequest request = SPOTIFY_API
            .searchAlbums(Q)
            .market(MARKET)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/search?q=Abba&market=SE&type=album",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "q", "Abba");
    assertHasQueryParameter(
            request,
            "type", "album");
    assertHasQueryParameter(
            request,
            "market", "SE");
  }

  @Test
  public void shouldCreateSearchUrlForArtist() {
    IRequest request = SPOTIFY_API
            .searchArtists(Q)
            .market(MARKET)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/search?q=Abba&market=SE&type=artist",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "q",
            "Abba");
    assertHasQueryParameter(
            request,
            "type",
            "artist");
    assertHasQueryParameter(
            request,
            "market",
            "SE");
  }

  @Test
  public void shouldCreateSearchUrlWithLimitParameter() {
    IRequest request = SPOTIFY_API
            .searchTracks(Q)
            .limit(LIMIT)
            .market(MARKET)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/search?q=Abba&limit=10&market=SE&type=track",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "q",
            "Abba");
    assertHasQueryParameter(
            request,
            "limit",
            "10");
    assertHasQueryParameter(
            request,
            "type",
            "track");
    assertHasQueryParameter(
            request,
            "market",
            "SE");
  }

  @Test
  public void shouldCreateSearchUrlWithOffsetParameter() {
    IRequest request = SPOTIFY_API
            .searchTracks(Q)
            .offset(OFFSET)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/search?q=Abba&offset=0&type=track",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "q",
            "Abba");
    assertHasQueryParameter(
            request,
            "offset",
            "0");
    assertHasQueryParameter(
            request,
            "type",
            "track");
  }

  @Test
  public void shouldModifySchemeInUrl() {
    SpotifyApi customSpotifyApi = new SpotifyApi.Builder()
            .setAccessToken(SPOTIFY_API.getAccessToken())
            .setScheme("http")
            .build();
    IRequest request = customSpotifyApi
            .getAlbum(ID_ALBUM)
            .build();
    assertEquals(
            "http://api.spotify.com:443/v1/albums/5zT1JLIj9E57p3e1rFm9Uq",
            request.getUri().toString());
  }

  @Test
  public void shouldModifyPortInUrl() {
    SpotifyApi customSpotifyApi = new SpotifyApi.Builder()
            .setAccessToken(SPOTIFY_API.getAccessToken())
            .setPort(8080)
            .build();
    IRequest request = customSpotifyApi
            .getAlbum(ID_ALBUM)
            .build();
    assertEquals(
            "https://api.spotify.com:8080/v1/albums/5zT1JLIj9E57p3e1rFm9Uq",
            request.getUri().toString());
  }

  @Test
  public void shouldModifyHostInUrl() {
    SpotifyApi customSpotifyApi = new SpotifyApi.Builder()
            .setAccessToken(SPOTIFY_API.getAccessToken())
            .setHost("example.com")
            .build();
    IRequest request = customSpotifyApi
            .getAlbum(ID_ALBUM)
            .build();
    assertEquals(
            "https://example.com:443/v1/albums/5zT1JLIj9E57p3e1rFm9Uq",
            request.getUri().toString());
  }

  @Test
  public void shouldCreateTopTracksUrl() {
    IRequest request = SPOTIFY_API
            .getArtistsTopTracks(ID_ARTIST, MARKET)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/top-tracks?country=SE",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "country",
            "SE");
  }

  @Test
  public void shouldCreateUserProfileUrl() {
    IRequest request = SPOTIFY_API
            .getUsersProfile(ID_USER)
            .build();
    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify",
            request.getUri().toString());
  }

  @Test
  public void shouldCreateUrlForListingAUsersPlaylists() {

    final IRequest request = SPOTIFY_API
            .getListOfUsersPlaylists(ID_USER)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
  }

  @Test
  public void shouldCreateRequestForTokensUrl() {
    final IRequest request = SPOTIFY_API
            .authorizationCode(AUTHORIZATION_CODE)
            .build();

    assertEquals(
            "https://accounts.spotify.com:443/api/token",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization",
            "Basic enl1eGhmbzFjNTFiNWh4amswOXgydWh2NW4wc3ZnZDZnOnp1ZGtueXFiaDN3dW5iaGN2Zzl1eXZvN3V3emV1Nm5uZQ==");
  }

  @Test
  public void shouldCreatePlaylistLookupUrl() {
    final IRequest request = SPOTIFY_API
            .getPlaylist(ID_USER, ID_PLAYLIST)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists/3AGOiaoRXMSjswCLtuNqv5",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
  }

  @Test
  public void shouldCreateCurrentUserLookupUrl() {
    final IRequest request = SPOTIFY_API
            .getCurrentUsersProfile()
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/me",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
  }

  @Test
  public void shouldCreateCreatePlaylistUrl() {
    final IRequest request = SPOTIFY_API
            .createPlaylist(ID_USER, NAME)
            .public_(PUBLIC)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
  }

  @Test
  public void shouldCreateAddTrackToPlaylistUrl() {
    final IRequest request = SPOTIFY_API
            .addTracksToPlaylist(ID_USER, ID_PLAYLIST, new String[]{"spotify:track:" + ID_TRACK, "spotify:track:" + ID_TRACK})
            .position(POSITION)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks?uris=spotify%3Atrack%3A01iyCAUm8EvOFqVWYJ3dVX%2Cspotify%3Atrack%3A01iyCAUm8EvOFqVWYJ3dVX&position=0",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
    assertHasHeader(
            request,
            "Content-Type",
            "application/json");
    assertHasQueryParameter(
            request,
            "uris",
            "spotify:track:01iyCAUm8EvOFqVWYJ3dVX,spotify:track:01iyCAUm8EvOFqVWYJ3dVX");
    assertHasQueryParameter(
            request,
            "position",
            0);
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
  }

  @Test
  public void shouldCreateRemoveTrackFromPlaylistUrl() {
    PlaylistTrackPosition playlistTrackPosition1 = new PlaylistTrackPosition.Builder()
            .setUri("spotify:track:" + ID_TRACK)
            .build();
    PlaylistTrackPosition playlistTrackPosition2 = new PlaylistTrackPosition.Builder()
            .setUri("spotify:track:" + ID_TRACK)
            .setPositions(new int[]{POSITION})
            .build();

    final PlaylistTrackPosition[] playlistTrackPositions = new PlaylistTrackPosition[]{
            playlistTrackPosition1,
            playlistTrackPosition2};
    final JsonArray tracksToRemove = new JsonParser().parse(
            new Gson().toJson(
                    new ArrayList<>(
                            Arrays.asList(playlistTrackPositions))))
            .getAsJsonArray();

    final IRequest request = SPOTIFY_API
            .removeTracksFromPlaylist(ID_USER, ID_PLAYLIST, tracksToRemove)
            .snapshotId(SNAPSHOT_ID)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
    assertHasBodyParameter(
            request,
            "tracks",
            "[{\"uri\":\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"},{\"uri\":\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\",\"positions\":[0]}]");
    assertHasBodyParameter(
            request,
            "snapshot_id",
            "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+");
  }

  @Test
  public void shouldCreateReorderTracksInPlaylistUrl() {
    final IRequest request = SPOTIFY_API
            .reorderPlaylistsTracks(ID_USER, ID_PLAYLIST, RANGE_START, INSERT_BEFORE)
            .range_length(RANGE_LENGTH)
            .snapshot_id(SNAPSHOT_ID)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization", "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
    assertHasBodyParameter(
            request,
            "range_start",
            0);
    assertHasBodyParameter(
            request,
            "insert_before",
            0);
    assertHasBodyParameter(
            request,
            "range_length",
            1);
    assertHasBodyParameter(
            request,
            "snapshot_id",
            "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+");
  }

  @Test
  public void shouldCreateChangePlaylistDetailsUrl() {
    final IRequest request = SPOTIFY_API
            .changePlaylistsDetails(ID_USER, ID_PLAYLIST)
            .public_(PUBLIC)
            .name(NAME)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists/3AGOiaoRXMSjswCLtuNqv5",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
    assertHasBodyParameter(
            request,
            "name",
            "Abba");
    assertHasBodyParameter(
            request,
            "public",
            false);
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
  }

  @Test
  public void shouldCreateClientCredentialsGrantUrl() {
    final IRequest request = SPOTIFY_API
            .clientCredentials()
            .build();

    assertEquals(
            "https://accounts.spotify.com:443/api/token",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization",
            "Basic enl1eGhmbzFjNTFiNWh4amswOXgydWh2NW4wc3ZnZDZnOnp1ZGtueXFiaDN3dW5iaGN2Zzl1eXZvN3V3emV1Nm5uZQ==");
  }

  @Test
  public void shouldCreateAGetPlaylistTracksURL() {
    final IRequest request = SPOTIFY_API
            .getPlaylistsTracks(ID_USER, ID_PLAYLIST)
            .fields("items")
            .limit(LIMIT)
            .offset(OFFSET)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/users/abbaspotify/playlists/3AGOiaoRXMSjswCLtuNqv5/tracks?fields=items&limit=10&offset=0",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "fields",
            "items");
    assertHasQueryParameter(
            request,
            "limit",
            "10");
    assertHasQueryParameter(
            request,
            "offset",
            "0");
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
  }

  @Test
  public void shouldCreateRelatedArtistsURL() {
    final IRequest request = SPOTIFY_API
            .getArtistsRelatedArtists(ID_ARTIST)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/related-artists",
            request.getUri().toString());
  }

  @Test
  public void shouldCreateAuthorizeURL() {
    String authorizeUrlString = SPOTIFY_API
            .authorizationCodeUri()
            .scope(SpotifyApi.concat(SCOPES, ' '))
            .state(STATE)
            .build()
            .getUri()
            .toString();
    assertEquals(
            "https://accounts.spotify.com:443/authorize?client_id=zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g&response_type=code&redirect_uri=https%3A%2F%2Fexample.com%2Fspotify-redirect&scope=user-read-birthday+user-read-email&state=x4xkmn9pu3j6ukrs8n",
            authorizeUrlString);
  }

  @Test
  public void shouldCreateAuthorizeUrlWithOptionalParameters() {
    String authorizeUrlString = SPOTIFY_API
            .authorizationCodeUri()
            .scope(SpotifyApi.concat(SCOPES, ' '))
            .build()
            .getUri()
            .toString();

    assertEquals(
            "https://accounts.spotify.com:443/authorize?client_id=zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g&response_type=code&redirect_uri=https%3A%2F%2Fexample.com%2Fspotify-redirect&scope=user-read-birthday+user-read-email",
            authorizeUrlString);
  }

  @Test
  public void shouldCreateAuthorizeUrlWithShowDialog() {

    String authorizeURLString = SPOTIFY_API
            .authorizationCodeUri()
            .scope(SpotifyApi.concat(SCOPES, ' '))
            .state(STATE)
            .show_dialog(SHOW_DIALOG)
            .build()
            .getUri()
            .toString();
    assertEquals(
            "https://accounts.spotify.com:443/authorize?client_id=zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g&response_type=code&redirect_uri=https%3A%2F%2Fexample.com%2Fspotify-redirect&scope=user-read-birthday+user-read-email&state=x4xkmn9pu3j6ukrs8n&show_dialog=true",
            authorizeURLString);
  }

  @Test
  public void shouldCreateGetMyTracksURL() {
    final IRequest request = SPOTIFY_API
            .getUsersSavedTracks()
            .limit(LIMIT)
            .offset(OFFSET)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/me/tracks?limit=10&offset=0",
            request.getUri().toString());
    assertHasQueryParameter(
            request,
            "limit", "10");
    assertHasQueryParameter(
            request,
            "offset", "0");
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
  }

  @Test
  public void shouldCreatePutTracksURL() {
    final IRequest request = SPOTIFY_API
            .saveTracksForUser(ID_TRACK, ID_TRACK)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/me/tracks?ids=01iyCAUm8EvOFqVWYJ3dVX%2C01iyCAUm8EvOFqVWYJ3dVX",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
  }

  @Test
  public void shouldCreateRemoveTracksURL() {
    final IRequest request = SPOTIFY_API
            .removeUsersSavedTracks(ID_TRACK, ID_TRACK)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/me/tracks?ids=01iyCAUm8EvOFqVWYJ3dVX%2C01iyCAUm8EvOFqVWYJ3dVX",
            request.getUri().toString());
  }

  @Test
  public void shouldCreateGetNewReleasesRequest() {
    final IRequest request = SPOTIFY_API
            .getListOfNewReleases()
            .limit(LIMIT)
            .offset(OFFSET)
            .country(MARKET)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/browse/new-releases?limit=10&offset=0&country=SE",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
    assertHasQueryParameter(
            request, "limit",
            "10");
    assertHasQueryParameter(
            request, "offset",
            "0");
    assertHasQueryParameter(
            request, "country",
            "SE");
  }

  @Test
  public void shouldCreateFeaturedPlaylistsRequest() {
    final IRequest request = SPOTIFY_API
            .getListOfFeaturedPlaylists()
            .country(MARKET)
            .locale(LOCALE)
            .limit(LIMIT)
            .offset(OFFSET)
            .timestamp(TIMESTAMP)
            .build();

    assertEquals(
            "https://api.spotify.com:443/v1/browse/featured-playlists?country=SE&locale=sv_SE&limit=10&offset=0&timestamp=2014-10-23T09%3A00%3A00",
            request.getUri().toString());
    assertHasHeader(
            request,
            "Authorization",
            "Bearer taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk");
    assertHasQueryParameter(
            request, "limit",
            "10");
    assertHasQueryParameter(
            request, "offset",
            "0");
    assertHasQueryParameter(
            request, "country",
            "SE");
    assertHasQueryParameter(
            request, "locale",
            "sv_SE");
    assertHasQueryParameter(
            request, "timestamp",
            "2014-10-23T09:00:00");
  }
}
