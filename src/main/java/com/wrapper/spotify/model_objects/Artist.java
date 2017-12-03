package com.wrapper.spotify.model_objects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Artist extends AbstractModelObject {
  private final ExternalUrls externalUrls;
  private final Followers followers;
  private final String[] genres;
  private final String href;
  private final String id;
  private final Image[] images;
  private final String name;
  private final int popularity;
  private final ModelObjectType type;
  private final String uri;

  private Artist(final Artist.Builder builder) {
    super(builder);

    this.externalUrls = builder.externalUrls;
    this.followers = builder.followers;
    this.genres = builder.genres;
    this.href = builder.href;
    this.id = builder.id;
    this.images = builder.images;
    this.name = builder.name;
    this.popularity = builder.popularity;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  public Followers getFollowers() {
    return followers;
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
    private ExternalUrls externalUrls;
    private Followers followers;
    private String[] genres;
    private String href;
    private String id;
    private Image[] images;
    private String name;
    private int popularity;
    private ModelObjectType type;
    private String uri;

    public Builder setExternalUrls(ExternalUrls externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    public Builder setFollowers(Followers followers) {
      this.followers = followers;
      return this;
    }

    public Builder setGenres(String[] genres) {
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

    public Builder setImages(Image[] images) {
      this.images = images;
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

    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public Artist build() {
      return new Artist(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Artist> {
    public Artist createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Artist.Builder()
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
              .setFollowers(new Followers.JsonUtil().createModelObject(jsonObject.getAsJsonObject("followers")))
              .setGenres(new Gson().fromJson(jsonObject.getAsJsonArray("genres"), String[].class))
              .setHref(jsonObject.get("href").getAsString())
              .setId(jsonObject.get("id").getAsString())
              .setImages(new Image.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("images")))
              .setName(jsonObject.get("name").getAsString())
              .setPopularity(jsonObject.get("popularity").getAsInt())
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setUri(jsonObject.get("uri").getAsString())
              .build();
    }
  }

}