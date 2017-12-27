package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.exceptions.*;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRequest implements Request {

  private HttpManager httpManager;
  private URI uri;
  private List<Header> headers;
  private List<NameValuePair> formParameters;
  private List<NameValuePair> bodyParameters;

  protected AbstractRequest(Builder<?> builder) {
    assert (builder.httpManager != null);
    assert (builder.scheme != null);
    assert (builder.host != null);
    assert (builder.port > 0);
    assert (builder.path != null);
    assert (builder.pathParameters != null);
    assert (builder.queryParameters != null);
    assert (builder.headers != null);
    assert (builder.formParameters != null);
    assert (builder.bodyParameters != null);

    this.httpManager = builder.httpManager;

    URIBuilder uriBuilder = new URIBuilder();
    uriBuilder
            .setScheme(builder.scheme)
            .setHost(builder.host)
            .setPort(builder.port)
            .setPath(builder.path);
    if (builder.queryParameters.size() > 0) {
      uriBuilder
              .setParameters(builder.queryParameters);
    }

    try {
      this.uri = uriBuilder.build();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    this.headers = builder.headers;
    this.formParameters = builder.formParameters;
    this.bodyParameters = builder.bodyParameters;
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
    Header[] headerArray = new Header[]{};
    headers.toArray(headerArray);

    return httpManager.get(uri, headerArray);
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
    Header[] headerArray = new Header[]{};
    headers.toArray(headerArray);

    return httpManager.post(uri, headerArray, formParameters);
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
    Header[] headerArray = new Header[]{};
    headers.toArray(headerArray);

    return httpManager.put(uri, headerArray, formParameters);
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
    Header[] headerArray = new Header[]{};
    headers.toArray(headerArray);

    return httpManager.delete(uri, headerArray);
  }

  public <T> SettableFuture<T> executeAsync(T value) {
    final SettableFuture<T> settableFuture = SettableFuture.create();

    try {
      settableFuture.set(value);
    } catch (Exception e) {
      settableFuture.setException(e);
    }

    return settableFuture;
  }

  public HttpManager getHttpManager() {
    return httpManager;
  }

  public URI getUri() {
    return uri;
  }

  public List<Header> getHeaders() {
    return headers;
  }

  public List<NameValuePair> getFormParameters() {
    return formParameters;
  }

  public List<NameValuePair> getBodyParameters() {
    return bodyParameters;
  }

  public static abstract class Builder<BuilderType extends Builder<?>> implements Request.Builder {

    private HttpManager httpManager = Api.DEFAULT_HTTP_MANAGER;
    private String scheme = Api.DEFAULT_SCHEME;
    private String host = Api.DEFAULT_HOST;
    private Integer port = Api.DEFAULT_PORT;
    private String path = null;
    private List<NameValuePair> pathParameters = new ArrayList<>();
    private List<NameValuePair> queryParameters = new ArrayList<>();
    private List<Header> headers = new ArrayList<>();
    private List<NameValuePair> formParameters = new ArrayList<>();
    private List<NameValuePair> bodyParameters = new ArrayList<>();

    public BuilderType accessToken(final String accessToken) {
      return setHeader("Authorization", "Bearer " + accessToken);
    }

    public BuilderType setHttpManager(final HttpManager httpManager) {
      assert (httpManager != null);
      this.httpManager = httpManager;
      return (BuilderType) this;
    }

    public BuilderType setScheme(final String scheme) {
      assert (scheme != null);
      this.scheme = scheme;
      return (BuilderType) this;
    }

    public BuilderType setHost(final String host) {
      assert (host != null);
      this.host = host;
      return (BuilderType) this;
    }

    public BuilderType setPort(final Integer port) {
      assert (port > -1);
      this.port = port;
      return (BuilderType) this;
    }

    public BuilderType setPath(final String path) {
      assert (path != null);
      String builtPath = path;

      for (NameValuePair nameValuePair : pathParameters) {
        builtPath = builtPath.replaceAll("\\{" + nameValuePair.getName() + "\\}", nameValuePair.getValue());
      }

      this.path = builtPath;
      return (BuilderType) this;
    }

    public BuilderType setPathParameter(final String name, final String value) {
      assert (name != null);
      assert (value != null);
      this.pathParameters.add(new BasicNameValuePair(name, value));
      return (BuilderType) this;
    }

    public <T> BuilderType setQueryParameter(final String name, final T value) {
      assert (name != null);
      assert (value != null);
      this.queryParameters.add(new BasicNameValuePair(name, String.valueOf(value)));
      return (BuilderType) this;
    }

    public <T> BuilderType setHeader(final String name, final T value) {
      this.headers.add(new BasicHeader(name, String.valueOf(value)));
      return (BuilderType) this;
    }

    public <T> BuilderType setFormParameter(final String name, final T value) {
      this.formParameters.add(new BasicNameValuePair(name, String.valueOf(value)));
      return (BuilderType) this;
    }

    public <T> BuilderType setBodyParameter(final String name, final T value) {
      this.bodyParameters.add(new BasicNameValuePair(name, String.valueOf(value)));
      return (BuilderType) this;
    }
  }
}
