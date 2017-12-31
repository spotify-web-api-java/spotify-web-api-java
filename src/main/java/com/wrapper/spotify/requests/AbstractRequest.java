package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.IHttpManager;
import com.wrapper.spotify.exceptions.*;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.*;

public abstract class AbstractRequest implements IRequest {

  private IHttpManager httpManager;
  private URI uri;
  private Map<String, String> headers;
  private Map<String, String> formParameters;
  private Map<String, String> bodyParameters;
  private String body;

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
    assert (builder.body != null);

    this.httpManager = builder.httpManager;

    URIBuilder uriBuilder = new URIBuilder();
    uriBuilder
            .setScheme(builder.scheme)
            .setHost(builder.host)
            .setPort(builder.port)
            .setPath(builder.path);
    if (builder.queryParameters.size() > 0) {
      for (Map.Entry<String, String> parameterMap : builder.queryParameters.entrySet()) {
        uriBuilder.addParameter(parameterMap.getKey(), parameterMap.getValue());
      }
    }

    try {
      this.uri = uriBuilder.build();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    this.headers = builder.headers;
    this.formParameters = builder.formParameters;
    this.bodyParameters = builder.bodyParameters;
    this.body = builder.body;
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

    return httpManager.get(uri, toHeaderArray(headers));
  }

  protected final Header[] toHeaderArray(Map<String, String> headerMap) {
    Header[] headers = new Header[headerMap.size()];
    int i = 0;
    for (Map.Entry<String, String> entry : headerMap.entrySet()) {
      headers[i++] = new BasicHeader(entry.getKey(), entry.getValue());
    }
    return headers;
  }


  protected final List<NameValuePair> toNameValuePairList(Map<String, String> map) {
    List<NameValuePair> nameValuePairList = new ArrayList<>();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      nameValuePairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
    }
    return nameValuePairList;
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
//    TODO this is a hack
    formParameters.putAll(bodyParameters);
    return httpManager.post(uri, toHeaderArray(headers), toNameValuePairList(formParameters));
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

    return httpManager.put(uri, toHeaderArray(headers), toNameValuePairList(formParameters));
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
    return httpManager.delete(uri, toHeaderArray(headers));
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

  public IHttpManager getHttpManager() {
    return httpManager;
  }

  public URI getUri() {
    return uri;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public Map<String, String> getFormParameters() {
    return formParameters;
  }

  public Map<String, String> getBodyParameters() {
    return bodyParameters;
  }

  public String getBody() {
    return body;
  }

  public static abstract class Builder<BuilderType extends Builder<?>> implements IRequest.Builder {

    public static final String CONTENT_TYPE_HEADER=  "Content-Type";

    private IHttpManager httpManager = Api.DEFAULT_HTTP_MANAGER;
    private String scheme = Api.DEFAULT_SCHEME;
    private String host = Api.DEFAULT_HOST;
    private Integer port = Api.DEFAULT_PORT;
    private String path = null;
    private Map<String, String> pathParameters = new LinkedHashMap<>();
    private Map<String, String> queryParameters = new LinkedHashMap<>();
    private Map<String, String> headers = new LinkedHashMap<>();
    private Map<String, String> formParameters = new LinkedHashMap<>();
    private Map<String, String> bodyParameters = new LinkedHashMap<>();
    private String body = "";

    protected Builder() {
      setHeader(CONTENT_TYPE_HEADER, "application/json");
    }

    public BuilderType setHttpManager(final IHttpManager httpManager) {
      assert (httpManager != null);
      this.httpManager = httpManager;
      return (BuilderType) this;
    }

    public BuilderType setScheme(final String scheme) {
      assert (scheme != null);
      assert (!scheme.equals(""));
      this.scheme = scheme;
      return (BuilderType) this;
    }

    public BuilderType setHost(final String host) {
      assert (host != null);
      assert (!scheme.equals(""));
      this.host = host;
      return (BuilderType) this;
    }

    public BuilderType setPort(final Integer port) {
      assert (port != null);
      assert (port >= 0);
      this.port = port;
      return (BuilderType) this;
    }

    public BuilderType setPath(final String path) {
      assert (path != null);
      assert (!path.equals(""));

      String builtPath = path;

      for (Map.Entry<String, String> nameValuePair : pathParameters.entrySet()) {
        builtPath = builtPath.replaceAll("\\{" + nameValuePair.getKey() + "\\}", nameValuePair.getValue());
      }

      this.path = builtPath;
      return (BuilderType) this;
    }

    public BuilderType setPathParameter(final String name, final String value) {
      assert (name != null && value != null);
      assert (!name.equals("") && !value.equals(""));

      String encodedValue = null;

      try {
        encodedValue = URLEncoder.encode(value, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }

      this.pathParameters.put(name, encodedValue);
      return (BuilderType) this;
    }

    public BuilderType setDefaults(final IHttpManager httpManager,
                                   final String scheme,
                                   final String host,
                                   final Integer port) {
      setHttpManager(httpManager);
      setScheme(scheme);
      setHost(host);
      setPort(port);

      return (BuilderType) this;
    }

    public <T> BuilderType setQueryParameter(final String name, final T value) {
      assert (name != null);
      assert (value != null);
      this.queryParameters.put(name, String.valueOf(value));
      return (BuilderType) this;
    }

    public <T> BuilderType setHeader(final String name, final T value) {
      this.headers.put(name, String.valueOf(value));
      return (BuilderType) this;
    }

    public <T> BuilderType setFormParameter(final String name, final T value) {
      this.formParameters.put(name, String.valueOf(value));
      return (BuilderType) this;
    }

    public <T> BuilderType setBodyParameter(final String name, final T value) {
      this.bodyParameters.put(name, String.valueOf(value));
      return (BuilderType) this;
    }

    public BuilderType setBody(final String value) {
      this.body = value;
      return (BuilderType) this;
    }
  }
}
