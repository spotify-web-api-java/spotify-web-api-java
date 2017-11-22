package com.wrapper.spotify.methods;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.UrlUtil;
import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.*;
import net.sf.json.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRequest implements Request {

  private Url url;
  private HttpManager httpManager;

  public AbstractRequest(Builder<?> builder) {
    assert (builder.scheme != null);
    assert (builder.host != null);
    assert (builder.port > 0);
    assert (builder.path != null);
    assert (builder.parameters != null);
    assert (builder.bodyParameters != null);
    assert (builder.headerParameters != null);
    assert (builder.parts != null);

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

  public Url toUrl() {
    return url;
  }

  public String toString() {
    return this.toString(true);
  }

  public String toString(final boolean withQueryParameters) {
    return UrlUtil.urlToUri(url, withQueryParameters).toString();
  }

  public String getJson() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return httpManager.get(url);
  }

  public String postJson() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return httpManager.post(url);
  }

  public String putJson() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return httpManager.put(url);
  }

  public String deleteJson() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return httpManager.delete(url);
  }

  public static abstract class Builder<BuilderType extends Builder<?>> implements Request.Builder {

    protected HttpManager httpManager;
    protected Url.Scheme scheme = Api.DEFAULT_SCHEME;
    protected String host = Api.DEFAULT_HOST;
    protected int port = Api.DEFAULT_PORT;
    protected String path = null;
    protected List<Url.Parameter> parameters = new ArrayList<>();
    protected List<Url.Parameter> headerParameters = new ArrayList<>();
    protected List<Url.Parameter> bodyParameters = new ArrayList<>();
    protected List<Url.Part> parts = new ArrayList<>();
    protected JSON jsonBody;

    public BuilderType setHttpManager(HttpManager httpManager) {
      assert (httpManager != null);
      this.httpManager = httpManager;
      return (BuilderType) this;
    }

    public BuilderType setScheme(Url.Scheme scheme) {
      assert (scheme != null);
      this.scheme = scheme;
      return (BuilderType) this;
    }

    public BuilderType setHost(String host) {
      assert (host != null);
      this.host = host;
      return (BuilderType) this;
    }

    public BuilderType setPort(int port) {
      assert (port > -1);
      this.port = port;
      return (BuilderType) this;
    }

    public BuilderType setPath(String path) {
      assert (path != null);
      this.path = path;
      return (BuilderType) this;
    }

    public BuilderType setParameter(String name, String value) {
      addParameter(Url.Parameter.newBuilder(), parameters, name, value);
      return (BuilderType) this;
    }

    public BuilderType setHeaderParameter(String name, String value) {
      addParameter(Url.Parameter.newBuilder(), headerParameters, name, value);
      return (BuilderType) this;
    }

    public BuilderType setBodyParameter(String name, String value) {
      addParameter(Url.Parameter.newBuilder(), bodyParameters, name, value);
      return (BuilderType) this;
    }

    public BuilderType setPart(Url.Part part) {
      assert (part != null);
      parts.add(part);
      return (BuilderType) this;
    }

    public BuilderType setBodyParameter(JSON jsonBody) {
      assert (jsonBody != null);
      this.jsonBody = jsonBody;
      return (BuilderType) this;
    }

    private void addParameter(Url.Parameter.Builder builder, List<Url.Parameter> parameters, String  name, String value) {
      assert (name != null);
      assert (name.length() > 0);
      assert (value != null);

      parameters.add(builder.setName(name).setValue(value).build());
    }

  }

}
