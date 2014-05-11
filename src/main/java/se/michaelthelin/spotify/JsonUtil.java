package se.michaelthelin.spotify;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtil {

  public static List<Artist> createArtists(String json) {
    return createArtists(JSONObject.fromObject(json));
  }

  private static List<Artist> createArtists(JSONObject jsonObject) {
    List<Artist> returnedArtists = new ArrayList<Artist>();
    JSONArray artistsObject = jsonObject.getJSONArray("artists");
    for (int i = 0; i < artistsObject.size(); i++) {
      returnedArtists.add(createArtist(artistsObject.getJSONObject(i)));
    }
    return returnedArtists;
  }

  public static Artist createArtist(String json) {
    return createArtist(JSONObject.fromObject(json));
  }

  private static Artist createArtist(JSONObject jsonObject) {
    if (jsonObject == null || jsonObject.isNullObject()) {
      return null;
    }

    Artist artist = new Artist();

    artist.setExternalUrls(createExternalUrls(jsonObject.getJSONObject("external_urls")));
    artist.setGenres(createGenres(jsonObject.getJSONArray("genres")));
    artist.setHref(jsonObject.getString("href"));
    artist.setId(jsonObject.getString("id"));
    artist.setImages(createImages(jsonObject.getJSONArray("images")));
    artist.setName(jsonObject.getString("name"));
    artist.setPopularity(jsonObject.getInt("popularity"));
    artist.setType(createSpotifyEntityType(jsonObject.getString("type")));
    artist.setUri(jsonObject.getString("uri"));

    return artist;
  }

  public static SpotifyEntityType createSpotifyEntityType(String type) {
    return SpotifyEntityType.valueOf(type);
  }

  public static ExternalUrls createExternalUrls(JSONObject externalUrls) {
    ExternalUrls returnedExternalUrls = new ExternalUrls();
    Map<String,String> addedExternalUrls = returnedExternalUrls.getExternalUrls();
    for (Object keyObject : externalUrls.keySet()) {
      String key = (String) keyObject;
      addedExternalUrls.put(key, externalUrls.getString(key));
    }
    return returnedExternalUrls;
  }

  public static List<Image> createImages(JSONArray images) {
    List<Image> returnedImages = new ArrayList<Image>();
    for (int i = 0; i < images.size(); i++) {
      returnedImages.add(createImage(images.getJSONObject(i)));
    }
    return returnedImages;
  }

  private static Image createImage(JSONObject image) {
    Image returnedImage = new Image();
    returnedImage.setHeight(image.getInt(("height")));
    returnedImage.setWidth(image.getInt(("width")));
    returnedImage.setUrl(image.getString("url"));
    return returnedImage;
  }

  public static List<String> createGenres(JSONArray genres) {
    List<String> returnedGenres = new ArrayList<String>();
    for (int i = 0; i < genres.size(); i++) {
      genres.add(genres.getString(i));
    }
    return returnedGenres;
  }

  public static Album createAlbum(String json) {
    return createAlbum(JSONObject.fromObject(json));
  }

  private static Album createAlbum(JSONObject albumJson) {
    if (albumJson == null || albumJson.isNullObject()) {
      return null;
    }

    Album album = new Album();

    album.setAlbumType(createAlbumType(albumJson.getString("album_type")));
    album.setArtists(createSimpleArtists(albumJson.getJSONArray("artists")));
    album.setAvailableMarkets(createAvailableMarkets(albumJson.getJSONArray("available_markets")));
    album.setExternalIds(createExternalIds(albumJson.getJSONObject("external_ids")));
    album.setExternalUrls(createExternalUrls(albumJson.getJSONObject("external_urls")));
    album.setGenres(createGenres(albumJson.getJSONArray("genres")));
    album.setHref(albumJson.getString("href"));
    album.setId(albumJson.getString("id"));
    album.setImages(createImages(albumJson.getJSONArray("images")));
    album.setName(albumJson.getString("name"));
    album.setPopularity(albumJson.getInt("popularity"));
    album.setReleaseDate(createReleaseDate(albumJson.getJSONObject("release_date")));
    album.setTracks(createSimpleTracks(albumJson.getJSONArray("tracks")));
    album.setType(createSpotifyEntityType(albumJson.getString("type")));
    album.setUri(albumJson.getString("uri"));

    return album;
  }

  public static List<SimpleTrack> createSimpleTracks(JSONArray tracksJson) {
    List<SimpleTrack> tracks = new ArrayList<SimpleTrack>();
    for (int i = 0; i < tracksJson.size(); i++) {
      tracks.add(createSimpleTrack(tracksJson.getJSONObject(i)));
    }
    return tracks;
  }

  public static SimpleTrack createSimpleTrack(JSONObject simpleTrackJson) {
    SimpleTrack track = new SimpleTrack();

    track.setArtists(createSimpleArtists(simpleTrackJson.getJSONArray("artists")));
    track.setDiscNumber(simpleTrackJson.getInt("disc_number"));
    track.setDuration(simpleTrackJson.getInt("duration"));
    track.setExplicit(simpleTrackJson.getBoolean("explicit"));
    track.setExternalUrls(createExternalUrls(simpleTrackJson.getJSONObject("externalUrls")));
    track.setHref(simpleTrackJson.getString("href"));
    track.setId(simpleTrackJson.getString("id"));
    track.setName(simpleTrackJson.getString("name"));
    track.setPreviewUrl(simpleTrackJson.getString("preview_url"));
    track.setTrackNumber(simpleTrackJson.getInt("track_number"));
    track.setType(createSpotifyEntityType(simpleTrackJson.getString("type")));
    track.setUri(simpleTrackJson.getString("uri"));

    return track;
  }

  public static ReleaseDate createReleaseDate(JSONObject releaseDateJson) {
    ReleaseDate releaseDate = new ReleaseDate();

    releaseDate.setYear(releaseDateJson.getInt("year"));
    releaseDate.setMonth(releaseDateJson.getInt("month"));
    releaseDate.setDate(releaseDateJson.getInt("date"));

    return releaseDate;
  }

  public static List<String> createAvailableMarkets(JSONArray availableMarketsJson) {
    List<String> availableMarkets = new ArrayList<String>();
    for (int i = 0; i < availableMarketsJson.size(); i++) {
      availableMarkets.add(availableMarketsJson.getString(i));
    }
    return availableMarkets;
  }

  public static AlbumType createAlbumType(String albumType) {
    return AlbumType.valueOf(albumType);
  }

  public  static SimpleAlbum createSimpleAlbum(JSONObject simpleAlbumJson) {
    if (simpleAlbumJson == null || simpleAlbumJson.isNullObject()) {
      return null;
    }

    SimpleAlbum simpleAlbum = new SimpleAlbum();

    simpleAlbum.setAlbumType(createAlbumType(simpleAlbumJson.getString("album_type")));
    simpleAlbum.setExternalUrls(createExternalUrls(simpleAlbumJson.getJSONObject("external_urls")));
    simpleAlbum.setHref(simpleAlbumJson.getString("href"));
    simpleAlbum.setId(simpleAlbumJson.getString("id"));
    simpleAlbum.setImages(createImages(simpleAlbumJson.getJSONArray("images")));
    simpleAlbum.setName(simpleAlbumJson.getString("name"));
    simpleAlbum.setType(createSpotifyEntityType(simpleAlbumJson.getString("type")));
    simpleAlbum.setUri(simpleAlbumJson.getString("type"));

    return simpleAlbum;
  }

  public static List<Album> createAlbums(String json) {
    JSONObject jsonObject = JSONObject.fromObject(json);
    JSONArray albumsJsonArray = jsonObject.getJSONArray("albums");
    return createAlbums(albumsJsonArray);

  }

  public static List<Album> createAlbums(JSONArray jsonArray) {
    List<Album> returnedAlbums = new ArrayList<Album>();
    for (int i = 0; i < jsonArray.size(); i++) {
      returnedAlbums.add(createAlbum(jsonArray.getJSONObject(i)));
    }
    return returnedAlbums;
  }

  public static SimpleArtist createSimpleArtist(JSONObject simpleArtistJson) {
    if (simpleArtistJson == null || simpleArtistJson.isNullObject()) {
      return null;
    }

    SimpleArtist simpleArtist = new SimpleArtist();

    simpleArtist.setExternalUrls(createExternalUrls(simpleArtistJson.getJSONObject("external_urls")));
    simpleArtist.setHref(simpleArtistJson.getString("href"));
    simpleArtist.setId(simpleArtistJson.getString("id"));
    simpleArtist.setName(simpleArtistJson.getString("name"));
    simpleArtist.setType(createSpotifyEntityType(simpleArtistJson.getString("type")));
    simpleArtist.setUri(simpleArtistJson.getString("uri"));

    return simpleArtist;
  }

  private static List<SimpleArtist> createSimpleArtists(JSONArray artists) {
   List<SimpleArtist> returnedArtists = new ArrayList<SimpleArtist>();
    for (int i = 0; i < artists.size(); i++) {
      returnedArtists.add(createSimpleArtist(artists.getJSONObject(i)));
    }
    return returnedArtists;
  }

  public static Track createTrack(String trackJson) {
    return createTrack(JSONObject.fromObject(trackJson));
  }

  private static Track createTrack(JSONObject trackJson) {

    Track track = new Track();

    track.setAlbum(createSimpleAlbum(trackJson.getJSONObject("album")));
    track.setArtists(createSimpleArtists(trackJson.getJSONArray("artists")));
    track.setAvailableMarkets(createAvailableMarkets(trackJson.getJSONArray("available_markets")));
    track.setDiscNumber(trackJson.getInt("disc_number"));
    track.setDuration(trackJson.getInt("duration"));
    track.setExplicit(trackJson.getBoolean("explicit"));
    track.setExternalIds(createExternalIds(trackJson.getJSONObject("external_ids")));
    track.setExternalUrls(createExternalUrls(trackJson.getJSONObject("external_urls")));
    track.setHref(trackJson.getString("href"));
    track.setId(trackJson.getString("id"));
    track.setName(trackJson.getString("name"));
    track.setPopularity(trackJson.getInt("popularity"));
    track.setPreviewUrl(trackJson.getString("preview_url"));
    track.setTrackNumber(trackJson.getInt(("track_number")));
    track.setType(createSpotifyEntityType(trackJson.getString("type")));
    track.setUri(trackJson.getString("uri"));

    return track;
  }

  public static List<Track> createTracks(String json) {
    return createTracks(JSONObject.fromObject(json));
  }

  public static List<Track> createTracks(JSONObject jsonObject) {
    List<Track> returnedTracks = new ArrayList<Track>();
    JSONArray tracks = jsonObject.getJSONArray("tracks");
    for (int i = 0; i < tracks.size(); i++) {
      returnedTracks.add(createTrack(tracks.getJSONObject(i)));
    }
    return returnedTracks;
  }

  public static ExternalIds createExternalIds(JSONObject externalIds) {
    ExternalIds returnedExternalIds = new ExternalIds();
    Map<String,String> addedIds = returnedExternalIds.getExternalIds();

    for (Object keyObject : externalIds.keySet()) {
      String key = (String) keyObject;
      addedIds.put(key, externalIds.getString(key));
    }

    return returnedExternalIds;
  }

  public static Page<Album> createAlbumPage(String albumPageJson) {
    return createAlbumPage(JSONObject.fromObject(albumPageJson));
  }

  public static Page<Album> createAlbumPage(JSONObject albumPageJson) {
    Page page = createItemlessPage(albumPageJson);
    page.setItems(createAlbums(albumPageJson.getJSONArray("items")));
    return page;
  }

  private static Page<Album> createItemlessPage(JSONObject pageJson) {
    Page page = new Page();
    page.setHref(pageJson.getString("href"));
    page.setLimit(pageJson.getInt("limit"));
    page.setNext(pageJson.getString("next"));
    page.setOffset(pageJson.getInt("offset"));
    page.setPrevious(pageJson.getString("previous"));
    page.setTotal(pageJson.getInt("total"));
    return page;
  }

  public static Page<Track> createTrackPage(String trackPageJson) {
    return createTrackPage(JSONObject.fromObject(trackPageJson));
  }

  public static Page<Track> createTrackPage(JSONObject trackPageJson) {
    Page page = createItemlessPage(trackPageJson);
    page.setItems(createTracks(trackPageJson));
    return page;
  }

  public static Page<Artist> createArtistPage(String artistPageJson) {
    return createArtistPage(JSONObject.fromObject(artistPageJson));
  }

  public static Page<Artist> createArtistPage(JSONObject artistPageJson) {
    Page page = createItemlessPage(artistPageJson);
    page.setItems(createArtists(artistPageJson));
    return page;
  }


}
