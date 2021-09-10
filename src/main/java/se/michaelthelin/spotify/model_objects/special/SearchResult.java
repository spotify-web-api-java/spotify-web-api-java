package se.michaelthelin.spotify.model_objects.special;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;
import se.michaelthelin.spotify.requests.data.search.interfaces.ISearchModelObject;

/**
 * Retrieve the searched-for items by building instances from this class. This objects contains
 * for every type specified by the {@code type} parameter in the {@link SearchItemRequest}
 * the searched-for items wrapped in a {@link Paging} object.
 */
@JsonDeserialize(builder = SearchResult.Builder.class)
public class SearchResult extends AbstractModelObject implements IArtistTrackModelObject, ISearchModelObject {
  private final Paging<AlbumSimplified> albums;
  private final Paging<Artist> artists;
  private final Paging<EpisodeSimplified> episodes;
  private final Paging<PlaylistSimplified> playlists;
  private final Paging<ShowSimplified> shows;
  private final Paging<Track> tracks;

  private SearchResult(final Builder builder) {
    super(builder);

    this.albums = builder.albums;
    this.artists = builder.artists;
    this.episodes = builder.episodes;
    this.playlists = builder.playlists;
    this.shows = builder.shows;
    this.tracks = builder.tracks;
  }

  /**
   * Get the album objects contained in the search result object. <br>
   * <b>Note:</b> The search result only contains album objects when the {@code album} parameter has been specified
   * in the request.
   *
   * @return Albums from the search result.
   */
  public Paging<AlbumSimplified> getAlbums() {
    return albums;
  }

  /**
   * Get the artist objects contained in the search result object. <br>
   * <b>Note:</b> The search result only contains artist objects when the {@code artist} parameter has been specified
   * in the request.
   *
   * @return Artists from the search result.
   */
  public Paging<Artist> getArtists() {
    return artists;
  }

  /**
   * Get the episode objects contained in the search result object. <br>
   * <b>Note:</b> The search result only contains episode objects when the {@code episode} parameter has been specified
   * in the request.
   *
   * @return Episodes from the search result.
   */
  public Paging<EpisodeSimplified> getEpisodes() {
    return episodes;
  }

  /**
   * Get the playlist objects contained in the search result object. <br>
   * <b>Note:</b> The search result only contains playlist objects when the {@code playlist} parameter has been specified
   * in the request.
   *
   * @return Playlists from the search result.
   */
  public Paging<PlaylistSimplified> getPlaylists() {
    return playlists;
  }

  /**
   * Get the show objects contained in the search result object. <br>
   * <b>Note:</b> The search result only contains show objects when the {@code show} parameter has been specified
   * in the request.
   *
   * @return Shows from the search result.
   */
  public Paging<ShowSimplified> getShows() {
    return shows;
  }

  /**
   * Get the track objects contained in the search result object. <br>
   * <b>Note:</b> The search result only contains track objects when the {@code track} parameter has been specified
   * in the request.
   *
   * @return Tracks from the search result.
   */
  public Paging<Track> getTracks() {
    return tracks;
  }

  @Override
  public String toString() {
    return "SearchResult(albums=" + albums + ", artists=" + artists + ", episodes=" + episodes + ", playlists="
        + playlists + ", shows=" + shows + ", tracks=" + tracks + ")";
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
    private Paging<EpisodeSimplified> episodes;
    private Paging<PlaylistSimplified> playlists;
    private Paging<ShowSimplified> shows;
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
     * The episodes setter.
     *
     * @param episodes Episodes from the search result.
     * @return A {@link SearchResult.Builder}.
     */
    public Builder setEpisodes(Paging<EpisodeSimplified> episodes) {
      this.episodes = episodes;
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
     * The shows setter.
     *
     * @param shows Shows from the search result.
     * @return A {@link SearchResult.Builder}.
     */
    public Builder setShows(Paging<ShowSimplified> shows) {
      this.shows = shows;
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
        .setEpisodes(
          hasAndNotNull(jsonObject, "episodes")
            ? new EpisodeSimplified.JsonUtil().createModelObjectPaging(
            jsonObject.getAsJsonObject("episodes"))
            : null)
        .setPlaylists(
          hasAndNotNull(jsonObject, "playlists")
            ? new PlaylistSimplified.JsonUtil().createModelObjectPaging(
            jsonObject.getAsJsonObject("playlists"))
            : null)
        .setShows(
          hasAndNotNull(jsonObject, "shows")
            ? new ShowSimplified.JsonUtil().createModelObjectPaging(
            jsonObject.getAsJsonObject("shows"))
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
