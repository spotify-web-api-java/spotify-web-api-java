package com.wrapper.spotify.objects;

import net.sf.json.JSONObject;

public class Context extends AbstractModelObject {
  private final ObjectType type;
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

  public ObjectType getType() {
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
    private ObjectType type;
    private String href;
    private ExternalUrls externalUrls;
    private String uri;

    public Builder setType(ObjectType type) {
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
    public Context createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new Context.Builder()
              .setType(ObjectType.valueOf(jsonObject.getString("type")))
              .setHref(jsonObject.getString("href"))
              .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getJSONObject("external_urls")))
              .setUri(jsonObject.getString("uri"))
              .build();
    }
  }
}
