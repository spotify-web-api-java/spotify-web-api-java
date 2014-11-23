package com.wrapper.spotify.methods;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.UrlUtil;
import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.WebApiException;

import net.sf.json.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRequest implements Request {

  private Url url;

  private HttpManager httpManager;

  public Url toUrl() {
    return url;
  }

  public String toString() {
    return UrlUtil.assemble(url);
  }

  public String toStringWithQueryParameters() {
    return UrlUtil.assembleWithQueryParameters(url);
  }

  public String getJson() throws IOException, WebApiException {
    return httpManager.get(url);
  }

  public String postJson() throws IOException, WebApiException {
    return httpManager.post(url);
  }

  public String putJson() throws IOException, WebApiException {
    return httpManager.put(url);
  }

  public String deleteJson() throws IOException, WebApiException {
    return httpManager.delete(url);
  }

  public AbstractRequest(Builder<?> builder) {
    assert (builder.path != null);
    assert (builder.host != null);
    assert (builder.port > 0);
    assert (builder.scheme != null);
    assert (builder.parameters != null);
    assert (builder.parts != null);
    assert (builder.bodyParameters != null);
    assert (builder.headerParameters != null);

    if (builder.httpManager == null) {
      httpManager = Api.DEFAULT_HTTP_MANAGER;
    } else {
      httpManager = builder.httpManager;
    }

    Url.Builder urlBuilder = Url.newBuilder()
             .setScheme(builder.scheme)
             .setHost(builder.host)
             .setPort(builder.port)
             .setPath(builder.path)
             .addAllParameters(builder.parameters)
             .addAllBodyParameters(builder.bodyParameters)
             .addAllHeaderParameters(builder.headerParameters)
             .addAllParts(builder.parts);

    if (builder.jsonBody != null) {
      urlBuilder.setJsonBody(builder.jsonBody.toString());
    }

    url = urlBuilder.build();
  }

  public static abstract class Builder<BuilderType extends Builder<?>> implements Request.Builder {

    protected Url.Scheme scheme = Api.DEFAULT_SCHEME;
    protected String host = Api.DEFAULT_HOST;
    protected int port = Api.DEFAULT_PORT;
    protected String path = null;
    protected HttpManager httpManager;
    protected JSON jsonBody;
    protected List<Url.Parameter> parameters = new ArrayList<Url.Parameter>();
    protected List<Url.Parameter> headerParameters = new ArrayList<Url.Parameter>();
    protected List<Url.Part> parts = new ArrayList<Url.Part>();
    protected List<Url.Parameter> bodyParameters = new ArrayList<Url.Parameter>();

    public BuilderType httpManager(HttpManager httpManager) {
      this.httpManager = httpManager;
      return (BuilderType) this;
    }

    public BuilderType host(String host) {
      this.host = host;
      return (BuilderType) this;
    }

    public BuilderType port(int port) {
      this.port = port;
      return (BuilderType) this;
    }

    public BuilderType scheme(Url.Scheme scheme) {
      this.scheme = scheme;
      return (BuilderType) this;
    }

    public BuilderType parameter(String name, String value) {
      assert (name != null);
      assert (name.length() > 0);
      assert (value != null);

      Url.Parameter parameter = Url.Parameter.newBuilder().setName(name).setValue(value).build();
      parameters.add(parameter);

      return (BuilderType) this;
    }

    public BuilderType body(String name, String value) {
      assert (name != null);
      assert (name.length() > 0);
      assert (value != null);

      Url.Parameter parameter = Url.Parameter.newBuilder().setName(name).setValue(value).build();
      bodyParameters.add(parameter);

      return (BuilderType) this;
    }

    public BuilderType body(JSON jsonBody) {
      assert (jsonBody != null);
      this.jsonBody = jsonBody;

      return (BuilderType) this;
    }

    public BuilderType header(String name, String value) {
      assert (name != null);
      assert (name.length() > 0);
      assert (value != null);

      Url.Parameter parameter= Url.Parameter.newBuilder().setName(name).setValue(value).build();
      headerParameters.add(parameter);

      return (BuilderType) this;
    }

    public BuilderType part(Url.Part part) {
      assert (part != null);
      parts.add(part);
      return (BuilderType) this;
    }

    public BuilderType path(String path) {
      this.path = path;
      return (BuilderType) this;
    }

  }

}
