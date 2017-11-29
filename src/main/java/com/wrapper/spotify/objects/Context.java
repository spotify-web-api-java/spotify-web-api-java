package com.wrapper.spotify.objects;

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

  public static final class Builder extends AbstractModelObject.Builder<Context.Builder> {
    private ObjectType type;
    private String href;
    private ExternalUrls externalUrls;
    private String uri;

    public void setType(ObjectType type) {
      this.type = type;
    }

    public void setHref(String href) {
      this.href = href;
    }

    public void setExternalUrls(ExternalUrls externalUrls) {
      this.externalUrls = externalUrls;
    }

    public void setUri(String uri) {
      this.uri = uri;
    }

    @Override
    public Context build() {
      return new Context(this);
    }
  }
}
