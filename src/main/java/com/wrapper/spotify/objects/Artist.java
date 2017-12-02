package com.wrapper.spotify.objects;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.List;

public class Artist extends AbstractModelObject {
  private final ExternalUrls externalUrls;
  private final Followers followers;
  private final List<String> genres;
  private final String href;
  private final String id;
  private final List<Image> images;
  private final String name;
  private final int popularity;
  private final ObjectType type;
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
    private ExternalUrls externalUrls;
    private Followers followers;
    private List<String> genres;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private int popularity;
    private ObjectType type;
    private String uri;

    public Builder setExternalUrls(ExternalUrls externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    public Builder setFollowers(Followers followers) {
      this.followers = followers;
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

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setPopularity(int popularity) {
      this.popularity = popularity;
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
    public Artist build() {
      return new Artist(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Artist> {
    public Artist createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new Artist.Builder()
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getJSONObject("external_urls")))
              .setFollowers(new Followers.JsonUtil().createModelObject(jsonObject.getJSONObject("followers")))
              .setGenres(JSONArray.toList(jsonObject.getJSONArray("genres"), new Object(), new JsonConfig()))
              .setHref(jsonObject.getString("href"))
              .setId(jsonObject.getString("id"))
              .setImages(new Image.JsonUtil().createModelObjectList(jsonObject.getJSONArray("images")))
              .setName(jsonObject.getString("name"))
              .setPopularity(jsonObject.getInt("popularity"))
              .setType(ObjectType.valueOf(jsonObject.getString("type")))
              .setUri(jsonObject.getString("uri"))
              .build();
    }
  }

}