package se.michaelthelin.spotify;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static se.michaelthelin.spotify.TestUtil.readFromFileTry;

public interface ITest<T> {

  SpotifyApi SPOTIFY_API = new SpotifyApi.Builder()
    .setClientId("zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g")
    .setClientSecret("zudknyqbh3wunbhcvg9uyvo7uwzeu6nne")
    .setRedirectUri(SpotifyHttpManager.makeUri("https://example.com/spotify-redirect"))
    .setAccessToken("taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk")
    .setRefreshToken("b0KuPuLw77Z0hQhCsK-GTHoEx_kethtn357V7iqwEpCTIsLgqbBC_vQBTGC6M5rINl0FrqHK-D3cbOsMOlfyVKuQPvpyGcLcxAoLOTpYXc28nVwB7iBq2oKj9G9lHkFOUKn")
    .build();

  Date AFTER = new Date(1517087230000L);
  String ALBUM_TYPE = AlbumType.ALBUM.getType();
  JsonArray ALBUMS = JsonParser.parseString("[\"5zT1JLIj9E57p3e1rFm9Uq\", \"5zT1JLIj9E57p3e1rFm9Uq\"]" ).getAsJsonArray();
  JsonArray ARTISTS = JsonParser.parseString("[\"0LcJLqbBmaGUft1e9Mm8HV\", \"0LcJLqbBmaGUft1e9Mm8HV\"]" ).getAsJsonArray();
  String AUTHORIZATION_CODE = "c-oGaPdYJF3tu3oUZRUiBHWQvm4oHnBrsxfHackYzzomKJiy5te1k04LJdr6XxjACe9TonpJR8NPOQ3o5btASx_oMw4trmXLYdkda77wY0NJ9Scl69lKvGiOfdnRi5Q0IbBu185Y0TZgyUJz3Auqqv-Wk7zjRke4DzqYEc3ucyUBOq08j5223te-G2K72aL9PxgVJaEHBbLvhdJscCy-zcyU29EZoNlG_E5";
  Date BEFORE = new Date(1453932420000L);
  String CATEGORY_ID = "dinner";
  String CODE_VERIFIER = "NlJx4kD4opk4HY7zBM6WfUHxX7HoF8A2TUhOIPGA74w";
  String CODE_CHALLENGE = "w6iZIj99vHGtEx_NVl9u3sthTN646vvkiP8OMCGfPmo";
  boolean COLLABORATIVE = false;
  String CONTEXT_URI = "spotify:album:5zT1JLIj9E57p3e1rFm9Uq";
  CountryCode COUNTRY = CountryCode.SE;
  String DESCRIPTION = "Amazing music.";
  String DEVICE_ID = "5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e";
  String FIELDS = "description";
  String ADDITIONAL_TYPES = "track,episode";
  String ID_ALBUM = "5zT1JLIj9E57p3e1rFm9Uq";
  String ID_ARTIST = "0LcJLqbBmaGUft1e9Mm8HV";
  String ID_EPISODE = "4GI3dxEafwap1sFiTGPKd1";
  String ID_PLAYLIST = "3AGOiaoRXMSjswCLtuNqv5";
  String ID_SHOW = "5AvwZVawapvyhJUIx71pdJ";
  String ID_TRACK = "01iyCAUm8EvOFqVWYJ3dVX";
  String ID_USER = "abbaspotify";
  String ID_USER_NON_ASCII = "abbaspötify";
  String IMAGE_DATA = readFromFileTry(new File("examples/image_data.txt"));
  int INSERT_BEFORE = 0;
  int LIMIT = 10;
  String LOCALE = LanguageCode.sv + "_" + CountryCode.SE;
  CountryCode MARKET = CountryCode.SE;
  int MAX_POPULARITY = 50;
  int MIN_POPULARITY = 10;
  String NAME = "Abba";
  int OFFSET = 0;
  JsonObject OFFSET_JSON = JsonParser.parseString("{\"uri\":\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"}").getAsJsonObject();
  boolean PLAY = false;
  int POSITION = 0;
  int POSITION_MS = 10000;
  boolean PUBLIC = false;
  String Q = "Abba";
  int RANGE_LENGTH = 1;
  int RANGE_START = 0;
  String SCOPE = "user-read-birthday user-read-email";
  String SEED_ARTISTS = "0LcJLqbBmaGUft1e9Mm8HV";
  String SEED_GENRES = "electro";
  String SEED_TRACKS = "01iyCAUm8EvOFqVWYJ3dVX";
  boolean SHOW_DIALOG = true;
  JsonArray SHOWS = JsonParser.parseString("[\"5AvwZVawapvyhJUIx71pdJ\", \"5AvwZVawapvyhJUIx71pdJ\"]").getAsJsonArray();
  String SNAPSHOT_ID = "JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+";
  String STATE = "track";
  boolean STATE_BOOLEAN = false;
  int TARGET_POPULARITY = 20;
  String TIME_RANGE = "medium_term";
  JsonArray TRACKS = JsonParser.parseString("[\"01iyCAUm8EvOFqVWYJ3dVX\", \"01iyCAUm8EvOFqVWYJ3dVX\"]").getAsJsonArray();
  Date TIMESTAMP = new Date(1414054800000L);
  ModelObjectType TYPE = ModelObjectType.ARTIST;
  JsonArray URIS = JsonParser.parseString("[\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\",\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"]").getAsJsonArray();
  int VOLUME_PERCENT = 100;

  void shouldComplyWithReference();

  void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException;

  void shouldReturnDefault_async() throws ExecutionException, InterruptedException;

  void shouldReturnDefault(final T type);

}
