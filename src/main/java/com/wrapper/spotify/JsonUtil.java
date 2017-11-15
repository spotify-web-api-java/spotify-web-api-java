package com.wrapper.spotify;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.models.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonUtil {

  // Album
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
    album.setCopyrights(createCopyrights(albumJson.getJSONArray("copyrights")));
    album.setExternalIds(createExternalIds(albumJson.getJSONObject("external_ids")));
    album.setExternalUrls(createExternalUrls(albumJson.getJSONObject("external_urls")));
    album.setGenres(createGenres(albumJson.getJSONArray("genres")));
    album.setHref(albumJson.getString("href"));
    album.setId(albumJson.getString("id"));
    album.setImages(createImages(albumJson.getJSONArray("images")));
    album.setName(albumJson.getString("name"));
    album.setPopularity(albumJson.getInt("popularity"));
    album.setReleaseDate(albumJson.getString("release_date"));
    album.setReleaseDatePrecision(ReleaseDatePrecision.valueOf(albumJson.getString("release_date_precision")));
    album.setTracks(createSimpleTrackPage(albumJson.getJSONObject("tracks")));
    album.setType(createObjectType(albumJson.getString("type")));
    album.setUri(albumJson.getString("uri"));

    return album;
  }

  public static List<Album> createAlbums(String json) {
    JSONObject jsonObject = JSONObject.fromObject(json);
    JSONArray albumsJsonArray = jsonObject.getJSONArray("albums");
    return createAlbums(albumsJsonArray);

  }

  public static List<Album> createAlbums(JSONArray jsonArray) {
    List<Album> returnedAlbums = new ArrayList<>();
    for (int i = 0; i < jsonArray.size(); i++) {
      returnedAlbums.add(createAlbum(jsonArray.getJSONObject(i)));
    }
    return returnedAlbums;
  }

  public static Paging<Album> createAlbumPage(JSONObject albumPageJson) {
    Paging<Album> page = createItemlessAlbumPage(albumPageJson.getJSONObject("albums"));
    page.setItems(createAlbums(albumPageJson.getJSONObject("albums").getJSONArray("items")));
    return page;
  }

  private static Paging<Album> createItemlessAlbumPage(JSONObject pageJson) {
    Paging<Album> page = new Paging<>();
    page.setHref(pageJson.getString("href"));
    page.setLimit(pageJson.getInt("limit"));
    if (existsAndNotNull("next", pageJson)) {
      page.setNext(pageJson.getString("next"));
    }
    page.setOffset(pageJson.getInt("offset"));
    if (existsAndNotNull("previous", pageJson)) {
      page.setPrevious(pageJson.getString("previous"));
    }
    page.setTotal(pageJson.getInt("total"));
    return page;
  }

  // SimpleAlbum
  public static AlbumSimplified createSimpleAlbum(JSONObject simpleAlbumJson) {
    if (simpleAlbumJson == null || simpleAlbumJson.isNullObject()) {
      return null;
    }

    AlbumSimplified simpleAlbum = new AlbumSimplified();

    simpleAlbum.setAlbumType(createAlbumType(simpleAlbumJson.getString("album_type")));
    simpleAlbum.setExternalUrls(createExternalUrls(simpleAlbumJson.getJSONObject("external_urls")));
    simpleAlbum.setHref(simpleAlbumJson.getString("href"));
    simpleAlbum.setId(simpleAlbumJson.getString("id"));
    simpleAlbum.setImages(createImages(simpleAlbumJson.getJSONArray("images")));
    simpleAlbum.setName(simpleAlbumJson.getString("name"));
    simpleAlbum.setType(createObjectType(simpleAlbumJson.getString("type")));
    simpleAlbum.setUri(simpleAlbumJson.getString("uri"));
    simpleAlbum.setAvailableMarkets(
            createAvailableMarkets(simpleAlbumJson.getJSONArray("available_markets")));

    return simpleAlbum;
  }

  public static List<AlbumSimplified> createAlbumsSimplified(String json) {
    JSONArray albumsJsonArray = JSONObject.fromObject(json).getJSONArray("albums");
    return createAlbumsSimplified(albumsJsonArray);
  }

  public static List<AlbumSimplified> createAlbumsSimplified(JSONArray jsonArray) {
    List<AlbumSimplified> returnedAlbums = new ArrayList<>();
    for (int i = 0; i < jsonArray.size(); i++) {
      returnedAlbums.add(createSimpleAlbum(jsonArray.getJSONObject(i)));
    }
    return returnedAlbums;
  }

  public static Paging<AlbumSimplified> createSimpleAlbumPage(String simpleAlbumPageJson) {
    return createSimpleAlbumPage(JSONObject.fromObject(simpleAlbumPageJson));
  }

  private static Paging<AlbumSimplified> createItemlessSimpleAlbumPage(JSONObject pageJson) {
    Paging<AlbumSimplified> page = new Paging<>();
    page.setHref(pageJson.getString("href"));
    page.setLimit(pageJson.getInt("limit"));
    if (existsAndNotNull("next", pageJson)) {
      page.setNext(pageJson.getString("next"));
    }
    page.setOffset(pageJson.getInt("offset"));
    if (existsAndNotNull("previous", pageJson)) {
      page.setPrevious(pageJson.getString("previous"));
    }
    page.setTotal(pageJson.getInt("total"));
    return page;
  }

  public static Paging<AlbumSimplified> createSimpleAlbumPage(JSONObject simpleAlbumPageJson) {
    Paging<AlbumSimplified> page = createItemlessSimpleAlbumPage(simpleAlbumPageJson);
    page.setItems(createAlbumsSimplified(simpleAlbumPageJson.getJSONArray("items")));
    return page;
  }

  // AlbumType
  public static AlbumType createAlbumType(String albumType) {
    if ("null".equalsIgnoreCase(albumType)) {
      return null;
    }
    return AlbumType.valueOf(albumType.toUpperCase());
  }

  // Artist
  public static Artist createArtist(String json) {
    return createArtist(JSONObject.fromObject(json));
  }

  public static Artist createArtist(JSONObject jsonObject) {
    if (jsonObject == null || jsonObject.isNullObject()) {
      return null;
    }

    Artist artist = new Artist();

    artist.setExternalUrls(createExternalUrls(jsonObject.getJSONObject("external_urls")));
    artist.setGenres(createGenres(jsonObject.getJSONArray("genres")));
    artist.setHref(jsonObject.getString("href"));
    artist.setFollowers(createFollowers(jsonObject.getJSONObject("followers")));
    artist.setId(jsonObject.getString("id"));
    artist.setImages(createImages(jsonObject.getJSONArray("images")));
    artist.setName(jsonObject.getString("name"));
    artist.setPopularity(jsonObject.getInt("popularity"));
    artist.setType(createObjectType(jsonObject.getString("type")));
    artist.setUri(jsonObject.getString("uri"));

    return artist;
  }

  public static List<Artist> createArtists(String json) {
    return createArtists(JSONObject.fromObject(json));
  }

  public static List<Artist> createArtists(JSONObject jsonObject) {
    List<Artist> returnedArtists = new ArrayList<>();
    JSONArray artistsObject = jsonObject.getJSONArray("artists");
    for (int i = 0; i < artistsObject.size(); i++) {
      returnedArtists.add(createArtist(artistsObject.getJSONObject(i)));
    }
    return returnedArtists;
  }

  private static List<Artist> createArtists(JSONArray artistsJsonArray) {
    List<Artist> returnedArtists = new ArrayList<>();
    for (int i = 0; i < artistsJsonArray.size(); i++) {
      returnedArtists.add(createArtist(artistsJsonArray.getJSONObject(i)));
    }
    return returnedArtists;
  }

  public static Paging<Artist> createArtistPage(JSONObject artistPageJson) {
    Paging<Artist> page = createItemlessArtistPage(artistPageJson);
    page.setItems(createArtists(artistPageJson.getJSONArray("items")));
    return page;
  }

  private static Paging<Artist> createItemlessArtistPage(JSONObject pageJson) {
    Paging<Artist> page = new Paging<>();
    page.setHref(pageJson.getString("href"));
    page.setLimit(pageJson.getInt("limit"));
    if (existsAndNotNull("next", pageJson)) {
      page.setNext(pageJson.getString("next"));
    }
    page.setOffset(pageJson.getInt("offset"));
    if (existsAndNotNull("previous", pageJson)) {
      page.setPrevious(pageJson.getString("previous"));
    }
    page.setTotal(pageJson.getInt("total"));
    return page;
  }

  // ArtistSimplified
  public static ArtistSimplified createSimpleArtist(JSONObject simpleArtistJson) {
    if (simpleArtistJson == null || simpleArtistJson.isNullObject()) {
      return null;
    }

    ArtistSimplified simpleArtist = new ArtistSimplified();

    simpleArtist.setExternalUrls(createExternalUrls(simpleArtistJson.getJSONObject("external_urls")));
    simpleArtist.setHref(simpleArtistJson.getString("href"));
    simpleArtist.setId(simpleArtistJson.getString("id"));
    simpleArtist.setName(simpleArtistJson.getString("name"));
    simpleArtist.setType(createObjectType(simpleArtistJson.getString("type")));
    simpleArtist.setUri(simpleArtistJson.getString("uri"));

    return simpleArtist;
  }

  private static List<ArtistSimplified> createSimpleArtists(JSONArray artists) {
    List<ArtistSimplified> returnedArtists = new ArrayList<>();
    for (int i = 0; i < artists.size(); i++) {
      returnedArtists.add(createSimpleArtist(artists.getJSONObject(i)));
    }
    return returnedArtists;
  }

  // AuthorizationCodeCredentials
  public static AuthorizationCodeCredentials createTokenResponse(JSONObject tokenResponse) {
    AuthorizationCodeCredentials response = new AuthorizationCodeCredentials();

    response.setAccessToken(tokenResponse.getString("access_token"));
    response.setExpiresIn(tokenResponse.getInt("expires_in"));
    response.setRefreshToken(tokenResponse.getString("refresh_token"));
    response.setTokenType(tokenResponse.getString("token_type"));

    return response;
  }

  // ClientCredentials
  public static ClientCredentials createApplicationAuthenticationToken(JSONObject jsonObject) {
    final ClientCredentials token = new ClientCredentials();

    token.setAccessToken(jsonObject.getString("access_token"));
    token.setExpiresIn(jsonObject.getInt("expires_in"));
    token.setTokenType(jsonObject.getString("token_type"));

    return token;
  }

  // Copyright
  /**
   * Create a list of Copyright object.
   * @param copyrightsJson A JSON array containing copyright information retrieved from the Web API.
   * @return A list of Copyright objects.
   */
  public static List<Copyright> createCopyrights(JSONArray copyrightsJson) {
    List<Copyright> copyrights = new ArrayList<>();
    for (int i = 0; i < copyrightsJson.size(); i++) {
      Copyright copyright = new Copyright();
      JSONObject copyrightJson = copyrightsJson.getJSONObject(i);
      if (existsAndNotNull("text", copyrightJson)) {
        copyright.setText(copyrightJson.getString("text"));
      }
      if (existsAndNotNull("type", copyrightJson)) {
        copyright.setType(CopyrightType.valueOf(copyrightJson.getString("type")));
      }
      copyrights.add(copyright);
    }
    return copyrights;
  }

  // ExternalIds
  public static ExternalIds createExternalIds(JSONObject externalIds) {
    ExternalIds returnedExternalIds = new ExternalIds();
    Map<String,String> addedIds = returnedExternalIds.getExternalIds();

    for (Object keyObject : externalIds.keySet()) {
      String key = (String) keyObject;
      addedIds.put(key, externalIds.getString(key));
    }

    return returnedExternalIds;
  }

  // ExternalUrl
  public static ExternalUrls createExternalUrls(JSONObject externalUrls) {
    ExternalUrls returnedExternalUrls = new ExternalUrls();
    Map<String,String> addedExternalUrls = returnedExternalUrls.getExternalUrls();
    for (Object keyObject : externalUrls.keySet()) {
      String key = (String) keyObject;
      addedExternalUrls.put(key, externalUrls.getString(key));
    }
    return returnedExternalUrls;
  }

  // Followers
  private static Followers createFollowers(JSONObject followers) {
    final Followers returnedFollowers = new Followers();
    if (existsAndNotNull("href", followers)) {
      returnedFollowers.setHref(followers.getString("href"));
    }
    if (existsAndNotNull("total", followers)) {
      returnedFollowers.setTotal(followers.getInt("total"));
    }
    return returnedFollowers;
  }

  // Image
  private static Image createImage(JSONObject image) {
    if (image.isNullObject()) {
      return null;
    }

    final Image returnedImage = new Image();
    if (image.containsKey("height") && !image.get("height").equals(JSONNull.getInstance())) {
      returnedImage.setHeight(image.getInt(("height")));
    }
    if (image.containsKey("width") && !image.get("width").equals(JSONNull.getInstance())) {
      returnedImage.setWidth(image.getInt(("width")));
    }
    if (image.containsKey("url") && !image.get("url").equals(JSONNull.getInstance())) {
      returnedImage.setUrl(image.getString("url"));
    }
    return returnedImage;
  }

  public static List<Image> createImages(JSONArray images) {
    List<Image> returnedImages = new ArrayList<>();
    for (int i = 0; i < images.size(); i++) {
      returnedImages.add(createImage(images.getJSONObject(i)));
    }
    return returnedImages;
  }

  // ObjectType
  public static ObjectType createObjectType(String type) {
    return ObjectType.valueOf(type.toUpperCase());
  }

  // Playlist
  public static Playlist createPlaylist(JSONObject jsonObject) {
    final Playlist returnedPlaylist = new Playlist();

    returnedPlaylist.setCollaborative(jsonObject.getBoolean("collaborative"));

    if (existsAndNotNull("description", jsonObject)) {
      returnedPlaylist.setDescription(jsonObject.getString("description"));
    }
    returnedPlaylist.setExternalUrls(createExternalUrls(jsonObject.getJSONObject("external_urls")));

    if (existsAndNotNull("followers", jsonObject)) {
      returnedPlaylist.setFollowers(createFollowers(jsonObject.getJSONObject("followers")));
    }

    returnedPlaylist.setHref(jsonObject.getString("href"));
    returnedPlaylist.setId(jsonObject.getString("id"));
    if (existsAndNotNull("images", jsonObject)) {
      returnedPlaylist.setImages(createImages(jsonObject.getJSONArray("images")));
    }

    returnedPlaylist.setName(jsonObject.getString("name"));
    returnedPlaylist.setOwner(createUser(jsonObject.getJSONObject("owner")));
    returnedPlaylist.setPublicAccess(jsonObject.getBoolean("public"));

    if (existsAndNotNull("tracks", jsonObject)) {
      returnedPlaylist.setTracks(createPlaylistTrackPage(jsonObject.getJSONObject("tracks")));
    }

    returnedPlaylist.setType(createObjectType(jsonObject.getString("type")));
    returnedPlaylist.setUri(jsonObject.getString("uri"));
    return returnedPlaylist;
  }

  private static List<Playlist> createPlaylists(JSONArray playlistsJson) {
    final List<Playlist> returnedPlaylists = new ArrayList<>();
    for (int i = 0; i < playlistsJson.size(); i++) {
      returnedPlaylists.add(createPlaylist(playlistsJson.getJSONObject(i)));
    }
    return returnedPlaylists;
  }

  private static Paging<Playlist> createItemlessPlaylistPage(JSONObject pageJson) {
    Paging<Playlist> page = new Paging<>();
    page.setHref(pageJson.getString("href"));
    page.setLimit(pageJson.getInt("limit"));
    if (existsAndNotNull("next", pageJson)) {
      page.setNext(pageJson.getString("next"));
    }
    page.setOffset(pageJson.getInt("offset"));
    if (existsAndNotNull("previous", pageJson)) {
      page.setPrevious(pageJson.getString("previous"));
    }
    page.setTotal(pageJson.getInt("total"));
    return page;
  }

  public static Paging<Playlist> createPlaylistPage(JSONObject jsonObject) {
    final Paging<Playlist> playlistPage = createItemlessPlaylistPage(jsonObject);
    playlistPage.setItems(createPlaylists(JSONArray.fromObject(jsonObject.getJSONArray("items"))));
    return playlistPage;
  }

  // PlaylistSimplified
  public static PlaylistSimplified createSimplePlaylist(JSONObject playlistJson) {
    final PlaylistSimplified playlist = new PlaylistSimplified();
    playlist.setCollaborative(playlistJson.getBoolean("collaborative"));
    playlist.setExternalUrls(createExternalUrls(playlistJson.getJSONObject("external_urls")));
    playlist.setHref(playlistJson.getString("href"));
    playlist.setId(playlistJson.getString("id"));
    playlist.setImages(createImages(playlistJson.getJSONArray("images")));
    playlist.setName(playlistJson.getString("name"));
    playlist.setOwner(createUser(playlistJson.getJSONObject("owner")));
    if (existsAndNotNull("public", playlistJson)) {
      playlist.setPublicAccess(playlistJson.getBoolean("public"));
    }
    playlist.setTracks(createPlaylistTracksInformation(playlistJson.getJSONObject("tracks")));
    playlist.setType(createObjectType(playlistJson.getString("type")));
    playlist.setUri(playlistJson.getString("uri"));
    return playlist;
  }

  public static List<PlaylistSimplified> createSimplePlaylists(JSONArray playlistsJson) {
    List<PlaylistSimplified> returnedPlaylists = new ArrayList<>();
    for (int i = 0; i < playlistsJson.size(); i++) {
      returnedPlaylists.add(createSimplePlaylist(playlistsJson.getJSONObject(i)));
    }
    return returnedPlaylists;
  }

  private static Paging<PlaylistSimplified> createItemlessSimplePlaylistPage(JSONObject pageJson) {
    Paging<PlaylistSimplified> page = new Paging<>();
    page.setHref(pageJson.getString("href"));
    page.setLimit(pageJson.getInt("limit"));
    if (existsAndNotNull("next", pageJson)) {
      page.setNext(pageJson.getString("next"));
    }
    page.setOffset(pageJson.getInt("offset"));
    if (existsAndNotNull("previous", pageJson)) {
      page.setPrevious(pageJson.getString("previous"));
    }
    page.setTotal(pageJson.getInt("total"));
    return page;
  }

  public static Paging<PlaylistSimplified> createSimplePlaylistsPage(JSONObject jsonObject) {
    Paging<PlaylistSimplified> playlistsPage = createItemlessSimplePlaylistPage(jsonObject);
    playlistsPage.setItems(createSimplePlaylists(jsonObject.getJSONArray("items")));
    return playlistsPage;
  }

  // PlaylistTrack
  private static PlaylistTrack createPlaylistTrack(JSONObject playlistTrackJson) {
    final PlaylistTrack returnedPlaylistTrack = new PlaylistTrack();
    try {
      returnedPlaylistTrack.setAddedAt(createDate(playlistTrackJson.getString("added_at")));
    } catch (ParseException e) {
      returnedPlaylistTrack.setAddedAt(null);
    }
    try {
      returnedPlaylistTrack.setAddedBy(createUser(playlistTrackJson.getJSONObject("added_by")));
    } catch (JSONException e) {
      returnedPlaylistTrack.setAddedBy(null);
    }
    returnedPlaylistTrack.setTrack(createTrack(playlistTrackJson.getJSONObject("track")));
    return returnedPlaylistTrack;
  }

  private static List<PlaylistTrack> createPlaylistTracksInformation(JSONArray playlistTrackPageJson) {
    final List<PlaylistTrack> returnedPlaylistTracks = new ArrayList<>();
    for (int i = 0; i < playlistTrackPageJson.size(); i++) {
      returnedPlaylistTracks.add(createPlaylistTrack(playlistTrackPageJson.getJSONObject(i)));
    }
    return returnedPlaylistTracks;
  }

  private static Paging<PlaylistTrack> createItemlessPlaylistTrackPage(JSONObject pageJson) {
    Paging<PlaylistTrack> page = new Paging<>();
    page.setHref(pageJson.getString("href"));
    page.setLimit(pageJson.getInt("limit"));
    if (existsAndNotNull("next", pageJson)) {
      page.setNext(pageJson.getString("next"));
    }
    page.setOffset(pageJson.getInt("offset"));
    if (existsAndNotNull("previous", pageJson)) {
      page.setPrevious(pageJson.getString("previous"));
    }
    page.setTotal(pageJson.getInt("total"));
    return page;
  }

  public static Paging<PlaylistTrack> createPlaylistTrackPage(JSONObject playlistTrackPageJson) {
    final Paging<PlaylistTrack> returnedPage = createItemlessPlaylistTrackPage(playlistTrackPageJson);
    returnedPage.setItems(createPlaylistTracksInformation(playlistTrackPageJson.getJSONArray("items")));
    return returnedPage;
  }

  // PlaylistTracksInformation
  private static PlaylistTracksInformation createPlaylistTracksInformation(JSONObject tracksInformationJson) {
    PlaylistTracksInformation playlistTracksInformation = new PlaylistTracksInformation();
    playlistTracksInformation.setHref(tracksInformationJson.getString("href"));
    playlistTracksInformation.setTotal(tracksInformationJson.getInt("total"));
    return playlistTracksInformation;
  }

  // ProductType
  private static ProductType createProductType(String product) {
    return ProductType.valueOf(product.toUpperCase());
  }

  // RefreshAccessTokenCredentials
  public static RefreshAccessTokenCredentials createRefreshAccessTokenResponse(JSONObject jsonObject) {
    RefreshAccessTokenCredentials refreshAccessTokenResponse = new RefreshAccessTokenCredentials();

    refreshAccessTokenResponse.setTokenType(jsonObject.getString("token_type"));
    refreshAccessTokenResponse.setAccessToken(jsonObject.getString("access_token"));
    refreshAccessTokenResponse.setExpiresIn(jsonObject.getInt("expires_in"));

    return refreshAccessTokenResponse;
  }

  // SnapshotResult
  public static SnapshotResult createSnapshotResult(String jsonString) {
    JSONObject jsonObject = JSONObject.fromObject(jsonString);
    SnapshotResult result = new SnapshotResult();
    result.setSnapshotId(jsonObject.getString("snapshot_id"));
    return result;
  }

  // Track
  public static Track createTrack(String trackJson) {
    return createTrack(JSONObject.fromObject(trackJson));
  }

  private static Track createTrack(JSONObject trackJson) {

    Track track = new Track();

    track.setAlbum(createSimpleAlbum(trackJson.getJSONObject("album")));
    track.setArtists(createSimpleArtists(trackJson.getJSONArray("artists")));
    track.setAvailableMarkets(createAvailableMarkets(trackJson.getJSONArray("available_markets")));
    track.setDiscNumber(trackJson.getInt("disc_number"));
    track.setDurationMs(trackJson.getInt("duration_ms"));
    track.setExplicit(trackJson.getBoolean("explicit"));
    track.setExternalIds(createExternalIds(trackJson.getJSONObject("external_ids")));
    track.setExternalUrls(createExternalUrls(trackJson.getJSONObject("external_urls")));
    track.setHref(trackJson.getString("href"));
    track.setId(trackJson.getString("id"));
    track.setName(trackJson.getString("name"));
    track.setPopularity(trackJson.getInt("popularity"));
    track.setPreviewUrl(trackJson.getString("preview_url"));
    track.setTrackNumber(trackJson.getInt(("track_number")));
    track.setType(createObjectType(trackJson.getString("type")));
    track.setUri(trackJson.getString("uri"));

    return track;
  }

  public static List<Track> createTracks(String json) {
    return createTracks(JSONObject.fromObject(json));
  }

  public static List<Track> createTracks(JSONObject jsonObject) {
    List<Track> returnedTracks = new ArrayList<>();
    JSONArray tracks = jsonObject.getJSONArray("tracks");
    for (int i = 0; i < tracks.size(); i++) {
      returnedTracks.add(createTrack(tracks.getJSONObject(i)));
    }
    return returnedTracks;
  }

  private static List<Track> createTracks(JSONArray tracksJson) {
    List<Track> returnedTracks = new ArrayList<>();
    for (int i = 0; i < tracksJson.size(); i++) {
      returnedTracks.add(createTrack(tracksJson.getJSONObject(i)));
    }
    return returnedTracks;
  }

  public static Paging<Track> createTrackPage(JSONObject trackPageJson) {
    Paging<Track> page = createItemlessTrackPage(trackPageJson.getJSONObject("tracks"));
    page.setItems(createTracks(trackPageJson.getJSONObject("tracks").getJSONArray("items")));
    return page;
  }

  private static Paging<Track> createItemlessTrackPage(JSONObject pageJson) {
    Paging<Track> page = new Paging<>();
    page.setHref(pageJson.getString("href"));
    page.setLimit(pageJson.getInt("limit"));
    if (existsAndNotNull("next", pageJson)) {
      page.setNext(pageJson.getString("next"));
    }
    page.setOffset(pageJson.getInt("offset"));
    if (existsAndNotNull("previous", pageJson)) {
      page.setPrevious(pageJson.getString("previous"));
    }
    page.setTotal(pageJson.getInt("total"));
    return page;
  }

  // TrackSimplified
  public static TrackSimplified createSimpleTrack(JSONObject simpleTrackJson) {
    TrackSimplified track = new TrackSimplified();

    track.setArtists(createSimpleArtists(simpleTrackJson.getJSONArray("artists")));
    track.setAvailableMarkets(createAvailableMarkets(simpleTrackJson.getJSONArray("available_markets")));
    track.setDiscNumber(simpleTrackJson.getInt("disc_number"));
    track.setDurationMs(simpleTrackJson.getInt("duration_ms"));
    track.setExplicit(simpleTrackJson.getBoolean("explicit"));
    track.setExternalUrls(createExternalUrls(simpleTrackJson.getJSONObject("externalUrls")));
    track.setHref(simpleTrackJson.getString("href"));
    track.setId(simpleTrackJson.getString("id"));
    track.setName(simpleTrackJson.getString("name"));
    track.setPreviewUrl(simpleTrackJson.getString("preview_url"));
    track.setTrackNumber(simpleTrackJson.getInt("track_number"));
    track.setType(createObjectType(simpleTrackJson.getString("type")));
    track.setUri(simpleTrackJson.getString("uri"));

    return track;
  }

  public static List<TrackSimplified> createTracksSimplified(JSONArray tracksJson) {
    List<TrackSimplified> tracks = new ArrayList<>();
    for (int i = 0; i < tracksJson.size(); i++) {
      tracks.add(createSimpleTrack(tracksJson.getJSONObject(i)));
    }
    return tracks;
  }

  private static Paging<TrackSimplified> createSimpleTrackPage(JSONObject simpleTrackPageJson) {
    Paging<TrackSimplified> page = createItemlessSimpleTrackPage(simpleTrackPageJson);
    page.setItems(createTracksSimplified(simpleTrackPageJson.getJSONArray("items")));
    return page;
  }

  private static Paging<TrackSimplified> createItemlessSimpleTrackPage(JSONObject pageJson) {
    Paging<TrackSimplified> page = new Paging<>();
    page.setHref(pageJson.getString("href"));
    page.setLimit(pageJson.getInt("limit"));
    if (existsAndNotNull("next", pageJson)) {
      page.setNext(pageJson.getString("next"));
    }
    page.setOffset(pageJson.getInt("offset"));
    if (existsAndNotNull("previous", pageJson)) {
      page.setPrevious(pageJson.getString("previous"));
    }
    page.setTotal(pageJson.getInt("total"));
    return page;
  }

  // User
  public static User createUser(String userJson) {
    return createUser(JSONObject.fromObject(userJson));
  }

  public static User createUser(JSONObject userJson) {
    User user = new User();

    // Always in the user object
    user.setExternalUrls(createExternalUrls(userJson.getJSONObject("external_urls")));
    user.setHref(userJson.getString("href"));
    user.setId(userJson.getString("id"));
    user.setType(createObjectType(userJson.getString("type")));
    user.setUri(userJson.getString("uri"));
    user.setFollowers(createFollowers(userJson.getJSONObject("followers")));

    if (existsAndNotNull("display_name", userJson)) {
      user.setDisplayName(userJson.getString("display_name"));
    }
    if (existsAndNotNull("email", userJson)) {
      user.setEmail(userJson.getString("email"));
    }
    if (existsAndNotNull("images", userJson)) {
      user.setImages(createImages(userJson.getJSONArray("images")));
    }
    if (existsAndNotNull("product", userJson)) {
      user.setProduct(createProductType(userJson.getString("product")));
    }
    if (existsAndNotNull("country", userJson)) {
      user.setCountry(CountryCode.getByCode(userJson.getString("country")));
    }
    if (existsAndNotNull("birthdate", userJson)) {
      user.setBirthdate(userJson.getString("birthdate"));
    }

    return user;
  }



  public static List<String> createGenres(JSONArray genres) {
    List<String> returnedGenres = new ArrayList<>();
    for (int i = 0; i < genres.size(); i++) {
      returnedGenres.add(genres.getString(i));
    }
    return returnedGenres;
  }
  public static List<Boolean> createBooleans(String response) {
    List<Boolean> returnedArray = new ArrayList<>();
    JSONArray tracksContainedArray = JSONArray.fromObject(response);
    for (Object tracksContainedString : tracksContainedArray) {
      if (String.valueOf(tracksContainedString).equals("false")) {
        returnedArray.add(false);
      } else {
        returnedArray.add(true);
      }
    }
    return returnedArray;
  }
  public static List<CountryCode> createAvailableMarkets(JSONArray availableMarketsJson) {
    List<CountryCode> availableMarkets = new ArrayList<>();
    for (int i = 0; i < availableMarketsJson.size(); i++) {
      availableMarkets.add(CountryCode.getByCode(availableMarketsJson.getString(i)));
    }
    return availableMarkets;
  }
  private static boolean existsAndNotNull(String key, JSONObject jsonObject) {
    return jsonObject.containsKey(key) &&
            !JSONNull.getInstance().equals(jsonObject.get(key));
  }
  /**
   * Create a Featured Playlist object
   * @param jsonObject The JSON object containing the featured playlists.
   * @return A Featured Playlists object.
   */
  public static FeaturedPlaylists createFeaturedPlaylist(JSONObject jsonObject) {
    FeaturedPlaylists featuredPlaylists = new FeaturedPlaylists();
    featuredPlaylists.setMessage(jsonObject.getString("message"));
    featuredPlaylists.setPlaylists(JsonUtil.createSimplePlaylistsPage(jsonObject.getJSONObject("playlists")));
    return featuredPlaylists;
  }
  public static NewReleases createNewReleases(JSONObject newReleasesJson) {
    final NewReleases newReleases = new NewReleases();
    newReleases.setAlbums(createSimpleAlbumPage(newReleasesJson.getJSONObject("albums")));
    return newReleases;
  }
  private static Date createDate(String dateString) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
    formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
    return formatter.parse(dateString);
  }
  private static Paging<LibraryTrack> createItemlessLibraryTrackPage(JSONObject pageJson) {
    Paging<LibraryTrack> page = new Paging<>();
    page.setHref(pageJson.getString("href"));
    page.setLimit(pageJson.getInt("limit"));
    if (existsAndNotNull("next", pageJson)) {
      page.setNext(pageJson.getString("next"));
    }
    page.setOffset(pageJson.getInt("offset"));
    if (existsAndNotNull("previous", pageJson)) {
      page.setPrevious(pageJson.getString("previous"));
    }
    page.setTotal(pageJson.getInt("total"));
    return page;
  }
  public static Paging<LibraryTrack> createLibraryTracksPage(JSONObject jsonObject) {
    final Paging<LibraryTrack> libraryTracksPage = createItemlessLibraryTrackPage(jsonObject);
    libraryTracksPage.setItems(createLibraryTracks(
            JSONArray.fromObject(jsonObject.getJSONArray("items"))));

    return libraryTracksPage;
  }
  private static List<LibraryTrack> createLibraryTracks(JSONArray items) {
    final List<LibraryTrack> returnedLibraryTracks = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      returnedLibraryTracks.add(createLibraryTrack(items.getJSONObject(i)));
    }
    return returnedLibraryTracks;
  }
  private static LibraryTrack createLibraryTrack(JSONObject item) {
    final LibraryTrack returnedLibraryTrack = new LibraryTrack();
    try {
      returnedLibraryTrack.setAddedAt(createDate(item.getString("added_at")));
    } catch (ParseException e) {
      returnedLibraryTrack.setAddedAt(null);
    }
    returnedLibraryTrack.setTrack(createTrack(item.getJSONObject("track")));
    return returnedLibraryTrack;
  }
}