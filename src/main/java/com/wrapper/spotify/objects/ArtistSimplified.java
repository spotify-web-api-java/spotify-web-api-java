package com.wrapper.spotify.objects;

import net.sf.json.JSONObject;

public class ArtistSimplified extends AbstractModelObject {
  private final ExternalUrls externalUrls;
  private final String href;
  private final String id;
  private final String name;
  private final ObjectType type;
  private final String uri;

  private ArtistSimplified(final ArtistSimplified.Builder builder) {
    super(builder);

    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.name = builder.name;
    this.type = builder.type;
    this.uri = builder.uri;
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
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private String name;
    private ObjectType type;
    private String uri;

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
    public ArtistSimplified build() {
      return new ArtistSimplified(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ArtistSimplified> {
    public ArtistSimplified createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new ArtistSimplified.Builder()
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getJSONObject("external_urls")))
              .setHref(jsonObject.getString("href"))
              .setId(jsonObject.getString("id"))
              .setName(jsonObject.getString("name"))
              .setType(ObjectType.valueOf(jsonObject.getString("type")))
              .setUri(jsonObject.getString("uri"))
              .build();
    }
  }
}
