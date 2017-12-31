package com.wrapper.spotify.model_objects.specification;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.enums.AlbumType;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.enums.ReleaseDatePrecision;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.TrackSimplified;

public class Album extends AbstractModelObject {
  private final AlbumType albumType;
  private final ArtistSimplified[] artists;
  private final CountryCode[] availableMarkets;
  private final Copyright[] copyrights;
  private final ExternalIds externalIds;
  private final ExternalUrls externalUrls;
  private final String[] genres;
  private final String href;
  private final String id;
  private final Image[] images;
  private final String label;
  private final String name;
  private final int popularity;
  private final String releaseDate;
  private final ReleaseDatePrecision releaseDatePrecision;
  private final Paging<TrackSimplified> tracks;
  private final ModelObjectType type;
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
    this.type = builder.type;
    this.uri = builder.uri;
  }

  public AlbumType getAlbumType() {
    return albumType;
  }

  public ArtistSimplified[] getArtists() {
    return artists;
  }

  public CountryCode[] getAvailableMarkets() {
    return availableMarkets;
  }

  public Copyright[] getCopyrights() {
    return copyrights;
  }

  public ExternalIds getExternalIds() {
    return externalIds;
  }

  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  public String[] getGenres() {
    return genres;
  }

  public String getHref() {
    return href;
  }

  public String getId() {
    return id;
  }

  public Image[] getImages() {
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

  public ModelObjectType getType() {
    return type;
  }

  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {

    private AlbumType albumType;
    private ArtistSimplified[] artists;
    private CountryCode[] availableMarkets;
    private Copyright[] copyrights;
    private ExternalIds externalIds;
    private ExternalUrls externalUrls;
    private String[] genres;
    private String href;
    private String id;
    private Image[] images;
    private String label;
    private String name;
    private int popularity;
    private String releaseDate;
    private ReleaseDatePrecision releaseDatePrecision;
    private Paging<TrackSimplified> tracks;
    private ModelObjectType type;
    private String uri;

    public Builder setAlbumType(AlbumType albumType) {
      this.albumType = albumType;
      return this;
    }

    public Builder setArtists(ArtistSimplified... artists) {
      this.artists = artists;
      return this;
    }

    public Builder setAvailableMarkets(CountryCode... availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    public Builder setCopyrights(Copyright... copyrights) {
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

    public Builder setGenres(String... genres) {
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

    public Builder setImages(Image... images) {
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

    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public Album build() {
      return new Album(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Album> {
    public Album createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Album.Builder()
              .setAlbumType(AlbumType.valueOf(jsonObject.get("album_type").getAsString().toUpperCase()))
              .setArtists(new ArtistSimplified.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("artists")))
              .setAvailableMarkets(new Gson().fromJson(jsonObject.getAsJsonArray("available_markets"), CountryCode[].class))
              .setCopyrights(new Copyright.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("copyrights")))
              .setExternalIds(new ExternalIds.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_ids")))
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
              .setGenres(new Gson().fromJson(jsonObject.getAsJsonArray("genres"), String[].class))
              .setHref(jsonObject.get("href").getAsString())
              .setId(jsonObject.get("id").getAsString())
              .setImages(new Image.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("images")))
              .setLabel(jsonObject.get("label").getAsString())
              .setName(jsonObject.get("name").getAsString())
              .setPopularity(jsonObject.get("popularity").getAsInt())
              .setReleaseDate(jsonObject.get("release_date").getAsString())
              .setReleaseDatePrecision(ReleaseDatePrecision.valueOf(jsonObject.get("release_date_precision").getAsString().toUpperCase()))
              .setTracks(new TrackSimplified.JsonUtil().createModelObjectPaging(jsonObject.getAsJsonObject("tracks")))
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setUri(jsonObject.get("uri").getAsString())
              .build();
    }
  }
}
