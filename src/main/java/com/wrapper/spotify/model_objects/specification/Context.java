package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class Context extends AbstractModelObject {
  private final ModelObjectType type;
  private final String href;
  private final ExternalUrls externalUrls;
  private final String uri;

  private Context(final Context.Builder builder) {
    super(builder);

    this.type = builder.type;
    this.href = builder.href;
    this.externalUrls = builder.externalUrls;
    this.uri = builder.uri;
  }

  public ModelObjectType getType() {
    return type;
  }

  public String getHref() {
    return href;
  }

  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private ModelObjectType type;
    private String href;
    private ExternalUrls externalUrls;
    private String uri;

    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setExternalUrls(ExternalUrls externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public Context build() {
      return new Context(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Context> {
    public Context createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Context.Builder()
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setHref(jsonObject.get("href").getAsString())
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
              .setUri(jsonObject.get("uri").getAsString())
              .build();
    }
  }
}
