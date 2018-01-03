package com.wrapper.spotify.model_objects.special;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.personalization.interfaces.IArtistTrackModelObject;
import com.wrapper.spotify.requests.data.search.interfaces.ISearchModelObject;

public class SearchResult extends AbstractModelObject implements IArtistTrackModelObject, ISearchModelObject {
  private final Paging<AlbumSimplified> albums;
  private final Paging<Artist> artists;
  private final Paging<PlaylistSimplified> playlists;
  private final Paging<Track> tracks;

  private SearchResult(final SearchResult.Builder builder) {
    super(builder);

    this.albums = builder.albums;
    this.artists = builder.artists;
    this.playlists = builder.playlists;
    this.tracks = builder.tracks;
  }

  public Paging<AlbumSimplified> getAlbums() {
    return albums;
  }

  public Paging<Artist> getArtists() {
    return artists;
  }

  public Paging<PlaylistSimplified> getPlaylists() {
    return playlists;
  }

  public Paging<Track> getTracks() {
    return tracks;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private Paging<AlbumSimplified> albums;
    private Paging<Artist> artists;
    private Paging<PlaylistSimplified> playlists;
    private Paging<Track> tracks;

    public Builder setAlbums(Paging<AlbumSimplified> albums) {
      this.albums = albums;
      return this;
    }

    public Builder setArtists(Paging<Artist> artists) {
      this.artists = artists;
      return this;
    }

    public Builder setPlaylists(Paging<PlaylistSimplified> playlists) {
      this.playlists = playlists;
      return this;
    }

    public Builder setTracks(Paging<Track> tracks) {
      this.tracks = tracks;
      return this;
    }

    @Override
    public SearchResult build() {
      return new SearchResult(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<SearchResult> {
    public SearchResult createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new SearchResult.Builder()
              .setAlbums(new AlbumSimplified.JsonUtil().createModelObjectPaging(jsonObject.getAsJsonObject("albums")))
              .setArtists(new Artist.JsonUtil().createModelObjectPaging(jsonObject.getAsJsonObject("artists")))
              .setPlaylists(new PlaylistSimplified.JsonUtil().createModelObjectPaging(jsonObject.getAsJsonObject("playlists")))
              .setTracks(new Track.JsonUtil().createModelObjectPaging(jsonObject.getAsJsonObject("tracks")))
              .build();
    }
  }

}