package com.wrapper.spotify.objects;

import com.neovisionaries.i18n.CountryCode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.List;

public class AlbumSimplified extends AbstractModelObject {
  private final AlbumType albumType;
  private final List<ArtistSimplified> artists;
  private final List<CountryCode> availableMarkets;
  private final ExternalUrls externalUrls;
  private final String href;
  private final String id;
  private final List<Image> images;
  private final String name;
  private final ObjectType type;
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

  public List<ArtistSimplified> getArtists() {
    return artists;
  }

  public List<CountryCode> getAvailableMarkets() {
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

  public List<Image> getImages() {
    return images;
  }

  public String getName() {
    return name;
  }

  public ObjectType getType() {
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
    private List<ArtistSimplified> artists;
    private List<CountryCode> availableMarkets;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private ObjectType type;
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

    public Builder setImages(List<Image> images) {
      this.images = images;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setType(ObjectType type) {
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
    public AlbumSimplified createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new AlbumSimplified.Builder()
              .setAlbumType(AlbumType.valueOf(jsonObject.getString("album_type")))
              .setArtists(new ArtistSimplified.JsonUtil().createModelObjectList(jsonObject.getJSONArray("artists")))
              .setAvailableMarkets(JSONArray.toList(jsonObject.getJSONArray("available_markets"), new Object(), new JsonConfig()))
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getJSONObject("external_urls")))
              .setHref(jsonObject.getString("href")).setId(jsonObject.getString("id"))
              .setId(jsonObject.getString("id"))
              .setImages(new Image.JsonUtil().createModelObjectList(jsonObject.getJSONArray("images")))
              .setName(jsonObject.getString("name"))
              .setType(ObjectType.valueOf(jsonObject.getString("type")))
              .setUri(jsonObject.getString("uri"))
              .build();
    }
  }
}
