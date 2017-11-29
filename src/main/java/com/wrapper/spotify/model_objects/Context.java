package com.wrapper.spotify.model_objects;

public class Context {

  private ModelObjectType type;
  private String href;
  private ExternalUrls externalUrls;
  private String uri;

  public ModelObjectType getType() {
    return type;
  }

  public void setType(ModelObjectType type) {
    this.type = type;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  public void setExternalUrls(ExternalUrls externalUrls) {
    this.externalUrls = externalUrls;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }
}
