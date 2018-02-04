package com.wrapper.spotify.model_objects.special;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;
import com.wrapper.spotify.requests.data.search.interfaces.ISearchModelObject;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/console/get-featured-playlists">
 * Search Result objects</a> by building instances from this class.
 */
public class SearchResult extends AbstractModelObject implements IArtistTrackModelObject, ISearchModelObject {
  private final Paging<AlbumSimplified> albums;
  private final Paging<Artist> artists;
  private final Paging<PlaylistSimplified> playlists;
  private final Paging<Track> tracks;

  private SearchResult(final Builder builder) {
    super(builder);

    this.albums = builder.albums;
    this.artists = builder.artists;
    this.playlists = builder.playlists;
    this.tracks = builder.tracks;
  }

  /**
   * Get the album objects contained in the search result object. <br>
   * <b>Note:</b> The serch result only contains album objects when the {@code album} parameter has been specified
   * in the request.
   *
   * @return Albums from the search result.
   */
  public Paging<AlbumSimplified> getAlbums() {
    return albums;
  }

  /**
   * Get the artist objects contained in the search result object. <br>
   * <b>Note:</b> The serch result only contains artist objects when the {@code artist} parameter has been specified
   * in the request.
   *
   * @return Artists from the search result.
   */
  public Paging<Artist> getArtists() {
    return artists;
  }

  /**
   * Get the playlist objects contained in the search result object. <br>
   * <b>Note:</b> The serch result only contains playlist objects when the {@code playlis} parameter has been specified
   * in the request.
   *
   * @return Playlists from the search result.
   */
  public Paging<PlaylistSimplified> getPlaylists() {
    return playlists;
  }

  /**
   * Get the track objects contained in the search result object. <br>
   * <b>Note:</b> The serch result only contains track objects when the {@code track} parameter has been specified
   * in the request.
   *
   * @return Tracks from the search result.
   */
  public Paging<Track> getTracks() {
    return tracks;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link SearchResult} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Paging<AlbumSimplified> albums;
    private Paging<Artist> artists;
    private Paging<PlaylistSimplified> playlists;
    private Paging<Track> tracks;

    /**
     * The albums setter.
     *
     * @param albums Albums from the search result.
     * @return A {@link SearchResult.Builder}.
     */
    public Builder setAlbums(Paging<AlbumSimplified> albums) {
      this.albums = albums;
      return this;
    }

    /**
     * The artists setter.
     *
     * @param artists Artists from the search result.
     * @return A {@link SearchResult.Builder}.
     */
    public Builder setArtists(Paging<Artist> artists) {
      this.artists = artists;
      return this;
    }

    /**
     * The playlists setter.
     *
     * @param playlists Playlists from the search result.
     * @return A {@link SearchResult.Builder}.
     */
    public Builder setPlaylists(Paging<PlaylistSimplified> playlists) {
      this.playlists = playlists;
      return this;
    }

    /**
     * The tracks setter.
     *
     * @param tracks Tracks from the search result.
     * @return A {@link SearchResult.Builder}.
     */
    public Builder setTracks(Paging<Track> tracks) {
      this.tracks = tracks;
      return this;
    }

    @Override
    public SearchResult build() {
      return new SearchResult(this);
    }
  }

  /**
   * JsonUtil class for building {@link SearchResult} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<SearchResult> {
    public SearchResult createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new SearchResult.Builder()
              .setAlbums(
                      hasAndNotNull(jsonObject, "albums")
                              ? new AlbumSimplified.JsonUtil().createModelObjectPaging(
                              jsonObject.getAsJsonObject("albums"))
                              : null)
              .setArtists(
                      hasAndNotNull(jsonObject, "artists")
                              ? new Artist.JsonUtil().createModelObjectPaging(
                              jsonObject.getAsJsonObject("artists"))
                              : null)
              .setPlaylists(
                      hasAndNotNull(jsonObject, "playlists")
                              ? new PlaylistSimplified.JsonUtil().createModelObjectPaging(
                              jsonObject.getAsJsonObject("playlists"))
                              : null)
              .setTracks(
                      hasAndNotNull(jsonObject, "tracks")
                              ? new Track.JsonUtil().createModelObjectPaging(
                              jsonObject.getAsJsonObject("tracks"))
                              : null)
              .build();
    }
  }

}