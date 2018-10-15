package com.wrapper.spotify.requests.data.playlists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get full details of the tracks of a playlist owned by a Spotify user.
 */
public class GetPlaylistsTracksRequest extends AbstractDataRequest {

  /**
   * The private {@link GetPlaylistsTracksRequest} constructor.
   *
   * @param builder A {@link GetPlaylistsTracksRequest.Builder}.
   */
  private GetPlaylistsTracksRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get a playlist's tracks.
   *
   * @return A playlist's tracks.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Paging<PlaylistTrack> execute() throws
          IOException,
          SpotifyWebApiException {
    return new PlaylistTrack.JsonUtil().createModelObjectPaging(getJson());
  }

  /**
   * Builder class for building a {@link GetPlaylistsTracksRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetPlaylistsTracksRequest.Builder}.
     * <p>
     * Both Public and Private playlists belonging to any user are retrievable on provision of a valid access token.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The user ID setter.
     *
     * @deprecated Playlist IDs are unique for themselves. This parameter is thus no longer used
     * (https://developer.spotify.com/community/news/2018/06/12/changes-to-playlist-uris/)
     * @param user_id The user's Spotify user ID.
     * @return A {@link GetPlaylistsTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    @Deprecated
    public Builder user_id(final String user_id) {
      assert (user_id != null);
      assert (!user_id.equals(""));
      return setPathParameter("user_id", user_id);
    }

    /**
     * The playlist ID setter.
     *
     * @param playlist_id The Spotify ID for the playlist.
     * @return A {@link GetPlaylistsTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify: URIs &amp; IDs</a>
     */
    public Builder playlist_id(final String playlist_id) {
      assert (playlist_id != null);
      assert (!playlist_id.equals(""));
      return setPathParameter("playlist_id", playlist_id);
    }

    /**
     * The fields filter setter.
     *
     * @param fields Optional. Filters for the query: a comma-separated list of the fields to return.
     *               If omitted, all fields are returned.
     * @return A {@link GetPlaylistsTracksRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/get-playlists-tracks/#tablepress-107">
     * Spotify: More Details on Playlist Fields</a>
     */
    public Builder fields(final String fields) {
      assert (fields != null);
      assert (!fields.equals(""));
      return setQueryParameter("fields", fields);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The maximum number of tracks to return. Default: 100. Minimum: 1. Maximum: 100.
     * @return A {@link GetPlaylistsTracksRequest.Builder}.
     */
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 100);
      return setQueryParameter("limit", limit);
    }

    /**
     * The offset setter.
     *
     * @param offset Optional. The index of the first track to return. Default: 0 (the first object).
     * @return A {@link GetPlaylistsTracksRequest.Builder}.
     */
    public Builder offset(final Integer offset) {
      assert (offset >= 0);
      return setQueryParameter("offset", offset);
    }

    /**
     * The market country code setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this
     *               parameter if you want to apply Track Relinking.
     * @return A {@link GetPlaylistsTracksRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     * @see <a href="https://developer.spotify.com/web-api/track-relinking-guide/">Spotify: Track Relinking Guide</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetPlaylistsTracksRequest}.
     */
    @Override
    public GetPlaylistsTracksRequest build() {
      setPath("/v1/playlists/{playlist_id}/tracks");
      return new GetPlaylistsTracksRequest(this);
    }
  }
}
