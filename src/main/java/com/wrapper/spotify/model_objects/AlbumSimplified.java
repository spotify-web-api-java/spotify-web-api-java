package com.wrapper.spotify.model_objects;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;

public class AlbumSimplified extends AbstractModelObject {
  private final AlbumType albumType;
  private final ArtistSimplified[] artists;
  private final CountryCode[] availableMarkets;
  private final ExternalUrls externalUrls;
  private final String href;
  private final String id;
  private final Image[] images;
  private final String name;
  private final ModelObjectType type;
  private final String uri;

  private AlbumSimplified(final AlbumSimplified.Builder builder) {
    super(builder);

    this.albumType = builder.albumType;
    this.artists = builder.artists;
    this.availableMarkets = builder.availableMarkets;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.name = builder.name;
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

  public ExternalUrls getExternalUrls() {
    return externalUrls;
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
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private Image[] images;
    private String name;
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

    public Builder setExternalUrls(ExternalUrls externalUrls) {
      this.externalUrls = externalUrls;
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

    public Builder setName(String name) {
      this.name = name;
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
    public AlbumSimplified build() {
      return new AlbumSimplified(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AlbumSimplified> {
    public AlbumSimplified createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AlbumSimplified.Builder()
              .setAlbumType((jsonObject.get("album_type") instanceof JsonNull) ? null : AlbumType.valueOf(jsonObject.get("album_type").getAsString().toUpperCase()))
              .setArtists(new ArtistSimplified.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("artists")))
              .setAvailableMarkets(new Gson().fromJson(jsonObject.get("available_markets"), CountryCode[].class))
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
              .setHref((jsonObject.get("href") instanceof JsonNull) ? null : jsonObject.get("href").getAsString())
              .setId((jsonObject.get("id") instanceof JsonNull) ? null : jsonObject.get("id").getAsString())
              .setImages(new Image.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("images")))
              .setName(jsonObject.get("name").getAsString())
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setUri((jsonObject.get("uri") instanceof JsonNull) ? null : jsonObject.get("uri").getAsString())
              .build();
    }
  }
}
