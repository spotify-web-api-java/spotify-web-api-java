package com.wrapper.spotify.objects;

import com.neovisionaries.i18n.CountryCode;

import java.util.List;

public class Album extends AbstractModelObject {

  private final AlbumType albumType;
  private final List<ArtistSimplified> artists;
  private final List<CountryCode> availableMarkets;
  private final List<Copyright> copyrights;
  private final ExternalIds externalIds;
  private final ExternalUrls externalUrls;
  private final List<String> genres;
  private final String href;
  private final String id;
  private final List<Image> images;
  private final String label;
  private final String name;
  private final int popularity;
  private final String releaseDate;
  private final ReleaseDatePrecision releaseDatePrecision;
  private final Paging<TrackSimplified> tracks;
  private final ObjectType type = ObjectType.ALBUM;
  private final String uri;

  private Album(final Album.Builder builder) {
    super(builder);

    this.albumType = builder.albumType;
    this.artists = builder.artists;
    this.availableMarkets = builder.availableMarkets;
    this.copyrights = builder.copyrights;
    this.externalIds = builder.externalIds;
    this.externalUrls = builder.externalUrls;
    this.genres = builder.genres;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.label = builder.label;
    this.name = builder.name;
    this.popularity = builder.popularity;
    this.releaseDate = builder.releaseDate;
    this.releaseDatePrecision = builder.releaseDatePrecision;
    this.tracks = builder.tracks;
//    this.type = builder.type;
    this.uri = builder.uri;
  }

  public static Builder builder() {
    return new Builder();
  }

  public AlbumType getAlbumType() {
    return albumType;
  }

  public List<ArtistSimplified> getArtists() {
    return artists;
  }

  public List<CountryCode> getAvailableMarkets() {
    return availableMarkets;
  }

  public List<Copyright> getCopyrights() {
    return copyrights;
  }

  public ExternalIds getExternalIds() {
    return externalIds;
  }

  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  public List<String> getGenres() {
    return genres;
  }

  public String getHref() {
    return href;
  }

  public String getId() {
    return id;
  }

  public List<Image> getImages() {
    return images;
  }

  public String getName() {
    return name;
  }

  public int getPopularity() {
    return popularity;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public ReleaseDatePrecision getReleaseDatePrecision() {
    return releaseDatePrecision;
  }

  public Paging<TrackSimplified> getTracks() {
    return tracks;
  }

  public ObjectType getType() {
    return type;
  }

  public String getUri() {
    return uri;
  }

  public static final class Builder extends AbstractModelObject.Builder<Album.Builder> {

    private AlbumType albumType;
    private List<ArtistSimplified> artists;
    private List<CountryCode> availableMarkets;
    private List<Copyright> copyrights;
    private ExternalIds externalIds;
    private ExternalUrls externalUrls;
    private List<String> genres;
    private String href;
    private String id;
    private List<Image> images;
    private String label;
    private String name;
    private int popularity;
    private String releaseDate;
    private ReleaseDatePrecision releaseDatePrecision;
    private Paging<TrackSimplified> tracks;
//    private ObjectType type = ObjectType.ALBUM;
    private String uri;

    public Builder setAlbumType(AlbumType albumType) {
      this.albumType = albumType;
      return this;
    }

    public Builder setArtists(List<ArtistSimplified> artists) {
      this.artists = artists;
      return this;
    }

    public Builder setAvailableMarkets(List<CountryCode> availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    public Builder setCopyrights(List<Copyright> copyrights) {
      this.copyrights = copyrights;
      return this;
    }

    public Builder setExternalIds(ExternalIds externalIds) {
      this.externalIds = externalIds;
      return this;
    }

    public Builder setExternalUrls(ExternalUrls externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    public Builder setGenres(List<String> genres) {
      this.genres = genres;
      return this;
    }

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setImages(List<Image> images) {
      this.images = images;
      return this;
    }

    public Builder setLabel(String label) {
      this.label = label;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setPopularity(int popularity) {
      this.popularity = popularity;
      return this;
    }

    public Builder setReleaseDate(String releaseDate) {
      this.releaseDate = releaseDate;
      return this;
    }

    public Builder setReleaseDatePrecision(ReleaseDatePrecision releaseDatePrecision) {
      this.releaseDatePrecision = releaseDatePrecision;
      return this;
    }

    public Builder setTracks(Paging<TrackSimplified> tracks) {
      this.tracks = tracks;
      return this;
    }

//    public Builder setType(ObjectType type) {
//      this.type = type;
//      return this;
//    }

    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public Album build() {
      return new Album(this);
    }

  }
}
