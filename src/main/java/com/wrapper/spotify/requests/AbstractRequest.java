package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonElement;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.UrlUtil;
import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRequest implements Request {

  private Url url;
  private HttpManager httpManager;

  protected AbstractRequest(Builder<?> builder) {
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

  public <T> SettableFuture getAsync(T value) {
    final SettableFuture<T> settableFuture = SettableFuture.create();

    try {
      settableFuture.set(value);
    } catch (Exception e) {
      settableFuture.setException(e);
    }

    return settableFuture;
  }

  public String toString() {
    return this.toString(true);
  }

  public String toString(final boolean withQueryParameters) {
    return UrlUtil.urlToUri(url, withQueryParameters).toString();
  }

  public Url toUrl() {
    return url;
  }

  public static abstract class Builder<BuilderType extends Builder<?>> implements Request.Builder {

    private HttpManager httpManager;
    private Url.Scheme scheme = Api.DEFAULT_SCHEME;
    private String host = Api.DEFAULT_HOST;
    private int port = Api.DEFAULT_PORT;
    private String path = null;
    private List<Url.Parameter> parameters = new ArrayList<>();
    private List<Url.Parameter> headerParameters = new ArrayList<>();
    private List<Url.Parameter> bodyParameters = new ArrayList<>();
    private List<Url.Part> parts = new ArrayList<>();
    private JsonElement jsonBody;

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
      addParameter(Url.Parameter.newBuilder(), this.parameters, name, value);
      return (BuilderType) this;
    }

    public BuilderType setParameter(String name, int value) {
      addParameter(Url.Parameter.newBuilder(), this.parameters, name, String.valueOf(value));
      return (BuilderType) this;
    }

    public BuilderType setHeaderParameter(String name, String value) {
      addParameter(Url.Parameter.newBuilder(), this.headerParameters, name, value);
      return (BuilderType) this;
    }

    public BuilderType setBodyParameter(String name, String value) {
      addParameter(Url.Parameter.newBuilder(), this.bodyParameters, name, value);
      return (BuilderType) this;
    }

    public BuilderType setPart(Url.Part part) {
      assert (part != null);
      this.parts.add(part);
      return (BuilderType) this;
    }

    public BuilderType setBodyParameter(JsonElement jsonBody) {
      assert (jsonBody != null);
      this.jsonBody = jsonBody;
      return (BuilderType) this;
    }

    private void addParameter(Url.Parameter.Builder builder, List<Url.Parameter> parameters, String name, String value) {
      assert (name != null);
      assert (name.length() > 0);
      assert (value != null);

      parameters.add(builder.setName(name).setValue(value).build());
    }

  }

}
