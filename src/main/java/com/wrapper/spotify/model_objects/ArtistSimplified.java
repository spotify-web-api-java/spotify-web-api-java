package com.wrapper.spotify.model_objects;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class ArtistSimplified extends AbstractModelObject {
  private final ExternalUrls externalUrls;
  private final String href;
  private final String id;
  private final String name;
  private final ModelObjectType type;
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
    private String href;
    private String id;
    private String name;
    private ModelObjectType type;
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

    public Builder setType(ModelObjectType type) {
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
    public ArtistSimplified createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new ArtistSimplified.Builder()
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
              .setHref((jsonObject.get("href") instanceof JsonNull) ? null : jsonObject.get("href").getAsString())
              .setId((jsonObject.get("id") instanceof JsonNull) ? null : jsonObject.get("id").getAsString())
              .setName(jsonObject.get("name").getAsString())
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setUri((jsonObject.get("uri") instanceof JsonNull) ? null : jsonObject.get("uri").getAsString())
              .build();
    }
  }
}
